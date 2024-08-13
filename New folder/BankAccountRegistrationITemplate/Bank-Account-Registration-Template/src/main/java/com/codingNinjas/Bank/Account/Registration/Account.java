package com.codingNinjas.Bank.Account.Registration;


public interface Account {

    /** This method returns the type of Account in string format, for example "Current Account". **/
    String getAccountType();

   
    void addBalance(double balance);

    /** This method returns the balance amount in double format. **/
    double getBalance();
}
