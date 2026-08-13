import java.util.Objects;
import java.math.BigDecimal;

public class BankAccount{
    private final int id;
    private static int nextId = 0;
    private final String name;
    private BigDecimal balance;

    public BankAccount(String name, BigDecimal initialBalance){
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Account holder's name cannot be blank or null.");
        }
        if(initialBalance==null){
            throw new IllegalArgumentException("Initial balance cannot be null.");
        }
        if(initialBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Initial balance cannot be less than 0.");
        }
        this.name = name;
        id = nextId++;
        this.balance = initialBalance;
    }
    public void depositMoney(BigDecimal amount){
        if(amount==null){
            throw new IllegalArgumentException("Cannot deposit a null amount.");
        }
        if(amount.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("Deposit amount must be greater than 0.");
        }
        balance = balance.add(amount);
    }
    public void withdrawMoney(BigDecimal amount){
        if(amount==null){
            throw new IllegalArgumentException("Cannot withdraw a null amount.");
        }
        if(amount.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0.");
        }
        if(amount.compareTo(balance)>0){
            throw new IllegalStateException("Cannot withdraw more than the account balance.");
        }
        balance = balance.subtract(amount);
    }
    public BigDecimal getBalance(){
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
        if(o==this){
            return true;
        }
        if(!(o instanceof BankAccount)){
            return false;
        }
        BankAccount b = (BankAccount)o;
        return b.getId() == this.id;
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}