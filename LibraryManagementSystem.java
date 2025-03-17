import java.util.ArrayList;

abstract class LibraryItem {
    private String itemId;
    private String title;
    private String author;
    private boolean isReserved = false; // Encapsulation

    // Constructor
    public LibraryItem(String itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
    }

    // Encapsulation: Getters
    public String getItemId() { return itemId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    // Abstract Method for Loan Duration
    public abstract int getLoanDuration();

    // Display Item Details
    public void getItemDetails() {
        System.out.println("Item ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Loan Duration: " + getLoanDuration() + " days");
        System.out.println("Reserved: " + (isReserved ? "Yes" : "No"));
    }

    // Encapsulation: Handling Reservations
    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        this.isReserved = reserved;
    }
}

// Interface for Reservable Items
interface Reservable {
    void reserveItem();
    boolean checkAvailability();
}

// Book Class
class Book extends LibraryItem implements Reservable {
    public Book(String itemId, String title, String author) {
        super(itemId, title, author);
    }

    @Override
    public int getLoanDuration() {
        return 14; // Books can be borrowed for 14 days
    }

    @Override
    public void reserveItem() {
        if (!isReserved()) {
            setReserved(true);
            System.out.println("Book '" + getTitle() + "' has been reserved.");
        } else {
            System.out.println("Book '" + getTitle() + "' is already reserved.");
        }
    }

    @Override
    public boolean checkAvailability() {
        return !isReserved();
    }
}

// Magazine Class
class Magazine extends LibraryItem {
    public Magazine(String itemId, String title, String author) {
        super(itemId, title, author);
    }

    @Override
    public int getLoanDuration() {
        return 7; // Magazines can be borrowed for 7 days
    }
}

// DVD Class
class DVD extends LibraryItem implements Reservable {
    public DVD(String itemId, String title, String author) {
        super(itemId, title, author);
    }

    @Override
    public int getLoanDuration() {
        return 5; // DVDs can be borrowed for 5 days
    }

    @Override
    public void reserveItem() {
        if (!isReserved()) {
            setReserved(true);
            System.out.println("DVD '" + getTitle() + "' has been reserved.");
        } else {
            System.out.println("DVD '" + getTitle() + "' is already reserved.");
        }
    }

    @Override
    public boolean checkAvailability() {
        return !isReserved();
    }
}



public class LibraryManagementSystem {
    public static void main(String[] args) {
        ArrayList<LibraryItem> libraryItems = new ArrayList<>();

        // Creating Library Items
        LibraryItem book1 = new Book("B001", "Java Programming", "John Doe");
        LibraryItem magazine1 = new Magazine("M001", "Tech Today", "Jane Smith");
        LibraryItem dvd1 = new DVD("D001", "Inception", "Christopher Nolan");

        // Adding to Library Collection
        libraryItems.add(book1);
        libraryItems.add(magazine1);
        libraryItems.add(dvd1);

        // Displaying Item Details and Loan Duration
        for (LibraryItem item : libraryItems) {
            item.getItemDetails();
            System.out.println();
        }

        // Demonstrating Reservations
        if (book1 instanceof Reservable) {
            ((Reservable) book1).reserveItem();
        }

        if (dvd1 instanceof Reservable) {
            ((Reservable) dvd1).reserveItem();
        }

        // Checking Availability After Reservation
        System.out.println("\nChecking Availability:");
        System.out.println("Book available? " + ((Reservable) book1).checkAvailability());
        System.out.println("DVD available? " + ((Reservable) dvd1).checkAvailability());
    }
}
