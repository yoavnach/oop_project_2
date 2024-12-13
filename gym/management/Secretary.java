package gym.management;


import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.List;

import static gym.customers.Client.makeClient;
import static gym.customers.Instructor.initiateInstructor;
import static gym.management.Sessions.Session.initiateSession;

public class Secretary {
    private int salary;
    private Person person;
    private Secretary(Person p, int salary) {
        this.person = p;
        this.salary = salary;
        }
    public static Secretary makeSecretary(Person p, int salary){
        return new Secretary(p,salary);
        }
    public Client registerClient(Person p) {
        Client client = makeClient(p);
        Gym.addRegisteredClients(client);
        return client;
        }
    public void unregisterClient(Client client){
        Gym.removeRegisteredClient(client);
        }

    public Instructor hireInstructor(Person p, int sal, List<SessionType> allowed) {
        return  initiateInstructor(p, sal, allowed);
        }

    public Session addSession(SessionType t, String dt, ForumType pType, Instructor instruct) {
        return initiateSession(t, dt, pType, instruct);
        }
    }





