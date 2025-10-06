package uku.java.JavaCore.Multithreading.SecondTask;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DataProcessor {
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final AtomicInteger activeTaskCount  = new AtomicInteger(0);
    AtomicInteger countTaskNumber = new AtomicInteger(0);
    private final static int NUMBER_OF_DIGITS_IN_LIST = 10;

    private final Map<String, Integer> result = new HashMap<>();

    public void submitTask() {
        activeTaskCount.incrementAndGet();
        Future<Integer> integerFuture = null;
        List<Integer> list = generateRandomList();
        String taskName = getTaskName();
        try{
            integerFuture = executorService.submit(new CalculateSumTask(list, taskName));
            Integer result = integerFuture.get();
            putIntegerInMap(taskName, result);
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        } finally {
            activeTaskCount.decrementAndGet();
        }
    }

    public int getActiveTaskCount() {
        return activeTaskCount.get();
    }

    //метод заполняющий список рандомными цифрами
    private List<Integer> generateRandomList(){
        return new Random()
                .ints(NUMBER_OF_DIGITS_IN_LIST, 0, 100)
                .boxed()
                .toList();
    }

    //метод присваивает имя таске "task " + [счётчик задач].
    private String getTaskName(){
        return String.format("task %d", countTaskNumber.incrementAndGet());
    }

    //добавляем данные после суммирования значений списка в Map "task 1: 546"
    private void putIntegerInMap(String key, Integer value) {
        synchronized (result) {
            result.put(key, value);
        }
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public synchronized Optional<Integer> getResult(String taskName) {
        Optional<Integer> taskResult = Optional.ofNullable(result.get(taskName));
        return Optional.of(result.get(taskName));
    }

    public Map<String, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
