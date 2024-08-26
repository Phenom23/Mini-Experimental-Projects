package BankSource.Atm;
import BankSource.Toolkits.HashTools;
import BankSource.Bank;
import BankSource.Toolkits.DoubleChecker;
import java.util.HashSet;
import java.util.Scanner;

public class AtmMenu { //FINAL FORM, STABLE AND CLEAN
	
	private HashSet<AtmCustomer> allAtmCustomers; //for exposure purposes
	private String currrentUserName; //it gets it, it's not stored here
	private final String EXITNUM = "4";
	private AtmMemory atmMemory; //for exposure purposes
	
	public AtmMenu(Scanner sc, String name, AtmMemory atmMemory, Bank bank) {
		this.allAtmCustomers = atmMemory.getAllAtmCustomers();
		this.currrentUserName = name;
		this.atmMemory = atmMemory;
		
		String option = "5";
		while (!option.equals("4")) {
			
			menuDialog(EXITNUM);
			option = sc.nextLine().trim();
			
			switch (option) {
				
				case "1":
					deposit(sc); //should be ok
					break;
				
				case "2":
					withdraw(sc, currrentUserName); //should be ok
					break;
				
				case "3":
					transfer(sc, bank); //to be done
					break;
				
				case EXITNUM:
					System.out.println("\nGoodbye");
					break;
				
				default:
					System.out.println("\nPlease enter a valid option!!");
			}
		}
	}
	
	private void menuDialog(String EXITNUM){
		System.out.println("\n=========ATM Menu=========");
		System.out.println("| Name: "+this.currrentUserName +" | Balance: "+ getCustomerBalance(currrentUserName)+" |");
		System.out.println("------------------------------");
		System.out.println("1 - Deposit");
		System.out.println("2 - Withdraw");
		System.out.println("3 - Transfer");
		System.out.println( EXITNUM+" - Exit ATM Main Menu");
		System.out.print("\nPlease enter an option: ");
	}
	
	private double getCustomerBalance(String name){
		for (AtmCustomer i : this.allAtmCustomers) {
			if (i.getName().equals(name)) {
				return i.getBalance();
			}
		}return -1.0;
	}
	
	private void deposit(Scanner sc){ //wrapper of putMoney
		putMoney(sc,currrentUserName,"");
	}
	
	private void withdraw(Scanner sc, String inDebtName){
		pullMoney(sc, inDebtName,"withdraw");
	} //Wrapper of pullMoney
	
	private void transfer(Scanner sc, Bank bank){
		
		//First, it pulls money from your account
		String moneyToBeSend = pullMoney(sc, currrentUserName,"transfer");
		
		if(!moneyToBeSend.isEmpty()){
			
			String receiverName;
			boolean exit = false;
			while (!exit) { //if no premature "exit" happens
				
				receiverName = HashTools.nameProvider(sc, bank, atmMemory, false, "a");
				
				if(receiverName.equals("exit")){ //if premature exit occur
					System.out.println("\nTransfer failed");
					putMoney(sc,currrentUserName,moneyToBeSend);
					exit = true;
				}
				else if (receiverName.equalsIgnoreCase(currrentUserName)) { //it repeats asking for a name if recursion occur
					System.out.println("\nYou can't send money to yourself.");
					continue;
				}
				else{
					putMoney(sc, receiverName, moneyToBeSend);
					System.out.println("\nMoney has been sent successfully");
					exit = true;
				}
			}
		}
	}
	
	
	private String pullMoney(Scanner sc, String inDebtName, String actionTitle){
		
		double upperLimit = getCustomerBalance(inDebtName);
		
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
			for (AtmCustomer i : allAtmCustomers) {
				if (i.getName().equals(inDebtName)) {
					i.setBalance(i.getBalance() - Double.parseDouble(tempAmount));
					return tempAmount; // it returns the amount of money that was pulled from that account
				}
			}
		}
		return "";
	}
	
	//headlessAmount : if not null, it overrides all the dialogs that ask for money to be put
	//It asks for money, it checks if it is valid amount, and it put it to the "name" account
	private void putMoney(Scanner sc, String name, String headlessAmount){
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
		for (AtmCustomer i : allAtmCustomers) {
			//finds the customer, and adjusts his balance accordingly
			if (i.getName().equals(name)) {
				i.setBalance(i.getBalance() + Double.parseDouble(amount));
				break;
			}
		}
	}
}
