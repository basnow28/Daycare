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

    public void displayChildren() {
        for(int i = 0; i < database.getChildren().size(); i++) {
            System.out.println(database.getChildren().get(i));
        }
    }

    public Boolean searchChildren() throws InterruptedException {

        String input = scanner.nextLine();
        System.out.println();

        boolean ok_object = false, ok_headline = false;

        for(Child ch : database.getChildren()) {

            if (ch.getId() == (Integer.parseInt(input)))  {

                ok_object = true;

                if(!ok_headline)  {
                    System.out.printf("%-5s%-15s%-15s%-5s%-15s%-15s%n","ID","First Name", "Last Name", "Age", "CPR", "Parent");
                    System.out.println("----------------------------------------------------------------------------------");
                    ok_headline = true;
                }
                ch.toStringConsoleFormat();
            }
        }

        System.out.println();

        if(!ok_object)  {
            System.out.println("The child hasn't been found");
            Thread.sleep(1000);
            return false;
        }
        return true;
    }

    public void updateChild(int id, String field) {

        String oldLine = database.getChildren().get(id).toString();

        switch(field) {
            case "firstName":
                database.getChildren().get(id).setFirstName(validation.getValidatedName("Choose a new First Name"));
                break;

            case "lastName":
                database.getChildren().get(id).setLastName(validation.getValidatedName("Choose a new First Name"));
                break;
            case "age":
                database.getChildren().get(id).setAge(validation.getValidatedAge("Choose new Age"));
                break;
            case "cpr":
                database.getChildren().get(id).setCpr(validation.getValidateCpr("Choose new CPR"));
                break;
            case "parentId":
                database.getChildren().get(id).setParentId(validation.getValidatedInt("Choose new Parent ID"));
                break;
            case "everything":
                database.getChildren().get(id).setFirstName(validation.getValidatedName("Choose a new First Name"));
                database.getChildren().get(id).setLastName(validation.getValidatedName("Choose a new First Name"));
                database.getChildren().get(id).setAge(validation.getValidatedAge("Choose new Age"));
                database.getChildren().get(id).setCpr(validation.getValidateCpr("Choose new CPR"));
                database.getChildren().get(id).setParentId(validation.getValidatedInt("Choose new Parent ID"));
                break;

            default:
                System.out.println("Wrong field");
        }

        String newLine = database.getChildren().get(id).toString();

        try {
            fm.modifyFile(oldLine,newLine,"children.txt",database.getChildren());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteChild(int id){
        String oldLineChild = database.getChildren().get(id).toString();
        String oldLineParent = database.getParents().get(database.getChildren().get(id).getParentId()).toStringConsole();
        database.getChildren().remove(id);
        database.getParents().get(database.getChildren().get(id).getParentId());
        System.out.println("Child and Parent Information:");
        System.out.println(oldLineChild);
        System.out.println(oldLineParent);
        System.out.println("Child and Parent was removed");

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

    public void displayParents() {
        for(int i = 0; i < database.getParents().size(); i++) {
            System.out.println(database.getParents().get(i));
        }
    }

    public Boolean searchParents() throws InterruptedException {

        String input = scanner.nextLine();
        System.out.println();

        boolean ok_object = false, ok_headline = false;

        for(Parent p : database.getParents()) {

            if (p.getId() == (Integer.parseInt(input)))  {

                ok_object = true;

                if(!ok_headline)  {
                    System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-5s%n", "ID",  "First Name", "Last Name", "CPR", "Email", "Phone Number", "Child ID");
                    System.out.println("----------------------------------------------------");
                    ok_headline = true;
                }
                System.out.printf("%-5d%-15s%-15s%-15s%-15s%-15s%-5d%n", p.getId(), p.getFirstName(), p.getLastName(), p.getCpr(), p.getEmail(), p.getPhoneNumber(), p.getChildId());
            }
        }

        System.out.println();

        if(!ok_object)  {
            System.out.println("The child hasn't been found");
            Thread.sleep(1000);
            return false;
        }
        return true;
    }

    public void updateParent(int id, String field) {

        String oldLine = database.getParents().get(id).toString();

        switch(field) {
            case "firstName":
                database.getParents().get(id).setFirstName(validation.getValidatedName("Choose a new First Name"));
                break;

            case "lastName":
                database.getParents().get(id).setLastName(validation.getValidatedName("Choose a new First Name"));
                break;
            case "cpr":
                database.getParents().get(id).setCpr(validation.getValidateCpr("Choose new CPR"));
                break;
            case "email":
                database.getParents().get(id).setEmail(validation.getValidatedEmail("Choose new Email"));
                break;
            case "phoneNumber":
                database.getParents().get(id).setPhoneNumber(validation.getValidatedPhone("Choose new Phone Number"));
                break;
            case "childId":
                database.getParents().get(id).setChildId(validation.getValidatedInt("Choose new Child ID"));
                break;
            case "everything":
                database.getParents().get(id).setLastName(validation.getValidatedName("Choose a new First Name"));
                database.getParents().get(id).setLastName(validation.getValidatedName("Choose a new First Name"));
                database.getParents().get(id).setCpr(validation.getValidateCpr("Choose new CPR"));
                database.getParents().get(id).setEmail(validation.getValidatedEmail("Choose new Email"));
                database.getParents().get(id).setPhoneNumber(validation.getValidatedPhone("Choose new Phone Number"));
                database.getParents().get(id).setChildId(validation.getValidatedInt("Choose new Child ID"));
                break;

            default:
                System.out.println("Wrong field");
        }

        String newLine = database.getParents().get(id).toString();

        try {
            fm.modifyFile(oldLine,newLine,"parents.txt",database.getParents());
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