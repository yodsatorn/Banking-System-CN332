/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author Administrator
 */
public class User_BankingSystem {

    static ArrayList<Client> allClients;
    static ArrayList<BankAccount> allAccounts;
    static Scanner input;

    public User_BankingSystem() {
        allAccounts = new ArrayList<BankAccount>();
        allClients = new ArrayList<Client>();
        input = new Scanner(System.in);
        
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
