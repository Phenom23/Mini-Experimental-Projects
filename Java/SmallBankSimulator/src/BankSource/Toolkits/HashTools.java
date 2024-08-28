package BankSource.Toolkits;
import BankSource.Atm.AtmCustomer;
import BankSource.Bank.Bank;
import BankSource.Bank.BankOutputUtilities;
import BankSource.Bank.Customer;
import java.util.Scanner;

public class HashTools { //FINAL FORM, STABLE AND CLEAN
	
	//crossBase = true -> It checks both of atm customer memory and bank customer memory (queue)
	//crossBase = false -> it only checks of the bank customer memory (queue)
	
	public static String nameProvider(Scanner sc, Bank bank, boolean unique, String crossBase){
		
		//Responsible for asking for a name, making sure it is not null, and it is unique or not
		while(true){
			NotNullStringLoader loader;
			loader = new NotNullStringLoader(sc, "Give a UserName");
			
			//if the user decides to exit prematurely
			if (loader.getWannaExit()) {
				return "fail"; //premature exit
			}
			
			else if( ((!nameFinder( bank, loader.getString(), crossBase)) && unique) ||
					(nameFinder(bank, loader.getString(), crossBase) && (!unique))){
						return loader.getString();
			/*if the name is unique (cross-databased or just queues), and that is what we want         OR
			  if the name is not unique (cross-databased or just queues), and that is what we want */
			}
			
			System.out.println("\nProvide a valid UserName");
			//otherwise it loops again
		}
	}
	
	public static boolean nameFinder(Bank bank, String name, String crossBase) {
		
		//crossBase = 2 | It searches the queues and the atm memory, it returns true if name is  found either in atm or in queue, or in both (it is the same name in both scenarios, queues and atm)
		boolean found = false;
		if (crossBase.equalsIgnoreCase("qa")) {
			found = false;
			//searches all usernames, to verify UserName uniqueness, in atm memory
			/*for (AtmCustomer tempAtmCustomer : BankOutputUtilities.getAtmMemory(bank)) {
				if (tempAtmCustomer.getName().equalsIgnoreCase(name)) {     legacy version  */
			if (!BankOutputUtilities.getAtmMemory(bank).contains(new AtmCustomer(name))) { //if found
				found = true; //it exists
			}
			if (!found) {
				// if it is not found in atm memory, it searches in "queues memories"
			/*for (Customer tempCustomer : BankOutputUtilities.getAllCustomers(bank)) {
				if (tempCustomer.getName().equalsIgnoreCase(name)) {        legacy  version */
				if (!BankOutputUtilities.getAllCustomers(bank).contains(new Customer(name))) { //if found
					found = true;
				}
			}
			return found;
		}
	
		else if(crossBase.equals("a")){
			// crossBase = 1 | searches all usernames, to verify UserName uniqueness in atm memory only
			/*for (AtmCustomer tempAtmCustomer : BankOutputUtilities.getAtmMemory(bank)) {
				if (tempAtmCustomer.getName().equalsIgnoreCase(name)) {
					return true; //it exists
				}
			}*/
			if (!BankOutputUtilities.getAtmMemory(bank).contains(new AtmCustomer(name))) { //if found
				return true; //it exists
			}
		}
		
		else{
			// crossBase = q | searches all usernames in queues only, returns true if found
			/*for (Customer tempCustomer : BankOutputUtilities.getAllCustomers(bank)) {
				if (tempCustomer.getName().equalsIgnoreCase(name)) {
					return true; //it exists
				}
			}*/
			if(!BankOutputUtilities.getAllCustomers(bank).contains(new Customer(name))){ //if found
				return true;
			}
		}
		
		return false; //it does not exist
	}
}
