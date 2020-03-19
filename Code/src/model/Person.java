package model;

public abstract class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String cpr;
    private String email;
    private String phoneNumber;

    public Person(){

    }

    public Person(int id, String firstName, String lastName, String cpr, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpr = cpr;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}