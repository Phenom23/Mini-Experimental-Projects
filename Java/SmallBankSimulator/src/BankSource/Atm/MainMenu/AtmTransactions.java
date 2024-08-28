package BankSource.Atm.MainMenu;
import BankSource.Atm.AtmCustomer;
import BankSource.Bank.Bank;
import BankSource.Bank.BankOutputUtilities;
import BankSource.Toolkits.DoubleChecker;
import BankSource.Toolkits.HashTools;

import java.util.Scanner;

public class AtmTransactions {  //FINAL FORM, STABLE AND CLEAN - Utility Class for money actions of atm
	
	protected static double getCustomerBalance(String name, Bank bank){
		for (AtmCustomer i : BankOutputUtilities.getAtmMemory(bank)) {
			if (i.getName().equals(name)) {
				return i.getBalance();
			}
		}return -1.0;
	}
	
	
	protected static void deposit(Scanner sc, AtmMenu atmMenu, Bank bank){ //wrapper of putMoney
		putMoney(sc, atmMenu,"", bank);
	}
	
	
	protected static void withdraw(Scanner sc, AtmMenu atmMenu, Bank bank){
		pullMoney(sc, atmMenu,"withdraw", bank);
	} //Wrapper of pullMoney
	
	
	protected static void transfer(Scanner sc, Bank bank, AtmMenu atmMenu){
		
		//First, it pulls money from your account
		String moneyToBeSend = pullMoney(sc, atmMenu,"transfer", bank);
		
		if(!moneyToBeSend.isEmpty()){
			
			String receiverName;
			boolean exit = false;
			while (!exit) { //if no premature "exit" happens
				
				receiverName = HashTools.nameProvider(sc, bank, false, "a");
				
				if(receiverName.equals("exit")){ //if premature exit occur
					System.out.println("\nTransfer failed");
					putMoney(sc,atmMenu,moneyToBeSend, bank);
					exit = true;
				}
				else if (receiverName.equalsIgnoreCase(atmMenu.getCurrrentUserName())) { //it repeats asking for a name if recursion occur
					System.out.println("\nYou can't send money to yourself.");
					continue;
				}
				else{
					putMoney(sc, atmMenu, moneyToBeSend, bank);
					System.out.println("\nMoney has been sent successfully");
					exit = true;
				}
			}
		}
	}
	
	
	protected static String pullMoney(Scanner sc, AtmMenu atmMenu, String actionTitle, Bank bank){
		
		double upperLimit = getCustomerBalance(atmMenu.getCurrrentUserName(), bank);
		
		if (upperLimit == 0) {
			System.out.println("\nYou have no money to "+actionTitle);
			System.out.println("Exiting");
		} else {
			//trying to get a valid amount to pull
			System.out.print("Give an amount of money to "+actionTitle+": ");
			String tempAmount = sc.nextLine().trim();
			while (!DoubleChecker.checker(tempAmount, 0.01, upperLimit)) {
				System.out.print("\nPlease give a valid amount (from 0.01 to "+ upperLimit + "): ");
				tempAmount = sc.nextLine().trim();
			}
			//finding the account of that name, and pulling the money
			for (AtmCustomer i : BankOutputUtilities.getAtmMemory(bank)) {
				if (i.getName().equals(atmMenu.getCurrrentUserName())) {
					i.setBalance(i.getBalance() - Double.parseDouble(tempAmount));
					return tempAmount; // it returns the amount of money that was pulled from that account
				}
			}
		}
		return "";
	}
	
	
	//headlessAmount : if not null, it overrides all the dialogs that ask for money to be put
	//It asks for money, it checks if it is valid amount, and it put it to the "name" account
	protected static void putMoney(Scanner sc, AtmMenu atmMenu, String headlessAmount, Bank bank){
		String amount;
		if(headlessAmount.isBlank()){ //in case you want the dialog for getting the amount
			System.out.print("Give an amount of money to deposit: ");
			amount = sc.nextLine().trim();
			while (!DoubleChecker.checker(amount, 0.01)) {
				//checks whether the amount is valid
				System.out.print("\nPlease give a valid amount: ");
				amount = sc.nextLine().trim();
			}
		}
		else{ //in case you don't need the dialog for the amount, headless operation
			amount = headlessAmount;
		}
		for (AtmCustomer i : BankOutputUtilities.getAtmMemory(bank)) {
			//finds the customer, and adjusts his balance accordingly
			if (i.getName().equals(atmMenu.getCurrrentUserName())) {
				i.setBalance(i.getBalance() + Double.parseDouble(amount));
				break;
			}
		}
	}
}
