import java.time.LocalDate;
import java.time.LocalTime;

public class Mood {
    private String moodName;
    private LocalDate date;
    private LocalTime time;
    private String notes;

    // Constructor
    public Mood(String moodName, LocalDate date, LocalTime time, String notes) {
        this.moodName = moodName;
        this.date = date;
        this.time = time;
        this.notes = notes;
    }

    // Getters
    public String getMoodName() {
        return moodName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getNotes() {
        return notes;
    }

    // Setter
    public void setNotes(String notes) {
        this.notes = notes;
    }
}