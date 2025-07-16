package uku.java.Multithreading.FirstTask;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements SiteVisitCounter {
    // Используйте обычный int с синхронизацией доступа через ReentrantLock
    private int counter = 0;
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void incrementVisitCount() {
        this.lock.lock();
        try {
            counter++;
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public int getVisitCount() {
        this.lock.lock();
        try {
            return counter;
        } finally {
            this.lock.unlock();
        }

    }
}
