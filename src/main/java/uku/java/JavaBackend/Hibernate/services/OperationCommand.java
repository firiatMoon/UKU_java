package uku.java.JavaBackend.Hibernate.services;

import uku.java.JavaBackend.Hibernate.enums.TypeOfOperations;

public interface OperationCommand {
    void execute();
    TypeOfOperations getTypeOfOperation();
}
