package code_clause;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    private static ArrayList<String> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            System.out.println("----- To-Do List -----");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Remove Task");
            System.out.println("4. View Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    editTask();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    viewTasks();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        System.out.println("Exiting the program. Goodbye!");
        scanner.close();
    }

    private static void addTask() {
        System.out.print("Enter the task: ");
        String task = scanner.nextLine();
        tasks.add(task);
        System.out.println("Task added successfully!");
    }

    private static void editTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to edit.");
            return;
        }
        
        System.out.print("Enter the index of the task to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid index.");
            return;
        }
        
        System.out.print("Enter the new task: ");
        String newTask = scanner.nextLine();
        tasks.set(index, newTask);
        System.out.println("Task edited successfully!");
    }

    private static void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }
        
        System.out.print("Enter the index of the task to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid index.");
            return;
        }
        
        String removedTask = tasks.remove(index);
        System.out.println("Task \"" + removedTask + "\" removed successfully!");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
            return;
        }
        
        System.out.println("----- Tasks -----");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i));
        }
        System.out.println("-----------------");
    }
}
