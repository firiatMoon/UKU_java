package uku.java.Multithreading.SecondTask;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CalculateSumTask implements Callable<Integer> {
    private final static int IMITATION_OF_DELAY_IN_EXECUTION = 2;
    private final List<Integer> list;
    private final String taskName;

    CalculateSumTask(List<Integer> list, String taskName){
        this.list = list;
        this.taskName = taskName;
    }

    @Override
    public Integer call() {
        System.out.printf("%s is running. Thread is %s%n", taskName, Thread.currentThread().getName());
        Integer sumIntegers = list.stream().mapToInt(Integer::intValue).sum();
        try {
            TimeUnit.MILLISECONDS.sleep(IMITATION_OF_DELAY_IN_EXECUTION);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return sumIntegers;
    }
}
