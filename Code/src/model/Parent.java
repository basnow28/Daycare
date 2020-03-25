package model;

public class Parent extends Person{
    //  Fields
    private int childId;

    //  Constructors
    public Parent() {}

    public Parent(int id, String firstName, String lastName, String cpr, String email, String phoneNumber, int childId) {
        super(id, firstName, lastName, cpr, email, phoneNumber);
        this.childId = childId;
    }

    //  Getters & Setters
    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    //  Printing

    @Override
    public String toString() {
        return "Parent{" +
                super.toString() +
                "childId=" + childId +
                '}';
    }

    public String toStringConsole(){
        return super.toStringConsole() + "\t" + Integer.toString(childId);
    }
}