package ui;
import main.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class EmployeeMenu{
    //Console inputs
    private static Scanner console = new Scanner(System.in);
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public EmployeeMenu() {}

    public static void displayEmployeeMenu(){
        System.out.println("\t\t\t -MANAGE EMPLOYEE MENU- \t\t\t");
        System.out.println("[1] Display information on employees");
        System.out.println("[2] Create a new employee ");
        System.out.println("[3] Search for an employee");
        System.out.println("[4] Exit");
    }

    public static void displaySearchEmployeeMenu(){
        System.out.println("Do you wish to perform further operations such as: ");
        System.out.println("[1] Update the employee's information");
        System.out.println("[2] Remove the employee");
        System.out.println("[3] Exit");
    }

    public static void employeeMenu(){
        String choice = "-1";
        do {
            displayEmployeeMenu();
            choice = console.next();
            switch (choice) {
                case "1":
                    MainMenu.printEmptyLines();
                    App.getController().displayEmployees();
                    MainMenu.printEmptyLines();
                    break;

                case "2":
                    MainMenu.printEmptyLines();
                    App.getController().createEmployee();
                    MainMenu.printEmptyLines();
                    break;

                case "3":
                    MainMenu.printEmptyLines();
                    searchEmployeeMenu();
                    MainMenu.printEmptyLines();
                    break;

                case "4":
                    break;

                default:
                    MainMenu.printEmptyLines();
                    System.out.println("Choice must be a value between \"1\" and \"4\".");
                    MainMenu.printEmptyLines();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        } while (!choice.equals("4"));
    }

    public static void searchEmployeeMenu(){
        String choice = "-1";
        boolean repeat = true;
        boolean updated = false;
        boolean found = true;

        do {
            System.out.println("SEARCH EMPLOYEE MENU");
            System.out.println("****************************");


            if(repeat)  {
                System.out.println("Type the <Employee ID>.");
                found = App.getController().searchEmployee();
            }

            if(!found) {
                MainMenu.printEmptyLines();
                continue;
            }

          displaySearchEmployeeMenu();

            try {
                choice = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }


            switch (choice) {
                case "1":
                    updateEmployeeMenu();
                    repeat = false;
                    updated = true;
                    MainMenu.printEmptyLines();
                    break;

                case "2":
                    repeat = App.getController().removeEmployee();
                    updated = true;
                    MainMenu.printEmptyLines();
                    break;

                case "3":
                    break;

                default:
                    MainMenu.printEmptyLines();
                    System.out.println("Choice must be a value between \"1\" and \"3\".");
                    MainMenu.printEmptyLines();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        } while (!choice.equals("3") && !updated);
    }


    public static void updateEmployeeMenu(){
        System.out.println("Type the <ID> of the employee you want to modify ");
        int toUpdate = console.nextInt();

        String choice = "-1";
        do {
            System.out.println();
            System.out.println("Choose what to modify: ");
            System.out.println("[1] First name ");
            System.out.println("[2] Last name");
            System.out.println("[3] CPR");
            System.out.println("[4] Email");
            System.out.println("[5] Phone number");
            System.out.println("[6] Job type");
            System.out.println("[7] Salary");
            System.out.println("[8] Work hours");
            System.out.println("[9] Everything");
            System.out.println("[10] Go back");

            try {
                choice = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch(choice) {
                case "1":

                    MainMenu.printEmptyLines();
                    App.getController().updateEmployee(toUpdate,"firstName");
                    MainMenu.printEmptyLines();
                    break;

                case "2":

                    MainMenu.printEmptyLines();
                    App.getController().updateEmployee(toUpdate,"lastName");
                    MainMenu.printEmptyLines();
                    break;

                case "3":

                    MainMenu.printEmptyLines();
                    App.getController().updateEmployee(toUpdate,"cpr");
                    MainMenu.printEmptyLines();
                    break;

                case "4":

                    MainMenu.printEmptyLines();
                    App.getController().updateEmployee(toUpdate,"email");
                    MainMenu.printEmptyLines();
                    break;

                case "5":

                    MainMenu.printEmptyLines();
                    App.getController().updateEmployee(toUpdate,"phoneNumber");
                    MainMenu.printEmptyLines();
                    break;

                case "6":

                    MainMenu.printEmptyLines();
                    App.getController().updateEmployee(toUpdate,"type");
                    MainMenu.printEmptyLines();
                    break;

                case "7":

                    MainMenu.printEmptyLines();
                    App.getController().updateEmployee(toUpdate,"salary");
                    MainMenu.printEmptyLines();
                    break;

                case "8":

                    MainMenu.printEmptyLines();
                    App.getController().updateEmployee(toUpdate,"workingHours");
                    MainMenu.printEmptyLines();
                    break;

                case "9":

                    MainMenu.printEmptyLines();
                    App.getController().updateEmployee(toUpdate,"everything");
                    MainMenu.printEmptyLines();
                    break;

                case "10":
                    break;

                default:
                    MainMenu.printEmptyLines();
                    System.out.println("Choice must be a value between \"1\" and \"10\".");
                    MainMenu.printEmptyLines();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }  while(!choice.equals("10"));
    }
}