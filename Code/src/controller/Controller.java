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
        Child child = new Child(id, firstName, lastName, age, cpr);
        database.getChildren().add(child);
        return id;
    }

    public void createParent(String firstName, String lastName, String cpr, String email, String phoneNumber, int childId){
        int id = database.getParents().size();
        database.getChildren().get(childId).setParentId(id);
        Parent parent = new Parent(id, firstName, lastName,cpr, email, phoneNumber, childId );
        database.getParents().add(parent);
        try {
            fm.addNewLineToFile(database.getChildren().get(childId).toStringConsole(), database.getChildren().size(), "children.txt");
            fm.addNewLineToFile(parent.toStringConsole(), database.getParents().size(), "parents.txt");
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

    public ArrayList<WaitingList> getWaitingLists(){
        return database.getWaitingLists();
    }

    public void addChildToWaitingList(int childId, String listId) {
        database.getList(Integer.parseInt(listId)).addChild(childId);
    }
}