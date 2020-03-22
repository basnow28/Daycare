package ui;
import java.io.*;

// I remember we talked about having the delete and update in the search option, so they only need to be connected
// same goes for the other menus
public class ChildrenMenu{

    public ChildrenMenu(){}

    public static void displayChildrenMenu() throws FileNotFoundException {
        System.out.println("\t\t\t -MANAGE CHILDREN MENU- \t\t\t");
        System.out.println("1. Display information on children");
        System.out.println("2. Add child from the Waiting list to the quarter");
        System.out.println("3. Search for a child");
        System.out.println("4. Exit");
    }

    public static void displaySearchChildMenu() throws FileNotFoundException {
        System.out.println("Do you wish to perform further operations such as: ");
        System.out.println("1. Update the child's information");
        System.out.println("2. Remove the child from the quarter");
        System.out.println("3. Exit");
    }

    public static void childrenMenu() throws FileNotFoundException {
        String choice = "-1";
        do {
            displayChildrenMenu();

            switch (choice) {
                case "1":
                    //MainMenu.printEmptyLines();
                    //App.getController().displayChild();
                    //MainMenu.printEmptyLines();
                    break;

                case "2":
                    //MainMenu.printEmptyLines();
                    //App.getController().addChildToQuarter()
                    //MainMenu.printEmptyLines();
                    break;

                case "3":
                    //MainMenu.printEmptyLines();
                    //App.getController().searchChild();
                    //MainMenu.printEmptyLines();
                    break;

                case "4":
                    break;
            }
        } while (!choice.equals("5"));
    }

    public static void searchChildMenu() throws FileNotFoundException {
        String choice = "-1";
        do {
            displaySearchChildMenu();

            switch (choice) {
                case "1":
                    //MainMenu.printEmptyLines();
                    //App.getController().updateChild();
                    //MainMenu.printEmptyLines();
                    break;

                case "2":
                    //MainMenu.printEmptyLines();
                    //App.getController().removeChild();
                    //MainMenu.printEmptyLines();
                    break;

                case "3":
                    break;
            }
        } while (!choice.equals("4"));
    }
}