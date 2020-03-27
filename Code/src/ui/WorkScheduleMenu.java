package ui;
import controller.Controller;
import main.App;
import model.Database;
import model.Shift;

import java.io.*;
import java.util.Scanner;

public class WorkScheduleMenu{
    //  Console Inputs
    private static Scanner console = new Scanner(System.in);
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    //  Instantiate Validation Object
    private static Validation validation = new Validation();

    //  Constructors
    public WorkScheduleMenu() {}

    //  UI Methods
    public static void displayWorkScheduleMenu(){
        System.out.println("\t\t\t -MANAGE WORK SCHEDULE MENU- \t\t\t");
        System.out.println("[1] Display the work schedule");
        System.out.println("[2] Create Work Schedule");  //also create shifts
        System.out.println("[3] Search for a work schedule");
        System.out.println("[4] Back to main menu");
    }

    public static void displaySearchWorkScheduleMenuOptions(){
        System.out.println("Do you wish to perform further operations such as: ");
        System.out.println("[1] Update work schedule");
        System.out.println("[2] Remove work schedule");
        System.out.println("[3] Back to Work Schedule Menu");
    }

    public static void workScheduleMenu() {
        String choice = "-1";
        do {
            displayWorkScheduleMenu();
            choice = console.next();
            switch (choice) {
                case "1":
                    MainMenu.printEmptyLines();
                    App.getController().displayWorkSchedule();
                    validation.doesStop();
                    MainMenu.printEmptyLines();
                    break;

                case "2":
                    MainMenu.printEmptyLines();
                    App.getController().createWorkSchedule();
                    validation.doesStop();
                    MainMenu.printEmptyLines();
                    break;

                case "3":
                    MainMenu.printEmptyLines();
                    searchWorkScheduleMenu();
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

    public static void searchWorkScheduleMenu(){
        String choice = "-1";
        boolean repeat = true;
        boolean updated = false;
        boolean found = true;

        do {
            System.out.println("SEARCH WORK SCHEDULE MENU");
            System.out.println("****************************");


            if(repeat)  {
                System.out.println("Type the <Employee ID> of the Work Schedule.");
                found = App.getController().searchWorkSchedule();
            }

            if(!found) {
                MainMenu.printEmptyLines();
                continue;
            }

            displaySearchWorkScheduleMenuOptions();

            try {
                choice = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (choice) {
                case "1":
                    updateWorkScheduleMenu();
                    repeat = false;
                    updated = true;
                    MainMenu.printEmptyLines();
                    break;

                case "2":
                    repeat = App.getController().deleteWorkSchedule();
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

    public static void updateWorkScheduleMenu(){

        int toUpdate = validation.getValidatedInt("Type the <ID> of the Work Schedule you want to modify");

        String choice = "-1";
        do {
            System.out.println();
            System.out.println("Choose what to modify: ");
            System.out.println("[1] Employee ID ");
            System.out.println("[2] Shift IDs");
            System.out.println("[3] Everything");
            System.out.println("[4] Go back");

            try {
                choice = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch(choice) {
                case "1":

                    MainMenu.printEmptyLines();
                    App.getController().updateWorkSchedule(toUpdate,"employeeId");
                    MainMenu.printEmptyLines();
                    break;

                case "2":

                    MainMenu.printEmptyLines();
                    App.getController().updateWorkSchedule(toUpdate,"shiftIds");
                    MainMenu.printEmptyLines();
                    break;

                case "3":

                    MainMenu.printEmptyLines();
                    App.getController().updateWorkSchedule(toUpdate,"everything");
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
        }  while(!choice.equals("4"));
    }
}