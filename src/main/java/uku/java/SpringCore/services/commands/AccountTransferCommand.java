package uku.java.SpringCore.services.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uku.java.SpringCore.enums.TypeOfOperations;
import uku.java.SpringCore.services.OperationCommand;
import uku.java.SpringCore.services.UserService;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class AccountTransferCommand implements OperationCommand {
    private final UserService userService;

    @Autowired
    public AccountTransferCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter from Account ID: ");
        Long fromAccountId = scanner.nextLong();
        System.out.print("Enter to Account ID: ");
        Long toAccountId = scanner.nextLong();
        System.out.print("Enter amount to transfer: ");
        BigDecimal amount = scanner.nextBigDecimal();
        userService.transferMoney(fromAccountId, toAccountId, amount);
    }

    @Override
    public TypeOfOperations getTypeOfOperation() {
        return TypeOfOperations.ACCOUNT_TRANSFER;
    }
}
