import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NMModel {
    public static void main(String[] args) {
        int[] num = {10, 100, 500, 1000};

        for (int i = 0; i < num.length; i++) {
            long inicio = System.currentTimeMillis();

            ExecutorService n = Executors.newFixedThreadPool(num[i]);

            for (int j = 1; j <= num[i]; j++) {
                final int threadId = j;
                n.submit(() -> {
                    for (int k = 0; k < 5; k++) {
                        System.out.println("Virtual Thread " + threadId + " executando " + k);
                        try {
                            Thread.sleep(100); // trabalho simulado
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }
                });
            }

            n.shutdown();
            try {
                if (!n.awaitTermination(30, TimeUnit.MINUTES)) {
                    System.out.println("Timeout aguardando término do pool.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }

            long fim = System.currentTimeMillis();
            System.out.println("Tempo [N:M] | [" + num[i] + "]: " + (fim - inicio) + " ms\n");
        }
    }
}
