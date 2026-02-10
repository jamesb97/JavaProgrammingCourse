package ObjectOriented.my_arraylist_proj.src;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArrayListExample {
    public static void main(String s[]) {
        try {
            // Create a Scanner object to read user input
            Scanner scanner = new Scanner(System.in);
            // Create an ArrayList to store Task objects (to-do list)
            ArrayList<Task> todoList = new ArrayList<Task>();

            // Infinite loop to keep the program running until the user chooses to exit
            while (true) {
                // Display the menu options to the user
                System.out.println(
                        "Press 1 to add a task, " +
                                "\n2 to view all the tasks " +
                                "\n3 to change status of tasks " +
                                "\n4 to delete a task " +
                                "\n5 to sort the list based on priority " +
                                "\nAny other key to exit");

                // Read the user's choice
                String userAction = scanner.nextLine();

                // Option 1: Add a task to the to-do list
                if (userAction.equals("1")) {
                    // Prompt the user to enter a task description
                    System.out.println("Enter the task ");
                    String taskStr = scanner.nextLine();

                    // Prompt the user to enter the priority of the task
                    System.out.println("Enter Priority - 1 Low, 2 Medium, 3 High ");
                    int priority = Integer.parseInt(scanner.nextLine());

                    // Validate the priority input (default to 1 if invalid)
                    priority = priority > 3 ? 1 : priority;

                    // Create a new Task object and add it to the to-do list
                    todoList.add(new Task(taskStr, priority));
                    System.out.println("The task has been added to the list");

                }
                // Option 2: View all tasks in the to-do list
                else if (userAction.equals("2")) {
                    // Use forEach to print each task in the to-do list
                    todoList.forEach(task -> System.out.println(task));
                }
                // Option 3: Change the status of a task
                else if (userAction.equals("3")) {
                    // Prompt the user to enter the index of the task to update
                    System.out.println("Enter the index of the status you want to change ");
                    int chgIdx = Integer.parseInt(scanner.nextLine());

                    // Check if the index is valid
                    if (chgIdx > (todoList.size() - 1)) {
                        System.out.println("There is no such index position in the list");
                    } else {
                        // Prompt the user to enter the new status for the task
                        System.out
                                .println("Enter the new status for the task P for 'In Progress' or C for 'Completed'");
                        String updatedStatus = scanner.nextLine();

                        // Update the task status based on user input
                        if (updatedStatus.equalsIgnoreCase("P")) {
                            todoList.get(chgIdx).setStatus(Task.IN_PROGRESS);
                        } else if (updatedStatus.equalsIgnoreCase("C")) {
                            todoList.get(chgIdx).setStatus(Task.COMPLETED);
                        }
                    }
                    System.out.println("The task has been changed in the list");
                }
                // Option 4: Delete a task
                else if (userAction.equals("4")) {
                    // Prompt the user to enter the index of the task to delete
                    System.out.println("Enter the index of the status you want to delete ");
                    int rmvIdx = Integer.parseInt(scanner.nextLine());

                    // Check if the index is valid
                    if (rmvIdx > (todoList.size() - 1)) {
                        System.out.println("There is no such index position in the list");
                    } else {
                        todoList.remove(rmvIdx);
                        System.out.println("The task has been removed from the list");
                    }
                }
                // Option 5: Sort tasks by priority
                else if (userAction.equals("5")) {
                    // Sort the ArrayList by age using a Comparator
                    Collections.sort(todoList, new Comparator<Task>() {
                        @Override
                        public int compare(Task t1, Task t2) {
                            return Integer.compare(t2.getPriority(), t1.getPriority());
                        }
                    });
                    System.out.println("Tasks sorted by priority (High to Low):");
                    todoList.forEach(task -> System.out.println(task));
                }
                // Exit the program if the user enters any other key
                else {
                    break;
                }
            }
            scanner.close(); // Close the scanner to prevent resource leaks
        } catch (NumberFormatException nfe) {
            // Handle invalid number input (e.g., non-integer input for priority or index)
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
}