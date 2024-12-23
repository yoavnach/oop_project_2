package gym.customers;

import gym.management.Sessions.SessionType;

import java.util.ArrayList;
import java.util.List;

public class Instructor {
    private Person person;
    private int salary;
    private List<SessionType> allowedClasses;

     Instructor(Person p, int sal, List<SessionType> allowed) {
        this.person = p;
        this.salary = sal;
        this.allowedClasses = allowed;
    }

    public Person getPerson() {
        return person;
    }
    public int getSalary() {return salary;}
}
