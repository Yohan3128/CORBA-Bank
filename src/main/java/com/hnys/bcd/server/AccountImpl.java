package com.hnys.bcd.server;

import BankingApp.Account;
import BankingApp.AccountPOA;
import BankingApp.InsufficientBalance;

import java.sql.SQLOutput;
import java.util.HashMap;

public class AccountImpl extends AccountPOA {

    private HashMap<String, Double> db = new HashMap<>();

    public AccountImpl() {
        db.put("ACC001", 500.00);
        db.put("ACC002", 1500.50);
        db.put("ACC003", 750.00);
    }

    @Override
    public double getBalance(String accNo) {
        return db.getOrDefault(accNo, 0.00);
    }

    @Override
    public void deposit(String accNo, double amount) {
        db.put(accNo, amount + getBalance(accNo));

        System.out.println("Server Log : LKR "+amount+" deposited to account "+accNo);
    }

    @Override
    public void withdraw(String accNo, double amount) throws InsufficientBalance {

        double currentBalance = getBalance(accNo);

        if (currentBalance < amount) {
            System.out.println("Server Log : Failed to withdraw from account "+accNo);
            throw new InsufficientBalance("Insufficient Balance");
        }

        db.put(accNo, currentBalance - amount);

        System.out.println("Server Log : LKR "+amount+" withdrawn from account "+accNo);
    }
}
