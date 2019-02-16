package one.diao.com.a17_thread_interaction;

/**
 * @author diaokaibin@gmail.com on 2019/2/16.
 */
public class WaitDemo implements TestDemo {


    private String shareString;


    private synchronized void initString() {

        shareString = "i'm oneBit";
        notifyAll();

    }


    private synchronized void printString() {
        while (shareString == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" String : " + shareString);
        }
    }


    @Override
    public void runTest() {

        Thread thread1 = new Thread() {

            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                printString();
            }
        };

        thread1.start();


        Thread thread2 = new Thread() {

            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                initString();
            }
        };

        thread2.start();

    }
}
