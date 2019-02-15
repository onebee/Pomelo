package one.diao.com.a16_thread_sync;

/**
 * @author diaokaibin@gmail.com on 2019/2/15.
 */
public class Synchronized1Demo implements TestDemo {

    private int x = 0;
    private int y = 0;

    private void count(int newvalue) {
        x = newvalue;
        y = newvalue;
        if (x != y) {
            System.out.println(" x = " + x + "  -- y = " + y);
        }
    }


    @Override
    public void runTest() {

        new Thread() {
            @Override
            public void run() {
                super.run();

                for (int i = 0; i < 1_000_000_000; i++) {
                    count(i);
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();

                for (int i = 0; i < 1_000_000_000; i++) {
                    count(i);
                }
            }
        }.start();

    }
}
