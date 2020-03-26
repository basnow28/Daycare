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

    public static void childrenMenu() throws InterruptedException, IOException {
        String choice = "-1";
        do {
            displayParentsMenu();
            choice = br.readLine();

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
                    MainMenu.mainMenuAdmin();
                    break;
                default:
                    MainMenu.printEmptyLines();
                    System.out.println("Choice must be a value between \"1\" and \"3\".");
                    MainMenu.printEmptyLines();
                    Thread.sleep(1000);
            }
        } while (!choice.equals("3"));
    }

    public static void searchParentMenu() throws IOException, InterruptedException{
        String choice = "-1";
        boolean repeat = true;
        boolean updated = false;
        boolean found = true;

        do {
            System.out.println("SEARCH PARENT MENU");
            System.out.println("****************************");
            choice = br.readLine();

            if(repeat)  {
                System.out.println("Type the <PARENT ID>");
                found = App.getController().searchParents();
            }

            if(!found) {
                MainMenu.printEmptyLines();
                continue;
            }

            displayParentsMenuOptions();

            choice = br.readLine();
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
                    Thread.sleep(1000);
            }
        } while (!choice.equals("2") && !updated);
    }


    public static void updateParentMenu()  throws IOException, InterruptedException {
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

            choice = br.readLine();
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