package uku.java.JavaBackend.Hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uku.java.JavaBackend.Hibernate.services.OperationsConsoleListener;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(
                "uku.java.JavaBackend.Hibernate");
        OperationsConsoleListener operationsConsoleListener = context.getBean(OperationsConsoleListener.class);
    }
}
