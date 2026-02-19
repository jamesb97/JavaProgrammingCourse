package AppDevelopmentProject.PetCareScheduler;

import java.util.List;

public class Pet {
    public Pet(String petName, String petType, int petAge) {
        // TODO Auto-generated constructor stub
    }

    // Unique Pet ID
    private String petID;
    // Name of the pet private String name;
    // Type of pet (e.g., dog, cat, bird)
    private String type;
    // Age of the pet in years private int age;
    // Owner's name
    private String ownerName;
    private String ownerContact;
    // Registration date of the pet in the system
    private String registrationDate;
    // List of appointments scheduled for the pet
    private List<Appointment> appointments;

    public int getPetName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPetName'");
    }

    public int getPetType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPetType'");
    }

    public int getPetAge() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPetAge'");
    }
}