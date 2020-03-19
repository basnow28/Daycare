package model;

import java.util.ArrayList;

public class Database{
    private DBConnection dbC;
    private ArrayList<Child> children;
    private ArrayList<WorkSchedule> workSchedules;
    private ArrayList<Employee> employees;

    public Database(){
        dbC = new DBConnection();
        children = new ArrayList<Child>();
        workSchedules = new ArrayList<WorkSchedule>();
        employees = new ArrayList<Employee>();

        // the loading of the data has to be in the constructor
        //all the loading needs to be managed by the DBConnection
        //dbC.uploadChildren(children , filename)
    }
}