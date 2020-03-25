package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Shift{

    //  Fields
    private String startTime;
    private String endTime;
    private ShiftType shiftType;
    private EmployeeType employeeType;
    private Date date;        //This is the format: "dd-MMMM-yyyy" --> "25-March-2020"

    //  Constructors
    public Shift() {}

    public Shift(String startTime, String endTime, ShiftType shiftType, EmployeeType employeeType, Date date) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftType = shiftType;
        this.employeeType = employeeType;
        this.date = date;
    }

    //  Getters & Setters
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ShiftType getShiftType() {
        return shiftType;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //  Printing

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
        String dateString = formatter.format(date);
        return String.join(" ", startTime, endTime, shiftType.name(), employeeType.name(), dateString);
    }   //only used to print back to file

    public void toStringConsole() {
        //System.out.printf("");  -  formatted printing
    }   //used to print to console
}