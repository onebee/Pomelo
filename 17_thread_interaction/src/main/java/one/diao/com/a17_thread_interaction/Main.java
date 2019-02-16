package one.diao.com.a17_thread_interaction;

/**
 * @author diaokaibin@gmail.com on 2019/2/15.
 */
public class Main {

    public static void main(String[] args) {


//        threadInteraction();

//        runWaitDemo();

        customizable();
    }

    private static void customizable() {
        new CustomizableThreadDemo().runTest();
    }

    private static void runWaitDemo() {

        new WaitDemo().runTest();
    }

    private static void threadInteraction() {
        new ThreadInteraction().runTest();
    }
}
