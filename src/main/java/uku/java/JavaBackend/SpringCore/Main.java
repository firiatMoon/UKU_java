package uku.java.JavaBackend.SpringCore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uku.java.JavaBackend.SpringCore.services.OperationsConsoleListener;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(
                "uku.java.JavaBackend.SpringCore");
        OperationsConsoleListener operationsConsoleListener = context.getBean(OperationsConsoleListener.class);
    }
}
