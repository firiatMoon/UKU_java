package uku.java.Multithreading.FirstTask;

import java.util.ArrayList;
import java.util.List;


public class MultithreadingSiteVisitor {
    private final SiteVisitCounter siteVisitCounter;
    private final List<Thread> threads ;
    private long startTimeThread;
    private long endTimeThread;

    public MultithreadingSiteVisitor(SiteVisitCounter siteVisitCounter) {
        this.siteVisitCounter = siteVisitCounter;
        threads = new ArrayList<>();
    }

    public void visitMultithread(int numOfThreads){
        startTimeThread = System.currentTimeMillis();
        for(int i = 0; i < numOfThreads; i++){
            Thread thread = new Thread(siteVisitCounter::incrementVisitCount);
            threads.add(thread);
            thread.start();
        }
    }

    public void waitUntilAllVisited(){
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        });
        endTimeThread = System.currentTimeMillis();
    }

    public double getTotalTimeOfHandling(){
        return (endTimeThread - startTimeThread) / 1000.0;
    }

}
