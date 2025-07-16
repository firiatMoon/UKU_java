package uku.java.Multithreading.FirstTask;

import java.util.concurrent.TimeUnit;

public class SynchronizedBlockCounter implements SiteVisitCounter{
    // Используйте обычный Integer с синхронизацией доступа через synchronized блок
    private int counter = 0;

    @Override
    public void incrementVisitCount() {
        try {
            doCounter();
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private synchronized void doCounter() throws InterruptedException {
            counter++;
    }

    @Override
    public synchronized int getVisitCount() {
            return counter;
    }
}
