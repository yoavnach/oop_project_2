package gym.customers;

import gym.DateToInt;
import gym.management.Secretary;

public class Person {
//    Person p1 = new Person("David", 500, Gender.Male, "20-02-1978");
    private int id;
    private final String name;
    private int balance;
    private final Gender gender;
    private final String birthDate;
    public Person(String name, int balance, Gender gender, String birthDate)  {
        this.name =name;
        this.balance = balance;
        this.gender = gender;
        this.birthDate = birthDate;
        this.id = 0;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public int getBalance(){
        return this.balance;
    }

    /**
     * Add a sum to the balance. if the sum is negative, removing the sum from the balance
     * @param sum The sum to add to the balance
     */
    public void setBalance(int sum){
        this.balance += sum;
    }
    public Gender getGender(){return this.gender;}
    public String getBirthDate(){return this.birthDate;}
    public int getAge()
    {
        try {

            int age =DateToInt.getYear(Secretary.getCurrentTime())- DateToInt.getYear(this.getBirthDate());
            if(DateToInt.getMonth(Secretary.getCurrentTime())>DateToInt.getMonth(this.getBirthDate())){
                return age;
            }
            if(DateToInt.getMonth(Secretary.getCurrentTime())<DateToInt.getMonth(this.getBirthDate())){
                return age-1;
            }
            if(DateToInt.getDay(Secretary.getCurrentTime())>DateToInt.getDay(this.getBirthDate())){
                return age;
            }
            if(DateToInt.getDay(Secretary.getCurrentTime())<DateToInt.getDay(this.getBirthDate())){
                return age-1;
            }
            return age;

        }
        catch (Exception e) {
            return 0;
        }

    }
    @Override
    public String toString() {
        //ID: 1112 | Name: Nofar | Gender: Female | Birthday: 03-07-1998 | Age: 26 | Balance: 890
        return "ID: "+this.id+" | Name: "+this.name+" | Gender: "+this.gender+" | Birth Date: "+this.birthDate+" | Age: "+getAge()+" | Balance: "+this.balance;
    }

    public boolean equals(Person person) {
        if(person==null)
        {
            return false;
        }
        if(person.getName().equals(this.name)&&person.getGender().equals(this.gender)&&person.getBirthDate().equals(this.birthDate))
        {
            return true;
        }
        return false;
    }
}
