package Java;

import java.util.concurrent.Semaphore;

class SimpleSemaphore{
    private Semaphore semaphore;
    int fooInt;
    public SimpleSemaphore() {
        semaphore = new Semaphore(1);
    }

    void increment() throws InterruptedException{
        //semaphore.acquire();
        fooInt++;
        //semaphore.release();
    }

}

public class Main {
    public static void main(String[] args) {
        SimpleSemaphore simpleSemaphore = new SimpleSemaphore();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    simpleSemaphore.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(simpleSemaphore.fooInt);
    }
}