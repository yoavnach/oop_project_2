package gym.management.Sessions;

import gym.customers.Instructor;
import gym.management.ForumType;

public class Session {
    private SessionType type;
    private String dateTime;
    private ForumType personType;
    private Instructor instructor;
    private int numOfClients=0;
    public Session(SessionType t, String dt, ForumType pType, Instructor instruct) {
        this.type = t;
        this.dateTime = dt;
        this.personType = pType;
        this.instructor = instruct;
    }
    public static Session initiateSession(SessionType t, String dt, ForumType pType, Instructor instruct) {
        return new Session(t, dt, pType, instruct);
    }
    public SessionType getType()
    {
        return type;
    }
    public String getDateTime()
    {
        return dateTime;
    }
    public ForumType getForumType()
    {
        return personType;
    }
    public Instructor getInstructor()
    {
        return instructor;
    }
    public void addClient()
    {
        numOfClients++;
    }
    public int getNumOfClients()
    {
        return numOfClients;
    }
}
