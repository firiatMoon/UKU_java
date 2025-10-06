package uku.java.SpringCore.services.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uku.java.SpringCore.enums.TypeOfOperations;
import uku.java.SpringCore.services.OperationCommand;
import uku.java.SpringCore.services.UserService;

import java.util.Scanner;

@Component
public class AccountCreateCommand implements OperationCommand {
    private final UserService userService;

    @Autowired
    public AccountCreateCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        userService.createAccount(scanner.nextLong());
    }

    @Override
    public TypeOfOperations getTypeOfOperation() {
        return TypeOfOperations.ACCOUNT_CREATE;
    }
}
