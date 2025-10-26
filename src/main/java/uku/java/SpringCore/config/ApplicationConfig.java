package uku.java.SpringCore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uku.java.SpringCore.services.OperationsConsoleListener;

import java.util.Scanner;

@Configuration
public class ApplicationConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

//    @Bean
//    public OperationsConsoleListener operationsConsoleListener(Scanner scanner) {
//        return new OperationsConsoleListener(scanner);
//    }
}
