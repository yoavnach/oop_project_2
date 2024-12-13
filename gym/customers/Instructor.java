package gym.customers;

import gym.management.Sessions.SessionType;

import java.util.ArrayList;
import java.util.List;

public class Instructor {
    private Person person;
    private int salary;
    private List<SessionType> allowedClasses;

    private Instructor(Person p, int sal, List<SessionType> allowed) {
        this.person = p;
        this.salary = sal;
        this.allowedClasses = allowed;
    }
    public static Instructor initiateInstructor(Person p, int sal, List<SessionType> allowed) {
        return new Instructor(p,sal,allowed);
    }
}
