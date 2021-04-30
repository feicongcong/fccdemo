package com.example.juc;

import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallTask callTask = new CallTask();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(
//                callTask
                () -> {
                    Thread.sleep(1000*30);
                    int i =1/0;
                    return 1; }
                );
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

//        ExecutorService executors = Executors.newFixedThreadPool(1);
//        Future<?> future = executors.submit(futureTask);
//        Object o = future.get();
        new Thread(()->{
            try {
                System.out.println(futureTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                System.out.println(futureTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Future<Integer> future = executor.submit(() -> {
//            return 1;
//        });
//        System.out.println(future.get());
//        System.out.println(executor.submit(callTask).get());
    }

}
class CallTask implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return 1;
    }
}