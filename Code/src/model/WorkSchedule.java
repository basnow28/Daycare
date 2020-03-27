package model;

import controller.Controller;
import main.App;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WorkSchedule {

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

        String[] ids = shiftIds.replaceAll("\\[|\\]| ", "").split(",");

        int length = ids.length;

        if(ids.length == 1) {       //java shit stuff, even if the string is empty, the length is still 1
            try {
                Integer.parseInt(ids[0]);
            } catch (NumberFormatException e) {
                length = 0;     //hacks ^^
            }
        }

        if(length != 0) {
            for (int i = 0; i < ids.length; i++) {
                this.shiftIds.add(Integer.parseInt(ids[i]));
            }
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
        return id + "  " + employeeId + "  " + shiftIds.toString();
    }

    public void toStringConsole() {

        System.out.printf("%-5d%-15d%-15s",id, employeeId, shiftIds);
        for(int i = 0; i < shiftIds.size(); i++) {
            App.getController().getShift().get(shiftIds.get(i)).toStringConsole();
            System.out.printf("%-35s"," ");
        }
        System.out.println();
    }
}