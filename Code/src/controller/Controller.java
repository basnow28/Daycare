package controller;
import fileManagement.FileManagement;
import model.Child;
import model.Database;
import model.Parent;
import model.WaitingList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Controller {
    private static Database database;
    private static FileManagement fm;

    public Controller() {
        database = new Database();
        fm = new FileManagement();

        try {
            fm.inputFromFile(database.getChildren(), database.getParents(), database.getEmployees(), database.getWaitingLists(),"children.txt", "parents.txt",
                    "employees.txt", "waitingLists.txt");
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

    public WaitingList getCurrentList(){
        return database.getList(0);
    }

    public ArrayList<Child> getChildrenFromList(int id){
        ArrayList<Child> children = new ArrayList<Child>();

        for(int i=0; i<database.getList(id).getChildrenIds().size(); i++) {
            database.getChildren().forEach(child -> {
                if(child.getId() == id){
                    children.add(child);
                }
            });
        }

        return children;
    }

}