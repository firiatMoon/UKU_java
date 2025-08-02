package uku.java.Multithreading.SecondTask;


public class Main {
    public static void main(String[] args) {
        DataProcessor dataProcessor = new DataProcessor();
        for (int i = 0; i < 100; i++) {
            dataProcessor.submitTask();
        }

        System.out.println(dataProcessor.getResult());
        dataProcessor.shutdown();
    }
}
