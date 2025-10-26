package uku.java.SpringCore.services.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uku.java.SpringCore.enums.TypeOfOperations;
import uku.java.SpringCore.services.OperationCommand;
import uku.java.SpringCore.services.UserService;

import java.util.Scanner;

@Component
public class CreateUserCommand implements OperationCommand {
    private final UserService userService;
    private final Scanner scanner;

    @Autowired
    public CreateUserCommand(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter login for new user:");
        userService.createUser(scanner.nextLine());
    }

    @Override
    public TypeOfOperations getTypeOfOperation() {
        return TypeOfOperations.USER_CREATE;
    }
}
