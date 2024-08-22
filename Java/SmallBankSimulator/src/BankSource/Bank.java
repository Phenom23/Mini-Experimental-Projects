package BankSource;
import BankSource.TypeCheckers.IntegerChecker;
import java.io.Serializable;
import java.util.*;

public class Bank implements Serializable {
	
	private PriorityQueue<Customer>[] cashiers; //for every cashier in the array, there is a linked list with strings (names of customers)
	private Random r = new Random();
	private int cashiersBulk; //total amount of cashiers
	
	public Bank(Scanner sc){ //initializing stuff
		setCashiersBulk(sc);
		this.cashiers = new PriorityQueue[cashiersBulk];
		for (int i = 0; i < cashiers.length; i ++){
			cashiers[i] = new PriorityQueue<>();
		}
	}
	
	private void setCashiersBulk(Scanner sc){
		
		System.out.print("Please provide a positive number for the number of cashiers: ");
		String cashiersBulk = sc.next();
		boolean ok = IntegerChecker.IntegerChecker(cashiersBulk,1);
		
		while (!ok){
			sc.nextLine(); //cleans the invalid token with the enter inside
			System.out.print("Please provide a POSITIVE number for the number of cashiers: ");
			cashiersBulk = sc.next();
			ok = IntegerChecker.IntegerChecker(cashiersBulk,1);
		}
		this.cashiersBulk = Math.abs(Integer.parseInt(cashiersBulk));
	}
	
	public void customerEnters(Customer customer){
		
		ArrayList<Integer> idleCashiersPositions = new ArrayList<>();
		for(int i = 0; i < cashiersBulk; i++){ //IF some cashier's queue is empty, put its index in the arraylist of idle cashiers
			if(cashiers[i].isEmpty()){
				idleCashiersPositions.add(i);
			}
		}idleCashiersPositions.trimToSize();
		
		if(!idleCashiersPositions.isEmpty()){
			cashiers[idleCashiersPositions.get(r.nextInt(idleCashiersPositions.size()))].add(customer); //put the new customer to a random idle cashier
		}
		else{   //tries to popularize the less full queue
			LinkedList<Integer> allQ = new LinkedList<>(); // A list that stores the length of each queue
			for(int i = 0; i <cashiersBulk;i++){
				allQ.add(cashiers[i].size());
			}
			int minQueueIndex = allQ.indexOf(Collections.min(allQ)); //index of the cashier with the least amount of customers
			cashiers[minQueueIndex].add(customer); //put there the new customer
		}
		System.out.println("\nThe customer has been entered successfully.");
		System.out.println("==========================================");
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
			int temp = r.nextInt(busyCashiersPositions.size()); //random index to pick a random cashier to serve his customer
			System.out.println("\nThe cashier "+(busyCashiersPositions.get(temp)+1)+" served the customer(s): "+cashiers[busyCashiersPositions.get(temp)].remove());
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
