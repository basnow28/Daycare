package model;

public class Employee extends Person{

    //  Fields
    private EmployeeType type;
    private double salary;
    private int workingHours;

    //  Constructors
    public Employee()   {}

    public Employee(int id, String firstName, String lastName, String cpr, String email, String phoneNumber,
                    EmployeeType type, double salary, int workingHours) {
        super(id, firstName, lastName, cpr, email, phoneNumber);
        this.type = type;
        this.salary = salary;   //salary per hour,
        this.workingHours = workingHours;
    }

    //  Getters & Setters
    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    //  Printing

    @Override
    public String toString() {
        return "Employee{" +
                super.toString() +
                "type=" + type +
                ", salary=" + salary +
                ", workingHours=" + workingHours +
                '}';
    }
}