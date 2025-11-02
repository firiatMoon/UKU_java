package uku.java.JavaBackend.Hibernate.services;

import org.springframework.stereotype.Component;
import uku.java.JavaBackend.Hibernate.enums.TypeOfOperations;
import uku.java.JavaBackend.Hibernate.exceptions.UserException;

import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OperationsConsoleListener {
    private final ConcurrentHashMap<TypeOfOperations, OperationCommand> commandMap;
    private final Scanner scanner;

    public OperationsConsoleListener(List<OperationCommand> commands) {
        this.commandMap = new ConcurrentHashMap<>();
        commands.forEach(command -> commandMap.put(command.getTypeOfOperation(), command));
        this.scanner = new Scanner(System.in);
    }

    private void showCommands(){
        System.out.printf("%nPlease enter one of operation type:%n");
        EnumSet<TypeOfOperations> typeOfOperations = EnumSet.allOf(TypeOfOperations.class);
        for (TypeOfOperations typeOfOperation : typeOfOperations) {
            System.out.println(typeOfOperation);
        }
    }

    public void receiveCommand(){
        while(!Thread.currentThread().isInterrupted()) {
            showCommands();
            try{
                TypeOfOperations typeOfOperation = TypeOfOperations.valueOf(scanner.nextLine());
                handleCommand(typeOfOperation);
            } catch (IllegalArgumentException e) {
                System.out.println("Incorrect command, try again.");
            }

        }
    }

    private void handleCommand(TypeOfOperations typeCommand) {
        OperationCommand command = commandMap.get(typeCommand);
        try {
            command.execute();
        } catch (UserException ex) {
            System.out.printf("Error executing command %s: error %s",typeCommand, ex.getMessage());
        }
    }
}
