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

    public void displayChildren() {
        for(int i = 0; i < database.getChildren().size(); i++) {
            System.out.println(database.getChildren().get(i));
        }
    }

    public Boolean searchChildren(){

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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
                database.getChildren().get(id).setCpr(validation.getValidatedCpr("Choose new CPR"));
                break;
            case "parentId":
                database.getChildren().get(id).setParentId(validation.getValidatedInt("Choose new Parent ID"));
                break;
            case "everything":
                database.getChildren().get(id).setFirstName(validation.getValidatedName("Choose a new First Name"));
                database.getChildren().get(id).setLastName(validation.getValidatedName("Choose a new First Name"));
                database.getChildren().get(id).setAge(validation.getValidatedAge("Choose new Age"));
                database.getChildren().get(id).setCpr(validation.getValidatedCpr("Choose new CPR"));
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
        String oldLineParent = database.getParents().get(database.getChildren().get(id).getParentId()).toString();
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
            fm.addNewLineToFile(database.getChildren().get(childId).toString(), database.getChildren().size(), CHILDREN_FILE);
            fm.addNewLineToFile(parent.toString(), database.getParents().size(), PARENTS_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayParents() {
        for(int i = 0; i < database.getParents().size(); i++) {
            System.out.println(database.getParents().get(i));
        }
    }

    public Boolean searchParents(){

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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
                database.getParents().get(id).setLastName(validation.getValidatedName("Choose a new Last Name"));
                break;
            case "cpr":
                database.getParents().get(id).setCpr(validation.getValidatedCpr("Choose new CPR"));
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
                database.getParents().get(id).setLastName(validation.getValidatedName("Choose a new Last Name"));
                database.getParents().get(id).setCpr(validation.getValidatedCpr("Choose new CPR"));
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
        String oldLine = database.getList(listId).toString();
        database.getList(listId).addChild(childId);
        String newLine = database.getList(listId).toString();
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
            fm.addNewLineToFile(waitingList.toString(), database.getWaitingLists().size(), WAITING_LISTS_FILE);
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
            //headline  -  it'll be displayed for every iteration in this case, because you display multiple details
                        //on multiple lines, otherwise it gets a bit lost
            System.out.printf("%-5s%-15s%-10s%-15s%-15s%n","ID", "Quarter", "Year", "Capacity", "Children IDs");
            System.out.println("--------------------------------------------------------------------");
            //I added it, but it's up to you whether you keep it or not
            wl.toStringConsole();
            System.out.println("Children: ");
            displayChildrenFromList(wl.getId());
            System.out.println();
            System.out.println();   //another space is a bit needed, for clarity
        }
        return waitingLists;
    }

    public void updateWaitingList(int id, String field) {
        String oldLine = database.getWaitingLists().get(id).toString();
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
        newLine = database.getWaitingLists().get(id).toString();
        System.out.println(newLine);
        try {
            fm.modifyFile(oldLine, newLine, WAITING_LISTS_FILE, database.getWaitingLists());
            System.out.println("Updated successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteChildFromList(int listId, int id) {
        String oldLine = database.getWaitingLists().get(listId).toString();
        database.getWaitingLists().get(listId).deleteChild(id);
        String newLine = database.getWaitingLists().get(listId).toString();

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

    public Boolean searchWorkSchedule(){

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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public boolean deleteWorkSchedule(){
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
            try {
                fm.deleteFromFile(database.getWorkSchedules().get(toDelete).toString(),"workSchedules.txt",database.getWorkSchedules());
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Delete from file
            database.getWorkSchedules().remove(toDelete);   //Delete from array
            System.out.println("The Work Schedule has been deleted");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return false;
        }  else  {
            return true;
        }
    }

    public ArrayList<Shift> getShift() {
        return database.getShifts();
    }


    // EMPLOYEE //
    public void createEmployee() {
        Employee employee = new Employee();
        System.out.println("In order to create a new employee, please enter the following:");

        employee.setId(database.getEmployees().size());

        employee.setFirstName(validation.getValidatedName("Employee's first name:"));

        employee.setLastName(validation.getValidatedName("Employee's last name"));

        employee.setCpr(validation.getValidatedCpr("Employee's CPR:"));

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
                database.getEmployees().get(id).setCpr(validation.getValidatedCpr("Choose a new <Employee CPR>"));
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
                database.getEmployees().get(id).setCpr(validation.getValidatedCpr("Choose a new <Employee CPR>"));
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
    public  Boolean searchEmployee() {
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
                e.toString();
            }
        }

        System.out.println();

        if(!ok_object)  {
            System.out.println("The staff member hasn't been found");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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