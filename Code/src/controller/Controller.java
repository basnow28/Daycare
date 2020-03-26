package controller;
import fileManagement.FileManagement;
import java.io.IOException;

import main.App;
import model.*;
import ui.Validation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    //  Instantiate Objects
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
        System.out.println("Shifts");

        System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%n","Shift ID", "Start Time",
                "EndTime", "ShiftType", "EmployeeType", "Date");
        System.out.println("--------------------------------------------------------------------------");

        for (int i=0; i < database.getShifts().size(); i++) {
            database.getShifts().get(i).toStringConsole();
        }
    }

    // WORK SCHEDULE //
    public void displayWorkSchedule() {
        System.out.println("Work Schedules");

        System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n","ID","Employee ID", "Shift Ids",
                "Shift ID", "Start Time", "EndTime", "ShiftType", "EmployeeType", "Date");
        System.out.println("-----------------------------------------------------------------------------" +
                "-------------------------------------------------");

        for (int i=0; i < database.getWorkSchedules().size(); i++) {
            database.getWorkSchedules().get(i).toStringConsole();
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
                    System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n","ID","Employee ID", "Shift Ids",
                            "Shift ID", "Start Time", "EndTime", "ShiftType", "EmployeeType", "Date");
                    System.out.println("-----------------------------------------------------------------------------" +
                            "-------------------------------------------------");
                    ok_headline = true;
                }
                ws.toStringConsole();
            }
        }

        System.out.println();

        if(!ok_object)  {
            System.out.println("The Work Schedule hasn't been found");
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

    public boolean deleteWorkSchedule() throws InterruptedException, IOException {
//        int toDelete = whereInsideStaffTempArray();
//        int remember = chooseStaff(toDelete);
        int toDelete = validation.getValidatedInt("Type the <ID> of the Work Schedule you want to delete");

        String firstName = database.getEmployees().get(database.getWorkSchedules().get(toDelete).getEmployeeId()).getFirstName();
        //the first name of the employee that is to be deleted
        String lastName = database.getEmployees().get(database.getWorkSchedules().get(toDelete).getEmployeeId()).getLastName();
        //the last name of the employee that is to be deleted

        System.out.println();
        System.out.println("Are you sure you want to delete <" + firstName + " " + lastName + ">'s Work Schedule ? " +
                "(Type \"Y/YES\" or \"N/NO\")");

        String answer = validation.getValidatedAnswer("");

        if(answer.equalsIgnoreCase("YES") || answer.equalsIgnoreCase("Y"))   {
            fm.deleteFromFile(database.getWorkSchedules().get(toDelete).toString(),"workSchedules.txt",database.getWorkSchedules());
            //Delete from file
            database.getWorkSchedules().remove(toDelete);   //Delete from array
            System.out.println("The Work Schedule has been deleted");
            Thread.sleep(1000);

            return false;
        }  else  {
            return true;
        }
    }

    public ArrayList<Shift> getShift() {
        return database.getShifts();
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