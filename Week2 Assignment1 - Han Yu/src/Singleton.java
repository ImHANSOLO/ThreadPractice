public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void doWork() {
        System.out.println("Singleton instance hash: " + this.hashCode());
    }
}

class SingletonTest {
    public static void main(String[] args) throws InterruptedException {
        final int THREADS = 5;
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                Singleton singleton = Singleton.getInstance();
                singleton.doWork();
            });
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        // All threads should print the same hash code, indicating the same instance.
    }
}
