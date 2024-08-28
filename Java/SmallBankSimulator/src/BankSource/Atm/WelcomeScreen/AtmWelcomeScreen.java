package BankSource.Atm.WelcomeScreen;
import BankSource.Bank.Bank;
import BankSource.Bank.BankInputUtilities;

import java.util.HashSet;
import java.util.Scanner;

import static BankSource.Atm.WelcomeScreen.AtmWelcScrActions.*;

public class AtmWelcomeScreen { //FINAL FORM, STABLE AND CLEAN
	
	private final String EXITNUM = "5";
	
	private void welcomeScreenDialog(String EXITNUM) {
		System.out.println("\n======ATM Welcome Screen======");
		System.out.println("1 - Register a new ATM account");
		System.out.println("2 - Sign in");
		System.out.println("---------");
		System.out.println("3 - Memory Diagnostics [STUFF ONLY]");
		System.out.println("4 - Reset ATM Memory");
		System.out.println("---------");
		System.out.println(EXITNUM + " - Exit Atm");
		System.out.print("\nPlease enter an option: ");
	}
	
	public AtmWelcomeScreen(Scanner sc, Bank bank) { //An atm wrapper screen
		
		String option = "";
		
		while (!option.equals(EXITNUM)) {
			
			welcomeScreenDialog(EXITNUM);
			option = sc.nextLine().trim();
			
			switch (option) {
				
				case "1":
					//Registering a new customer (with unique and valid UserName and with valid password)
					//after successful registration, menu pops up
					registerCustomer(bank, false,false);
					break;
				
				case "2":
					//Signing in customer, after successful sign in, menu pops up
					signInCustomer(sc, bank);
					break;
				
				case "3":
					//MENU ONLY FOR STUFF (it shows all atm  users and their credentials)
					hiddenStuffMenu(bank);
					break;
				
				case "4":
					BankInputUtilities.setAtmMemory(new HashSet<>(),bank);
					System.out.println("\nThe memory of the ATM has been reset successfully!");
					break;
				
				case EXITNUM:
					System.out.println("\nBye"); // it works
					break;
				
				default:
					System.out.println("\nPlease enter a valid option!!");
			}
		}
	}
}