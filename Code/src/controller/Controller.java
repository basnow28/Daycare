package controller;
import fileManagement.FileManagement;
import java.io.IOException;

import main.App;
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
    private final String CHILDREN_FILE, PARENTS_FILE, EMPLOYEES_FILE, SHIFTS_FILE, WORK_SCHEDULES_FILE, WAITING_LISTS_FILE;

    {
        CHILDREN_FILE = "children.txt";
        PARENTS_FILE = "parents.txt";
        EMPLOYEES_FILE = "employees.txt";
        SHIFTS_FILE = "shifts.txt";
        WORK_SCHEDULES_FILE = "workSchedules.txt";
        WAITING_LISTS_FILE = "waitingLists.txt";
    }

    //  Constructors
    public Controller() {
        database = new Database();
        fm = new FileManagement();

        try {
            fm.inputFromFile(database.getChildren(), database.getParents(), database.getEmployees(), database.getShifts(),
                    database.getWorkSchedules(),database.getWaitingLists(), CHILDREN_FILE, PARENTS_FILE,
                    EMPLOYEES_FILE, SHIFTS_FILE, WORK_SCHEDULES_FILE, WAITING_LISTS_FILE);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        System.out.println(database.getList(1).toStringConsole());
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
            fm.addNewLineToFile(database.getChildren().get(childId).toStringConsole(), database.getChildren().size(), CHILDREN_FILE);
            fm.addNewLineToFile(parent.toStringConsole(), database.getParents().size(), PARENTS_FILE);
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

    public void displayChildrenFromList(int id){
        if(getChildrenFromList(id).size() == 0 ){
            System.out.println("List is empty");
        }else{
        for(Child child : getChildrenFromList(id)){
            System.out.println(child.toString());
        }}
    }

    public ArrayList<WaitingList> getWaitingLists(){
        return database.getWaitingLists();
    }

    public void addChildToWaitingList(int childId, int listId) {
        String oldLine = database.getList(listId).toStringConsole();
        database.getList(listId).addChild(childId);
        String newLine = database.getList(listId).toStringConsole();
        System.out.println(oldLine);
        System.out.println(newLine);
        try {
            fm.modifyFile(oldLine, newLine, WAITING_LISTS_FILE, database.getWaitingLists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createNewWaitingList(Quarter quarter, String year, int capacity) {
        WaitingList waitingList = new WaitingList(database.getWaitingLists().size(), quarter, year, capacity);
        database.getWaitingLists().add(waitingList);
        try {
            fm.addNewLineToFile(waitingList.toStringConsole(), database.getWaitingLists().size(), WAITING_LISTS_FILE);
            System.out.println("Added successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<WaitingList> getWaitingList(String key) {
        ArrayList<WaitingList> waitingLists = new ArrayList<WaitingList>();

        for(WaitingList waitingList : database.getWaitingLists()){
            if(key.contains(waitingList.getYear()) || key.contains(waitingList.getQuarter().toString().toLowerCase()) ||
                    key.contains(waitingList.getQuarter().toString().toUpperCase()) || key.contains(Integer.toString(waitingList.getCapacity()))){
                waitingLists.add(waitingList);
            }
        }
        for(WaitingList wl : waitingLists){
            System.out.println(wl.toString());
            System.out.println("Children: ");
            displayChildrenFromList(wl.getId());
            System.out.println();
        }
        return waitingLists;
    }

    public void updateWaitingList(int id, String field) {
        String oldLine = database.getWaitingLists().get(id).toStringConsole();
        String newLine;
        System.out.println(oldLine);
        switch (field.toLowerCase()) {
            case "year":
                String newValue = validation.getValidatedYear("Write new " + field);
                database.getWaitingLists().get(id).setYear(newValue);
                break;
            case "quarter":
                Quarter newQuarter = validation.getValidatedQuarter("Write new " + field);
                database.getWaitingLists().get(id).setQuarter(newQuarter);
                break;
            case "capacity":
                int newCapacity = validation.getValidatedInt("Write new " + field);
                database.getWaitingLists().get(id).setCapacity(newCapacity);
                break;
            default:
                break;
        }
        newLine = database.getWaitingLists().get(id).toStringConsole();
        System.out.println(newLine);
        try {
            fm.modifyFile(oldLine, newLine, WAITING_LISTS_FILE, database.getWaitingLists());
            System.out.println("Updated successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteChildFromList(int listId, int id) {
        String oldLine = database.getWaitingLists().get(listId).toStringConsole();
        database.getWaitingLists().get(listId).deleteChild(id);
        String newLine = database.getWaitingLists().get(listId).toStringConsole();

        try {
            fm.modifyFile(oldLine, newLine, WAITING_LISTS_FILE, database.getWaitingLists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String findChildOnAList(String key){
        String children = "";
        for(Child child : database.getChildren()){
            if(child.toString().contains(key))
            for(WaitingList wl : database.getWaitingLists()){
                for(int id : wl.getChildrenIds()){
                    if(child.getId() == id){
                        children += child.toString() + "\t| LIST:" +wl.getId() + "  " + wl.getYear() + " " + wl.getQuarter() + "\n";
                    }
                }
            }
        }
        return children;
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
        String cpr = validation.getValidatedCpr(scanner.next());
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