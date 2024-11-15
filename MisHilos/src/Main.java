import java.sql.Array;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Entry point for the supermarket simulation.
 * Sets up the checkouts and customers, and starts the processing threads.
 */
public class Main {

    public static void main(String[] args){

        // Create a shared queue for customers, with a capacity of 5
        BlockingQueue<Customer> queue = new ArrayBlockingQueue<>(5);

        Checkout checkout1 = new Checkout("1", queue);
        Checkout checkout2 = new Checkout("2", queue);

        // Create two checkout counters (threads)
        Thread checkoutThread1 =  new Thread(checkout1);
        Thread checkoutThread2 = new Thread(checkout2);

        // Start the checkout threads
        checkoutThread1.start();
        checkoutThread2.start();

        // Create customers with their respective products
        Customer customer1 = new Customer("Pepe", Arrays.asList("Leche", "Pan", "Huevos", "Yogurt", "Platano"));
        Customer customer2 = new Customer("Juan", Arrays.asList("Café", "Oreos", "Manzanas", "Arroz", "Pechuga de pollo"));
        Customer customer3 = new Customer("Ana", Arrays.asList("Azúcar", "Cerveza", "Cereales", "Hamburguesas", "Cebollas"));
        Customer customer4 = new Customer("Marta", Arrays.asList("Aguacate", "Mantequilla", "Pimients", "Agua", "Zumo de manzana"));
        Customer customer5 = new Customer("Rafa", Arrays.asList("Patatas", "Tomates", "Avellanas", "Dorada", "Peras"));

        // Add the customers to the shared queue
        try {
            queue.put(customer1);
            queue.put(customer2);
            queue.put(customer3);
            queue.put(customer4);
            queue.put(customer5);
        } catch (InterruptedException e) {
            System.out.println("Se ha interrumpido los hilos");
        }
    }
}