package controller;
import fileManagement.FileManagement;
import java.io.IOException;
import model.*;
import ui.Validation;
import java.util.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Controller {
    //  Fields
    private static Database database;
    private static FileManagement fm;
    private static Scanner scanner = new Scanner(System.in);
    private static Validation validation = new Validation();
    //  Constructors
    public Controller() {
        database = new Database();
        fm = new FileManagement();

        try {
            fm.inputFromFile(database.getChildren(), database.getParents(), database.getEmployees(), database.getShifts(),
                    database.getWorkSchedules(),database.getWaitingLists(), "children.txt", "parents.txt",
                    "employees.txt", "shifts.txt", "workSchedules.txt", "waitingLists.txt");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public int createChild(String firstName, String lastName, int age, String cpr){
        int id = database.getChildren().size();
        Child child = new Child(id, firstName, lastName, age, cpr);
        database.getChildren().add(child);
        return id;
    }

    public void createParent(String firstName, String lastName, String cpr, String email, String phoneNumber, int childId){
        int id = database.getParents().size();
        database.getChildren().get(childId).setParentId(id);
        Parent parent = new Parent(id, firstName, lastName,cpr, email, phoneNumber, childId );
        database.getParents().add(parent);
        try {
            fm.addNewLineToFile(database.getChildren().get(childId).toStringConsole(), database.getChildren().size(), "children.txt");
            fm.addNewLineToFile(parent.toStringConsole(), database.getParents().size(), "parents.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public WaitingList getCurrentList(){
        return database.getList(0);
    }

    public ArrayList<Child> getChildrenFromList(int id){
        ArrayList<Child> children = new ArrayList<Child>();

        for(int i=0; i<database.getChildren().size(); i++) {
            Child child = database.getChildren().get(i);
            for(int childId : database.getList(id).getChildrenIds()){
                if(child.getId() == childId) {
                    children.add(child);
                }
            }
        }

        return children;
    }

    public ArrayList<WaitingList> getWaitingLists(){
        return database.getWaitingLists();
    }

    public void addChildToWaitingList(int childId, String listId) {
        database.getList(Integer.parseInt(listId)).addChild(childId);
        //database.getParents().add(new Parent(id, firstName, lastName, cpr, email, phoneNumber, childId ));
    }

    public void displayShifts() {
        for(int i = 0; i < database.getShifts().size(); i++) {
            System.out.println(database.getShifts().get(i));
        }
    }

    public void displayWorkSchedule() {
        for(int i = 0; i < database.getWorkSchedules().size(); i++) {
            System.out.println(database.getWorkSchedules().get(i));
        }
    }

    public void createWorkSchedule() {
        WorkSchedule workSchedule = new WorkSchedule();

        System.out.println("In order to create a new work schedule please enter the following:");

        workSchedule.setId(database.getWorkSchedules().size());

        workSchedule.setEmployeeId(validation.getValidatedInt("Employee ID"));

        workSchedule.setShiftIds(validation.getValidatedIds("Shift IDs"));

        database.getWorkSchedules().add(workSchedule);
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
        //EmployeeType type = new EmployeeType();
        //e.setType(type);
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