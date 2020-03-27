package model;

public class Child{

    //  Fields
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String cpr;
    private int parentId;

    //  Constructors
    public Child() {}

    public Child(int id, String firstName, String lastName, int age, String cpr) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.cpr = cpr;
        this.parentId = parentId;
    }
    public Child(int id, String firstName, String lastName, int age, String cpr, int parentId) {
        this(id, firstName, lastName, age, cpr);
        this.parentId = parentId;
    }
    //  Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    //  Printing
    public String toStringConsoleFormat(){
        return String.format("%-5d%-15s%-15s%-15s%-10d%-15d",id, firstName, lastName, cpr, age, parentId);
    }

    public String toString(){
        return String.join("  ", Integer.toString(id), firstName, lastName, Integer.toString(age), cpr, Integer.toString(parentId));
    }
}