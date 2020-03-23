package controller;
import fileManagement.FileManagement;
import model.Child;
import model.Database;
import model.Parent;

import java.io.IOException;


public class Controller {
    private static Database database;
    private static FileManagement fm;

    public Controller() {
        database = new Database();
        fm = new FileManagement();

        try {
            fm.inputFromFile(database.getChildren(), database.getParents(), database.getEmployees(), "children.txt", "parents.txt",
                    "employees.txt");
        } catch (IOException e) {
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
        database.getParents().add(new Parent(id, firstName, lastName,cpr, email, phoneNumber, childId ));
    }
}