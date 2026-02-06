package ObjectOriented.my_impl_proj.src;

public class BulbOperate {
    public static void main(String s[]) {
        // Create an array of Switchable objects to hold different types of bulbs
        Switchable switchables[] = new Switchable[3];

        // Create instances of SmartBulb, DimmableBulb, and RegularBulb
        SmartBulb sb = new SmartBulb(); // SmartBulb implements Switchable, Adjustable, and Connectable
        DimmableBulb db = new DimmableBulb(); // DimmableBulb implements Switchable and Adjustable
        RegularBulb rb = new RegularBulb(); // RegularBulb implements only Switchable

        // Populate the array with the bulb instances
        switchables[0] = sb; // Add SmartBulb to the array
        switchables[1] = db; // Add DimmableBulb to the array
        switchables[2] = rb; // Add RegularBulb to the array

        // Loop through the array and turn each bulb on and off
        for (int i = 0; i < switchables.length; i++) {
            switchables[i].turnOn(); // Turn on the bulb
            switchables[i].turnOff(); // Turn off the bulb
        }

        // Increase brightness of the SmartBulb directly
        sb.increase(); // Calls the increase() method of SmartBulb

        // Decrease brightness of the DimmableBulb by casting to Adjustable
        ((Adjustable) switchables[1]).decrease(); // Cast switchables[1] (DimmableBulb) to Adjustable and call
                                                  // decrease()

        // Connect the SmartBulb to the network by casting to Connectable
        ((Connectable) switchables[0]).connect(); // Cast switchables[0] (SmartBulb) to Connectable and call connect()
    }
}