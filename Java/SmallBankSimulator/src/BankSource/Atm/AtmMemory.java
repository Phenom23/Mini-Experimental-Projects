package BankSource.Atm;
import java.io.Serializable;
import java.util.HashSet;

public class AtmMemory implements Serializable { //FINAL FORM, STABLE AND CLEAN
	
	//It is stored inside the bank object
	
	private HashSet<AtmCustomer> allAtmCustomers;
	
	public AtmMemory() {
		allAtmCustomers = new HashSet<>();
	}
	
	public HashSet<AtmCustomer> getAllAtmCustomers(){
		return allAtmCustomers;
	}
}


