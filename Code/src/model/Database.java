package model;

import java.util.ArrayList;

public class Database {
    private DBConnection dbC;
    private ArrayList<Child> children;
    private ArrayList<WorkSchedule> workSchedules;
    private ArrayList<Employee> employees;
    private ArrayList<Parent> parents;
    private ArrayList<WaitingList> waitingLists;
    private ArrayList<Shift> shifts;

    public Database(){
        dbC = new DBConnection();
        children = new ArrayList<Child>();
        workSchedules = new ArrayList<WorkSchedule>();
        employees = new ArrayList<Employee>();
        parents = new ArrayList<Parent>();
        waitingLists = new ArrayList<WaitingList>();
        shifts = new ArrayList<Shift>();
        // the loading of the data has to be in the constructor
        //all the loading needs to be managed by the DBConnection
        //dbC.uploadChildren(children , filename)
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public ArrayList<WorkSchedule> getWorkSchedules() {
        return workSchedules;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Parent> getParents() {
        return parents;
    }

    public ArrayList<WaitingList> getWaitingLists() {
        return waitingLists;
    }

    public WaitingList getList(int id) {
        return waitingLists.get(id);
    }

    public ArrayList<Shift> getShifts() {
        return shifts;
    }
}