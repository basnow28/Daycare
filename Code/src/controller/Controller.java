package controller;
import fileManagement.FileManagement;
import model.*;
import ui.Validation;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Controller {
    //  Fields
    private static Database database;
    private static FileManagement fm;

    //  Instantiate Validation Object
    Validation validation = new Validation();

    //  Constructors
    public Controller() {
        database = new Database();
        fm = new FileManagement();

        try {
            fm.inputFromFile(database.getChildren(), database.getParents(), database.getEmployees(), database.getShifts(),
                    database.getWorkSchedules(), "children.txt", "parents.txt",
                    "employees.txt", "shifts.txt", "workSchedules.txt");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public int createChild(String firstName, String lastName, int age, String cpr){
        int id = database.getChildren().size();
        database.getChildren().add(new Child(id, firstName, lastName, age, cpr));
        return id;
    }

    public void createParent(String firstName, String lastName, String cpr, String email, String phoneNumber, int childId){
        int id = database.getParents().size();
        database.getParents().add(new Parent(id, firstName, lastName, cpr, email, phoneNumber, childId ));
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
}