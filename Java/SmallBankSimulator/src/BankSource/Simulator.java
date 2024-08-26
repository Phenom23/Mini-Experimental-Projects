package BankSource;
import BankSource.Toolkits.IntegerChecker;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class Simulator{ //FINAL FORM, STABLE AND CLEAN - A real Life scenario of a bank, wrapper per se
	
	private Bank bank;
	private int propCome; //probability of a customer to come
	private int iterations;
	private Scanner sc;
	
	public Simulator(Scanner sc, Bank bank){
		this.sc = sc;
		Random r = new Random();
		this.bank = bank;
		for (int i = 0; i < bank.getCashiers().length; i ++){
			
			//init the queues, deletes previous data of queues, keeps tho the ATM data :)
			bank.getCashiers()[i] = new PriorityQueue<>();
		}
		
		setPropCome();
		setIterations();
		System.out.println();
		
		for(int i = 0; i < iterations; i++){
			int temp = r.nextInt(1, 11);  // Generates a number from 1 to 10 inclusive
			if(temp <= propCome / 10){
				System.out.println("Based on probability of " + propCome + "%");
				Customer customer = new Customer();
				System.out.print("Give a customer name to be served: ");
				customer.setName(sc.nextLine());
				customer.setPriority(sc);
				bank.customerEnters(customer,false);
			} else {
				System.out.println("Based on probability of " + (100 - propCome) + "%");
				System.out.print("\t");
				bank.customerServed();
			}
			System.out.println(bank);
		}
	}
	
	public Bank getBank(){
		return this.bank;
	}
	
	private void setPropCome(){
		System.out.print("Give me the probability a customer will come [0-100%]: ");
		String propCome = sc.next();
		sc.nextLine(); // Clear the buffer after next()
		boolean ok = IntegerChecker.checker(propCome, 0, 100);
		while(!ok){
			System.out.print("Please provide a POSITIVE INTEGER number for the probability a customer will come [0-100%]: ");
			propCome = sc.next();
			sc.nextLine(); // Clear the buffer after next()
			ok = IntegerChecker.checker(propCome, 0, 100);
		}
		this.propCome = Integer.parseInt(propCome);
		System.out.println("The probability of a customer to be served will be calculated additionally\n");
	}
	
	private void setIterations(){
		System.out.print("How many actions will take place inside the simulation?: ");
		String iter = sc.next();
		sc.nextLine(); // Clear the buffer after next()
		boolean ok = IntegerChecker.checker(iter, 1);
		while(!ok){
			System.out.print("Please provide a POSITIVE INTEGER number for the iterations: ");
			iter = sc.next();
			sc.nextLine(); // Clear the buffer after next()
			ok = IntegerChecker.checker(iter, 1);
		}
		this.iterations = Integer.parseInt(iter);
	}
}
