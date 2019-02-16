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
                // run 方法结束 就代表线程结束
                super.run();
                for (int i = 0; i < 1_000_000_000; i++) {
//                    if (isInterrupted()) {
//                        return;
//                    }

                    if (Thread.interrupted()) {
                        return;
                    }

                    try {
                         Thread.sleep(2000);
                    } catch (InterruptedException e) {
//                        此处抛异常之后会把interrupt 的状态重置
                        e.printStackTrace ();

                        // 收尾操作
//                        return;
                    }
                    System.out.println("number : " + i);
                }
            }
        };

        thread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        thread.stop();
        thread.interrupt();

    }
}
