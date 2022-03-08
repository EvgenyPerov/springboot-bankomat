package my.home.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

public class Solution {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                String treadName = Thread.currentThread().getName();
                System.out.println("This is tread: " + treadName);
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception e){ }
//                System.out.println("This is tread 2 " + treadName);
            }
        };

//        Thread thread = new Thread(task);
//        thread.start();

//        Callable task2 = () -> {
//            try {
//                  Thread.sleep(2000);
//                return 123;
//                } catch (Exception e){ }
//                throw new InterruptedException("Error");
//        };
//
//        ExecutorService executor = Executors.newFixedThreadPool(1); // исполнитель с 1 нитью и 1 задачей
//        Future<Integer> future= executor.submit(task2);
//        try {
//            System.out.println("Процессы закончились? "+future.isDone());
//            System.out.println("Значение метода: "+ future.get(3, TimeUnit.SECONDS));
//            System.out.println("Процессы закончились? "+future.isDone());
//        } catch (Exception e) {}

//        try {
//            executor.shutdown();
//            executor.awaitTermination(5, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            if (!executor.isTerminated()){
//                System.err.println("Процессы еще не закончились. Прирываем их вручную");
//                executor.shutdownNow();
//            } else {
//                System.out.println("Процессы закончились. Задача выполнена");
//            }
//        }

        ExecutorService executor2 = Executors.newWorkStealingPool(); // Исполнитель со списком задач

//        List<Callable<String>> callableList = Arrays.asList( //Callable список объектов. Возвращают строки.
//                callable("task11",30),
//                callable("task12",10),
//                callable("task13",4)
//                );

//        System.out.println(executor2.invokeAny(callableList));

//        executor2.invokeAll(callableList)
//                .stream()
//                .map(stringFuture -> {
//                    try {
//                        return stringFuture.get();
//                    } catch (Exception e) {
//                        throw new IllegalStateException(e);
//                    } })
//                .forEach(System.out::println);



//        Callable task11 = new Callable() {
//            @Override
//            public String call() throws Exception {
//                return "task11";
//            }
//        };
//        Callable task12 = new Callable() {
//            @Override
//            public String call() throws Exception {
//                return "task12";
//            }
//        };
//        Callable task13 = new Callable() {
//            @Override
//            public String call() throws Exception {
//                return "task13";
//            }
//        };
//
//        callableList.add(task11);
//        callableList.add(task12);
//        callableList.add(task13);

//        try {
//            List<Future<String>> futures = executor2.invokeAll(callableList);
//            futures.forEach(stringFuture -> {
//                try {
//                    System.out.println("Rez= "+stringFuture.get());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//    ScheduledExecutorService executor3 = Executors.newScheduledThreadPool(1); //планировщик-исполнитель
//    Runnable task3 = () -> {
//        try {
//            TimeUnit.MILLISECONDS.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Schedule: "+ System.nanoTime()); }; // задача
//        int initialDelay = 0;//задержка перед стартом 3 секунды
//        int period = 1; //повторять в цикле задачу через каждую секунду - после завершения последней задачи
//        ScheduledFuture future = executor3.scheduleWithFixedDelay(task3,initialDelay,period, TimeUnit.SECONDS); //запуск задачи через 3 секунды
//        System.out.printf("Remaining Delay: %sms \n", future.getDelay(TimeUnit.MILLISECONDS)); //проверка, сколько осталось до запуска
//        TimeUnit.MILLISECONDS.sleep(1000);
//        System.out.printf("Remaining Delay: %sms \n", future.getDelay(TimeUnit.MILLISECONDS));
//        TimeUnit.MILLISECONDS.sleep(1000);
//        System.out.printf("Remaining Delay: %sms \n", future.getDelay(TimeUnit.MILLISECONDS));
//        TimeUnit.MILLISECONDS.sleep(1000);
//        System.out.printf("Remaining Delay: %sms \n", future.getDelay(TimeUnit.MILLISECONDS));
//        executor3.shutdown();
//*****************************************************
//        AtomicInteger count = new AtomicInteger(0);
//
//        Callable<Integer> task4 = () -> {
//            return count.incrementAndGet();
//        };
//
//        ExecutorService executor4 = Executors.newFixedThreadPool(2);
//        for (int i=0; i<1000; i++) {
//            Future<Integer> future4 =  executor4.submit(task4);
//            System.out.println(future4.get());
//        }
//        executor4.shutdown();
//        System.out.println("rez= " +count);
//*******************************

        Solution solution = new Solution();
        ExecutorService executor5 = Executors.newFixedThreadPool(10);
//        executor5.submit(solution.task5);
        for (int i=0; i<10; i++) {
            executor5.submit(solution.task6);
        }
        executor5.shutdown();

//        long start= System.currentTimeMillis();
//        long end= System.currentTimeMillis();

//        System.out.println("total time sec: "+ (end-start));

    } // end Main

    int count = 0;

    StampedLock lock = new StampedLock();
    Map<String,String> map = new HashMap<>();

     Runnable task5 = () -> {
         long stamp = lock.readLock();
         try {
             if (count == 0) {
                 stamp = lock.tryConvertToWriteLock(stamp);
                 if (count == 0L) {
                     System.out.println("Could not convert to write lock");
                     stamp = lock.writeLock();
                 }
                 count = 23;
             }
             System.out.println(count);
 //            try {  TimeUnit.MILLISECONDS.sleep(1000);   } catch (InterruptedException e) {  }
         } finally {
             lock.unlock(stamp);
         }
    };

     Semaphore semaphore = new Semaphore(5);
    Runnable task6 = () -> {
        boolean permit = false;
//        System.out.println("key: " + map.get("key1"));
        try {
            permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
            if (permit){
                System.out.println("Semaphore acquired");
                TimeUnit.MILLISECONDS.sleep(1200);
            }  else {
            System.out.println("Could not acquire semaphore");
             }
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        } finally {
            if (permit) {
                semaphore.release();
            }
        }

    };


//    public static Callable callable(String result, long sleepSeconds) {
//        return () -> {
//            TimeUnit.SECONDS.sleep(sleepSeconds);
//            return result;
//        };
//    }



}
