package gym.customers;

import gym.management.Sessions.SessionType;

import java.util.List;

public class CustomersFactory {
    public Client makeClient(Person person) {
        if(person!=null) {
            return new Client(person);
        }
        return null;
    }
    public Instructor makeInstuctor(Person person, int sal, List<SessionType> allowed) {
        if(person!=null) {
            return new Instructor(person,sal,allowed);
        }
        return null;
    }
    public Person makePerson(String name, int balance, Gender gender, String birthDate) {
        return new Person(name,balance,gender,birthDate);
    }
}
