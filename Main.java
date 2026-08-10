import java.math.BigDecimal;

public class Main {
    public static void main(String[] args){
        Bank chase = new Bank("Chase bank");
        BankAccount b = chase.openAccount("Jamie", new BigDecimal(0));
        b.depositMoney(BigDecimal.ZERO);
    }
}
