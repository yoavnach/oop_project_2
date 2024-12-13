package gym.customers;

public class Person {
//    Person p1 = new Person("David", 500, Gender.Male, "20-02-1978");
    private String name;
    private int balance;
    private Gender gender;
    private String dateJoined;
    public Person(String name, int balance, Gender gender, String dateJoined) {
        this.name =name;
        this.balance = balance;
        this.gender = gender;
        this.dateJoined = dateJoined;
    }
    public String getName() {
        return this.name;
    }
    public int getBalance(){
        return this.balance;
    }
    public void setBalance(int n){
        this.balance = n;
    }
}
