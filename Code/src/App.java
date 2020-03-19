import controller.Controller;
import ui.MainMenu;

public class App {
    //The controller is created in the App.java, so there is ONE object instance in the whole project
    // This will prevent loading data few times during the execution
    // When creating the Controller, inside of it's constructor, the static Database instance is created
    //In the database constructor the data is loaded.
    //If any Menu want to use the controller they can refer to it as:
    // App.getController()

    private static Controller controller;

    public static void main(String args[]){
        controller = new Controller();
        new MainMenu();
    }

    public static Controller getController(){
        return controller;
    }
}
