package one.diao.com.a16_thread_sync;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author diaokaibin@gmail.com on 2019/2/14.
 */
public class Main {

    public static void main(String[] agrs) {
//        thread();
//        runnable();
//        threadFactory();
//        executor();

//        runSynchronizedDemo1();

        runSynchronizedDemo2();
    }

    private static void runSynchronizedDemo2() {
        new Synchronized2Demo().runTest();
    }

    private static void runSynchronizedDemo1() {
        new Synchronized1Demo().runTest();
    }

    private static void executor() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(" thread with runnable start");
            }
        };

        Executor executor = Executors.newCachedThreadPool();
        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);

    }


    private static void threadFactory() {

        ThreadFactory threadFactory = new ThreadFactory() {

            int count = 0;

            @Override
            public Thread newThread(@NonNull Runnable r) {
                return new Thread(r, "Thread - " + count++);
            }

        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");

            }
        };

        Thread thread = threadFactory.newThread(runnable);

        thread.start();

        Thread thread1 = threadFactory.newThread(runnable);

        thread1.start();

    }


    private static void runnable() {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("thread runnable run");

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }

    private static void thread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread start");
            }
        });

        thread.start();
    }
}
