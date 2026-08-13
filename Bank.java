import java.util.HashMap;
import java.math.BigDecimal;
import java.util.Objects;

public class Bank {
    private final HashMap<Integer, BankAccount> accounts;
    private final int id;
    private static int nextId = 0;
    private final String name;

    public Bank(String name){
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Bank name cannot be null or blank.");
        }
        id = nextId++;
        this.name = name;
        accounts = new HashMap<>();
    }
    public BankAccount openAccount(String accountHolderName, BigDecimal initialBalance){
        BankAccount b = new BankAccount(accountHolderName, initialBalance);
        accounts.put(b.getId(), b);
        return b;
    }
    public BankAccount findAccountById(int id){
        BankAccount b = accounts.get(id);
        if(b==null){
            throw new IllegalStateException("Account with ID " + id + " does not exist in " + name + ".");
        }
        return b;
    }
    public void closeAccount(int id){
        BankAccount b = findAccountById(id);
        if(b.getBalance().compareTo(BigDecimal.ZERO)!=0){
            throw new IllegalStateException("Cannot close an account unless its balance is exactly 0.");
        }
        accounts.remove(id);
    }
    public void transfer(int id1, int id2, BigDecimal amount){
        if(amount==null){
            throw new IllegalArgumentException("Cannot transfer a null amount.");
        }
        if(amount.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("Transfer amount must be greater than 0.");
        }
        BankAccount b1 = findAccountById(id1);
        BankAccount b2 = findAccountById(id2);

        if(b1.equals(b2)){
            throw new IllegalArgumentException("Cannot transfer money to the same account.");
        }
        if(amount.compareTo(b1.getBalance()) > 0){
            throw new IllegalStateException("Cannot transfer more than the source account balance.");
        }

        b1.withdrawMoney(amount);
        b2.depositMoney(amount);
    }
    private void displayAccount(BankAccount b){
        System.out.println("Account information for account with ID " + b.getId() + ":");
        System.out.println("Account holder name: " + b.getName());
        System.out.println("Account balance: " + b.getBalance());
        System.out.println();
    }
    public void displayAccounts(){
        for(BankAccount b : accounts.values()){
            displayAccount(b);
        }
    }
    public BigDecimal getTotalMoney(){
        BigDecimal sum = BigDecimal.ZERO;
        for(BankAccount b : accounts.values()){
            sum = sum.add(b.getBalance());
        }
        return sum;
    }
    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(!(o instanceof Bank)){
            return false;
        }
        Bank b = (Bank)o;
        return b.getId() == this.id;
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
