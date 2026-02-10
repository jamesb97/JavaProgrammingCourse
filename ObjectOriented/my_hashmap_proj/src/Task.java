package ObjectOriented.my_hashmap_proj.src;

public class Task {
    public static int HIGH = 3;
    public static int MEDIUM = 2;
    public static int LOW = 1;

    public static String COMPLETED = "Completed";
    public static String IN_PROGRESS = "In Progress";
    public static String NEW = "New";

    private String taskName;
    private String taskDescription;
    private int priority = LOW;
    private String status = NEW;

    public Task(String taskName, String taskDescription, int priority) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.priority = priority;
    }

    public Task(String taskName, int priority) {
        this.taskName = taskName;
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return this.taskDescription;
    }

    public String getStatus() {
        return this.status;
    }

    public int getPriority() {
        return this.priority;
    }

    public String toString() {
        return taskName.concat("\n").concat(taskDescription).concat("\n").concat((priority + "")).concat("\n")
                .concat(status);
    }
}