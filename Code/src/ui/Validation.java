package ui;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static Scanner scanner = new Scanner(System.in);

    private boolean validateInt(String number) {
        Pattern pattern =  Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    private boolean validateName(String name){
        Pattern pattern = Pattern.compile("[A-Za-z]+");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean validateCpr(String cpr){
        Pattern pattern = Pattern.compile("\\d{6}-\\d{4}");
        Matcher matcher = pattern.matcher(cpr);
        return matcher.matches();
    }

    private boolean validateAge(String age){
        Pattern pattern = Pattern.compile("[1-6]");
        Matcher matcher = pattern.matcher(age);
        return matcher.matches();
    }

    private boolean validateEmail(String email){
        Pattern pattern = Pattern.compile("(.+)@(.+)");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validatePhoneNumber(String phoneNumber){
        Pattern pattern = Pattern.compile("\\d{8}");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public int getValidatedInt(String message) {
        System.out.println(message);
        String number = scanner.nextLine();
        if(this.validateInt(number)) {
            return Integer.parseInt(number);
        }
        return getValidatedInt("Invalid input. ID cannot contain any characters other than figures");
    }

    public ArrayList<Integer> getValidatedIds (String message) {
        System.out.println(message);
        ArrayList<Integer> ids = new ArrayList<Integer>();

        String input = scanner.nextLine();
        String[] split = input.split("\\s+");   //any spaces
        for(int i = 0; i < split.length; i++) {
            if(!validateInt(split[i])) {
                return getValidatedIds("Invalid Input. IDs can only be figures.");
            }
            ids.add(Integer.parseInt(split[i]));
        }
        return ids;
    }

    public String getValidatedName(String message){
        System.out.println(message);
        String name = scanner.nextLine();
        if(this.validateName(name)){
            return name;
        }
        System.out.println("Invalid name. Name cannot contain special characters or numbers");
        return getValidatedName(message);
    }

    public String getValidateCpr(String message){
        System.out.println(message);
        String cpr = scanner.next();
        if(this.validateCpr(cpr)){
            return cpr;
        }
        System.out.println("Invalid cpr number. Correct format: XXXXXX-XXXX");
        return getValidateCpr(message);
    }

    public int getValidatedAge(String message){
        System.out.println(message);
        String age = scanner.next();
        if(this.validateAge(age)){
            return Integer.parseInt(age);
        }
        System.out.println("Invalid age. Age cannot contain any characters. Child cannot be older than 6 years old.");
        return getValidatedAge(message);
    }

    public String getValidatedEmail(String message) {
        System.out.println(message);
        String email = scanner.next();
        if(this.validateEmail(email)){
            return email;
        }
        System.out.println("Invalid email address. Try again");
        return getValidatedEmail(message);
    }

    public String getValidatedPhone(String message) {
        System.out.println(message);
        String phoneNumber = scanner.next();
        if(this.validatePhoneNumber(phoneNumber)){
            return phoneNumber;
        }
        System.out.println("Invalid phone number. Should have 8 digits. Try again");
        return getValidatedEmail(message);
    }
}


