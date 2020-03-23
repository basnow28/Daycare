package ui;
import controller.Controller;

import java.io.FileNotFoundException;

//If any action will be performed in the Menus use App.getController() to get controller instance
// Bianca - for now do not dispatch any actions, but create the menus where you can just switch between different options
//and get input data when creating/updating and validate this data

public class MainMenu {
    public MainMenu(){}

    public void displayMainMenuAdmin() throws FileNotFoundException {
        System.out.println("\t\t\t -MENU- \t\t\t");
        System.out.println("1. Manage children menu");
        System.out.println("2. Manage employee menu");
        System.out.println("3. Manage waiting list menu");
        System.out.println("4. Manage work schedule menu");
        System.out.println("5. Exit");
    }

    // to me it makes sense to only have 2 options for employees, obviously we can change this after
    public void displayMainMenuEmployee() throws FileNotFoundException {
        System.out.println("\t\t\t -MENU- \t\t\t");
        System.out.println("1. View children's information on specific quarter");
        System.out.println("2. View work schedule");
        System.out.println("3. Exit");
    }

    // Log in system




    public void mainMenuAdmin() throws FileNotFoundException {
        String choice = "-1";
        do {
            displayMainMenuAdmin();

            switch (choice) {
                case "1":
                    //printEmptyLines();
                    //ChildrenMenu.childrenMenu();
                    //printEmptyLines();
                    break;

                case "2":
                    //printEmptyLines();
                    //EmployeeMenu.employeeMenu();
                    //printEmptyLines();
                    break;

                case "3":
                    //printEmptyLines();
                    //WaitingListMenu.waitingListMenu();
                    //printEmptyLines();
                    break;

                case "4":
                    //printEmptyLines();
                    //WorkScheduleMenu.workScheduleMenu();
                    //printEmptyLines();
                    break;


                case "5":
                    break;
            }
        } while (!choice.equals("6"));
    }

    public void mainMenuForEmployee() throws FileNotFoundException {
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

    public void printEmptyLines() {
        System.out.println();
        System.out.println();
        System.out.println();
    }
}

