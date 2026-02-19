package AppDevelopmentProject.PetCareScheduler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PetCareScheduler {
    public static void main(String[] args) {
        // Create collection objects to store Pet and Appointment objects
        java.util.ArrayList<Pet> petList = new java.util.ArrayList<>();
        java.util.ArrayList<Appointment> appointmentList = new java.util.ArrayList<>();
        
        // Create a Scanner instance to read user input from the console
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        // Load the existing data from files if any
        loadPetsFromFile(petList);
        loadAppointmentsFromFile(appointmentList);

        // Obtain input from the user to create new Pet and Appointment objects and add them to the collections.
        boolean continueApp = true;
        while (continueApp) {
            System.out.println("\n=== Pet Care Scheduler ===");
            System.out.println("1. Add a new pet");
            System.out.println("2. Add a new appointment");
            System.out.println("3. View all pets");
            System.out.println("4. View all appointments");
            System.out.println("5. Generate report");
            System.out.println("6. Exit");
            System.out.println("Choose an option (1-6): ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                addNewPet(petList, scanner);
            } else if (choice.equals("2")) {
                addNewAppointment(appointmentList, scanner);
            } else if (choice.equals("3")) {
                viewAllPets(petList);
            } else if (choice.equals("4")) {
                viewAllAppointments(appointmentList);
            } else if (choice.equals("5")) {
                generateReport(petList, appointmentList);
            } else if (choice.equals("6")) {
                continueApp = false;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        // Save the data to files before exiting the application.
        savePetsToFile(petList);
        saveAppointmentsToFile(appointmentList);

        // Verify that the data is correctly saved and can be loaded back into the application.
        System.out.println("Data saved successfully. Thank you for using Pet Care Scheduler!");
        
        // Close the scanner when done
        scanner.close();
    }

    // Method to add a new pet
    private static void addNewPet(java.util.ArrayList<Pet> petList, java.util.Scanner scanner) {
        try {
            System.out.println("\n=== Add New Pet ===");
            System.out.println("Enter pet name: ");
            String petName = scanner.nextLine().trim();

            if (petName.isEmpty()) {
                System.out.println("Pet name cannot be empty. Please try again.");
                return;
            }

            System.out.println("Enter pet type (e.g., Dog, Cat, Bird): ");
            String petType = scanner.nextLine().trim();

            if (petType.isEmpty()) {
                System.out.println("Pet type cannot be empty. Please try again.");
                return;
            }

            System.out.println("Enter pet age: ");
            int petAge = Integer.parseInt(scanner.nextLine().trim());

            Pet pet = new Pet(petName, petType, petAge);
            petList.add(pet);
            System.out.println("Pet added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid age format. Please enter a valid number.");
        }
    }

    // Method to add a new appointment
    private static void addNewAppointment(java.util.ArrayList<Appointment> appointmentList, java.util.Scanner scanner) {
        try {
            System.out.println("\n=== Add New Appointment ===");
            System.out.println("Enter pet name: ");
            String petName = scanner.nextLine().trim();

            if (petName.isEmpty()) {
                System.out.println("Pet name cannot be empty. Please try again.");
                return;
            }

            System.out.println("Enter appointment date (YYYY-MM-DD): ");
            String appointmentDate = scanner.nextLine().trim();

            System.out.println("Enter appointment time (HH:MM): ");
            String appointmentTime = scanner.nextLine().trim();

            System.out.println("Enter veterinarian name: ");
            String veterinarian = scanner.nextLine().trim();

            if (veterinarian.isEmpty()) {
                System.out.println("Veterinarian name cannot be empty. Please try again.");
                return;
            }

            Appointment appointment = new Appointment(petName, appointmentDate, appointmentTime, veterinarian);
            appointmentList.add(appointment);
            System.out.println("Appointment added successfully!");

        } catch (Exception e) {
            System.out.println("Error adding appointment: " + e.getMessage());
        }
    }

    // Method to view all pets
    private static void viewAllPets(java.util.ArrayList<Pet> petList) {
        if (petList.isEmpty()) {
            System.out.println("No pets in the system.");
        } else {
            System.out.println("\n=== All Pets ===");
            for (int i = 0; i < petList.size(); i++) {
                Pet pet = petList.get(i);
                System.out.println((i + 1) + ". Name: " + pet.getPetName() + 
                                 " | Type: " + pet.getPetType() + 
                                 " | Age: " + pet.getPetAge());
            }
        }
    }

    // Method to view all appointments
    private static void viewAllAppointments(java.util.ArrayList<Appointment> appointmentList) {
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments in the system.");
        } else {
            System.out.println("\n=== All Appointments ===");
            for (int i = 0; i < appointmentList.size(); i++) {
                Appointment appointment = appointmentList.get(i);
                System.out.println((i + 1) + ". Pet: " + appointment.getPetName() + 
                                 " | Date: " + appointment.getAppointmentDate() + 
                                 " | Time: " + appointment.getAppointmentTime() + 
                                 " | Veterinarian: " + appointment.getVeterinarian());
            }
        }
    }

    // Method to save pets to file
    private static void savePetsToFile(java.util.ArrayList<Pet> petList) {
        try (java.io.FileWriter fileWriter = new java.io.FileWriter("pets.txt")) {
            for (Pet pet : petList) {
                fileWriter.write(pet.getPetName() + "," + pet.getPetType() + "," + pet.getPetAge() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving pets to file: " + e.getMessage());
        }
    }

    // Method to save appointments to file
    private static void saveAppointmentsToFile(java.util.ArrayList<Appointment> appointmentList) {
        try (java.io.FileWriter fileWriter = new java.io.FileWriter("appointments.txt")) {
            for (Appointment appointment : appointmentList) {
                fileWriter.write(appointment.getPetName() + "," + appointment.getAppointmentDate() + "," + 
                               appointment.getAppointmentTime() + "," + appointment.getVeterinarian() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving appointments to file: " + e.getMessage());
        }
    }

    // Method to load pets from file
    private static void loadPetsFromFile(java.util.ArrayList<Pet> petList) {
        try (BufferedReader reader = new BufferedReader(new FileReader("pets.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line and create Pet objects
                // Expected format: petName,petType,petAge
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String petName = parts[0].trim();
                    String petType = parts[1].trim();
                    int petAge = Integer.parseInt(parts[2].trim());
                    
                    Pet pet = new Pet(petName, petType, petAge);
                    petList.add(pet);
                }
            }
            System.out.println("Pets loaded successfully from pets.txt");
        } catch (IOException e) {
            System.out.println("No existing pets.txt file found. Starting with an empty pet list.");
        }
    }

    // Method to load appointments from file
    private static void loadAppointmentsFromFile(java.util.ArrayList<Appointment> appointmentList) {
        try (BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line and create Appointment objects
                // Expected format: petName,appointmentDate,appointmentTime,veterinarian
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String petName = parts[0].trim();
                    String appointmentDate = parts[1].trim();
                    String appointmentTime = parts[2].trim();
                    String veterinarian = parts[3].trim();
                    
                    Appointment appointment = new Appointment(petName, appointmentDate, appointmentTime, veterinarian);
                    appointmentList.add(appointment);
                }
            }
            System.out.println("Appointments loaded successfully from appointments.txt");
        } catch (IOException e) {
            System.out.println("No existing appointments.txt file found. Starting with an empty appointment list.");
        }
    }

    // Method to generate a report
    private static void generateReport(java.util.ArrayList<Pet> petList, java.util.ArrayList<Appointment> appointmentList) {
        System.out.println("\n=== Pet Care Scheduler Report ===");
        System.out.println("\nTotal Pets: " + petList.size());
        System.out.println("Total Appointments: " + appointmentList.size());

        if (!petList.isEmpty()) {
            System.out.println("\n--- Pet Summary ---");
            for (Pet pet : petList) {
                System.out.println("- " + pet.getPetName() + " (" + pet.getPetType() + ", Age: " + pet.getPetAge() + ")");
            }
        }

        if (!appointmentList.isEmpty()) {
            System.out.println("\n--- Appointment Summary ---");
            for (Appointment appointment : appointmentList) {
                System.out.println("- " + appointment.getPetName() + " | " + appointment.getAppointmentDate() + 
                                 " at " + appointment.getAppointmentTime() + " | Dr. " + appointment.getVeterinarian());
            }
        }

        System.out.println("\n--- Report Generated ---");
    }
}