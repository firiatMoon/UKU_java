package uku.java.SpringCore.services;

import uku.java.SpringCore.enums.TypeOfOperations;

public interface OperationCommand {
    void execute();
    TypeOfOperations getTypeOfOperation();
}
