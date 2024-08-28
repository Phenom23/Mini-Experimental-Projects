package BankSource.Bank;
import BankSource.Atm.AtmCustomer;

import java.io.Serializable;
import java.util.*;

public class Bank implements Serializable { //FINAL FORM, STABLE AND CLEAN - It has: All the queues, the atm customer "database" (atmMemory)
	
	
	// the queues of the bank, for every cashier in the array, there is a linked list with strings (names of customers)
	protected PriorityQueue<Customer>[] cashiers;
	int cashiersBulk; //total amount of cashiers
	HashSet<AtmCustomer> atmMemory;
	
	
	public Bank(Scanner sc){
		atmMemory = new HashSet<>();  //every bank instance has an atm "memory"
		BankInputUtilities.setCashiersBulk(this); //every time a new bank instance is created, cashiers bulk has to be entered
		this.cashiers = new PriorityQueue[cashiersBulk];
		for (int i = 0; i < cashiers.length; i ++){
			cashiers[i] = new PriorityQueue<>();
		}
	}
	
	@Override
	public String toString() {
		return BankOutputUtilities.bankToString(this);
	}
}
