/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class User_BankingSystem {

    static ArrayList<Client> allClients;
    static ArrayList<BankAccount> allAccounts;
    static Scanner input;    private String name;
    private String address;
    private String phone;
    private double balance;
    private String password;
    private int i ;

    public User_BankingSystem() {
        allAccounts = new ArrayList<BankAccount>();
        allClients = new ArrayList<Client>();
        input = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        try {
        BufferedReader reader = new BufferedReader(new FileReader("AccountDataBase.csv"));
        String line = null;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        }
        catch (IOException e) {
            System.out.println("Exception caught:Division by zero");
        }

        
        for ( i = 0 ; i < lines.size() ; i++)
        {
        String[] array = lines.get(i).split(",");
        System.out.println(array.length);
        name = array[1];
        address = array[2];
        phone = array[3];
        password = array[4];
        balance = Double.parseDouble(array[6]);
        Client newClient = new Client(name, address, phone, password);
        BankAccount newAccount = null;

        if (array[5].equals("Basic Bank Account"))
        {
            newAccount = new BankAccount(balance);
        }
        else
        {
            newAccount = new SavingsBankAccount(balance);
        }
        newAccount.setOwner(newClient);
        newClient.setAccount(newAccount);
        allAccounts.add(newAccount);
        allClients.add(newClient);
        
        }

        
        while (true) {
            System.out.println("1- withdraw");
            System.out.println("2- deposit");
            System.out.println("3- exit");
            int option = input.nextInt();
            if (option == 1) {
                withdraw();
            } else if (option == 2) {

                deposit();
            } else if (option == 3) {
                break;
            }
        }
    }


    static int searchById(int accountId) {
        for (int i = 0; i < allAccounts.size(); i++) {
            if (accountId == allAccounts.get(i).getAccountId()) {
                return i;
            }
        }
        return -1;
    }

    static void deposit() {
        System.out.println("enter account id");
        int id = input.nextInt();
        System.out.println("enter account password");
        String password = input.next();

        if (allAccounts.get(id).getOwner().password.equals(password)) {
            int index = searchById(id);
            if (index == -1) {
                System.err.println("account not found");
                return;
            }
            System.out.println("enter amount to deposit");
            double amountOfMoney = input.nextDouble();
            if (allAccounts.get(index).deposit(amountOfMoney)) {
                System.out.println("successfull operation");
                DatabaseCSV database = new DatabaseCSV();
                database.savingTransaction("deposit","" + amountOfMoney , allAccounts.get(index));
            }
        } else {
            System.err.println("Incorrect Password");
        }

    }

    static void withdraw() {
        System.out.println("enter account id");
        int id = input.nextInt();
        System.out.println("enter account password");
        String password = input.next();

        if (allAccounts.get(id).getOwner().password.equals(password)) {
            int index = searchById(id);
            if (index == -1) {
                System.err.println("account not found");
                return;
            }
            System.out.println("enter amount to withdraw");
            double amountOfMoney = input.nextDouble();
            if (allAccounts.get(index).withdraw(amountOfMoney)) {
                System.out.println("successfull operation");
                DatabaseCSV database = new DatabaseCSV();
                database.savingTransaction("withdraw","" + amountOfMoney , allAccounts.get(index));
            }
        } else {
            System.err.println("Incorrect Password");
        }
    }
    

}
