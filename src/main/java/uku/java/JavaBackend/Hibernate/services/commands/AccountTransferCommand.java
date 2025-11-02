package uku.java.JavaBackend.Hibernate.services.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uku.java.JavaBackend.Hibernate.enums.TypeOfOperations;
import uku.java.JavaBackend.Hibernate.services.AccountService;
import uku.java.JavaBackend.Hibernate.services.OperationCommand;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class AccountTransferCommand implements OperationCommand {
    private final AccountService accountService;
    private final Scanner scanner;


    public AccountTransferCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter from Account ID: ");
        Long fromAccountId = scanner.nextLong();
        System.out.print("Enter to Account ID: ");
        Long toAccountId = scanner.nextLong();
        System.out.print("Enter amount to transfer: ");
        BigDecimal amount = scanner.nextBigDecimal();
        accountService.transferMoney(fromAccountId, toAccountId, amount);
    }

    @Override
    public TypeOfOperations getTypeOfOperation() {
        return TypeOfOperations.ACCOUNT_TRANSFER;
    }
}
