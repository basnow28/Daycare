package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WorkSchedule{

    //  Fields
    private int id;
    private int employeeId;
    private ArrayList<Integer> shiftIds;


    //  Constructors
    public WorkSchedule() {}

    public WorkSchedule(int id, int employeeId) {
        this.id = id;
        this.employeeId = employeeId;
        shiftIds = new ArrayList<Integer>();
    }

    public WorkSchedule(int id, int employeeId, String shiftIds) throws ParseException {
        this(id,employeeId);

        String[] ids = shiftIds.replaceAll("[\\[\\] ]", "").split(",");

        for (int i = 0; i < ids.length; i++) {
            this.shiftIds.add(Integer.parseInt(ids[i]));
        }
    }

    //  Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public ArrayList<Integer> getShiftIds() {
        return shiftIds;
    }

    public void setShiftIds(ArrayList<Integer> shiftIds) {
        this.shiftIds = shiftIds;
    }

    //  Printing
    public String toString() {
        return id + " " + employeeId + " " + shiftIds.toString();
    }
}