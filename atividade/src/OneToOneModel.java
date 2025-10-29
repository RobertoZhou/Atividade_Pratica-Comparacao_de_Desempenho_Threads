public class OneToOneModel {
    public static void main(String[] args) {
        int[] num = {10, 100, 500, 1000};

        for (int i = 0; i < num.length; i++) {
            long inicio = System.currentTimeMillis();

            Thread[] threads = new Thread[num[i]];

            for (int y = 0; y < num[i]; y++) {
                final int threadId = y + 1;
                threads[y] = new Thread(() -> {
                    for (int j = 0; j < 5; j++) {
                        System.out.println("Thread " + threadId + " executando passo " + j);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }
                });
                threads[y].start();
            }
            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }

            long fim = System.currentTimeMillis();
            System.out.println("Tempo [1:1] | [" + num[i] + "]: " + (fim - inicio) + " ms\n");
        }
    }
}
