import java.util.List;

public class Checkout implements Runnable {

    private String name;
    private List<Customer> customers;
    private long initialTime;

    // Constructs a checkout with a name and a shared list of customers
    public Checkout(String name, List<Customer> customers) {
        this.name = name;
        this.customers = customers;
    }

    // Main execution logic for the checkout thread
    @Override
    public void run() {
        while (true) {
            Customer customer = null;

            synchronized (customers) {
                if (!customers.isEmpty()) {
                    // Remove the first customer from the list
                    customer = customers.remove(0);
                }
            }

            if (customer != null) {
                this.initialTime = System.currentTimeMillis(); // Record the start time
                processCustomer(customer); // Process the customer
                long totalTime = System.currentTimeMillis() - this.initialTime; // Calculate total processing time
                System.out.println("Caja " + name + " terminó de procesar " + customer.getName() + " en " + totalTime + "ms");
            } else {
                // If no customers are left, end the thread
                System.out.println("Caja " + name + " no tiene más clientes que procesar.");
                break;
            }
        }
    }

    // Processes a single customer by scanning all their products
    private void processCustomer(Customer customer) {
        System.out.println("Caja " + name + " está procesando a " + customer.getName());

        for (String product : customer.getProducts()) {
            long productStartTime = System.currentTimeMillis(); // Start time for this product
            System.out.println("Caja " + name + " está escaneando el producto " + product + " de " + customer.getName());

            try {
                Thread.sleep(1000); // Simulate time taken to scan a product
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            long productEndTime = System.currentTimeMillis(); // End time for this product
            System.out.println("El tiempo de procesar " + product + " ha sido de " + (productEndTime - productStartTime) + "ms");
        }
    }
}

