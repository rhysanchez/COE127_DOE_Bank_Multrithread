import java.io.*;
import java.util.Random;
import java.util.Scanner;
public class BankoNiRice {
public static int NewRandom(int min, int max) {
    Random rand = new Random();
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;
}
public static void main(String args[])throws IOException, InterruptedException {
    InputStreamReader ir = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(ir);
    Bank myBank = new Bank();
    Thread timer = new Thread (new Time());
    Thread load = new Thread (new Loading());
    Thread safety = new Thread (new Notice());
    Thread ads = new Thread (new Ads());
    timer.start();
    ads.start();
    safety.start();
    load.start();
    int timing;
    timing = NewRandom (0000, 2000);
    int Option = 1, Account_Number, Account_Password, attempts = 0, Pass;
    String Name;
    double Balance, Money;
    System.out.println("Opening");
    while(Option !=5) {
        Thread.sleep(timing);
        System.out.println("[1] Create New Account");
        Thread.sleep(250);
        System.out.println("[2] Deposit");
        Thread.sleep(250);
        System.out.println("[3] Withdraw");
        Thread.sleep(250);
        System.out.println("[4] Account Info and History");
        Thread.sleep(250);
        System.out.println("[5] Quit");       
        System.out.println();
        //System.out.println("Time counter is turned off.");
        System.out.print("                       [1-5]: ");
        Option = Integer.parseInt(br.readLine());
        switch(Option) {
            case 1 : System.out.println("Input name:");
                     Name = br.readLine();
                     System.out.println("Opening balance:");
                     Balance = Double.parseDouble(br.readLine());
                     Thread.sleep(timing);
                     System.out.println("Creating account");
                     System.out.println("Please wait...");
                     Thread.sleep(3000);
                     Thread.sleep(timing);
                     int[] arrDetails= myBank.AddNewAccount(Name, Balance);                     
                     System.out.println("Account Has been created\n Account number: " + arrDetails[0]+"\nYour password : "+ arrDetails[1]);
                     break;
            case 2 : System.out.println("Account number:");
                     Account_Number = Integer.parseInt(br.readLine());
                     System.out.println("Password:");
                     Account_Password = Integer.parseInt(br.readLine());
                     System.out.println("Deposit amount:");
                     Money = Double.parseDouble(br.readLine());
                     myBank.Deposit(Account_Number, Account_Password, Money);
                     System.out.println("Please wait...");
                     Thread.sleep(3000);
                     break;
            case 3 : System.out.println("Account number:");
                     Account_Number = Integer.parseInt(br.readLine());
                     System.out.println("Password:");
                     Account_Password = Integer.parseInt(br.readLine());
                     System.out.println("Withdraw amount:");
                     Money = Double.parseDouble(br.readLine());
                     myBank.Withdraw(Account_Number, Account_Password, Money);
                     System.out.println("Please wait...");
                     Thread.sleep(3000);
                     break;
            case 4 : System.out.println("Account number:");
                     Account_Number = Integer.parseInt(br.readLine());
                     System.out.println("Password:");
                     Account_Password = Integer.parseInt(br.readLine());
                     myBank.Transactions(Account_Number, Account_Password);
                     System.out.println("Please wait...");
                     Thread.sleep(3000);
                     break;
            case 5 : Thread.sleep(timing);
            		 System.out.println("                       Stopping time thread...");
            		 timer.stop();
            		 Thread.sleep(timing);
            		 System.out.println("                       Stopping advertisement thread...");
            		 ads.stop();
            		 Thread.sleep(timing);
            		 System.out.println("                       Stopping notice thread...");
            		 safety.stop();
            		 Thread.sleep(timing);
            		 System.out.println("                       Shutting down...");
            		 Thread.sleep(timing);
                     Option = 5;
                     break;
            default: System.out.println("Error. Enter valid inputs [1-5] only.");
            		 System.out.println("Please wait...");
            		 Thread.sleep(3000);
            		 break;
        }
    }
}
static class Bank {
    private BankAccount[] accounts;
    private int numOfAccounts;
    public Bank() {
        accounts = new BankAccount[100];
        numOfAccounts = 0;
    }
    public int [] AddNewAccount(String Name, Double Balance) {
        BankAccount b = new BankAccount(Name, Balance);
        accounts[numOfAccounts] = b;
        numOfAccounts++;
        int Acc = b.getAccountNum()[0];
        int Pass = b.getAccountNum()[1];
        int[]details = {Acc, Pass};
        return details;
    }
    public void Withdraw(int Account_Number, int pass, double Money) {
        for (int i =0; i<numOfAccounts; i++) {     
            int a = accounts[i].getAccountNum()[0];
            if (Account_Number == a) {
                int p = accounts[i].getAccountNum()[1];
                if( pass == p) {
                    accounts[i].withdraw(Money);
                    return;
                }
            }   
        }  
        System.out.println("                       Invalid Account number or Password.");
    }
    public void Deposit(int Account_Number, int pass, double Money) {  
        for (int i =0; i<numOfAccounts; i++) {     
            int a = accounts[i].getAccountNum()[0];
            if (Account_Number == a) {
                int p = accounts[i].getAccountNum()[1];
                if( pass == p) {
                    accounts[i].deposit(Money);
                    return;   
                }
            }   
        }  
        System.out.println("                       Invalid Account number or Password.");
    }
    public void Transactions(int Account_Number, int pass) {
        for(int i = 0;i<numOfAccounts; i++) {
            int a = accounts[i].getAccountNum()[0];
            if (Account_Number ==  a ) {
                int p = accounts[i].getAccountNum()[1];
                if( pass == p) {
                    System.out.println(accounts[i].getAccountInfo());
                    System.out.println("             Last transaction: " + accounts[i].getTransactionInfo(accounts[i].getNumberOfTransactions()-1));
                    return;
                }
            }
        }
        System.out.println("                       Invalid Account number or Password.");
    }
}
static class BankAccount{
    private int User_Password;
    private int accountNum;
    private String customerName;
    private double balance;
    private double[] transactions;
    private String[] transactionsSummary;
    private int numOfTransactions;
    private  static int noOfAccounts=0;
    public String getAccountInfo(){          
        return "                        Account number: " + accountNum + "\n                        Customer Name: " + customerName + "\n                        Balance:" + balance +"\n";
    }
    public String getTransactionInfo(int n) {
        String transaction = transactionsSummary[n];
        return transaction;
        }
    public BankAccount(String abc, double xyz){        
        customerName = abc;        
        balance = xyz;        
        noOfAccounts ++;
        User_Password = NewRandom(0000, 9999);
        accountNum = NewRandom(100000000, 999999999);       
        transactions = new double[100];                         
        transactionsSummary = new String[100];               
        transactions[0] = balance;                      
        transactionsSummary[0] = "Balance:P" + Double.toString(balance) + " was deposited.";       
        numOfTransactions = 1;             
    }
    public int [] getAccountNum(){
        int account = accountNum;
        int Pass = User_Password;
        int [] details = {account, Pass};
        return details;
    }
    public int getNumberOfTransactions() {           
        return numOfTransactions;          
    }         
    public void deposit(double amount){         
        if (amount<=0) {         
            System.out.println("Deposit amount should be positive, exit if insufficient money.");        
        } else {          
            balance = balance + amount;            
            transactions[numOfTransactions] = amount;            
            transactionsSummary[numOfTransactions] = "P" + Double.toString(amount) + " was deposited.";            
            numOfTransactions++;
            System.out.println("                       Deposit Success");
        }         
    }
    public void withdraw(double amount) {                   
        if (amount<=0){                
            System.out.println("Withdrawn amount should be positive"); 
        } 
        else {  
            if (balance < amount) {  
                System.out.println("Insufficient balance");
            } else {  
                balance = balance - amount;
                transactions[numOfTransactions] = amount;
                transactionsSummary[numOfTransactions] = "P" + Double.toString(amount) + " was withdrawn.";
                numOfTransactions++;
                System.out.println("                       Withdraw Success");
            }
        }
    }
}
}