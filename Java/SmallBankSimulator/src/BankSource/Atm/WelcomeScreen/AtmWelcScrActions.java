package BankSource.Atm.WelcomeScreen;
import BankSource.Atm.AtmCustomer;
import BankSource.Atm.MainMenu.AtmMenu;
import BankSource.Bank.Bank;
import BankSource.Bank.BankOutputUtilities;
import BankSource.Toolkits.HashTools;
import BankSource.Toolkits.NotNullStringLoader;

import java.util.Scanner;

public class AtmWelcScrActions { //FINAL FORM, STABLE AND CLEAN - Utility Class for atm welcome portal actions
	
	public static String registerCustomer (Bank bank, Boolean crossBase, Boolean headless){
		
		Scanner sc = new Scanner(System.in);
		//returns the name if registration was completed
		//returns false if premature exit happened
		
		String newCustomerName = "false";
		
		boolean uniqueName = false;
		System.out.println("\n=======ATM Registration Screen=======");
		
		while (!uniqueName) { //loading a unique and valid UserName
			
			if (!crossBase){
				//NOT considering the uniqueness of the name, in the queues, only in the atm memory
				newCustomerName = HashTools.nameProvider(sc,bank,true,"a");
			}
			else{
				//Considering the uniqueness in the queues as well, useful for outside use
				newCustomerName = HashTools.nameProvider(sc,bank,true,"qa");
			}
			
			//if the user decides to exit prematurely
			if (newCustomerName.equals("fail")) { return "false";}
			
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
			return "false";
		}
		tempNewPasskey = loader.getString(); //the password, null safe
		
		//Now that both are ok, save them
		BankOutputUtilities.getAtmMemory(bank).
				add(new AtmCustomer(newCustomerName, tempNewPasskey));
		
		//a main atm menu is opening
		if(!headless){
			System.out.println("\nRegistration was successful");
			new AtmMenu(sc,newCustomerName, bank);
		}
		return newCustomerName;
	}
	
	
	
	protected static void signInCustomer (Scanner sc, Bank bank){
		
		boolean matchingName = false;
		String tempName = "";
		NotNullStringLoader loader;
		
		System.out.println("\n=======Sign-In Screen=======");
		//loading a name, checking if it already exists and it re-asks until it's unique, or until customer exists prematurely
		
		loader = new NotNullStringLoader(sc,"Give a name");
		while(!loader.getWannaExit()){ //as long as not premature exiting
			if(HashTools.nameFinder( bank, loader.getString(),"a")){ //crossBase = a | looks on atm memory
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
					for (AtmCustomer tempCustomer : BankOutputUtilities.getAtmMemory(bank)) {
						//if matching customer with that UserName has the same passkey as the user entered
						if (tempCustomer.getName().equalsIgnoreCase(tempName) &&
								tempCustomer.getPasskey().equals(tempPasskey))
						{
							System.out.println("\nYou logged in successfully !");
							passkeyUnlock = true;
							//a main atm menu is opening
							new AtmMenu(sc, tempName, bank);
							break;
						}
					}
					System.out.println("\nWrong password, try again");
				}
				else break;
			}
		}
	}
	
	
	
	protected static void hiddenStuffMenu(Bank bank){ // a quick print of all atm users, password required
		
		Scanner sc = new Scanner(System.in);
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
			for(AtmCustomer i: BankOutputUtilities.getAtmMemory(bank)){
				System.out.println(i);
			}
		}
	}
}
