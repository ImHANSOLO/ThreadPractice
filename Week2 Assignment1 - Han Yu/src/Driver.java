import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Driver {
    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Thread t8 = new Thread(() -> {
            try {
                if (lock1.tryLock(5, TimeUnit.SECONDS)) {
                    System.out.println("t8 has key 1.");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (lock2.tryLock(5, TimeUnit.SECONDS)) {
                        try {
                            System.out.println("t8 has key 2");
                        } finally {
                            lock2.unlock();
                        }
                    } else {
                        System.out.println("t8 could not get key2");
                    }
                } else {
                    System.out.println("t8 could not get key1");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (((ReentrantLock) lock1).isHeldByCurrentThread()) {
                    lock1.unlock();
                }
            }
        });

        Thread t9 = new Thread(() -> {
            try {
                if (lock2.tryLock(6, TimeUnit.SECONDS)) {
                    System.out.println("t9 has key 2.");
                    if (lock1.tryLock(6, TimeUnit.SECONDS)) {
                        try {
                            System.out.println("t9 has key 1");
                        } finally {
                            lock1.unlock();
                        }
                    } else {
                        System.out.println("t9 could not get key1");
                    }
                } else {
                    System.out.println("t9 could not get key2");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (((ReentrantLock) lock2).isHeldByCurrentThread()) {
                    lock2.unlock();
                }
            }
        });
        t8.start();
        t9.start();
    }
}
