package model;

public class Employee extends Person{
    private EmployeeType type;
    private double salary;
    private double wage;
    private double workingHours;

    public Employee(int id, String firstName, String lastName, String cpr, String email, String phoneNumber, EmployeeType type, double salary, double wage, double workingHours) {
        super(id, firstName, lastName, cpr, email, phoneNumber);
        this.type = type;
        this.salary = salary;
        this.wage = wage;
        this.workingHours = workingHours;
    }
}