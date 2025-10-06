package uku.java.JavaCore.Multithreading.FirstTask;

import java.util.concurrent.TimeUnit;

public class VolatileCounter implements SiteVisitCounter{
    // Используйте volatile int для счетчика без дополнительной синхронизации
    private volatile int counter = 0;

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
        counter++;
    }

    @Override
    public int getVisitCount() {
        return counter;
    }
}
