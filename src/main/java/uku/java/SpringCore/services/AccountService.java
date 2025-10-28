package uku.java.SpringCore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uku.java.SpringCore.config.AccountProperties;
import uku.java.SpringCore.entities.Account;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AccountService {
    private final AtomicLong countOfAccountId = new AtomicLong(0L);
    private final AccountProperties accountProperties;

    @Autowired
    public AccountService(AccountProperties accountProperties) {
        this.accountProperties = accountProperties;
    }

    public Account createNewAccount(long userId) {
        BigDecimal balance = new BigDecimal(accountProperties.getDefaultAmount());
        return new Account(countOfAccountId.incrementAndGet(), userId, balance);
    }

    public Account createAnotherAccount (long userId) {
        return new Account(countOfAccountId.incrementAndGet(), userId, BigDecimal.ZERO);
    }
}
