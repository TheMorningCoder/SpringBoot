package com.codingNinjas.Bank.Account.Registration;

/**

  This class is an implementation of a "Account" Interface based on the selection 
  done in the console for account type .You need to complete this class
  based on the following tasks.

     Tasks:
   a. Create attribute amount(double) 
   b. Override the methods of Account Interface.
   c. Build the logic for all the methods based on the description mentioned in the Account Interface.

**/

	public class currentAccount implements Account{
	double amount;
	public void init() {
		System.out.println("Current Account has been created and I'm in the init() method");
	}

	@Override
	public String getAccountType() {
		return "Current Account";
	}

	@Override
	public void addBalance(double balance) {
		amount=amount+balance;
	}

	@Override
	public double getBalance() {
		return amount;
	}

}
