package model;

public class Shift{
    private String startTime;
    private String endTime;
    private ShiftType shiftType;
    private EmployeeType employeeType;
    private String date;

    public Shift(String startTime, String endTime, ShiftType shiftType, EmployeeType employeeType, String date) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftType = shiftType;
        this.employeeType = employeeType;
        this.date = date;
    }
}