import java.util.ArrayList;

abstract class Patient {
    private int patientId;
    private String name;
    private int age;

    // Constructor
    public Patient(int patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }

    // Getters
    public int getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }

    // Abstract Method for Bill Calculation
    public abstract double calculateBill();

    // Display Patient Details
    public void getPatientDetails() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Total Bill: $" + calculateBill());
    }
}

// Interface for Medical Records
interface MedicalRecord {
    void addRecord(String record);
    void viewRecords();
}

// InPatient Class
class InPatient extends Patient implements MedicalRecord {
    private double roomCost;
    private double treatmentCost;
    private double medicationCost;
    private String medicalHistory = "";

    public InPatient(int patientId, String name, int age, double roomCost, double treatmentCost, double medicationCost) {
        super(patientId, name, age);
        this.roomCost = roomCost;
        this.treatmentCost = treatmentCost;
        this.medicationCost = medicationCost;
    }

    @Override
    public double calculateBill() {
        return roomCost + treatmentCost + medicationCost;
    }

    @Override
    public void addRecord(String record) {
        medicalHistory += record + "\n";
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical History: \n" + medicalHistory);
    }
}

// OutPatient Class
class OutPatient extends Patient implements MedicalRecord {
    private double consultationFee;
    private double medicationCost;
    private String medicalHistory = "";

    public OutPatient(int patientId, String name, int age, double consultationFee, double medicationCost) {
        super(patientId, name, age);
        this.consultationFee = consultationFee;
        this.medicationCost = medicationCost;
    }

    @Override
    public double calculateBill() {
        return consultationFee + medicationCost;
    }

    @Override
    public void addRecord(String record) {
        medicalHistory += record + "\n";
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical History: \n" + medicalHistory);
    }
}

public class HospitalPatientManagement{
    public static void main(String[] args) {
        ArrayList<Patient> patientList = new ArrayList<>();

        // Creating Patients
        Patient patient1 = new InPatient(101, "Alice", 45, 500, 1200, 300);
        Patient patient2 = new OutPatient(102, "Bob", 30, 100, 50);
        Patient patient3 = new InPatient(103, "Charlie", 60, 700, 1500, 500);

        // Adding to Patient List
        patientList.add(patient1);
        patientList.add(patient2);
        patientList.add(patient3);

        // Adding Medical Records
        if (patient1 instanceof MedicalRecord) {
            ((MedicalRecord) patient1).addRecord("Diagnosed with pneumonia.");
            ((MedicalRecord) patient1).addRecord("Given antibiotics and oxygen support.");
        }
        if (patient2 instanceof MedicalRecord) {
            ((MedicalRecord) patient2).addRecord("Diagnosed with common cold.");
            ((MedicalRecord) patient2).addRecord("Prescribed paracetamol.");
        }

        // Displaying Patient Details
        System.out.println("Hospital Patient Details:");
        for (Patient patient : patientList) {
            patient.getPatientDetails();
            if (patient instanceof MedicalRecord) {
                ((MedicalRecord) patient).viewRecords();
            }
            System.out.println();
        }
    }
}
