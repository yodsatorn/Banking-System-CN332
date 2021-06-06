/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jedsadagon Mos
 */
import java.util.Scanner;


public class User {
    
    static Scanner input;
    public static void main(String[] args) {

        input = new Scanner(System.in);
        while (true) {
            System.out.println("1- Admin");
            System.out.println("2- User");
            System.out.println("3- exit");
            System.out.print("please enter your role: ");
            int option = input.nextInt();
            if (option == 1) {
                new BankingSystem();
            } else if (option == 2) {
                new User_BankingSystem();
            }
            else if (option == 3) {
                break;
            }
        
      }
    }
}
