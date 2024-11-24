import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // Create a shared synchronized list for customers
        List<Customer> customers = Collections.synchronizedList(new ArrayList<>());

        // Add customers to the list
        customers.add(new Customer("Pepe", Arrays.asList("Leche", "Pan", "Huevos", "Yogurt", "Platano")));
        customers.add(new Customer("Juan", Arrays.asList("Café", "Oreos", "Manzanas", "Arroz", "Pechuga de pollo")));
        customers.add(new Customer("Ana", Arrays.asList("Azúcar", "Cerveza", "Cereales", "Hamburguesas", "Cebollas")));
        customers.add(new Customer("Marta", Arrays.asList("Aguacate", "Mantequilla", "Pimienta", "Agua", "Zumo de manzana")));
        customers.add(new Customer("Rafa", Arrays.asList("Patatas", "Tomates", "Avellanas", "Dorada", "Peras")));

        // Create two checkout counters (threads)
        Checkout checkout1 = new Checkout("1", customers);
        Checkout checkout2 = new Checkout("2", customers);

        // Create threads for the checkout counters
        Thread checkoutThread1 = new Thread(checkout1);
        Thread checkoutThread2 = new Thread(checkout2);

        // Start the threads
        checkoutThread1.start();
        checkoutThread2.start();
    }
}
