package ui;
import java.io.*;

public class WorkScheduleMenu{
    public WorkScheduleMenu() {}

    public static void displayWorkScheduleMenu() throws FileNotFoundException {
        System.out.println("\t\t\t -MANAGE WORK SCHEDULE MENU- \t\t\t");
        System.out.println("1. Display the work schedule");
        System.out.println("2. Add a shift to the work schedule");
        System.out.println("3. Search for a shift in the working schedule");
        System.out.println("4. Exit");
    }

    public static void displaySearchWorkScheduleMenu() throws FileNotFoundException {
        System.out.println("Do you wish to perform further operations such as: ");
        System.out.println("1. Update a shift in the work schedule");
        System.out.println("2. Remove a shift from the work schedule");
        System.out.println("3. Exit");
    }

    public static void workScheduleMenu() throws FileNotFoundException {
        String choice = "-1";
        do {
            displayWorkScheduleMenu();

            switch (choice) {
                case "1":
                    //MainMenu.printEmptyLines();
                    //App.getController().displayWorkSchedule();
                    //MainMenu.printEmptyLines();
                    break;

                case "2":
                    //MainMenu.printEmptyLines();
                    //App.getController().addShift();
                    //MainMenu.printEmptyLines();
                    break;

                case "3":
                    //MainMenu.printEmptyLines();
                    //App.getController().searchShift();
                    //MainMenu.printEmptyLines();
                    break;

                case "4":
                    break;
            }
        } while (!choice.equals("5"));
    }

    public static void searchWorkScheduleMenu() throws FileNotFoundException {
        String choice = "-1";
        do {
            displaySearchWorkScheduleMenu();

            switch (choice) {
                case "1":
                    //MainMenu.printEmptyLines();
                    //App.getController().updateWorkSchedule();
                    //MainMenu.printEmptyLines();
                    break;

                case "2":
                    //MainMenu.printEmptyLines();
                    //App.getController().removeShift();
                    //MainMenu.printEmptyLines();
                    break;

                case "3":
                    break;
            }
        } while (!choice.equals("4"));
    }
}