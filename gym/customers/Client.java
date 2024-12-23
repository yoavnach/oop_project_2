package gym.customers;

import gym.management.Secretary;
import gym.management.Sessions.Session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Client implements Observer {
    private Person person;
    private List<Session> registered;

    Client(Person P) {
        this.person = P;
        this.Messages = new ArrayList<>();
        this.registered = new ArrayList<>();
    }

    private List<String> Messages;

    public Person getPerson() {
        return person;
    }

    public String getName() {
        return person.getName();
    }

    public String getNotifications() {
        String ans = "[";
        for (int i = 0; i < this.Messages.size() - 1; i++) {
            ans += Messages.get(i) + ", ";
        }
        ans += Messages.get(this.Messages.size() - 1) + "]";
        return ans;
    }

    public void addMessage(String message) {
        this.Messages.add(message);
    }

    public void registerToSession(Session session) {
        this.registered.add(session);
    }

    public boolean isRegistered(Session session) {
        return this.registered.contains(session);
    }

    public boolean isRegisterInDay(String day) {
        for (Session session : this.registered) {
            if (session.getDateTime().substring(0, 10).equals(day)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void update(Observable o, Object arg) {
        addMessage((String) arg);
    }
}
