package uku.java.JavaBackend.Hibernate.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uku.java.JavaBackend.Hibernate.config.AccountProperties;
import uku.java.JavaBackend.Hibernate.entities.Account;
import uku.java.JavaBackend.Hibernate.entities.User;
import uku.java.JavaBackend.Hibernate.exceptions.UserException;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class AccountService {
    private final AccountProperties accountProperties;
    private final TransactionHelper transactionHelper;
    private final SessionFactory sessionFactory;

    @Autowired
    public AccountService(AccountProperties accountProperties, TransactionHelper transactionHelper, SessionFactory sessionFactory) {
        this.accountProperties = accountProperties;
        this.transactionHelper = transactionHelper;
        this.sessionFactory = sessionFactory;
    }

    public Account getById(Long id) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(Account.class, id);
        }
    }

    public Account createNewAccount(User user) {
        BigDecimal balance = new BigDecimal(accountProperties.getDefaultAmount());
        return new Account(user, balance);
    }

    public Account createAnotherAccount(User user) {
        return new Account(user, BigDecimal.ZERO);
    }

    public void closeAccount(Long accountId) {
        transactionHelper.executeIdTransaction(session -> {
            Account account = getById(accountId);

            if(Objects.isNull(account)) {
                throw new UserException("Account not found");
            }

            session.remove(account);
        });
    }

    public void depositAccount(Long accountId, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new UserException("Amount cannot be negative");
        }

        transactionHelper.executeIdTransaction(session -> {
            Account accountById = getById(accountId);

            if (Objects.isNull(accountById)) {
                throw new IllegalArgumentException("An account with the specified ID was not found.");
            }

            BigDecimal currentBalance = accountById.getMoneyAmount();
            BigDecimal updatedBalance = currentBalance.add(amount);
            accountById.setMoneyAmount(updatedBalance);
            session.merge(accountById);
        });
    }

    public void transferMoney(Long fromAccountId, Long toAccountId, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new UserException("Amount cannot be negative");
        }

        transactionHelper.executeIdTransaction(session -> {
            Account fromAccount = getById(fromAccountId);
            Account toAccount = getById(toAccountId);

            if (Objects.isNull(fromAccount) || Objects.isNull(toAccount)) {
                throw new IllegalArgumentException("An account with the specified ID was not found.");
            }

            BigDecimal commissionPercentage = new BigDecimal(accountProperties.getTransferCommission())
                    .divide(BigDecimal.valueOf(100))
                    .add(BigDecimal.ONE);

            BigDecimal amountToTransfer = new BigDecimal(amount.toString());

            if (fromAccount.getMoneyAmount().compareTo(amount) < 0){
                throw new IllegalStateException("Insufficient funds in the sender's account.");
            }

            if (!Objects.equals(toAccount.getUser().getId(), fromAccount.getUser().getId())) {
                amountToTransfer = amount.multiply(commissionPercentage);
            }
            fromAccount.setMoneyAmount(fromAccount.getMoneyAmount().subtract(amountToTransfer));
            toAccount.setMoneyAmount(toAccount.getMoneyAmount().add(amount));
            session.merge(fromAccount);
            session.merge(toAccount);
        });
    }

    public void withdrawMoney(Long accountId, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new UserException("Amount cannot be negative");
        }

        transactionHelper.executeIdTransaction(session -> {
            Account account = getById(accountId);
            if (Objects.isNull(account)) {
                throw new IllegalArgumentException("An account with the specified ID was not found.");
            }

            BigDecimal currentBalance = account.getMoneyAmount();
            BigDecimal updatedBalance = currentBalance.subtract(amount);
            account.setMoneyAmount(updatedBalance);
            session.merge(account);
        });
    }
}
