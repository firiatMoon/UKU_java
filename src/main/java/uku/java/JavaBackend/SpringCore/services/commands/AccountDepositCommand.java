package uku.java.JavaBackend.SpringCore.services.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uku.java.JavaBackend.SpringCore.enums.TypeOfOperations;
import uku.java.JavaBackend.SpringCore.services.OperationCommand;
import uku.java.JavaBackend.SpringCore.services.UserService;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class AccountDepositCommand implements OperationCommand {
    private final UserService userService;
    private final Scanner scanner;

    @Autowired
    public AccountDepositCommand(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
        ;
    }

    @Override
    public void execute() {
        System.out.print("Enter Account ID: ");
        Long accountId = scanner.nextLong();
        System.out.print("Enter Amount to deposit: ");
        BigDecimal amount = scanner.nextBigDecimal();
        userService.depositAccount(accountId, amount);
    }

    @Override
    public TypeOfOperations getTypeOfOperation() {
        return TypeOfOperations.ACCOUNT_DEPOSIT;
    }
}
