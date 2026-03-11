import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //System.out.println("This is a test!");
        // Create an ArrayList of US cities
        ArrayList<String> usCities = new ArrayList<>();

        // Add 10 US cities to the ArrayList
        usCities.add("New York");
        usCities.add("Los Angeles");
        usCities.add("Chicago");
        usCities.add("Houston");
        usCities.add("Phoenix");
        usCities.add("Philadelphia");
        usCities.add("San Antonio");
        usCities.add("Atlanta");
        usCities.add("Dallas");
        usCities.add("San Jose");

        for( String city: usCities) {
            System.out.println(city);
        }
    }
}