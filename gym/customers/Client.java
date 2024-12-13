package gym.customers;

public class Client {
    private Person person;
    private Client(Person P) {
        this.person = P;
    }
    public static Client makeClient(Person p){
        return new Client(p);
    }
}
