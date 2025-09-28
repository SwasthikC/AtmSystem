
import java.util.Scanner;

class BankAccount {
    private String name;
    private long accountNumber;
    private double balance;
    private int pin;
    private static final double MIN_BALANCE_REQUIRED = 500.0;

    public BankAccount(String name, long accountNumber, double balance, int pin) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }
    public String getName() {
        return name;
    }
    public long getAccountNumber() {
        return accountNumber;
    }

    private boolean validatePin(int enteredPin) {
        if (this.pin != enteredPin) {
            System.out.println(" Invalid PIN.");
            return false;
        }
        return true;
    }
    
    public void deposit(int enteredPin, double amount) {
        if (!validatePin(enteredPin)) return;
        if (amount <= 0) {
            System.out.println(" Deposit must be positive.");
            return;
        }
        balance += amount;
        System.out.println(" Deposit successful. New Balance: ₹" + balance);
    }

    public void withdraw(int enteredPin, double amount) {
        if (!validatePin(enteredPin)) return;
        if (amount <= 0) {
            System.out.println(" Invalid withdrawal amount.");
            return;
        }
        if (balance - amount < MIN_BALANCE_REQUIRED) {
            System.out.println(" Withdrawal failed. Must maintain min "+ MIN_BALANCE_REQUIRED + " balance.");
            return;
        }
        balance -= amount;
        System.out.println(" Withdrawal successful. Remaining Balance: ₹" + balance);
    }

    public void printBalance(int enteredPin) {
        if (!validatePin(enteredPin)) return;
        System.out.println(" Account Holder: " + name);
        System.out.println(" Account Number: " + accountNumber);
        System.out.println(" Current Balance: ₹" + balance);
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create multiple accounts
        BankAccount[] accounts = {
            new BankAccount("Rahul Sharma", 123456789L, 1000.0, 1234),
            new BankAccount("Anita Verma", 987654321L, 2000.0, 4321),
            new BankAccount("Suresh Kumar", 555666777L, 1500.0, 1111)
        };

        System.out.println("=== Welcome to Axis Bank ATM ===");

        while (true) {
            // Select account
            System.out.println("\nSelect Account:");
            for (int i = 0; i < accounts.length; i++) {
                System.out.println((i + 1) + ". " + accounts[i].getName() +
                                   " (Acc No: " + accounts[i].getAccountNumber() + ")");
            }
            System.out.println((accounts.length + 1) + ". Exit");
            System.out.print(" Enter choice: ");
            int accChoice = sc.nextInt();

            if (accChoice == accounts.length + 1) {
                System.out.println(" Thank you for banking with us!");
                break;
            }
            if (accChoice < 1 || accChoice > accounts.length) {
                System.out.println(" Invalid account choice. Try again.");
                continue;
            }

            BankAccount currentAcc = accounts[accChoice - 1];

            // Menu-driven ATM for selected account
            while (true) {
                System.out.println("\n--- Welcome, " + currentAcc.getName() + " ---");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Switch Account");
                System.out.print(" Enter choice: ");
                int choice = sc.nextInt();

                if (choice == 4) {
                    System.out.println(" Switching account...");
                    break;
                }

                System.out.print(" Enter PIN: ");
                int enteredPin = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter deposit amount: ₹");
                        double amount = sc.nextDouble();
                        currentAcc.deposit(enteredPin, amount);
                        break;
                    
                    case 2:
                        System.out.print("Enter withdrawal amount: ₹");
                        amount = sc.nextDouble();
                        currentAcc.withdraw(enteredPin, amount);
                        break;
                    case 3 : currentAcc.printBalance(enteredPin); break;
                    default : System.out.println(" Invalid choice. Try again."); break;
                }
            }
        }
        sc.close();
    }
}

