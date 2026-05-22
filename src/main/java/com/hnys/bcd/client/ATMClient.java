package com.hnys.bcd.client;

import BankingApp.Account;
import BankingApp.AccountHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Scanner;

public class ATMClient {
    public static void main(String[] args) {
        ORB orb = ORB.init(args, null);
        try {
            Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            Account account = AccountHelper.narrow(ncRef.resolve_str("Bank"));

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Your Account No:");
            String accNo = scanner.nextLine();

            boolean running = true;

            while (running) {
                System.out.println("1. check Balance | 2. Deposit | 3. Withdrawal | 4. Exit");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Current Balance: " + account.getBalance(accNo));
                        break;

                    case 2:
                        System.out.println("Enter Amount to Deposit : ");
                        double amount = scanner.nextDouble();
                        account.deposit(accNo, amount);
                        System.out.println("Amount deposited : " + amount);
                        System.out.println("Current Balance : " + account.getBalance(accNo));
                        break;

                    case 3:
                        System.out.println("Enter Amount to Withdrawal : ");
                        double withdrawalAmount = scanner.nextDouble();
                        account.withdraw(accNo, withdrawalAmount);
                        System.out.println("Amount withdrawn : " + withdrawalAmount);
                        System.out.println("Current Balance : " + account.getBalance(accNo));
                        break;

                    case 4:
                        System.out.println("Exit");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice");
                }
            }
            scanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
