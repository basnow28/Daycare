import controller.Controller;
import fileManagement.FileManagement;
import model.Child;
import model.Employee;
import model.Parent;
import ui.MainMenu;

import java.io.IOException;
import java.util.ArrayList;

public class App {
    //The controller is created in the App.java, so there is ONE object instance in the whole project
    // This will prevent loading data few times during the execution
    // When creating the Controller, inside of it's constructor, the static Database instance is created
    //In the database constructor the data is loaded.
    //If any Menu want to use the controller they can refer to it as:
    // App.getController()

    private static Controller controller;

    public static void main(String args[]) throws IOException {
        controller = new Controller();
        new MainMenu();

        //  ----just for testing purposes---
        ArrayList<Child> children = new ArrayList<>();
        ArrayList<Parent> parents = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        FileManagement fm =  new FileManagement();

        fm.inputFromFile(children,parents, employees,"children.txt","parents.txt",
                "employees.txt");       //just call this for the input from file, it subsequently calls
                                                        //specific methods afterwards
        for (Parent parent: parents) {
            System.out.println(parent.toString());
        }
        //  -----this block won't stay here----
    }

    public static Controller getController(){
        return controller;
    }
}
