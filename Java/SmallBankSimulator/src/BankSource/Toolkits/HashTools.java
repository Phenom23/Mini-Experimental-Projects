package BankSource.Toolkits;
import BankSource.Atm.AtmCustomer;
import BankSource.Atm.AtmMemory;
import BankSource.Bank;
import BankSource.Customer;

import java.util.Scanner;

public class HashTools {
	
	//crossBase = true -> It checks both of atm customer memory and bank customer memory (queue)
	//crossBase = false -> it only checks of the bank customer memory (queue)
	
	public static String nameProvider(Scanner sc, Bank bank, AtmMemory atmMemory, boolean unique, String crossBase){
		
		//Responsible for asking for a name, making sure it is not null, and it is unique or not
		while(true){
			NotNullStringLoader loader;
			loader = new NotNullStringLoader(sc, "Give a UserName");
			
			//if the user decides to exit prematurely
			if (loader.getWannaExit()) {
				return "fail"; //premature exit
			}
			
			else if( ((!nameFinder(atmMemory, bank, loader.getString(), crossBase)) && unique) ||
					(nameFinder(atmMemory, bank, loader.getString(), crossBase) && (!unique))){
						return loader.getString();
			/*if the name is unique (cross-databased or just queues), and that is what we want         OR
			  if the name is not unique (cross-databased or just queues), and that is what we want */
			}
			
			System.out.println("\nProvide a valid UserName");
			//otherwise it loops again
		}
	}
	
	public static boolean nameFinder(AtmMemory atmMemory, Bank bank, String name, String crossBase){
		
		//crossBase = 2 | It searches the queues and the atm memory, it returns true if name is not found neither in atm neither in queue (it is the same in both scenarios)
		if(crossBase.equalsIgnoreCase("qa")){
			boolean found = false;
			//searches all usernames, to verify UserName uniqueness, in atm memory
			for (AtmCustomer tempAtmCustomer : atmMemory.getAllAtmCustomers()) {
				if (tempAtmCustomer.getName().equalsIgnoreCase(name)) {
					found =  true; //it exists
					break;
				}
			}
			if(!found){
				// if it is not found in atm memory, it searches in "queues memories"
				for(Customer tempCustomer : bank.getAllCustomers()){
					if(tempCustomer.getName().equalsIgnoreCase(name)){
						found = true;
						break;
					}
				}
			}
			return found;
		}
		
		else if(crossBase.equals("a")){
			// crossBase = 1 | searches all usernames, to verify UserName uniqueness in atm memory only
			for (AtmCustomer tempAtmCustomer : atmMemory.getAllAtmCustomers()) {
				if (tempAtmCustomer.getName().equalsIgnoreCase(name)) {
					return true; //it exists
				}
			}
		}
		
		else{
			// crossBase = q | searches all usernames, to verify UserName uniqueness in queues only
			for (Customer tempCustomer : bank.getAllCustomers()) {
				if (tempCustomer.getName().equalsIgnoreCase(name)) {
					return true; //it exists
				}
			}
		}
		return false; //it does not exist
	}
}
