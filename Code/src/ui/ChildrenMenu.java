package ui;
import java.io.*;
import java.util.Scanner;

import controller.Controller;
import main.App;

// I remember we talked about having the delete and update in the search option, so they only need to be connected
// same goes for the other menus
public class ChildrenMenu{
    private static Scanner scanner = new Scanner(System.in);
    private static Validation validation = new Validation();

    public ChildrenMenu(){}

    public static void displayChildrenMenu(){
        System.out.println("\t\t\t -MANAGE CHILDREN MENU- \t\t\t");
        System.out.println("1. Display information on children");
        System.out.println("2. Add child from the Waiting list to the quarter");
        System.out.println("3. Search for a child");
        System.out.println("4. Exit");
    }

    public static void displaySearchChildMenu(){
        System.out.println("Do you wish to perform further operations such as: ");
        System.out.println("1. Update the child's information");
        System.out.println("2. Remove the child from the list");
        System.out.println("3. Exit");
    }

    public static void childrenMenu() throws InterruptedException, IOException {
        String choice = "-1";
        do {
            displayChildrenMenu();
            choice = scanner.next();

            switch (choice) {
                case "1":
                    MainMenu.printEmptyLines();
                    //App.getController().displayChild();
                    MainMenu.printEmptyLines();
                    break;

                case "2":
                    MainMenu.printEmptyLines();
                    createChild();
                    MainMenu.printEmptyLines();
                    break;

                case "3":
                    //MainMenu.printEmptyLines();
                    //App.getController().searchChild();
                    //MainMenu.printEmptyLines();
                    break;

                case "4":
                    MainMenu.mainMenuAdmin();
                    break;
            }
        } while (!choice.equals("5"));
    }

    public static void searchChildMenu(){
        String choice = "-1";
        do {
            displaySearchChildMenu();
            choice = scanner.next();

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

    private static void createChild(){
        String firstName = validation.getValidatedName("Child's first name?");
        String lastName = validation.getValidatedName("Child's last name?");
        int age = validation.getValidatedAge("Child's age?");
        String cpr = validation.getValidateCpr("Child's cpr number?");
        int childId = App.getController().createChild(firstName, lastName, age, cpr);
        createParent(childId);
    }

    private static void createParent(int childId){
        String firstName = validation.getValidatedName("Parent's first name?");
        String lastName = validation.getValidatedName("Parent's last name?");
        String cpr = validation.getValidateCpr("Parent's cpr number?");
        String email = validation.getValidatedEmail("Parent's email address?");
        String phoneNumber = validation.getValidatedPhone("Parent's phone number?");
        App.getController().createParent(firstName, lastName, cpr, email, phoneNumber, childId);
        addChildToWaitingList(childId);
    }

    private static void addChildToWaitingList(int childId){
        System.out.println("Do you wish to add the child to one of the waiting lists?");
        System.out.println(App.getController().getWaitingLists().toString());
        System.out.println("Which waiting list do you choose? Write down the id");
        String listId = scanner.next();
        App.getController().addChildToWaitingList(childId, listId);
        System.out.println(App.getController().getWaitingLists());
    }
}