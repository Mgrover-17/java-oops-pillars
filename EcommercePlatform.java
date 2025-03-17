import java.util.ArrayList;

abstract class Product {
    private int productId;
    private String name;
    private double price;

    // Constructor
    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    // Encapsulation: Getters & Setters
    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // Abstract Method
    public abstract double calculateDiscount();

    // Concrete Method to Display Product Details
    public void displayDetails() {
        System.out.println("Product ID: " + productId);
        System.out.println("Product Name: " + name);
        System.out.println("Base Price: " + price);
    }
}

// Interface for Taxable Products
interface Taxable {
    double calculateTax();
    String getTaxDetails();
}

// Electronics Class (Taxable)
class Electronics extends Product implements Taxable {
    public Electronics(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.10; // 10% discount on electronics
    }

    @Override
    public double calculateTax() {
        return getPrice() * 0.18; // 18% tax
    }

    @Override
    public String getTaxDetails() {
        return "Tax Rate: 18% (Electronics)";
    }
}

// Clothing Class (Taxable)
class Clothing extends Product implements Taxable {
    public Clothing(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.15; // 15% discount on clothing
    }

    @Override
    public double calculateTax() {
        return getPrice() * 0.12; // 12% tax
    }

    @Override
    public String getTaxDetails() {
        return "Tax Rate: 12% (Clothing)";
    }
}

// Groceries Class (Non-Taxable)
class Groceries extends Product {
    public Groceries(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.05; // 5% discount on groceries
    }
}


public class EcommercePlatform {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();

        // Creating Different Products
        Product laptop = new Electronics(101, "Laptop", 1000);
        Product tShirt = new Clothing(102, "T-Shirt", 50);
        Product apple = new Groceries(103, "Apple", 10);

        // Adding to Product List
        products.add(laptop);
        products.add(tShirt);
        products.add(apple);

        // Processing and Displaying Final Price
        for (Product product : products) {
            product.displayDetails();
            double discount = product.calculateDiscount();
            double tax = 0;

            // Check if product is taxable
            if (product instanceof Taxable) {
                tax = ((Taxable) product).calculateTax();
                System.out.println(((Taxable) product).getTaxDetails());
            }

            double finalPrice = product.getPrice() + tax - discount;
            System.out.println("Discount: " + discount);
            System.out.println("Tax: " + tax);
            System.out.println("Final Price: " + finalPrice);
            System.out.println();
        }
    }
}
