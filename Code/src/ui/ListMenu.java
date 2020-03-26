package ui;
import main.App;
import model.Quarter;
import model.WaitingList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ListMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static Validation validation = new Validation();
    public ListMenu(){}

    public static void displayListMenu(){
        System.out.println("\t\t\t -MANAGE LISTS MENU- \t\t\t");
        System.out.println("1. Display current children's list");
        System.out.println("2. Search for a waiting list information");
        System.out.println("3. Create new waiting list");
        System.out.println("4. Search for a child on the waiting list");
        System.out.println("5. Go back");
    }

    public static void listMenu(){
        String choice = "-1";
        do {
            displayListMenu();
            choice = scanner.next();
            switch (choice) {
                case "1":
                    MainMenu.printEmptyLines();
                    displayCurrentList();
                    MainMenu.printEmptyLines();
                    break;

                case "2":
                    MainMenu.printEmptyLines();
                    searchForList();
                    MainMenu.printEmptyLines();
                    break;

                case "3":
                    MainMenu.printEmptyLines();
                    createNewWaitingList();
                    MainMenu.printEmptyLines();
                    break;
                case "4":
                    MainMenu.printEmptyLines();
                    searchForAChild();
                    MainMenu.printEmptyLines();
                    break;
                case "5":
                    MainMenu.mainMenuAdmin();
                    break;
            }
        } while (!choice.equals("5"));
    }

    private static void createNewWaitingList(){
        Quarter quarter = validation.getValidatedQuarter("What quarter?");
        String year = validation.getValidatedYear("What year?");
        int capacity = validation.getValidatedInt("What is the capacity?");
        App.getController().createNewWaitingList(quarter, year, capacity);
        MainMenu.typeAnyKey();
    }

    private static void displayCurrentList(){
        System.out.println("Current list:");
        App.getController().displayChildrenFromList(0);
        MainMenu.typeAnyKey();
    }

    private static void searchForList(){
        System.out.println("Write any field from the list you want to work on");
        String key = scanner.next();

        if(App.getController().getWaitingList(key).size() > 0){
            System.out.println("What do you want to do next? ");
            System.out.println("1. Modify the list information");
            System.out.println("2. Delete a child from list");
            System.out.println("3. Go back");
            String choice = scanner.next();

            if(choice.equals("1")){
                int id = validation.getValidatedInt("Which waiting list you do you want to modify? Write the ID");
                modifyList(id);
            }
            else if(choice.equals("2")){
                int listId = validation.getValidatedInt("From which list do you want to delete a child? Write the id");
                int id = validation.getValidatedInt("Which child do you want to delete? Write down the id");
                deleteChild(listId, id);
            }
            else{
                listMenu();
            }
        }else{
            System.out.println("There is no list that matches the criteria");
            System.out.println("Do you want to search again? ");
            searchForList();
        }
    }

    private static void modifyList(int id){
        System.out.println("Which field do you want to update?");
        System.out.println("year / quarter / capacity");
        String field = scanner.next();

        if(field.toLowerCase().equals("year") || field.toLowerCase().equals("quarter") || field.toLowerCase().equals("capacity")) {
            App.getController().updateWaitingList(id, field);
        }else{
            System.out.println("There is no such a field to update");
            modifyList(id);
        }
    }

    private static void deleteChild(int listId, int id){
        App.getController().deleteChildFromList(listId, id);
    }

    private static void searchForAChild(){

    }
}