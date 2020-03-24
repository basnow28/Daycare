package ui;
import main.App;

import java.io.*;

public class ListMenu {
    public ListMenu(){}

    public static void displayListMenu(){
        System.out.println("\t\t\t -MANAGE LISTS MENU- \t\t\t");
        System.out.println("1. Display current children's list");
        System.out.println("2. Add a child's information to the waiting list ");
        System.out.println("3. Search for a child on the waiting list");
        System.out.println("4. Exit");
    }

    public static void displaySearchListMenu(){
        System.out.println("Do you wish to perform further operations such as: ");
        System.out.println("1. Update the child's information");
        System.out.println("2. Remove the child from the waiting list");
        System.out.println("3. Exit");
    }

    public static void listMenu(){
        String choice = "-1";
        do {
            displayListMenu();

            switch (choice) {
                case "1":
                    MainMenu.printEmptyLines();
                    displayCurrentList();
                    MainMenu.printEmptyLines();
                    break;

                case "2":
                    //MainMenu.printEmptyLines();
                    //App.getController().addChildToWaitingList();
                    //MainMenu.printEmptyLines();
                    break;

                case "3":
                    //MainMenu.printEmptyLines();
                    //App.getController().searchChildWaitingList();
                    //MainMenu.printEmptyLines();
                    break;

                case "4":
                    break;
            }
        } while (!choice.equals("5"));
    }

    public static void searchWaitingListMenu(){
        String choice = "-1";
        do {
            displaySearchListMenu();

            switch (choice) {
                case "1":
                    //MainMenu.printEmptyLines();
                    //App.getController().updateChildWaitingList();
                    //MainMenu.printEmptyLines();
                    break;

                case "2":
                    //MainMenu.printEmptyLines();
                    //App.getController().removeChildFromWaitingList();
                    //MainMenu.printEmptyLines();
                    break;

                case "3":
                    break;
            }
        } while (!choice.equals("4"));
    }

    private static void displayCurrentList(){
        App.getController().getChildrenFromList(0);
    }
}