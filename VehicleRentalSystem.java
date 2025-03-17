import java.util.ArrayList;

abstract class Vehicle {
    private String vehicleNumber;
    private String type;
    private double rentalRate;

    // Constructor
    public Vehicle(String vehicleNumber, String type, double rentalRate) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
    }

    // Encapsulation: Getters
    public String getVehicleNumber() { return vehicleNumber; }
    public String getType() { return type; }
    public double getRentalRate() { return rentalRate; }

    // Abstract Method for Rental Cost Calculation
    public abstract double calculateRentalCost(int days);

    // Concrete Method to Display Vehicle Details
    public void displayDetails() {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Vehicle Type: " + type);
        System.out.println("Rental Rate per Day: " + rentalRate);
    }
}

// Interface for Insurable Vehicles
interface Insurable {
    double calculateInsurance();
    String getInsuranceDetails();
}

// Car Class (Insurable)
class Car extends Vehicle implements Insurable {
    private String insurancePolicyNumber;

    public Car(String vehicleNumber, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Car", rentalRate);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days; // Flat rate per day
    }

    @Override
    public double calculateInsurance() {
        return getRentalRate() * 0.05; // 5% of rental rate as insurance
    }

    @Override
    public String getInsuranceDetails() {
        return "Car Insurance Policy: " + insurancePolicyNumber;
    }
}

// Bike Class (Not Insurable)
class Bike extends Vehicle {
    public Bike(String vehicleNumber, double rentalRate) {
        super(vehicleNumber, "Bike", rentalRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days * 0.9; // 10% discount on total rental
    }
}

// Truck Class (Insurable)
class Truck extends Vehicle implements Insurable {
    private String insurancePolicyNumber;

    public Truck(String vehicleNumber, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Truck", rentalRate);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days + 50; // Additional fixed charge of $50
    }

    @Override
    public double calculateInsurance() {
        return getRentalRate() * 0.1; // 10% of rental rate as insurance
    }

    @Override
    public String getInsuranceDetails() {
        return "Truck Insurance Policy: " + insurancePolicyNumber;
    }
}


public class VehicleRentalSystem {
    public static void main(String[] args) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        // Creating Different Vehicles
        Vehicle car1 = new Car("CAR123", 50, "CAR-INS-001");
        Vehicle bike1 = new Bike("BIKE456", 20);
        Vehicle truck1 = new Truck("TRUCK789", 100, "TRUCK-INS-002");

        // Adding to Vehicle List
        vehicles.add(car1);
        vehicles.add(bike1);
        vehicles.add(truck1);

        // Processing and Displaying Rental and Insurance Details
        int rentalDays = 5;
        for (Vehicle vehicle : vehicles) {
            vehicle.displayDetails();
            double rentalCost = vehicle.calculateRentalCost(rentalDays);
            double insuranceCost = 0;

            // Check if vehicle is insurable
            if (vehicle instanceof Insurable) {
                insuranceCost = ((Insurable) vehicle).calculateInsurance();
                System.out.println(((Insurable) vehicle).getInsuranceDetails());
            }

            double totalCost = rentalCost + insuranceCost;
            System.out.println("Rental Cost for " + rentalDays + " days: " + rentalCost);
            System.out.println("Insurance Cost: " + insuranceCost);
            System.out.println("Total Cost: " + totalCost);
            System.out.println();
        }
    }
}
