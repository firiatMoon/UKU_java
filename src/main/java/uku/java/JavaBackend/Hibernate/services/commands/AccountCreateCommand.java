package uku.java.JavaBackend.Hibernate.services.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uku.java.JavaBackend.Hibernate.enums.TypeOfOperations;
import uku.java.JavaBackend.Hibernate.services.OperationCommand;
import uku.java.JavaBackend.Hibernate.services.UserService;

import java.util.Scanner;

@Component
public class AccountCreateCommand implements OperationCommand {
    private final UserService userService;
    private final Scanner scanner;


    public AccountCreateCommand(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter User ID: ");
        userService.createAccount(scanner.nextLong());
    }

    @Override
    public TypeOfOperations getTypeOfOperation() {
        return TypeOfOperations.ACCOUNT_CREATE;
    }
}
