package BankSource.Atm.MainMenu;
import BankSource.Bank.Bank;
import java.util.Scanner;
import static BankSource.Atm.MainMenu.AtmTransactions.*;

public class AtmMenu { //FINAL FORM, STABLE AND CLEAN
	
	private String currrentUserName; //it gets it, it's not stored here, but at AtmWelcomeScreen
	private final String EXITNUM = "4";
	
	public AtmMenu(Scanner sc, String name, Bank bank) {
		this.currrentUserName = name;
		
		String option = "5";
		while (!option.equals("4")) {
			
			menuDialog(EXITNUM, bank);
			option = sc.nextLine().trim();
			
			switch (option) {
				
				case "1":
					deposit(sc,this, bank); //should be ok
					break;
				
				case "2":
					withdraw(sc, this, bank); //should be ok
					break;
				
				case "3":
					transfer(sc, bank,this); //to be done
					break;
				
				case EXITNUM:
					System.out.println("\nGoodbye");
					break;
				
				default:
					System.out.println("\nPlease enter a valid option!!");
			}
		}
	}
	
	private void menuDialog(String EXITNUM, Bank bank){
		System.out.println("\n=========ATM Menu=========");
		System.out.println("| Name: "+this.currrentUserName +" | Balance: "+ getCustomerBalance(this.currrentUserName,bank)+" |");
		System.out.println("------------------------------");
		System.out.println("1 - Deposit");
		System.out.println("2 - Withdraw");
		System.out.println("3 - Transfer");
		System.out.println( EXITNUM + " - Exit ATM Main Menu");
		System.out.print("\nPlease enter an option: ");
	}
	
	protected String getCurrrentUserName(){
		return this.currrentUserName;
	}
}
