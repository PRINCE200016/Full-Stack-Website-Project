package com.itrainu.Bean;

public class TableBean {
    private int id;
    private String Name; 
    private int Salary;  

    // Default constructor
    public TableBean() {
    }

    // Parameterized constructor
    public TableBean(int id, String Name, int Salary) {
        this.id = id;
        this.Name = Name;
        this.Salary = Salary;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for Name
    public String getName() {
        return Name;
    }

    // Setter for Name
    public void setName(String Name) {
        this.Name = Name;
    }

    // Getter for Salary
    public int getSalary() {
        return Salary;
    }

    // Setter for Salary
    public void setSalary(int Salary) {
        this.Salary = Salary;
    }
}
