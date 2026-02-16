package ObjectOriented.EventSchedulerApplication.src;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

public class Event {
    private String name;
    private String date;

    public Event(String name, String date) {
        this.name = name;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event: " + name + ", Date: " + date;
    }
}