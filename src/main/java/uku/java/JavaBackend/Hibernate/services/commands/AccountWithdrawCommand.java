package uku.java.JavaBackend.Hibernate.services.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uku.java.JavaBackend.Hibernate.enums.TypeOfOperations;
import uku.java.JavaBackend.Hibernate.services.AccountService;
import uku.java.JavaBackend.Hibernate.services.OperationCommand;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class AccountWithdrawCommand implements OperationCommand {
    private final AccountService accountService;
    private final Scanner scanner;


    public AccountWithdrawCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter from Account ID: ");
        Long accountId = scanner.nextLong();
        System.out.print("Enter amount to transfer: ");
        BigDecimal amount = scanner.nextBigDecimal();
        accountService.withdrawMoney(accountId, amount);
    }

    @Override
    public TypeOfOperations getTypeOfOperation() {
        return TypeOfOperations.ACCOUNT_WITHDRAW;
    }
}
