import java.util.List;

public class Customer {

    private String name;
    private List<String> products;

    public Customer(String name, List<String> products){
        this.name = name;
        this.products = products;
    }

    public List<String> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }
}
