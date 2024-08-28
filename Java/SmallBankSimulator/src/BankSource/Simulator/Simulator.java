package BankSource.Simulator;
import BankSource.Bank.BankInputUtilities;
import BankSource.Bank.BankOutputUtilities;
import BankSource.Toolkits.*;
import BankSource.Bank.Bank;
import BankSource.Bank.Customer;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class Simulator{ //FINAL FORM, STABLE AND CLEAN - A real Life scenario of a bank, wrapper per se
	
	private int propCome; //probability of a customer to come
	private int iterations;
	
	public Simulator(Scanner sc, Bank bank){
		
		this.iterations = SimulatorUtilities.IterationsGrabber();
		this.propCome = SimulatorUtilities.PropabilityGrabber();
		Random r = new Random();
		
		for (int i = 0; i < BankOutputUtilities.getCashiers(bank).length; i ++){
			
			//init the queues, deletes previous data of queues, keeps tho the ATM data :)
			BankOutputUtilities.getCashiers(bank)[i] = new PriorityQueue<>();
		}
		System.out.println();
		
		for(int i = 0; i < iterations; i++){
			int temp = r.nextInt(1, 11);  // Generates a number from 1 to 10 inclusive
			if(temp <= propCome / 10){
				System.out.println("Based on probability of " + propCome + "%");
				Customer customer = new Customer();
				System.out.print("Give a customer name to be served: ");
				String name;
				while( HashTools.nameFinder(bank, name = sc.nextLine(),"q")){ // as long as the name provided already exists in the queues
					System.out.print("\nThis name already exists, provide a unique one: ");
				}
				customer.setName(name);
				customer.setPriority();
				BankInputUtilities.customerEnters(bank,customer,false);
			} else {
				System.out.println("Based on probability of " + (100 - propCome) + "%");
				System.out.print("\t");
				BankOutputUtilities.customerServed(bank);
			}
			System.out.println(bank);
		}
	}
	
}
