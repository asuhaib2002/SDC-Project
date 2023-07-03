package com.company;

import java.util.Scanner;

public class TaskManagerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final TaskManager taskManager = new TaskManager();

        while (true) {
            System.out.println("Task Manager");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Print Tasks");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    return;
                case 1:
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    Task task = new Task(name, description);
                    taskManager.addTask(task);
                    break;
                case 2:
                    System.out.print("Enter the task index to mark as completed: ");
                    final int index = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    Thread markTaskThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            taskManager.markTaskAsCompleted(index - 1);
                        }
                    });
                    markTaskThread.start();
                    break;
                case 3:
                    taskManager.printTasks();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}
