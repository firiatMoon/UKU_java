package uku.java.JavaBackend.Hibernate.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleListenerStarter {

    private final OperationsConsoleListener consoleListener;
    private Thread thread;


    public ConsoleListenerStarter(OperationsConsoleListener consoleListener) {
        this.consoleListener = consoleListener;
    }

    @PostConstruct
    public void start() {
        this.thread = new Thread(consoleListener::receiveCommand);
        this.thread.start();
    }

    @PreDestroy
    public void stop() {
        this.thread.interrupt();
    }
}
