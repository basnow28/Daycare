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
    //  Instantiate Objects
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

    // WORK SCHEDULE //
    public void displayWorkSchedule() {
        for(int i = 0; i < database.getWorkSchedules().size(); i++) {
            System.out.println(database.getWorkSchedules().get(i));
        }
    }

    public void createWorkSchedule() {
        WorkSchedule workSchedule = new WorkSchedule();

        System.out.println("In order to create a new work schedule please enter the following:");

        workSchedule.setId(database.getWorkSchedules().size());   //the next id is the current size of the ArrayList

        workSchedule.setEmployeeId(validation.getValidatedInt("Employee ID"));

        workSchedule.setShiftIds(validation.getValidatedIds("Shift IDs"));

        database.getWorkSchedules().add(workSchedule);

        try {
            fm.addNewLineToFile(workSchedule.toString(), database.getWorkSchedules().size(), "workSchedules.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean searchWorkSchedule() throws InterruptedException {

        String input = scanner.nextLine();
        System.out.println();

        boolean ok_object = false, ok_headline = false;

        for(WorkSchedule ws : database.getWorkSchedules()) {

            if (ws.getEmployeeId() == (Integer.parseInt(input)))  {

                ok_object = true;

                if(!ok_headline)  {
                    System.out.printf("%-5s%-15s%-15s%n","ID","Employee ID", "Shift Ids");
                    System.out.println("----------------------------------------------------");
                    ok_headline = true;
                }
                ws.toStringConsole();
            }
        }

        System.out.println();

        if(!ok_object)  {
            System.out.println("The staff member hasn't been found");
            Thread.sleep(1000);
            return false;
        }
        return true;
    }

    public void updateWorkSchedule(int id, String field) {
        String oldLine = database.getWorkSchedules().get(id).toString();

        switch(field) {
            case "employeeId":
                database.getWorkSchedules().get(id).setEmployeeId(validation.getValidatedInt("Choose a new <Employee ID>"));
                break;

            case "shiftIds":
                database.getWorkSchedules().get(id).setShiftIds(validation.getValidatedIds("Choose the new <Shift IDs>"));
                break;

            case "everything":
                database.getWorkSchedules().get(id).setEmployeeId(validation.getValidatedInt("Choose a new <Employee ID>"));
                database.getWorkSchedules().get(id).setShiftIds(validation.getValidatedIds("Choose the new <Shift IDs>"));
                break;

            default:
                System.out.println("Wrong field");
        }

        String newLine = database.getWorkSchedules().get(id).toString();

        try {
            fm.modifyFile(oldLine,newLine,"workSchedules.txt",database.getWorkSchedules());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public boolean deleteWorkSchedule() {
        return false;
    }


    // EMPLOYEE //
    public void createEmployee() {
        Employee employee = new Employee();
        System.out.println("In order to create a new employee, please enter the following:");

        employee.setId(database.getEmployees().size());

        employee.setFirstName(validation.getValidatedName("Employee's first name:"));

        employee.setLastName(validation.getValidatedName("Employee's last name"));

        employee.setCpr(validation.getValidateCpr("Employee's CPR:"));

        employee.setEmail(validation.getValidatedEmail("Employee's email:"));

        employee.setPhoneNumber(validation.getValidatedPhone("Employee's phone number:"));

       // employee.setType(validation.getValidatedEmployeeType("Employee's job type:"));

        employee.setSalary(validation.getValidatedDouble("Employee's salary:"));

        employee.setWorkingHours(validation.getValidatedInt("Employee's number of work hours:"));

        database.getEmployees().add(employee);
    }

    public void displayEmployees() {
        for(int i = 0; i < database.getEmployees().size(); i++) {
            System.out.println(database.getEmployees().get(i));
        }
    }

    public void updateEmployee(int id, String field) {
        String oldLine = database.getEmployees().get(id).toString();

        switch(field) {
            case "firstName":
                database.getEmployees().get(id).setFirstName(validation.getValidatedName("Choose a new <Employee first name>"));
                break;

            case "lastName":
                database.getEmployees().get(id).setLastName(validation.getValidatedName("Choose a new <Employee last name>"));
                break;

            case "cpr":
                database.getEmployees().get(id).setCpr(validation.getValidateCpr("Choose a new <Employee CPR>"));
                break;

            case "email":
                database.getEmployees().get(id).setEmail(validation.getValidatedEmail("Choose a new <Employee email>"));
                break;

            case "phoneNumber":
                database.getEmployees().get(id).setPhoneNumber(validation.getValidatedPhone("Choose a new <Employee phone number>"));
                break;

            case "type":
              //  database.getEmployees().get(id).setType(validation.getValidatedEmployeeType("Choose a new <Employee job type>"));
                break;

            case "salary":
                database.getEmployees().get(id).setSalary(validation.getValidatedDouble("Choose a new <Employee salary>"));
                break;

            case "workingHours":
                database.getEmployees().get(id).setWorkingHours(validation.getValidatedInt("Choose a new <Employee work hours>"));
                break;

            case "everything":
                database.getEmployees().get(id).setFirstName(validation.getValidatedName("Choose a new <Employee first name>"));
                database.getEmployees().get(id).setLastName(validation.getValidatedName("Choose a new <Employee last name>"));
                database.getEmployees().get(id).setCpr(validation.getValidateCpr("Choose a new <Employee CPR>"));
                database.getEmployees().get(id).setEmail(validation.getValidatedEmail("Choose a new <Employee email>"));
                database.getEmployees().get(id).setPhoneNumber(validation.getValidatedPhone("Choose a new <Employee phone number>"));
                //  database.getEmployees().get(id).setType(validation.getValidatedEmployeeType("Choose a new <Employee job type>"));
                database.getEmployees().get(id).setSalary(validation.getValidatedDouble("Choose a new <Employee salary>"));
                database.getEmployees().get(id).setWorkingHours(validation.getValidatedInt("Choose a new <Employee work hours>"));
                break;

            default:
                System.out.println("Wrong field");
        }
        String newLine = database.getEmployees().get(id).toString();
        try {
            fm.modifyFile(oldLine,newLine,"employees.txt",database.getEmployees());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean removeEmployee() {
        int index = validation.getValidatedInt("Employee's ID you wish to remove:");
        database.getEmployees().remove(index);
        return true;
    }

// try tomorrow after menu changes
    public  Boolean searchEmployee() throws InterruptedException {
        String input = scanner.nextLine();
        System.out.println();

        boolean ok_object = false, ok_headline = false;

        for(Employee e : database.getEmployees()) {

            if (e.getId() == (Integer.parseInt(input)))  {

                ok_object = true;

                if(!ok_headline)  {
                    System.out.printf("%-5s%-15s%-15s%n","ID","Employee ID", "Shift Ids");
                    System.out.println("----------------------------------------------------");
                    ok_headline = true;
                }
                e.toStringConsole();
            }
        }

        System.out.println();

        if(!ok_object)  {
            System.out.println("The staff member hasn't been found");
            Thread.sleep(1000);
            return false;
        }
        return true;
    }

    public void debug() {
        try {
            fm.modifyFile(database.getWorkSchedules().get(0).toString(),"0  3  [0, 1]",
                    "workSchedules.txt",database.getWorkSchedules());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   //it was a hell of an adventure, testing purposes, keep it here for now pls
}