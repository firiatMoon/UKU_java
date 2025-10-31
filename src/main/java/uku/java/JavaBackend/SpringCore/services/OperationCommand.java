package uku.java.JavaBackend.SpringCore.services;

import uku.java.JavaBackend.SpringCore.enums.TypeOfOperations;

public interface OperationCommand {
    void execute();
    TypeOfOperations getTypeOfOperation();
}
