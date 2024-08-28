package BankSource.Bank;
import BankSource.Atm.AtmCustomer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;

public class BankOutputUtilities { //utilities that are for exporting data from the bank, and are multi lined.. //FINAL FORM, STABLE AND CLEAN
	
	public static HashSet<Customer>  getAllCustomers(Bank bank){ //just a shortcut of the above
		HashSet<Customer> allCustomers = new HashSet<>();
		for(PriorityQueue<Customer> tempQueue : getCashiers(bank)){
			allCustomers.addAll(tempQueue);
		}
		return allCustomers;
	}
	
	public static void customerServed(Bank bank){
		Random r = new Random();
		ArrayList<Integer> busyCashiersPositions = new ArrayList<>();
		
		for(int i = 0; i < getCashiers(bank).length; i++){ //IF some queue is busy, put its index to the arraylist
			if(!getCashiers(bank)[i].isEmpty()){
				busyCashiersPositions.add(i);
			}
		}
		busyCashiersPositions.trimToSize();
		
		if(!busyCashiersPositions.isEmpty()){ //if there are customers to be served
			int temp = r.nextInt(busyCashiersPositions.size());
			//random index to pick a random busy cashier to serve his customer
			System.out.println("\nThe cashier "+(busyCashiersPositions.get(temp)+1)+
					" served the customer(s): "+getCashiers(bank)[busyCashiersPositions.get(temp)].remove());
			System.out.println("==========================================");
		}
		else{
			System.out.println("\nThere are no customers to be served");
			System.out.println("==========================================");
		}
	}
	
	protected static String bankToString(Bank bank) {
		StringBuilder str = new StringBuilder();
		str.append("\n=====Snapshot of all the queues of all cashiers=====\n\n");
		for(int i = 0; i < getCashiersBulk(bank); i++){ //for every queue
			PriorityQueue<Customer>temp = new PriorityQueue<>(getCashiers(bank)[i]);
			str.append("\t-Customers of Cashier's "+(i+1)+" queue: [");
			if(!temp.isEmpty()){
				int tempSize = temp.size();
				for(int j = 0; j < tempSize-1; j++){
					str.append(temp.remove()+", ");
				}
				str.append(temp.remove()+"]\n\n");
			}
			else
				str.append("EMPTY]\n\n");
		}
		str.append("====================================================\n\n");
		return str.toString();
	}
	
	public static PriorityQueue<Customer>[] getCashiers(Bank bank) {
		return bank.cashiers;
	}
	
	public static HashSet<AtmCustomer> getAtmMemory(Bank bank) {
		return bank.atmMemory;
	}
	
	public static int getCashiersBulk(Bank bank){ return bank.cashiersBulk;}
	
}
