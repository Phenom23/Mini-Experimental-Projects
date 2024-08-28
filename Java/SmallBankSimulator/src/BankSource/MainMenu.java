package BankSource;
import BankSource.Atm.WelcomeScreen.AtmWelcScrActions;
import BankSource.Atm.WelcomeScreen.AtmWelcomeScreen;
import BankSource.Bank.Bank;
import BankSource.Bank.BankInputUtilities;
import BankSource.Bank.BankOutputUtilities;
import BankSource.Bank.Customer;
import BankSource.Simulator.Simulator;
import BankSource.Toolkits.HashTools;
import BankSource.Toolkits.IntegerChecker;
import FileMethods.RestoreInstance;
import FileMethods.SaveInstance;
import java.util.Scanner;

public class MainMenu { //FINAL FORM, STABLE AND CLEAN
	
	//The main menu of the program, where everything start and end up. The "main".
	
	private Bank bank; //The real bank initialization is here. Every other class just inherits it.
	private final String EXITNUM = "8";
	
	public MainMenu() {
		Scanner sc = new Scanner(System.in);
		bank = new Bank(sc);
		
		String option = "";
		
		while(!option.equals(EXITNUM)) {
			
			MenuDialog();
			option = sc.nextLine().trim();
			
			switch (option){
				case "1":
					mainAddACustomer(sc); //Wrapper of: bank.customerEnters + atm registerCustomer (2 in 1)
					System.out.println("\n"+bank);
					break;
					
				case "2":
					BankOutputUtilities.customerServed(bank);
					System.out.println("\n"+bank);
					break;
					
				case "3":
					Simulator sim = new Simulator(sc,bank);
					//this.bank = sim.getBank(); //Doesn't delete ATM Memory, only the queues
					break;
					
				case "4":
					System.out.println(bank); //Prints the current state of queues
					break;
					
				case "5":
					new AtmWelcomeScreen(sc, bank); //Launches ATM environment, with 'bank' instance as the "database"
					break;
					
				case "6":
					SaveInstance.SaveInstance(bank); //the bank object has it all...
					System.out.println("\nThe instance of the current Bank has been saved successfully!");
					break;
					
				case "7":
					bank = RestoreInstance.RestoreInstance();
					System.out.println("\nThe instance of the current Bank has been restored successfully!");
					break;
					
				case "8":
					System.out.println("\nGoodbye");
					break;
					
				default:
					System.out.println("\nPlease enter a valid option!!");
			}
		}
	}
	
	
	private void MenuDialog(){
		System.out.println("\nAction Menu:");
		System.out.println("======================");
		System.out.println("1 - Add a customer");
		System.out.println("2 - Serve a customer");
		System.out.println("---------");
		System.out.println("3 - Run a semiRandom Simulation (RESETS all queues)");
		System.out.println("4 - See the current state of queues");
		System.out.println("---------");
		System.out.println("5 - Use the ATM");
		System.out.println("---------");
		System.out.println("6 - Save");
		System.out.println("7 - Restore ");
		System.out.println("---------");
		System.out.println("8 - Exit");
		System.out.print("\nGive an option: ");
	}
	
	
	private void mainAddACustomer(Scanner sc){
		
		Customer customer = new Customer(); //creates a regular customer
		String regOpt; //registration option: 1 - ATM registration + queue entrance, 2 - Only Queue Entrance
		
		System.out.print("Would you like to also create an ATM account? [1-Yes, 2-No]: ");
		while(!IntegerChecker.checker((regOpt = sc.nextLine()),1,2)){ //check Toolkits package
			System.out.print("\nPlease provide a VALID number [1-Yes, 2-No]");
		}
		
		if(regOpt.equals("1")){ // 1 - ATM registration + queue entrance
			
			//it firsts make a atmAccount
			// if registration of atm account was successful
			String newCustName;
			if(!(newCustName = AtmWelcScrActions.registerCustomer(bank, true,true)).equals("fail")){
				// it also adds the new atm's customer's name as a new regular customer, to a queue
				// it makes sure the name of the atm account and the name of the customer in the queue are both unique to their "databases", and same
				customer.setName(newCustName);
				customer.setPriority();
				BankInputUtilities.customerEnters(bank, customer,true);
			}
			else{ //it switches mode, if premature exit happens(from atm registration)
				regOpt = "2";
			}
		}
		
		boolean prematureExit = false; // flag that checks if customer want ot exit the regular queue registration
		if (regOpt.equals("2")){ // 2 - Only Queue Entrance
			
			System.out.println("\n=======Queue Check in=======");
			String customerName = HashTools.nameProvider(sc, bank,true,"q");
			if(!customerName.equals("fail")){ // "fail" means premature exit
				customer.setName(customerName);
				customer.setPriority();
				BankInputUtilities.customerEnters(bank, customer,true);
			}
			else
				prematureExit = true;
		}
		System.out.println("\n----------------------------------------");
		
		if (!prematureExit) {
			
			if (regOpt.equals("1"))
				System.out.println("Customer has entered a queue and has been register to the ATM");
			else
				System.out.println("Customer has entered a queue");
		}
	}
}
