public class CounterInterferenceDemo {
    static class Counter {
        private int count = 0;

        public void increment() {
            count++;
        }

        public int getCount() {
            return count;
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

        // Run multiple times and notice that Actual Count often < Expected Count
    }
}
