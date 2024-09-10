import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance: " + balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void transfer(Account targetAccount, double amount) {
        if (amount <= balance) {
            this.withdraw(amount);
            targetAccount.deposit(amount);
            transactionHistory.add("Transferred " + amount + " to account " + targetAccount.getAccountNumber());
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for account " + accountNumber + ":");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class OnlineBankingSystem {
    private static ArrayList<Account> accounts = new ArrayList<>();

    public static Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Online Banking System");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. Transaction History");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.next();
                    System.out.print("Enter initial balance: ");
                    double balance = scanner.nextDouble();
                    accounts.add(new Account(accountNumber, balance));
                    System.out.println("Account created successfully!");
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.next();
                    Account account = findAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter amount to deposit: ");
                        double amount = scanner.nextDouble();
                        account.deposit(amount);
                        System.out.println("Amount deposited successfully!");
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.next();
                    account = findAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double amount = scanner.nextDouble();
                        account.withdraw(amount);
                        System.out.println("Amount withdrawn successfully!");
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                case 4:
                    System.out.print("Enter source account number: ");
                    String sourceAccountNumber = scanner.next();
                    System.out.print("Enter target account number: ");
                    String targetAccountNumber = scanner.next();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    Account sourceAccount = findAccount(sourceAccountNumber);
                    Account targetAccount = findAccount(targetAccountNumber);
                    if (sourceAccount != null && targetAccount != null) {
                        sourceAccount.transfer(targetAccount, transferAmount);
                        System.out.println("Amount transferred successfully!");
                    } else {
                        System.out.println("One or both accounts not found!");
                    }
                    break;
                case 5:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.next();
                    account = findAccount(accountNumber);
                    if (account != null) {
                        System.out.println("Account Balance: " + account.getBalance());
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                case 6:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.next();
                    account = findAccount(accountNumber);
                    if (account != null) {
                        account.printTransactionHistory();
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                case 7:
                    System.out.println("Exiting... Thank you for using Online Banking System.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}