import java.math.BigDecimal;

public class Main {
    public static void main(String[] args){
        Bank bank = new Bank("Test Bank");

        BankAccount a =
            bank.openAccount("Alice", new BigDecimal("100.00"));

        BankAccount b =
            bank.openAccount("Bob", new BigDecimal("50.00"));

        bank.transfer(
            a.getId(),
            b.getId(),
            new BigDecimal("25.00")
        );

        //System.out.println(a.getBalance()); // 75.00
        //System.out.println(b.getBalance()); // 75.00
        //System.out.println(bank.getTotalMoney()); // 150.00

        // Same account
        //bank.transfer(a.getId(), a.getId(), new BigDecimal("10.00"));

        // Too much
        //bank.transfer(a.getId(), b.getId(), new BigDecimal("1000.00"));

        // Zero
        //bank.transfer(a.getId(), b.getId(), BigDecimal.ZERO);

        // Negative
        //bank.transfer(a.getId(), b.getId(), new BigDecimal("-10.00"));

        // Null
        //bank.transfer(a.getId(), b.getId(), null);

        // Can't close account with money
        //bank.closeAccount(a.getId());
    }
}
