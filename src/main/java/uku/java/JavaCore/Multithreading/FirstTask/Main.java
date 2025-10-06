package uku.java.JavaCore.Multithreading.FirstTask;

public class Main {
    private static final int AMOUNT_OF_THREADS_TO_RUN_10 = 10;
    private static final int AMOUNT_OF_THREADS_TO_RUN_100 = 100;
    private static final String MESSAGE_TEMPLATE = "Class: %s. Thread execution time (%d threads): %.2f. Count: %d%n";

    public static void main(String[] args) {
        creatSiteVisitCounter(new UnsynchronizedCounter(), AMOUNT_OF_THREADS_TO_RUN_10, AMOUNT_OF_THREADS_TO_RUN_100);
        creatSiteVisitCounter(new VolatileCounter(), AMOUNT_OF_THREADS_TO_RUN_10, AMOUNT_OF_THREADS_TO_RUN_100);
        creatSiteVisitCounter(new SynchronizedBlockCounter(), AMOUNT_OF_THREADS_TO_RUN_10, AMOUNT_OF_THREADS_TO_RUN_100);
        creatSiteVisitCounter(new AtomicIntegerCounter(), AMOUNT_OF_THREADS_TO_RUN_10, AMOUNT_OF_THREADS_TO_RUN_100);
        creatSiteVisitCounter(new ReentrantLockCounter(), AMOUNT_OF_THREADS_TO_RUN_10, AMOUNT_OF_THREADS_TO_RUN_100);
    }

    public static void startThreads(SiteVisitCounter siteVisitCounter, int numOfThreads){
        MultithreadingSiteVisitor multithreadingSiteVisitor = new MultithreadingSiteVisitor(siteVisitCounter);
        multithreadingSiteVisitor.visitMultithread(numOfThreads);
        multithreadingSiteVisitor.waitUntilAllVisited();
        System.out.printf(MESSAGE_TEMPLATE, siteVisitCounter.getClass().getSimpleName(), numOfThreads,
                multithreadingSiteVisitor.getTotalTimeOfHandling(), siteVisitCounter.getVisitCount());
    }

    public static void creatSiteVisitCounter(SiteVisitCounter siteVisitCounter, int firstNumberOfThreads,
                                             int secondNumberOfThreads){
        startThreads(siteVisitCounter, firstNumberOfThreads);
        startThreads(siteVisitCounter, secondNumberOfThreads);
    }

}
