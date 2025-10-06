package uku.java.SpringCore.services;

import org.springframework.stereotype.Component;
import uku.java.SpringCore.enums.TypeOfOperations;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OperationsConsoleListener implements Runnable{
    private final ConcurrentHashMap<TypeOfOperations, OperationCommand> commandMap;
    private final Scanner scanner;

    public OperationsConsoleListener(List<OperationCommand> commands) {
        this.commandMap = new ConcurrentHashMap<>();
        commands.forEach(command -> commandMap.put(command.getTypeOfOperation(), command));
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        System.out.println("Please enter one of operation type:");
        EnumSet<TypeOfOperations> typeOfOperations = EnumSet.allOf(TypeOfOperations.class);
        for (TypeOfOperations typeOfOperation : typeOfOperations) {
            System.out.println(typeOfOperation);
        }
        String typeOfOperation = scanner.nextLine().trim().toUpperCase();

        while(true){
            switch (typeOfOperation) {
                case "USER_CREATE" -> {
                    handleCommand(TypeOfOperations.USER_CREATE);
                }
                case "SHOW_ALL_USERS" -> {
                    handleCommand(TypeOfOperations.SHOW_ALL_USERS);
                }
                case "ACCOUNT_CREATE" -> {
                    handleCommand(TypeOfOperations.ACCOUNT_CREATE);
                }
                case "ACCOUNT_CLOSE" -> {
                    handleCommand(TypeOfOperations.ACCOUNT_CLOSE);
                }
                case "ACCOUNT_DEPOSIT" -> {
                    handleCommand(TypeOfOperations.ACCOUNT_DEPOSIT);
                }
                case "ACCOUNT_TRANSFER" -> {
                    handleCommand(TypeOfOperations.ACCOUNT_TRANSFER);
                }
                case "ACCOUNT_WITHDRAW" -> {
                    handleCommand(TypeOfOperations.ACCOUNT_WITHDRAW);
                }
                default -> System.out.println("Incorrect command, try again.");
            }
        }
    }

    private void handleCommand(TypeOfOperations typeCommand) {
        OperationCommand command = commandMap.get(typeCommand);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Command not found.");
        }
    }
}
