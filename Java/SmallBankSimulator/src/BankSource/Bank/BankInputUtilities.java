package BankSource.Bank;
import BankSource.Atm.AtmCustomer;
import BankSource.Toolkits.IntegerChecker;
import java.util.*;

public class BankInputUtilities { //utilities that are for inserting data to the bank, and are multi lined.. //FINAL FORM, STABLE AND CLEAN
	
	public static void customerEnters(Bank bank, Customer customer, boolean headless){ //versatile for outside methods as well!
		
		/* Headless = true -> Skips the prints about the result, useful for outside use (to integrate with other functions as well
		   Headless = false -> Does NOT skip the prints */
		
		ArrayList<Integer> idleCashiersPositions = new ArrayList<>();
		for(int i = 0; i < BankOutputUtilities.getCashiersBulk(bank); i++){
			//IF some cashier's queue is empty, put its index in the arraylist of idle cashiers, popularize the empty ones
			if(BankOutputUtilities.getCashiers(bank)[i].isEmpty()){
				idleCashiersPositions.add(i);
			}
		}idleCashiersPositions.trimToSize();
		if(!idleCashiersPositions.isEmpty()){
			//put the new customer to a random idle cashier
			BankOutputUtilities.getCashiers(bank)[idleCashiersPositions.get(new Random().nextInt(idleCashiersPositions.size()))].add(customer);
			
		}
		else{   //tries to popularize the less full queue, if there are no idle cashiers
			LinkedList<Integer> allQ = new LinkedList<>();
			// A list that stores the length of each queue
			for(int i = 0; i < BankOutputUtilities.getCashiersBulk(bank);i++){
				allQ.add(BankOutputUtilities.getCashiers(bank)[i].size());
			}
			int minQueueIndex = allQ.indexOf(Collections.min(allQ));
			//index of the cashier with the least amount of customers
			BankOutputUtilities.getCashiers(bank)[minQueueIndex].add(customer); //put there the new customer
		}
		if(!headless){
			System.out.println("\nThe customer has been entered successfully.");
			System.out.println("==========================================");
		}
		
	}
	
	protected static void setCashiersBulk(Bank bank){
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Please provide a positive number for the number of cashiers: ");
		String cashiersBulk = sc.nextLine().trim();
		boolean ok = IntegerChecker.checker(cashiersBulk,1);
		
		while (!ok){
			System.out.print("Please provide a POSITIVE number for the number of cashiers: ");
			cashiersBulk = sc.nextLine().trim();
			ok = IntegerChecker.checker(cashiersBulk,1);
		}
		bank.cashiersBulk = Math.abs(Integer.parseInt(cashiersBulk)); //auto abs for safety
	}
	
	public static void setAtmMemory(HashSet<AtmCustomer> e, Bank bank){ bank.atmMemory = e;}
}
