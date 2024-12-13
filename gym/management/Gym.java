package gym.management;

import java.util.ArrayList;
import java.util.List;

import gym.customers.Client;
import gym.customers.Person;
import gym.management.Secretary.*;

import static gym.management.Secretary.makeSecretary;

//import static gym.management.Secretary.makeSecretary;

public class Gym {
    private static Gym instance;
    private String name;
    private Secretary secretary;
    private static List<Client> registeredClients = new ArrayList<>();
//    private int salary;

    private Gym() {}

    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }

    public static List<Client> getRegisteredClients() {
        return registeredClients;
    }

    public static void addRegisteredClients(Client client) {
        registeredClients.add(client);
    }

    public static void removeRegisteredClient(Client client){
        registeredClients.remove(client);
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSecretary(Person p, int salary) {
        this.secretary = makeSecretary(p,salary);
    }
    public Secretary getSecretary(){
        return this.secretary;
    }
}
