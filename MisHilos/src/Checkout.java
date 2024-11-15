import java.lang.management.ThreadInfo;
import java.util.concurrent.BlockingQueue;

public class Checkout implements Runnable {

    private String name;
    private BlockingQueue<Customer> queue;
    private long initialTime;

    // Constructor to initialize the checkout with a name and a shared customer queue
    public Checkout(String name, BlockingQueue<Customer> queue){
        this.name = name;
        this.queue = queue;
    }

    // Main execution logic for the checkout thread
    @Override
    public void run() {
        try {
            while (true){
                // Take the next customer from the queue (blocking if the queue is empty)
                Customer customer = queue.take();
                this.initialTime = System.currentTimeMillis(); // Record the start time for processing the customer
                processCustomer(customer); // Process the customer
                long totalTime = System.currentTimeMillis() - this.initialTime; // Calculate total processing time
                System.out.println("Caja " + name + " terminó de procesar " + customer.getName() + " en " + totalTime + "ms");
            }
        } catch (InterruptedException e) {
            System.out.println("Caja " + name + " se interrumpió.");
            Thread.currentThread().interrupt();
        }
    }

    //  Processes a single customer by scanning all their products
    private void processCustomer(Customer customer) {

        System.out.println("Caja " + name + " esta procesando a " + customer.getName());

        for (String product : customer.getProducts()){
            long productStartTime = System.currentTimeMillis(); // Start time for processing this product
            System.out.println("Caja " + name + " está escaneando el producto " + product + " de " + customer.getName());

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            long productEndTime = System.currentTimeMillis(); // End time for this product
            System.out.println("El tiempo de procesar " + product + " ha sido de " + (productEndTime - productStartTime) + "ms");
        }
    }
}

