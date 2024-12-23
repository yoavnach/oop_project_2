package gym.management;

import java.util.ArrayList;
import java.util.List;

import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Secretary.*;
import gym.management.Sessions.Session;

import static gym.management.Secretary.makeSecretary;

//import static gym.management.Secretary.makeSecretary;

public class Gym {
    private static Gym instance;
    private String name;
    private Secretary secretary;
    private static List<Client> registeredClients = new ArrayList<>();
    private  List<Instructor> instructors = new ArrayList<>();
    private int Balance=0;
    public int IDCounter=1111;
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
        Secretary.makeSecretary(p, salary);
        this.secretary = Secretary.getInstance();
    }
    public Secretary getSecretary(){
        return this.secretary;
    }

    public void paySalaries() {
        for(Instructor ins:instructors)
        {
            int salary = ins.getSalary();
            ins.getPerson().setBalance(salary);
            setBalance(-salary);
        }
        int salary = Secretary.getInstance().getSalary();
        Secretary.getInstance().getPerson().setBalance(salary);
        setBalance(-salary);
    }

    /**
     * Add a sum to the balance. if the sum is negative, removing the sum from the balance
     * @param sum The sum to add to the balance
     */
    public void setBalance(int sum) {
        this.Balance += sum;
    }


    public boolean isClientRegistered(Person person) {
        for(Client c:registeredClients)
        {
            if(c.getPerson().equals(person))
            {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return "Gym Name: "+this.name+"\n"+Secretary.getInstance().toString()+"\n"+"Gym Balance: "+this.Balance+"\n";
    }
}
