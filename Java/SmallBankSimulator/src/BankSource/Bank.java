package BankSource;
import BankSource.Atm.AtmMemory;
import BankSource.Toolkits.IntegerChecker;
import java.io.Serializable;
import java.util.*;

public class Bank implements Serializable { //FINAL FORM, STABLE AND CLEAN
	
	//It has: All the queues, the atm customer "database"
	
	private PriorityQueue<Customer>[] cashiers; // the queues of the bank
	//for every cashier in the array, there is a linked list with strings (names of customers)
	private Random r = new Random();
	private int cashiersBulk; //total amount of cashiers
	private AtmMemory atmMemory;
	
	
	public Bank(Scanner sc){
		atmMemory = new AtmMemory();  //every bank instance has an atm "memory"
		setCashiersBulk(sc); //every time a new bank instance is created, cashiers bulk has to be entered
		this.cashiers = new PriorityQueue[cashiersBulk];
		for (int i = 0; i < cashiers.length; i ++){
			cashiers[i] = new PriorityQueue<>();
		}
	}
	
	
	PriorityQueue<Customer>[] getCashiers() {
		return cashiers;
	}
	
	public HashSet<Customer> getAllCustomers(){ //just a shortcut of the above
		HashSet<Customer> allCustomers = new HashSet<>();
		for(PriorityQueue<Customer> tempQueue : getCashiers()){
			allCustomers.addAll(tempQueue);
		}
		return allCustomers;
	}
	
	public AtmMemory getAtmMemory() {
		return atmMemory;
	}
	
	
	private void setCashiersBulk(Scanner sc){
		
		System.out.print("Please provide a positive number for the number of cashiers: ");
		String cashiersBulk = sc.nextLine().trim();
		boolean ok = IntegerChecker.checker(cashiersBulk,1);
		
		while (!ok){
			System.out.print("Please provide a POSITIVE number for the number of cashiers: ");
			cashiersBulk = sc.nextLine().trim();
			ok = IntegerChecker.checker(cashiersBulk,1);
		}
		this.cashiersBulk = Math.abs(Integer.parseInt(cashiersBulk)); //auto abs for safety
	}
	
	
	
	public void customerEnters(Customer customer, boolean headless){ //versatile for outside methods as well!
		
		/* Headless = true -> Skips the prints about the result, useful for outside use (to integrate with other functions as well
		   Headless = false -> Does NOT skip the prints */
		
		ArrayList<Integer> idleCashiersPositions = new ArrayList<>();
		for(int i = 0; i < cashiersBulk; i++){
			//IF some cashier's queue is empty, put its index in the arraylist of idle cashiers, popularize the empty ones
			if(cashiers[i].isEmpty()){
				idleCashiersPositions.add(i);
			}
		}idleCashiersPositions.trimToSize();
		if(!idleCashiersPositions.isEmpty()){
			//put the new customer to a random idle cashier
			cashiers[idleCashiersPositions.get(r.nextInt(idleCashiersPositions.size()))].add(customer);
			
		}
		else{   //tries to popularize the less full queue, if there are no idle cashiers
			LinkedList<Integer> allQ = new LinkedList<>();
			// A list that stores the length of each queue
			for(int i = 0; i <cashiersBulk;i++){
				allQ.add(cashiers[i].size());
			}
			int minQueueIndex = allQ.indexOf(Collections.min(allQ));
			//index of the cashier with the least amount of customers
			cashiers[minQueueIndex].add(customer); //put there the new customer
		}
		if(!headless){
			System.out.println("\nThe customer has been entered successfully.");
			System.out.println("==========================================");
		}
		
	}
	
	
	
	public void customerServed(){
		ArrayList<Integer> busyCashiersPositions = new ArrayList<>();
		
		for(int i = 0; i < cashiersBulk; i++){ //IF some queue is busy, put its index to the arraylist
			if(!cashiers[i].isEmpty()){
				busyCashiersPositions.add(i);
			}
		}
		busyCashiersPositions.trimToSize();
		
		if(!busyCashiersPositions.isEmpty()){ //if there are customers to be served
			int temp = r.nextInt(busyCashiersPositions.size());
			//random index to pick a random busy cashier to serve his customer
			System.out.println("\nThe cashier "+(busyCashiersPositions.get(temp)+1)+
					" served the customer(s): "+cashiers[busyCashiersPositions.get(temp)].remove());
			System.out.println("==========================================");
		}
		else{
			System.out.println("\nThere are no customers to be served");
			System.out.println("==========================================");
		}
	}
	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\n=====Snapshot of all the queues of all cashiers=====\n\n");
		for(int i = 0; i < cashiersBulk; i++){ //for every queue
			PriorityQueue<Customer>temp = new PriorityQueue<>(cashiers[i]);
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
}
