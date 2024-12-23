package gym.management.Sessions;

import gym.customers.Instructor;
import gym.management.ForumType;

public class ThaiBoxingSession extends Session {
    public ThaiBoxingSession(String date, ForumType forumType, Instructor instructor)  {
        super(SessionType.valueOf("ThaiBoxing"),date,forumType,instructor);
    }


}
