import java.sql.Array;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args){

        BlockingQueue<Customer> queue = new ArrayBlockingQueue<>(5);

        Checkout checkout1 = new Checkout("1", queue);
        Checkout checkout2 = new Checkout("2", queue);

        Thread checkoutThread1 =  new Thread(checkout1);
        Thread checkoutThread2 = new Thread(checkout2);

        checkoutThread1.start();
        checkoutThread2.start();

        Customer customer1 = new Customer("Pepe", Arrays.asList("Leche", "Pan", "Huevos", "Yogurt", "Platano"));
        Customer customer2 = new Customer("Juan", Arrays.asList("Café", "Oreos", "Manzanas", "Arroz", "Pechuga de pollo"));
        Customer customer3 = new Customer("Ana", Arrays.asList("Azúcar", "Cerveza", "Cereales", "Hamburguesas", "Cebollas"));
        Customer customer4 = new Customer("Marta", Arrays.asList("Aguacate", "Mantequilla", "Pimients", "Agua", "Zumo de manzana"));
        Customer customer5 = new Customer("Rafa", Arrays.asList("Patatas", "Tomates", "Avellanas", "Dorada", "Peras"));

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