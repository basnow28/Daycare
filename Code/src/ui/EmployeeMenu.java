package ui;
import main.App;
import model.EmployeeType;

import java.util.*;


public class EmployeeMenu{

    private static Validation validation = new Validation();
    private static Scanner scanner = new Scanner(System.in);

    public EmployeeMenu() {}

    public static void displayEmployeeMenu(){
        System.out.println("\t\t\t -MANAGE EMPLOYEE MENU- \t\t\t");
        System.out.println("1. Display information on employees");
        System.out.println("2. Add a new employee ");
        System.out.println("3. Search for an employee");
        System.out.println("4. Exit");
    }

    public static void displaySearchEmployeeMenu(){
        System.out.println("Do you wish to perform further operations such as: ");
        System.out.println("1. Update the employee's information");
        System.out.println("2. Remove the employee");
        System.out.println("3. Exit");
    }

    public static void employeeMenu(){
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

    public static void searchEmployeeMenu(){
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

    private static void createEmployee(int employeeId){
        String firstName = validation.getValidatedName("Employee's first name?");
        String lastName = validation.getValidatedName("Employee's last name?");
        String cpr = validation.getValidateCpr("Employee's cpr number?");
        String email = validation.getValidatedEmail("Employee's email?");
        String phoneNumber = validation.getValidatedPhone("Employee's phone number?");
        String type = validation.getValidatedEmployeeType("");
        System.out.println("Employee's salary?");
        double salary = scanner.nextInt();
        System.out.println("Employee's working hours?");
        int workingHours = scanner.nextInt();
        employeeId = App.getController().createEmployee(firstName, lastName, cpr, email, phoneNumber, EmployeeType.valueOf(type), salary, workingHours);
        createEmployee(employeeId);
    }
}