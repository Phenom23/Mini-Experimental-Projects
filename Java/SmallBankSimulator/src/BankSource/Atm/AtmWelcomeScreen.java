package BankSource.Atm;
import BankSource.Bank;
import BankSource.Toolkits.NotNullStringLoader;
import BankSource.Toolkits.HashTools;

import java.util.Scanner;

public class AtmWelcomeScreen { //FINAL FORM, STABLE AND CLEAN
	
	private AtmMemory atmMemory; //just exposes its bank's atm memory, does not hold on its own
	private Boolean headless;
	private String newCustomerName; //useful for headless operations, outside of this class
	private final String EXITNUM = "5";
	
	private void welcomeScreenDialog(String EXITNUM) {
		System.out.println("\n======ATM Welcome Screen======");
		System.out.println("1 - Register a new ATM account");
		System.out.println("2 - Sign in");
		System.out.println("---------");
		System.out.println("3 - Memory Diagnostics [STUFF ONLY]");
		System.out.println("4 - Reset ATM Memory");
		System.out.println("---------");
		System.out.println( EXITNUM+" - Exit Atm");
		System.out.print("\nPlease enter an option: ");
	}
	
	public AtmWelcomeScreen(Scanner sc, Bank bank, Boolean headless) { //An atm wrapper screen
		
		this.atmMemory = bank.getAtmMemory(); //for exposure purposes
		this.headless = headless; //if headless, bypass the intro menu screen
		
		if(!headless){
			String option = "";
			
			while (!option.equals(EXITNUM)) {
				
				welcomeScreenDialog(EXITNUM);
				option = sc.nextLine().trim();
				
				switch (option) {
					
					case "1":
						//Registering a new customer (with unique and valid UserName and with valid password)
						//after successful registration, menu pops up
						registerCustomer(sc,bank,false);
						break;
					
					case "2":
						//Signing in customer, after successful sign in, menu pops up
						signInCustomer(sc, bank);
						break;
						
					case "3":
						//MENU ONLY FOR STUFF (it shows all atm  users and their credentials)
						hiddenStuffMenu(sc);
						break;
						
					case "4":
						resetAtmMemory();
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
	
	public String getNewCustomerName(){
		return this.newCustomerName;
	}
	
	
	
	public boolean registerCustomer (Scanner sc, Bank bank, Boolean crossBase){
		
		//returns true if registration was completed
		//returns false if premature exit happened
		
		this.newCustomerName = "";
		boolean uniqueName = false;
		System.out.println("\n=======ATM Registration Screen=======");
		
		while (!uniqueName) { //loading a unique and valid UserName
			
			if (!crossBase){
				//NOT considering the uniqueness of the name, in the queues, only in the atm memory
				newCustomerName = HashTools.nameProvider(sc,bank,atmMemory,true,"a");
			}
			else{
				//Considering the uniqueness in the queues as well, useful for outside use
				newCustomerName = HashTools.nameProvider(sc,bank,atmMemory,true,"qa");
			}
			
			//if the user decides to exit prematurely
			if (newCustomerName.equals("fail")) { return false;}
			
			else{
				/* if the UserName is not already registered by
				another customer in atm (& not in queues [optional]),
				and user doesn't want to exit prematurely */
				uniqueName = true;
			}
		}
		
		String tempNewPasskey;
		
		//loading a valid password (the name is now ok)
		NotNullStringLoader loader = new NotNullStringLoader(sc, "Give a passkey");
		if (loader.getWannaExit()) {
			return false;
		}
		tempNewPasskey = loader.getString(); //the password, null safe
		
		//Now that both are ok, save them
		atmMemory.getAllAtmCustomers().
				add(new AtmCustomer(newCustomerName, tempNewPasskey));
		
		//a main atm menu is opening
		if(!headless){
			System.out.println("\nRegistration was successful");
			new AtmMenu(sc, newCustomerName, atmMemory, bank);
		}
		return true;
	}
	
	
	
	private void signInCustomer (Scanner sc, Bank bank){
		
		boolean matchingName = false;
		String tempName = "";
		NotNullStringLoader loader;
		
		System.out.println("\n=======Sign-In Screen=======");
		//loading a name, checking if it already exists and it re-asks until it's unique, or until customer exists prematurely
		
		loader = new NotNullStringLoader(sc,"Give a name");
		while(!loader.getWannaExit()){ //as long as not premature exiting
			if(HashTools.nameFinder(atmMemory, bank, loader.getString(),"a")){ //crossBase = a | looks on atm memory
				matchingName = true;
				tempName = loader.getString();
				break;
			}
			loader = new NotNullStringLoader(sc,"Give a VALID name");
		}
		//loading a matching password for that PARTICULAR founded UserName
		if (matchingName) {
			
			String tempPasskey;
			boolean passkeyUnlock = false;
			
			//While the password is false
			while (!passkeyUnlock) {
				
				loader = new NotNullStringLoader(sc, "Give a passkey");
				//if user does NOT want to exit
				if (!loader.getWannaExit()) {
					
					tempPasskey = loader.getString();
					for (AtmCustomer tempCustomer : atmMemory.getAllAtmCustomers()) {
						//if matching customer with that UserName has the same passkey as the user entered
						if (tempCustomer.getName().equalsIgnoreCase(tempName) &&
							tempCustomer.getPasskey().equals(tempPasskey))
						{
							System.out.println("\nYou logged in successfully !");
							passkeyUnlock = true;
							//a main atm menu is opening
							new AtmMenu(sc, tempName, atmMemory, bank);
							break;
						}
					}
					System.out.println("\nWrong password, try again");
				}
				else break;
			}
		}
	}
	
	
	
	private void hiddenStuffMenu(Scanner sc){ // a quick print of all atm users, password required
		final String password = "admin2030"; String temp;
		do{
			System.out.print("Enter the stuff password: (type 'exit' for exiting): ");
			temp = sc.nextLine().trim();
			
			if(!temp.equals(password) && !temp.equalsIgnoreCase("exit") ){
				System.out.println("\nInvalid password!");
			}
		} while(!temp.equals(password) && !temp.equalsIgnoreCase("exit") );
		
		if(!temp.equalsIgnoreCase("exit")){
			System.out.println("\n=======All ATM Customers [STUFF ONLY]=======");
			for(AtmCustomer i: atmMemory.getAllAtmCustomers()){
				System.out.println(i);
			}
		}
	}
	
	private void resetAtmMemory(){
		this.atmMemory = new AtmMemory(); //modifies the bank's actual ATM memory (passed by reference)
	}
}