import java.util.ArrayList;
abstract class FoodItem {
    private String itemName;
    private double price;
    private int quantity;

    // Constructor
    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    // Encapsulation: Getters
    public String getItemName() { return itemName; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    // Abstract Method for Price Calculation
    public abstract double calculateTotalPrice();

    // Display Item Details
    public void getItemDetails() {
        System.out.println("Item: " + itemName);
        System.out.println("Price per unit: " + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Price: " + calculateTotalPrice());
    }
}

// Interface for Discountable Items
interface Discountable {
    void applyDiscount(double percentage);
    double getDiscountDetails();
}

// Veg Item Class
class VegItem extends FoodItem implements Discountable {
    private double discount = 0;

    public VegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return (getPrice() * getQuantity()) - discount;
    }

    @Override
    public void applyDiscount(double percentage) {
        discount = (getPrice() * getQuantity()) * (percentage / 100);
    }

    @Override
    public double getDiscountDetails() {
        return discount;
    }
}

// Non-Veg Item Class (Extra 10% Charge)
class NonVegItem extends FoodItem implements Discountable {
    private double discount = 0;

    public NonVegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        double total = getPrice() * getQuantity();
        double additionalCharge = total * 0.10; // 10% extra charge for non-veg
        return (total + additionalCharge) - discount;
    }

    @Override
    public void applyDiscount(double percentage) {
        discount = (getPrice() * getQuantity()) * (percentage / 100);
    }

    @Override
    public double getDiscountDetails() {
        return discount;
    }
}

public class OnlineFoodDelivery {
    public static void main(String[] args) {
        ArrayList<FoodItem> orderList = new ArrayList<>();

        // Creating Food Items
        FoodItem vegBurger = new VegItem("Veg Burger", 5.99, 2);
        FoodItem chickenPizza = new NonVegItem("Chicken Pizza", 12.99, 1);
        FoodItem paneerRoll = new VegItem("Paneer Roll", 4.50, 3);

        // Adding to Order List
        orderList.add(vegBurger);
        orderList.add(chickenPizza);
        orderList.add(paneerRoll);

        // Applying Discounts
        if (vegBurger instanceof Discountable) {
            ((Discountable) vegBurger).applyDiscount(10);
        }
        if (chickenPizza instanceof Discountable) {
            ((Discountable) chickenPizza).applyDiscount(5);
        }

        // Displaying Order Details
        System.out.println("Order Summary:");
        for (FoodItem item : orderList) {
            item.getItemDetails();
            if (item instanceof Discountable) {
                System.out.println("Discount Applied: " + ((Discountable) item).getDiscountDetails());
            }
            System.out.println();
        }
    }
}
