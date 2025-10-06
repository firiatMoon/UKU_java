package uku.java.JavaCore.Multithreading.FirstTask;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter implements SiteVisitCounter{
    //Используйте AtomicInteger для счетчика
    private final AtomicInteger counter = new AtomicInteger(0);


    @Override
    public void incrementVisitCount() {
        try {
            doCounter();
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void doCounter() throws InterruptedException {
        counter.incrementAndGet();
    }

    @Override
    public int getVisitCount() {
        return counter.get();
    }
}
