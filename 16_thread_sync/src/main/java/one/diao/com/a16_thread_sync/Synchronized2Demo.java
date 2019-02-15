package one.diao.com.a16_thread_sync;

/**
 * @author diaokaibin@gmail.com on 2019/2/15.
 */
public class Synchronized2Demo implements TestDemo {

    private int x;

    private synchronized void count() {
       x++;
    }


    @Override
    public void runTest() {

        new Thread() {
            @Override
            public void run() {
                super.run();

                for (int i = 0; i < 1_000_000_000; i++) {
                    count();
                }

                System.out.println(" final x form 1 : " + x);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();

                for (int i = 0; i < 1_000_000_000; i++) {
                    count();
                }
                System.out.println(" final x form 2 : " + x);
            }
        }.start();

    }
}
