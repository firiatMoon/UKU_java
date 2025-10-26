package uku.java.SpringCore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uku.java.SpringCore.config.AccountProperties;
import uku.java.SpringCore.enums.TypeOfOperations;
import uku.java.SpringCore.services.OperationCommand;
import uku.java.SpringCore.services.OperationsConsoleListener;
import uku.java.SpringCore.services.UserService;

import java.util.EnumSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("uku.java.SpringCore");
        OperationsConsoleListener operationsConsoleListener = context.getBean(OperationsConsoleListener.class);
//        operationsConsoleListener.receiveCommand();
//        Thread tread = new Thread(operationsConsoleListener);
//        tread.start();
    }
}
