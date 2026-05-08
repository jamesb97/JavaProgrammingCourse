package SingletonLab;

/**
 * OfficeTest - Simulates people in an office using the printer
 * This class demonstrates how the Singleton pattern works in practice
 */
public class OfficeTest {

    public static void main(String[] args) {
        System.out.println("🏢 Welcome to our office! Let's use the printer...\n");

        // SCENARIO 1: Alice needs to print something
        // When Alice calls getManager(), it creates the FIRST (and only) PrinterManager
        PrinterManager aliceManager = PrinterManager.getManager();
        aliceManager.checkStatus(); // Check if printer is free
        aliceManager.printDocument("Monthly Report", "Alice"); // Alice prints her document

        System.out.println();

        // SCENARIO 2: Bob also needs to print something
        // When Bob calls getManager(), he gets the SAME manager Alice got!
        // No new PrinterManager is created - this is the Singleton magic!
        PrinterManager bobManager = PrinterManager.getManager();
        bobManager.checkStatus(); // Check printer status
        bobManager.printDocument("Meeting Notes", "Bob"); // Bob prints his document

        System.out.println();

        // PROOF: Let's verify Alice and Bob have the same manager
        // The == operator checks if two variables point to the exact same object
        if (aliceManager == bobManager) {
            System.out.println("✅ Alice and Bob are using the SAME PrinterManager!");
            System.out.println("   This is the Singleton pattern working!");
            System.out.println("   aliceManager memory address: " + aliceManager.hashCode());
            System.out.println("   bobManager memory address: " + bobManager.hashCode());
        } else {
            System.out.println("❌ Something went wrong - they have different managers!");
        }

        System.out.println("\n📊 Printing Statistics:");
        System.out.println("📊 Total print jobs for the day: " + aliceManager.getTotalPrintJobs());

        System.out.println("\n🎯 Lab Complete! You've implemented the Singleton pattern!");
    }
}