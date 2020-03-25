package controller;
import fileManagement.FileManagement;
import model.*;
import ui.Validation;

import java.util.*;

import java.io.IOException;


public class Controller {
    private static Database database;
    private static FileManagement fm;
    private static Scanner scanner = new Scanner(System.in);
    private static Validation validation = new Validation();

    public Controller() {
        database = new Database();
        fm = new FileManagement();

        try {
            fm.inputFromFile(database.getChildren(), database.getParents(), database.getEmployees(), "children.txt", "parents.txt",
                    "employees.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int createChild(String firstName, String lastName, int age, String cpr){
        int id = database.getChildren().size();
        database.getChildren().add(new Child(id, firstName, lastName, age, cpr));
        return id;
    }

    public void createParent(String firstName, String lastName, String cpr, String email, String phoneNumber, int childId){
        int id = database.getParents().size();
        database.getParents().add(new Parent(id, firstName, lastName,cpr, email, phoneNumber, childId ));
    }

    public int createEmployee(String firstName, String lastName, String cpr, String email, String phoneNumber, EmployeeType type, double salary, int workingHours) {
        int id = database.getEmployees().size();
        database.getEmployees().add(new Employee(id, firstName, lastName, cpr, email, phoneNumber, type, salary,workingHours));
        return id;
    }

    public void updateEmployee() {
        int choice = -1;
        System.out.println("Enter the employee ID of the employee you wish to update:");
        choice = scanner.nextInt();
        Employee e = new Employee();
        System.out.println("Enter the new first name of the employee:");
        String firstName = validation.getValidatedName(scanner.next());
        e.setFirstName(firstName);
        System.out.println("Enter the new last name o the employee:");
        String lastName = validation.getValidatedName(scanner.next());
        e.setLastName(lastName);
        System.out.println("Enter the new CPR of the employee:");
        String cpr = validation.getValidateCpr(scanner.next());
        e.setCpr(cpr);
        System.out.println("Enter the new email of the employee:");
        String email = validation.getValidatedEmail(scanner.next());
        e.setEmail(email);
        System.out.println("Enter the new phone number of the employee:");
        String phoneNumber = validation.getValidatedPhone(scanner.next());
        e.setPhoneNumber(phoneNumber);
        System.out.println("Enter the new type of employee:");
        EmployeeType type = new EmployeeType();
        e.setType(type);
        System.out.println("Enter the new salary of the employee:");
        double salary = scanner.nextDouble();
        e.setSalary(salary);
        System.out.println("Enter the new number of work hours of the employee:");
        int workingHours = scanner.nextInt();
        e.setWorkingHours(workingHours);
    }

    public void removeEmployee(ArrayList<Employee> employees, int index) {
        int choice = -1;
        System.out.println("Enter the employee ID of the employee you wish to remove:");
        choice = scanner.nextInt();
        employees.remove(choice);
    }
}