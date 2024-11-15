import java.lang.management.ThreadInfo;
import java.util.concurrent.BlockingQueue;

public class Checkout implements Runnable {

    private String name;
    private BlockingQueue<Customer> queue;
    private long initialTime;

    public Checkout(String name, BlockingQueue<Customer> queue){
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true){
                Customer customer = queue.take();
                this.initialTime = System.currentTimeMillis();
                processCustomer(customer);
                long totalTime = System.currentTimeMillis() - this.initialTime;
                System.out.println("Caja " + name + " terminó de procesar " + customer.getName() + " en " + totalTime + "ms");
            }
        } catch (InterruptedException e) {
            System.out.println("Caja " + name + " se interrumpió.");
            Thread.currentThread().interrupt();
        }
    }

    private void processCustomer(Customer customer) {

        System.out.println("Caja " + name + " esta procesando a " + customer.getName());

        for (String product : customer.getProducts()){
            long productStartTime = System.currentTimeMillis();
            System.out.println("Caja " + name + " está escaneando el producto " + product + " de " + customer.getName());

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            long productEndTime = System.currentTimeMillis();
            System.out.println("El tiempo de procesar " + product + " ha sido de " + (productEndTime - productStartTime) + "ms");
        }
    }
}

