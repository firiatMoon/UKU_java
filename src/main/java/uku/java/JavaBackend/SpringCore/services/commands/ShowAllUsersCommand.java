package uku.java.JavaBackend.SpringCore.services.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uku.java.JavaBackend.SpringCore.enums.TypeOfOperations;
import uku.java.JavaBackend.SpringCore.services.OperationCommand;
import uku.java.JavaBackend.SpringCore.services.UserService;

@Component
public class ShowAllUsersCommand implements OperationCommand {
    private final UserService userService;

    @Autowired
    public ShowAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        userService.showAllUsers();
    }

    @Override
    public TypeOfOperations getTypeOfOperation() {
        return TypeOfOperations.SHOW_ALL_USERS;
    }
}
