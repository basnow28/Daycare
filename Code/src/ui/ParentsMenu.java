package ui;

import main.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ParentsMenu{
    private static Scanner scanner = new Scanner(System.in);
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static Validation validation = new Validation();

    public ParentsMenu(){}

    public static void displayParentsMenu(){
        System.out.println("\t\t\t -MANAGE PARENTS MENU- \t\t\t");
        System.out.println("1. Display information on parents");
        System.out.println("2. Search for a parent");
        System.out.println("3. Exit");
    }

    public static void displayParentsMenuOptions(){
        System.out.println("Do you wish to perform further operations such as: ");
        System.out.println("1. Update the parent's information");
        System.out.println("2. Exit");
    }

    public static void parentsMenu() {
        String choice = "-1";
        do {
            displayParentsMenu();
            try {
                choice = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (choice) {
                case "1":
                    MainMenu.printEmptyLines();
                    App.getController().displayParents();
                    MainMenu.printEmptyLines();
                    break;

                case "2":
                    MainMenu.printEmptyLines();
                    searchParentMenu();
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
        } while (!choice.equals("3"));
    }

    public static void searchParentMenu() {
        String choice = "-1";
        boolean repeat = true;
        boolean updated = false;
        boolean found = true;

        String continue_search = "";

        do {
            System.out.println("SEARCH PARENT MENU");
            System.out.println("****************************");


            if(repeat)  {
                System.out.println();
                System.out.println("Choose do you want to search: ");
                System.out.println("[1] Parent ID");
                System.out.println("[2] Parent First Name");
                System.out.println("[3] Parent Last Name");
                System.out.println("[4] Parent CPR");
                System.out.println("[5] Parent Email");
                System.out.println("[6] Parent Phone Number");
                System.out.println("[7] Parent's Child ID");

                try {
                    choice = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                switch (choice) {
                    case "1":
                        MainMenu.printEmptyLines();
                        found = App.getController().searchParents("id");
                        MainMenu.printEmptyLines();
                        break;

                    case "2":
                        MainMenu.printEmptyLines();
                        found = App.getController().searchParents("firstName");
                        MainMenu.printEmptyLines();
                        break;

                    case "3":
                        MainMenu.printEmptyLines();
                        found = App.getController().searchParents("lastName");
                        MainMenu.printEmptyLines();
                        break;

                    case "4":
                    MainMenu.printEmptyLines();
                    found = App.getController().searchParents("cpr");
                    MainMenu.printEmptyLines();
                        break;

                    case "5":
                        MainMenu.printEmptyLines();
                        found = App.getController().searchParents("email");
                        MainMenu.printEmptyLines();
                        break;

                    case "6":
                        MainMenu.printEmptyLines();
                        found = App.getController().searchParents("phoneNumber");
                        MainMenu.printEmptyLines();
                        break;

                    case "7":
                        MainMenu.printEmptyLines();
                        found = App.getController().searchParents("childId");
                        MainMenu.printEmptyLines();
                        break;

                }
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

            displayParentsMenuOptions();

            try {
                choice = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (choice) {

                case "1":
                    updateParentMenu();
                    repeat = false;
                    updated = true;
                    MainMenu.printEmptyLines();
                    break;
                case "2":
                    break;

                default:
                    MainMenu.printEmptyLines();
                    System.out.println("Choice must be a value between \"1\" and \"2\".");
                    MainMenu.printEmptyLines();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        } while (!choice.equals("2") && !updated);
    }


    public static void updateParentMenu()   {
        System.out.println("Type the <ID> of the Parent you want to modify ");
        int toUpdate = scanner.nextInt();

        String choice = "-1";
        do {
            System.out.println();
            System.out.println("Choose what to modify: ");
            System.out.println("[1] Parent First Name");
            System.out.println("[2] Parent Last Name");
            System.out.println("[3] Parent CPR");
            System.out.println("[4] Parent Email");
            System.out.println("[5] Parent Phone Number");
            System.out.println("[6] Child's ID");
            System.out.println("[7] Everything");
            System.out.println("[8] Go back");

            try {
                choice = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (choice) {
                case "1":
                    MainMenu.printEmptyLines();
                    App.getController().updateParent(toUpdate, "firstName");
                    MainMenu.printEmptyLines();
                    break;

                case "2":
                    MainMenu.printEmptyLines();
                    App.getController().updateParent(toUpdate, "lastName");
                    MainMenu.printEmptyLines();
                    break;

                case "3":
                    MainMenu.printEmptyLines();
                    App.getController().updateParent(toUpdate, "cpr");
                    MainMenu.printEmptyLines();
                    break;

                case "4":
                    MainMenu.printEmptyLines();
                    App.getController().updateParent(toUpdate, "email");
                    MainMenu.printEmptyLines();
                    break;

                case "5":
                    MainMenu.printEmptyLines();
                    App.getController().updateParent(toUpdate, "phoneNumber");
                    MainMenu.printEmptyLines();
                    break;
                case "6":
                    MainMenu.printEmptyLines();
                    App.getController().updateParent(toUpdate, "childId");
                    MainMenu.printEmptyLines();
                    break;
                case "7":
                    MainMenu.printEmptyLines();
                    App.getController().updateParent(toUpdate, "everything");
                    MainMenu.printEmptyLines();
                    break;
            }
        }while(!choice.equals("8"));
    }

}
