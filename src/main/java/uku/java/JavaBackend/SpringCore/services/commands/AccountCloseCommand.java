package uku.java.JavaBackend.SpringCore.services.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uku.java.JavaBackend.SpringCore.enums.TypeOfOperations;
import uku.java.JavaBackend.SpringCore.services.OperationCommand;
import uku.java.JavaBackend.SpringCore.services.UserService;

import java.util.Scanner;

@Component
public class AccountCloseCommand implements OperationCommand {
    private final UserService userService;
    private final Scanner scanner;

    @Autowired
    public AccountCloseCommand(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter Account ID: ");
        userService.closeAccount(scanner.nextLong());
    }

    @Override
    public TypeOfOperations getTypeOfOperation() {
        return TypeOfOperations.ACCOUNT_CLOSE;
    }
}
