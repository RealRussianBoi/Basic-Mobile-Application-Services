import java.util.ArrayList;
import java.util.Scanner;

public class TaskService {
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        String option = "-1";

        while (!option.equals("0")) {
            System.out.println("What would you like to do?");
            System.out.println("1: Add New Task");
            System.out.println("2: See All Tasks");
            System.out.println("3: Delete Task via task ID");
            System.out.println("4: Update Task via task ID");
            System.out.println("0: Exit Program");
            System.out.print("Enter Option Number: >>");
            option = scnr.nextLine();

            switch (option) {

                case "1":

                    tasks.add(addNewTask(scnr, tasks));

                    break;

                case "2":

                    showAllTasks(tasks);

                    break;

                case "3":

                    String ID;
                    System.out.println("Enter the ID of the task you wish to remove: >>");
                    ID = scnr.nextLine();
                    while (validateNumber10(ID)) {
                        System.out.println("Enter a valid integer ID no longer than 10 characters: >>");
                        ID = scnr.nextLine();
                    }

                    deleteTaskViaID(tasks, ID);

                    break;

                case "4":

                    String id;
                    System.out.println("Enter the ID of the task you wish to update: >>");
                    id = scnr.nextLine();
                    while (validateNumber10(id)) {
                        System.out.println("Enter a valid integer ID no longer than 10 characters: >>");
                        id = scnr.nextLine();
                    }

                    updateTaskViaID(scnr, tasks, id);

                    break;

                case "0":

                    break;

                default:

                    System.out.println("\nThat is not one of the options.\n");

                    break;

            }
        }
    }

    public static void showAllTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("\nYou have no tasks.\n");
        } else {
            System.out.println(); //Extra souts make new lines.
            for (Task t: tasks) {
                System.out.println(t.toString() + "\n");
            }
        }
    }

    public static Task addNewTask(Scanner scnr, ArrayList<Task> tasks) {
        String ID = tasks.size() + 1 + "";
        String Name;
        String Desc;

        System.out.println("Enter the task's name, with a maximum length of twenty letters: >>");
        Name = scnr.nextLine();
        while (fieldValidate20(Name)) {
            System.out.println("Enter a valid, non empty task name, with a maximum length of twenty letters: >>");
            Name = scnr.nextLine();
        }

        System.out.println("Enter the task's description, with a maximum length of fifty letters: >>");
        Desc = scnr.nextLine();
        while (fieldValidate50(Desc)) {
            System.out.println("Enter a valid, non empty task description, with a maximum of fifty letters: >>");
            Desc = scnr.nextLine();
        }

        Task t = new Task(ID, Name, Desc);

        System.out.println("\nTask recorded successfully. Task contents: " + t + "\n"); //To create a gap between the end of this function and menu.

        return t;
    }

    public static boolean fieldValidate20(String input) {
        if (input == "" || input == null) {
            return true;
        } else  if (input.length() > 20) {
            return true;
        }
        return false;
    }

    public static boolean fieldValidate50(String input) {
        if (input == "" || input == null) {
            return true;
        } else  if (input.length() > 50) {
            return true;
        }
        return false;
    }

    public static boolean validateNumber10(String input) {
        if (input == "" || input == null) {
            return true;
        } else  if (input.length() > 10) {
            return true;
        }

        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            return true;
        }

        return false;
    }

    public static void deleteTaskViaID(ArrayList<Task> tasks, String ID) {
        ArrayList<Task> t = new ArrayList<>();
        t.addAll(tasks);
        boolean reorganizeTheIDs = false;
        boolean nothing = true;

        for (Task task : tasks) {
            if (task.getID().equals(ID)) {
                reorganizeTheIDs = true;
                tasks.remove(task);
                break;
            }
        }

        if (nothing) {
            System.out.println("\nNo such task exists.\n");
            return;
        }

        if (reorganizeTheIDs) {
            int id = 0;
            for (Task task : tasks) {
                id++;
                task.setID(Integer.toString(id));
            }
        }

        System.out.println("\nSuccessfully deleted task.\n"); //To create a gap between the end of this function and menu.

    }

    public static void updateTaskViaID(Scanner scnr, ArrayList<Task> tasks, String ID) {
        String Name = "";
        String Desc = "";
        boolean bs = true;
        boolean updated = false;

        for (Task task : tasks) {
            if (task.getID().equals(ID)) {
                bs = false;
                Name = task.getName();
                Desc = task.getDescription();
                break;
            }
        }

        if (bs) {
            System.out.println("\nThere is no such task.\n");
            return;
        }

        String option;
        System.out.println("The task's name is: >>" + Name + ".");
        System.out.println("1: Change Name");
        System.out.println("2: Keep Name");
        option = scnr.nextLine();
        while (!check1or2(option)) {
            System.out.println("Enter 1 or 2: >>");
            option = scnr.nextLine();
        }

        if (option.equals("1")) {
            System.out.println("Enter the task's new name, with a maximum length of ten letters: >>");
            Name = scnr.nextLine();
            while (fieldValidate20(Name)) {
                System.out.println("Enter a valid, non empty task name, with a maximum length of twenty letters: >>");
                Name = scnr.nextLine();
            }
            updated = true;
        }

        System.out.println("The task's description is: >>" + Desc + ".");
        System.out.println("1: Change Description");
        System.out.println("2: Keep Description");
        option = scnr.nextLine();
        while (!check1or2(option)) {
            System.out.println("Enter 1 or 2: >>");
            option = scnr.nextLine();
        }

        if (option.equals("1")) {
            System.out.println("Enter the task description, with a maximum length of ten letters: >>");
            Desc = scnr.nextLine();
            while (fieldValidate50(Desc)) {
                System.out.println("Enter a valid, non empty task description, with a maximum length of fifty letters: >>");
                Desc = scnr.nextLine();
            }
            updated = true;
        }

        Task t = new Task(ID, Name, Desc);
        tasks.set(Integer.parseInt(ID) - 1, t);

        if (updated) {
            System.out.println("\nTask updated successfully. New task contents: " + t + "\n");
        } else {
            System.out.println("\nNothing was updated.\n");
        }
    }

    public static boolean check1or2(String input) {
        switch (input) {
            case "1", "2":
                return true;
        }
        return false;
    }
}