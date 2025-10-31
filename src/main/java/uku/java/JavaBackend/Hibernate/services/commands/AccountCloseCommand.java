package uku.java.JavaBackend.Hibernate.services.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uku.java.JavaBackend.Hibernate.enums.TypeOfOperations;
import uku.java.JavaBackend.Hibernate.services.AccountService;
import uku.java.JavaBackend.Hibernate.services.OperationCommand;

import java.util.Scanner;

@Component
public class AccountCloseCommand implements OperationCommand {
    private final AccountService accountService;
    private final Scanner scanner;

    @Autowired
    public AccountCloseCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter Account ID: ");
        accountService.closeAccount(scanner.nextLong());
    }

    @Override
    public TypeOfOperations getTypeOfOperation() {
        return TypeOfOperations.ACCOUNT_CLOSE;
    }
}
