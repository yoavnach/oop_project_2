package gym.management;


import Comarators.CompareDate;
import gym.DateToInt;
import gym.Exception.ClientCantRegisterToSessionException;
import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InvalidAgeException;
import gym.customers.*;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.text.SimpleDateFormat;
import java.util.*;

import static gym.management.Sessions.Session.initiateSession;

public class Secretary extends Observable {
    private static Secretary INSTANCE;
    private int salary;
    private Person person;
    private static List<String> actions=new ArrayList<>();
    private List<Observer> observers=new ArrayList<>();
    private CustomersFactory factory=new CustomersFactory();
    private Secretary(Person p, int salary) {
        this.person = p;
        this.salary = salary;

    }

    public static void makeSecretary(Person p, int salary) {
        INSTANCE=null;
        INSTANCE = new Secretary(p, salary);
        if(p.getId()==0)
        {
            INSTANCE.person.setId(Gym.getInstance().IDCounter);
            Gym.getInstance().IDCounter++;
        }
        addAction("A new secretary has started working at the gym: "+p.getName());
    }
    public static Secretary getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        else
            return null;
    }
    public Person getPerson() {
        return person;
    }
    public int getSalary() {return salary;}

    public Client registerClient(Person p) {
        try {
            if(p.getAge()<18)
            {
                throw new InvalidAgeException();
            }
            if(Gym.getInstance().isClientRegistered(p))
            {
                throw new DuplicateClientException();
            }
            if(p.getId()==0)
            {
                p.setId(Gym.getInstance().IDCounter);
                Gym.getInstance().IDCounter++;
            }
            Client client = factory.makeClient(p);
            observers.add(client);
            Gym.addRegisteredClients(client);
            addAction("Registered new client: "+p.getName());
            return client;

        }
        catch (InvalidAgeException ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
        catch (DuplicateClientException ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }


    }

    public void unregisterClient(Client client) {
        try {
            if(!Gym.getInstance().isClientRegistered(client.getPerson()))
            {
                throw new ClientNotRegisteredException();
            }
            Gym.removeRegisteredClient(client);
            observers.remove(client);
            addAction("Unregistered client: "+client.getName());
        }
        catch (ClientNotRegisteredException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public Instructor hireInstructor(Person p, int sal, List<SessionType> allowed) {
        if(p.getId()==0)
        {
            p.setId(Gym.getInstance().IDCounter);
            Gym.getInstance().IDCounter++;
        }
        addAction("Hired new instructor: "+p.getName()+ " with salary per hour: "+sal);
        return factory.makeInstuctor(p, sal, allowed);

    }

    public Session addSession(SessionType t, String dt, ForumType pType, Instructor instruct) {
        addAction("Created new session: "+t+" on "+dt+" with instructor: "+instruct.getPerson().getName());
        return initiateSession(t, dt, pType, instruct);
    }

    public void registerClientToLesson(Client c1, Session s1) {

        try {
            if(isDateOver(s1.getDateTime()))
            {
                throw new ClientCantRegisterToSessionException("Failed registration: Session is not in the future");
            }
            if(!isInForum(c1.getPerson(), s1.getForumType()))
            {
                switch (s1.getForumType())
                {
                    case Male:
                        throw new ClientCantRegisterToSessionException("Failed registration: Client's gender doesn't match the session's gender requirements");

                    case Female:
                        throw new ClientCantRegisterToSessionException("Failed registration: Client's gender doesn't match the session's gender requirements");

                    case Seniors:
                        throw new ClientCantRegisterToSessionException("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
                }

            }
            if(isSessionFull(s1))
            {
                throw new ClientCantRegisterToSessionException("Failed registration: No available spots for session");
            }
            int sessionPrice = getPrice(s1);
            if(!isClientHaveBalance(c1.getPerson(),sessionPrice))
            {
                throw new ClientCantRegisterToSessionException("Failed registration: Client doesn't have enough balance");
            }
            s1.addClient();
            c1.getPerson().setBalance(-sessionPrice);
            c1.registerToSession(s1);
            addAction("Registered client: "+c1.getName()+" to session: "+s1.getType()+" on "+s1.getDateTime()+ " for price: "+sessionPrice);
            Gym.getInstance().setBalance(sessionPrice);
        }
        catch (ClientCantRegisterToSessionException ex)
        {
            //System.out.println(ex.getMessage());
            addAction(ex.getMessage());
        }

    }


    public static String getCurrentTime()
    {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date now = new Date();
        return sdfDate.format(now);
    }

    public void notify(Session session, String message) {
            for(Observer o : observers)
            {
                if (o instanceof Client client)
                {
                    if(client.isRegistered(session))
                    {
                        o.update(this,message);
                    }
                }

            }
//        List<Client>clients= Gym.getInstance().getClients(session);
//        for (Client c : clients) {
//            c.addMessage(message);
//        }
        addAction("A message was sent to everyone registered for session "+session.getType()+" on "+session.getDateTime()+" : "+message);

    }

    public void notify(String day, String message) {
        for(Observer o : observers)
        {
            if(o instanceof Client client)
            {
                if(client.isRegisterInDay(day))
                {
                    o.update(this,message);
                }
            }
        }
//        List<Client>clients= Gym.getInstance().getClients(day);
//        for (Client c : clients) {
//            c.addMessage(message);
//        }
        addAction("A message was sent to everyone registered for session on "+day+" : "+message);

    }


    public void notify(String message) {
        for (Observer o : observers)
        {
            o.update(this,message);
        }
//        List<Client>clients= Gym.getInstance().getClients();
//        for (Client c : clients) {
//            c.addMessage(message);
//        }
        addAction("A message was sent to all gym clients: "+message);
    }

    public void paySalaries() {
        Gym.getInstance().paySalaries();
        addAction("Salaries have been paid to all employees");
    }

    public void printActions() {
        for (String a : actions) {
            System.out.println(a);
        }
    }

    private static void addAction(String action) {
        actions.add(action);
    }

    private boolean isDateOver(String date) {
        CompareDate compareDate = new CompareDate();
        return compareDate.compare(date, getCurrentTime()) > 0;
    }
    private boolean isInForum(Person p,ForumType f) {

        switch (f)
        {
            case All:
                return true;

            case Male:
                if(p.getGender().equals(Gender.Female))
                {
                    return false;
                }
                break;

            case Female:
                if(p.getGender().equals(Gender.Male))
                {
                    return false;
                }
                break;

            case Seniors:
                if(p.getAge()<65)
                {
                    return false;
                }
        }
        return true;
    }
    private boolean isSessionFull(Session session) {
        switch (session.getType())
        {
            case MachinePilates:
                if(session.getNumOfClients()<=10)
                {
                    return false;
                }
                break;

            case Pilates:
                if(session.getNumOfClients()<=30)
                {
                    return false;
                }
                break;

            case ThaiBoxing:
                if(session.getNumOfClients()<=20)
                {
                    return false;
                }
                break;

            case Ninja:
                if(session.getNumOfClients()<=5)
                {
                    return false;
                }
                break;

        }
        return true;
    }
    private boolean isClientHaveBalance(Person p, int price) {
        return p.getBalance() >= price;
    }
    private int getPrice(Session s)
    {
        switch (s.getType())
        {
            case MachinePilates:
                return 80;

            case Pilates:
                return  60;

            case ThaiBoxing:
                return  100;

            case Ninja:
                return  150;

        }
        return 0;
    }
    @Override
    public String toString()
    {
        return "Gym Secretary: "+this.getPerson().toString();
    }
}





