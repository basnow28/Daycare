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
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public ChildrenMenu(){}

    public static void displayChildrenMenu(){
        System.out.println("\t\t\t -MANAGE CHILDREN MENU- \t\t\t");
        System.out.println("1. Display information on children");
        System.out.println("2. Add new child to the Waiting list");
        System.out.println("3. Search for a child");
        System.out.println("4. Exit");
    }

        public static void displayChildrenMenuOptions(){
        System.out.println("Do you wish to perform further operations such as: ");
        System.out.println("1. Update the child's information");
        System.out.println("2. Remove the child from the list");
        System.out.println("3. Exit");
    }

    public static void childrenMenu(){
        String choice = "-1";
        do {
            displayChildrenMenu();
            try {
                choice = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (choice) {
                case "1":
                    MainMenu.printEmptyLines();
                    App.getController().displayChildren();
                    MainMenu.printEmptyLines();
                    break;

                case "2":
                    MainMenu.printEmptyLines();
                    createChild();
                    MainMenu.printEmptyLines();
                    break;

                case "3":
                    MainMenu.printEmptyLines();
                    searchChildMenu();
                    MainMenu.printEmptyLines();
                    break;

                case "4":
                    break;
                default:
                    MainMenu.printEmptyLines();
                    System.out.println("Choice must be a value between \"1\" and \"4\".");
                    MainMenu.printEmptyLines();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        } while (!choice.equals("4"));
    }

    public static void searchChildMenu(){

        String choice = "-1";
        boolean repeat = true;
        boolean updated = false;
        boolean found = true;

        String continue_search = "";

        do {

            System.out.println("SEARCH CHILD MENU");
            System.out.println("****************************");


            if(repeat)  {
                System.out.println();
                System.out.println("Choose do you want to search: ");
                System.out.println("[1] Child ID");
                System.out.println("[2] Child First Name");
                System.out.println("[3] Child Last Name");
                System.out.println("[4] Child Age");
                System.out.println("[5] Child CPR");
                System.out.println("[6] Child's Parent ID");

                try {
                    choice = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                switch (choice) {
                    case "1":
                        MainMenu.printEmptyLines();
                        found = App.getController().searchChildren("id");
                        MainMenu.printEmptyLines();
                        break;

                    case "2":
                        MainMenu.printEmptyLines();
                        found = App.getController().searchChildren("firstName");
                        MainMenu.printEmptyLines();
                        break;

                    case "3":
                        MainMenu.printEmptyLines();
                        found = App.getController().searchChildren("lastName");
                        MainMenu.printEmptyLines();
                        break;

                    case "4":
                        MainMenu.printEmptyLines();
                        found = App.getController().searchChildren("age");
                        MainMenu.printEmptyLines();
                        break;

                    case "5":
                        MainMenu.printEmptyLines();
                        found = App.getController().searchChildren("cpr");
                        MainMenu.printEmptyLines();
                        break;

                    case "6":
                        MainMenu.printEmptyLines();
                        found = App.getController().searchChildren("parentId");
                        MainMenu.printEmptyLines();
                        break;

                }



                /*System.out.println("Type the <Child ID>");
                found = App.getController().searchChildren();*/
            }

            if(!found) {
                MainMenu.printEmptyLines();
                System.out.println("");
                System.out.println("Do you want to search again?(Type Y/N): ");
                try {
                    continue_search = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (continue_search.equals("N")){
                    break;
                }else{
                    System.out.println("");
                    continue;

                }
            }

            displayChildrenMenuOptions();

            try {
                choice = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (choice) {
                case "1":
                    updateChildMenu();
                    repeat = false;
                    updated = true;
                    MainMenu.printEmptyLines();
                    break;
                case "2":
                    deleteChildMenu();
                    repeat = false;
                    updated = true;
                    MainMenu.printEmptyLines();
                    break;
                case "3":
                    break;
                default:
                    MainMenu.printEmptyLines();
                    System.out.println("Choice must be a value between \"1\" and \"3\".");
                    MainMenu.printEmptyLines();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        } while (!choice.equals("3") && !updated);
    }

    private static void createChild(){
        String firstName = validation.getValidatedName("Child's first name?");
        String lastName = validation.getValidatedName("Child's last name?");
        int age = validation.getValidatedAge("Child's age?");
        String cpr = validation.getValidatedCpr("Child's cpr number?");
        int childId = App.getController().createChild(firstName, lastName, age, cpr);
        createParent(childId);
    }

    public static void updateChildMenu(){
        System.out.println("Type the <ID> of the Child you want to modify ");
        int toUpdate = scanner.nextInt();

        String choice = "-1";
        do {
            System.out.println();
            System.out.println("Choose what to modify: ");
            System.out.println("[1] Child First Name");
            System.out.println("[2] Child Last Name");
            System.out.println("[3] Child Age");
            System.out.println("[4] Child CPR");
            System.out.println("[5] Child's Parent ID");
            System.out.println("[6] Everything");
            System.out.println("[7] Go back");

            try {
                choice = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (choice) {
                case "1":
                    MainMenu.printEmptyLines();
                    App.getController().updateChild(toUpdate, "firstName");
                    MainMenu.printEmptyLines();
                    break;

                case "2":
                    MainMenu.printEmptyLines();
                    App.getController().updateChild(toUpdate, "lastName");
                    MainMenu.printEmptyLines();
                    break;

                case "3":
                    MainMenu.printEmptyLines();
                    App.getController().updateChild(toUpdate, "age");
                    MainMenu.printEmptyLines();
                    break;

                case "4":
                    MainMenu.printEmptyLines();
                    App.getController().updateChild(toUpdate, "cpr");
                    MainMenu.printEmptyLines();
                    break;

                case "5":
                    MainMenu.printEmptyLines();
                    App.getController().updateChild(toUpdate, "parentId");
                    MainMenu.printEmptyLines();
                    break;
                case "6":
                    MainMenu.printEmptyLines();
                    App.getController().updateChild(toUpdate, "everything");
                    MainMenu.printEmptyLines();
                    break;
            }
        }while(!choice.equals("7"));
    }

    public static void deleteChildMenu() {
        System.out.println("Type the <ID> of the Child you want to modify ");
        int toDelete = scanner.nextInt();
        App.getController().deleteChild(toDelete);
    }

    private static void createParent(int childId){
        System.out.println("\nParent Information");
        String firstName = validation.getValidatedName("Parent's first name?");
        String lastName = validation.getValidatedName("Parent's last name?");
        String cpr = validation.getValidatedCpr("Parent's cpr number?");
        String email = validation.getValidatedEmail("Parent's email address?");
        String phoneNumber = validation.getValidatedPhone("Parent's phone number?");
        App.getController().createParent(firstName, lastName, cpr, email, phoneNumber, childId);
        addChildToWaitingList(childId);
    }

    private static void addChildToWaitingList(int childId){
        System.out.println("To which waiting list would you like to add the child?");
        System.out.println(App.getController().getWaitingLists().toString());
        int listId =validation.getValidatedInt("Which waiting list do you choose? Write down the id");
        App.getController().addChildToWaitingList(childId, listId);
        System.out.println(App.getController().getWaitingLists());
    }
}