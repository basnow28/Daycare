package model;

public class Child{
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String cpr;
    private Parent parent;

    public Child(int id, String firstName, String lastName, int age, String cpr, Parent parent) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.cpr = cpr;
        this.parent = parent;
    }
}