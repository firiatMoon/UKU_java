package uku.java.SpringCore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uku.java.SpringCore.config.AccountProperties;
import uku.java.SpringCore.entities.Account;
import uku.java.SpringCore.entities.User;
import uku.java.SpringCore.exceptions.UserException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();
    private final AtomicLong countOfUserId = new AtomicLong(0L);
    private final AccountProperties accountProperties;

    private final AccountService accountService;

    @Autowired
    public UserService(AccountProperties accountProperties, AccountService accountService) {
        this.accountProperties = accountProperties;
        this.accountService = accountService;
    }

    public void createUser(String login){
        synchronized (users) {
            if(users.stream().anyMatch(user -> user.getLogin().equals(login))){
                throw new UserException("User already exists");
            }

            long userId = countOfUserId.incrementAndGet();
            User newUser = new User(userId, login, new ArrayList<>());
            Account account = accountService.createNewAccount(userId);
            newUser.getAccountList().add(account);
            users.add(newUser);
            System.out.println(newUser);
        }
    }

    public void showAllUsers(){
        synchronized (users) {
            if(users.isEmpty()){
                throw new UserException("No users found");
            }
            users.forEach(System.out::println);
        }
    }

    public void createAccount(Long userId){
        synchronized (users) {
            Optional<User> user = users.stream().filter(userID -> userID.getId().equals(userId)).findFirst();
            user.ifPresent(value -> value.getAccountList().add(accountService.createAnotherAccount(userId)));
        }
    }

    public Optional<User> findUserByAccountId(Long accountId) {
       return users.stream()
                .filter(user -> user.getAccountList().stream()
                        .anyMatch(account -> account.getId().equals(accountId)))
                .findFirst();
    }

    public Optional<Account> findAccountById(Long accountId) {
        Optional<User> userOptional = findUserByAccountId(accountId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return user.getAccountList().stream()
                    .filter(account -> account.getId().equals(accountId))
                    .findFirst();
        }
        return Optional.empty();
    }

    public void closeAccount(Long accountId) {
        synchronized (users) {
            Optional<User> userOptional = findUserByAccountId(accountId);
            if(userOptional.isPresent()){
                User user = userOptional.get();
                if(user.getAccountList().size() > 1) {
                    Account firstAccount = user.getAccountList().get(0);
                    Account deletedAccount = user.getAccountList().stream()
                            .filter(account -> account.getId().equals(accountId))
                            .findFirst()
                            .get();

                    firstAccount.setMoneyAmount(firstAccount.getMoneyAmount().add(deletedAccount.getMoneyAmount()));
                    user.getAccountList().removeIf(account -> Objects.equals(account.getId(), accountId));
                } else {
                    System.out.println("You only have one account. It is not possible to delete an account.");
                }
            }
        }
    }

    public void depositAccount(Long accountId, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new UserException("Amount cannot be negative");
        }
        synchronized (users) {
            Optional<User> userOptional = findUserByAccountId(accountId);
            if(userOptional.isPresent()){
                User user = userOptional.get();
                Account account = user.getAccountList().stream()
                        .filter(x -> x.getId().equals(accountId))
                        .findFirst()
                        .get();

                account.setMoneyAmount(account.getMoneyAmount().add(amount));
            }
        }
    }

    public void transferMoney(Long fromAccountId, Long toAccountId, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new UserException("Amount cannot be negative");
        }

        Optional<Account> accountFrom = findAccountById(fromAccountId);
        Optional<Account> accountTo = findAccountById(toAccountId);
        if(accountFrom.isPresent() && accountTo.isPresent()){
            Account from = accountFrom.get();
            Account to = accountTo.get();
            BigDecimal commissionPercentage = new BigDecimal(accountProperties.getTransferCommission())
                    .divide(BigDecimal.valueOf(100))
                    .add(BigDecimal.ONE);

            BigDecimal amountToTransfer = new BigDecimal(amount.toString());

            if (from.getMoneyAmount().compareTo(amountToTransfer) < 0){
                throw new IllegalStateException("Insufficient funds in the sender's account.");
            }

            synchronized (this) {
                if (!Objects.equals(to.getUserId(), from.getUserId())){
                    amountToTransfer = amount.multiply(commissionPercentage);
                }
                from.setMoneyAmount(from.getMoneyAmount().subtract(amountToTransfer));
                to.setMoneyAmount(to.getMoneyAmount().add(amount));
            }
        }
    }

    public void withdrawMoney(Long accountId, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new UserException("Amount cannot be negative");
        }

        synchronized (this) {
            Optional<Account> accountOptional = findAccountById(accountId);
            if(accountOptional.isPresent()){
                Account account = accountOptional.get();
                if (account.getMoneyAmount().compareTo(amount) < 0){
                    throw new IllegalStateException("Insufficient funds in the sender's account.");
                }

                account.setMoneyAmount(account.getMoneyAmount().subtract(amount));
            }
        }
    }
}
