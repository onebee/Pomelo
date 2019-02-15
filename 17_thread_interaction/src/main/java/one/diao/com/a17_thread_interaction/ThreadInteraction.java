package one.diao.com.a17_thread_interaction;

/**
 * @author diaokaibin@gmail.com on 2019/2/15.
 */
public class ThreadInteraction implements TestDemo {
    @Override
    public void runTest() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 1_000_000_000; i++) {
                    System.out.println("number : " + i);
                }
            }
        };

        thread.start();

    }
}
