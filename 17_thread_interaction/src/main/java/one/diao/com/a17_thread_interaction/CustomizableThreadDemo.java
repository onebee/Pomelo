package one.diao.com.a17_thread_interaction;

/**
 * @author diaokaibin@gmail.com on 2019/2/16.
 */
public class CustomizableThreadDemo implements TestDemo {

    /**
     * 无限循环的Thread
     */
    private CustomThread thread = new CustomThread();


    @Override
    public void runTest() {
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.looper.setTask(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello one bit!");
            }
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        thread.looper.quit();
    }

    class CustomThread extends Thread {
        Looper looper = new Looper();
    }

    class AnotherThread extends Thread {
        Looper looper = new Looper();
    }

    class Looper {


        Runnable task;
        boolean quit;

        synchronized void setTask(Runnable task) {
            this.task = task;
            loop();
        }

        synchronized void quit() {
            quit = true;
        }


        public void loop() {
//            super.run();
            while (!quit) {
                synchronized (this) {
                    if (task != null) {
                        task.run();
                        task = null;
                    }
                }
            }
        }


    }

}
