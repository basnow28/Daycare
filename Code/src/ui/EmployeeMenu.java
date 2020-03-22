package ui;
import java.io.*;

public class EmployeeMenu{

    public EmployeeMenu() {}

    public static void displayEmployeeMenu() throws FileNotFoundException {
        System.out.println("\t\t\t -MANAGE EMPLOYEE MENU- \t\t\t");
        System.out.println("1. Display information on employees");
        System.out.println("2. Add a new employee ");
        System.out.println("3. Search for an employee");
        System.out.println("4. Exit");
    }

    public static void displaySearchEmployeeMenu() throws FileNotFoundException {
        System.out.println("Do you wish to perform further operations such as: ");
        System.out.println("1. Update the employee's information");
        System.out.println("2. Remove the employee");
        System.out.println("3. Exit");
    }

    public static void employeeMenu() throws FileNotFoundException {
        String choice = "-1";
        do {
            displayEmployeeMenu();

            switch (choice) {
                case "1":
                    //MainMenu.printEmptyLines();
                    //App.getController().displayEmployee();
                    //MainMenu.printEmptyLines();
                    break;

                case "2":
                    //MainMenu.printEmptyLines();
                    //App.getController().addEmployee();
                    //MainMenu.printEmptyLines();
                    break;

                case "3":
                    //MainMenu.printEmptyLines();
                    //App.getController().searchEmployee();
                    //MainMenu.printEmptyLines();
                    break;

                case "4":
                    break;
            }
        } while (!choice.equals("5"));
    }

    public static void searchEmployeeMenu() throws FileNotFoundException {
        String choice = "-1";
        do {
            displaySearchEmployeeMenu();

            switch (choice) {
                case "1":
                    //MainMenu.printEmptyLines();
                    //App.getController().updateEmployee();
                    //MainMenu.printEmptyLines();
                    break;

                case "2":
                    //MainMenu.printEmptyLines();
                    //App.getController().removeEmployee();
                    //MainMenu.printEmptyLines();
                    break;

                case "3":
                    break;
            }
        } while (!choice.equals("4"));
    }
}