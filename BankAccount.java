import java.util.Objects;

public class BankAccount{
    private final int id;
    private int nextId = 0;
    private final String name;
    private double balance = 0;

    public BankAccount(String name){
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Account holder's name cannot be blank or null.");
        }
        this.name = name;
        id = nextId++;
        balance = 0;
    }
    public void depositMoney(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Deposit amount must be greater than 0.");
        }

        balance += amount;
    }
    public void withdrawMoney(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0.");
        }
        if(amount > balance){
            throw new IllegalStateException("Cannot withdraw more than the account balance.");
        }
        balance -= amount;
    }
    public double getBalance(){
        return balance;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(!(o instanceof BankAccount)){
            return false;
        }
        BankAccount b = (BankAccount)o;
        return b.getId()==this.id;
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}