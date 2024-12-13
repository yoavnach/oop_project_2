package gym.management.Sessions;

import gym.customers.Instructor;
import gym.management.ForumType;

public class Session {
    private SessionType type;
    private String dateTime;
    private ForumType personType;
    private Instructor instructor;

    private Session(SessionType t, String dt, ForumType pType, Instructor instruct) {
        this.type = t;
        this.dateTime = dt;
        this.personType = pType;
        this.instructor = instruct;
    }
    public static Session initiateSession(SessionType t, String dt, ForumType pType, Instructor instruct) {
        return new Session(t, dt, pType, instruct);
    }
}
