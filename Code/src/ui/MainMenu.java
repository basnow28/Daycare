package ui;
import controller.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//If any action will be performed in the Menus use App.getController() to get controller instance
// Bianca - for now do not dispatch any actions, but create the menus where you can just switch between different options
//and get input data when creating/updating and validate this data

public class MainMenu {
    private static Scanner scanner = new Scanner(System.in);

    public MainMenu() {
        mainMenuAdmin();
    }

    private static void displayMainMenuAdmin(){
        System.out.println("\t\t\t -MENU- \t\t\t");
        System.out.println("1. Manage children menu");
        System.out.println("2. Manage employee menu");
        System.out.println("3. Manage waiting list menu");
        System.out.println("4. Manage work schedule menu");
        System.out.println("5. Exit");
    }

    // to me it makes sense to only have 2 options for employees, obviously we can change this after
    private static void displayMainMenuEmployee(){
        System.out.println("\t\t\t -MENU- \t\t\t");
        System.out.println("1. View children's information on specific quarter");
        //the employee should also be able to book a shift
        System.out.println("3. Book a shift");
        System.out.println("2. View work schedule");
        System.out.println("3. Exit");
    }

    // Log in system //




    public static void mainMenuAdmin(){
        String choice = "-1";
        do {
            displayMainMenuAdmin();
            choice = scanner.next();
            switch (choice) {
                case "1":
                    printEmptyLines();
                    ChildrenMenu.childrenMenu();
                    printEmptyLines();
                    break;

                case "2":
                    printEmptyLines();
                    EmployeeMenu.employeeMenu();
                    printEmptyLines();
                    break;

                case "3":
                    printEmptyLines();
                    ListMenu.listMenu();
                    printEmptyLines();
                    break;

                case "4":
                    printEmptyLines();
                    WorkScheduleMenu.workScheduleMenu();
                    printEmptyLines();
                    break;


                case "5":
                    break;
            }
        } while (!choice.equals("5"));
    }

    private static void mainMenuForEmployee(){
        String choice = "-1";
        do {
            displayMainMenuEmployee();

            switch (choice) {
                case "1":
                    //printEmptyLines();
                    //App.getController().displayChild();
                    //printEmptyLines();
                    break;

                case "2":
                    //printEmptyLines();
                    //App.getController().displayWorkSchedule();
                    //printEmptyLines();
                    break;

                case "3":
                    break;

            }
        } while (!choice.equals("4"));
    }

    public static void printEmptyLines() {
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void typeAnyKey(){
        System.out.println("\nType any key to continue");
        scanner.next();
    }
}

