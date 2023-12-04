package com.example.demo;
public interface BankFunctions {
    void depositCash(int accountNumber,double amount);
    void withdrawCash(int accountNumber,double amount);
    int displayAccountInfo(int accountNumber);
    void createAccount(BankAccount accounts, AccountHolder accountHolders);

    /*default void newMethod(){

    }
    public default void display(){

    }

    default void method(){

    }*/
}


