import java.util.List;

public class Customer {

    private String name;
    private List<String> products;

    // Constructor to initialize the customer with a name and a list of products
    public Customer(String name, List<String> products){
        this.name = name;
        this.products = products;
    }

    // Gets the list of products the customer is purchasing
    public List<String> getProducts() {
        return products;
    }

    // Gets the customer's name
    public String getName() {
        return name;
    }
}
