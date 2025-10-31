package uku.java.JavaBackend.Hibernate.services.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uku.java.JavaBackend.Hibernate.enums.TypeOfOperations;
import uku.java.JavaBackend.Hibernate.services.AccountService;
import uku.java.JavaBackend.Hibernate.services.OperationCommand;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class AccountDepositCommand implements OperationCommand {
    private final AccountService accountService;
    private final Scanner scanner;

    @Autowired
    public AccountDepositCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
        ;
    }

    @Override
    public void execute() {
        System.out.print("Enter Account ID: ");
        Long accountId = scanner.nextLong();
        System.out.print("Enter Amount to deposit: ");
        BigDecimal amount = scanner.nextBigDecimal();
        accountService.depositAccount(accountId, amount);
    }

    @Override
    public TypeOfOperations getTypeOfOperation() {
        return TypeOfOperations.ACCOUNT_DEPOSIT;
    }
}
