package uku.java.Multithreading.FirstTask;

import java.util.concurrent.TimeUnit;

public class UnsynchronizedCounter implements SiteVisitCounter{
    //Используйте обычный int для счетчика без механизмов синхронизации
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

    private void doCounter(){
        counter++;
    }

    @Override
    public int getVisitCount() {
        return counter;
    }
}
