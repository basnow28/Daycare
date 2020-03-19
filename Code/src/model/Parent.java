package model;

public class Parent extends Person{
    private int childId;
    public Parent(int id, String firstName, String lastName, String cpr, String email, String phoneNumber, int childId) {
        super(id, firstName, lastName, cpr, email, phoneNumber);
        this.childId = childId;
    }
}