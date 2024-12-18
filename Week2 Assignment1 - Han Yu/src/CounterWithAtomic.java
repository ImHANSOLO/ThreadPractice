import java.util.concurrent.atomic.AtomicInteger;

public class CounterWithAtomic {
    static class Counter {
        private final AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet();
        }

        public int getCount() {
            return count.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();
        final int THREADS = 3;
        final int INCREMENTS_PER_THREAD = 10000;
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    counter.increment();
                }
            });
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("Expected Count: " + (THREADS * INCREMENTS_PER_THREAD));
        System.out.println("Actual Count: " + counter.getCount());
        // This should consistently produce the expected result.
    }
}
