import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class DatabaseCSV implements DatabaseCSVInterface{
    private int bankAccID;
    private String name;
    private String address;
    private String phone;
    private double balance;
    private String accountType;
   
    
    public void savingAcc(BankAccount bankAcc, Client clientAcc, int accType ,double balance) {
        bankAccID = bankAcc.getAccountId();
        name = clientAcc.getName();
        address = clientAcc.getAddress();
        phone = clientAcc.getPhone();
        balance = bankAcc.getBalance();
        if(accType == 1){
            accountType = "Basic Bank Account";
        } else {
            accountType = "Saving Bank Account";
        }
        String path = "AccountDataBase.csv";
        File file = new File(path);   
        FileWriter writer;
        try {
            writer = new FileWriter(file,true);
            String savingInfo = String.format("%d,%s,%s,%s,%s,%f\r\n", bankAccID,name,address,phone,accountType,balance);
            writer.write(savingInfo);
            writer.close();
            System.out.println("Database updated");

        } catch (IOException error){
            error.printStackTrace();
        }
        
    }

    
    public void savingTransaction(String transaction,String amountOftransaction,BankAccount bankAcc) {
        balance = bankAcc.getBalance();
        bankAccID = bankAcc.getAccountId();
        String path = "TransactionDataBase.csv";
        File file = new File(path);   
        FileWriter writer;
        try {
            writer = new FileWriter(file,true);
            String savingInfo = String.format("%d,%s,%s,%s\r\n", bankAccID,transaction,amountOftransaction,"balance: "+balance);
            writer.write(savingInfo);
            writer.close();
            System.out.println("Database transaction updated");

        } catch (IOException error){
            error.printStackTrace();
        }
        
    }

    public void savingTransaction(String transaction,String amountOftransaction,SavingsBankAccount bankAcc) {
        String path = "TransactionDataBase.csv";
        File file = new File(path);   
        FileWriter writer;
        try {
            writer = new FileWriter(file,true);
            String savingInfo = String.format("%d,%s,%s,%s\r\n", bankAccID,transaction,amountOftransaction,"balance: "+balance);
            writer.write(savingInfo);
            writer.close();
            System.out.println("Database transaction updated");

        } catch (IOException error){
            error.printStackTrace();
        }
        
    }
    
}
