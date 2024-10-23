package Tasks;

import Enum.Role;
import business.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DoctorTask {
    static Scanner scanner = new Scanner(System.in);

    public static void mainTask() {
        while (true) {
            System.out.println("Enter the option " +
                    "\n Add Patient Record: 1 " +
                    "\n View Patient Records: 2 " +
                    "\n Update Patient Record: 3 " +
                    "\n Add Patient History: 4 " +
                    "\n View All Patient History: 5 " +
                    "\n Exit: 9");

            int num = scanner.nextInt();
            scanner.nextLine();

            switch (num) {
                case 1:
                    addPatientRecord();
                    break;
                case 2:
                    viewPatientRecords();
                    break;
                case 3:
                    updatePatientTask();
                    break;
                case 4:
                    addHistoryTask();
                    break;
                case 5:
                    viewAllPatientHistory();
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

public static void addPatientRecord() {
    String firstName = "";
    String lastName = "";
    String contactNumber = "";
    LocalDate birthDate = null;
    String address = "";
    Patient.GenderType gender = null;

    while (true) {
        System.out.print("Enter First Name: ");
        firstName = scanner.nextLine().trim();
        if (!firstName.isEmpty()) {
            break;
        }
        System.out.println("First Name cannot be empty. Please enter again.");
    }

    while (true) {
        System.out.print("Enter Last Name: ");
        lastName = scanner.nextLine().trim();
        if (!lastName.isEmpty()) {
            break;
        }
        System.out.println("Last Name cannot be empty. Please enter again .");
    }

    while (true) {
        System.out.print("Enter Contact Number: ");
        contactNumber = scanner.nextLine().trim();
        if (isValidPhoneNumber(contactNumber)) {
            break;
        }
        System.out.println("Invalid contact number format. Must be 10 digits.");
    }

    while (true) {
        System.out.print("Enter Birth Date (YYYY-MM-DD): ");
        String birthDateString = scanner.nextLine().trim();
        try {
            birthDate = LocalDate.parse(birthDateString);
            if (!birthDate.isAfter(LocalDate.now())) {
                break;
            }
            System.out.println("Birth date cannot be in the future.");
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    while (true) {
        System.out.print("Enter Address: ");
        address = scanner.nextLine().trim();
        if (!address.isEmpty()) {
            break;
        }
        System.out.println("Address cannot be empty.");
    }

    while (true) {
        System.out.print("Enter Gender (MALE/FEMALE): ");
        String genderString = scanner.nextLine().trim().toUpperCase();
        try {
            gender = Patient.GenderType.valueOf(genderString);
            break;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid gender. Please enter either MALE or FEMALE.");
        }
    }

    Patient newPatient = new Patient(firstName, lastName, contactNumber, birthDate, gender, address);
    if (Patient.addPatient(newPatient)) {
        System.out.println("Patient record added successfully.");
    } else {
        System.out.println("Failed to add patient record.");
    }
}

    private static boolean isValidPhoneNumber(String contactNumber) {
        return contactNumber.matches("\\d{10}");
    }

    public static void viewPatientRecords() {
        List<Patient> patients = Patient.viewAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patient records found.");
        } else {
            patients.forEach(System.out::println);
        }
    }

    public static void addHistoryTask() {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine().trim();

        Patient patient = Patient.getPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        LocalDate visitDate = null;
        while (true) {
            System.out.print("Enter Visit Date (YYYY-MM-DD): ");
            String dateString = scanner.nextLine().trim();
            try {
                visitDate = LocalDate.parse(dateString);
                if (visitDate.isAfter(LocalDate.now())) {
                    System.out.println("Visit date cannot be in the future. Please enter again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

        String syndrome = "";
        while (true) {
            System.out.print("Enter Syndrome: ");
            syndrome = scanner.nextLine().trim();
            if (!syndrome.isEmpty()) {
                break;
            }
            System.out.println("Syndrome cannot be empty. Please enter again.");
        }

        String prescription = "";
        while (true) {
            System.out.print("Enter Prescription: ");
            prescription = scanner.nextLine().trim();
            if (!prescription.isEmpty()) {
                break;
            }
            System.out.println("Prescription cannot be empty. Please enter again.");
        }

        PatientHistory history = new PatientHistory(visitDate, syndrome, prescription, patient);
        if (PatientHistory.addPatientHistory(history, patient)) {
            System.out.println("Patient history added successfully.");
        } else {
            System.out.println("Failed to add patient history.");
        }
    }

    public static void updatePatientTask() {
        String contactNumber = "";
        String address = "";
        System.out.print("Enter Patient ID to update: ");
        String patientId = scanner.nextLine();

        Patient patient = Patient.getPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        while (true) {
            System.out.print("Enter Contact Number: ");
            contactNumber = scanner.nextLine().trim();
            if (isValidPhoneNumber(contactNumber)) {
                break;
            }
            System.out.println("Invalid contact number format. Must be 10 digits.");
        }
        patient.setContactNumber(contactNumber);
        while (true) {
            System.out.print("Enter Address: ");
            address = scanner.nextLine().trim();
            if (!address.isEmpty()) {
                break;
            }
            System.out.println("Address cannot be empty.");
        }
        patient.setAddress(address);
        if (Patient.updatePatient(patient)) {
            System.out.println("Patient record updated successfully.");
        } else {
            System.out.println("Failed to update patient record.");
        }
    }

    public static void viewAllPatientHistory() {
        System.out.print("Enter Patient ID to view history: ");
        String patientId = scanner.nextLine();

        List<PatientHistory> historyList = PatientHistory.getPatientHistoryById(patientId);
        if (historyList.isEmpty()) {
            System.out.println("No history records found for this patient.");
        } else {
            historyList.forEach(System.out::println);
        }
    }
}
