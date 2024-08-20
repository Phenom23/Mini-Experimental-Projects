package BankSource;
import BankSource.TypeCheckers.IntegerChecker;

import java.util.Random;
import java.util.Scanner;

public class Simulator {
	
	private Random r;
	private Bank bank;
	private int propCome;
	private int iterations;
	private Scanner sc;
	
	public Simulator(Scanner sc){
		this.sc = sc;
		r = new Random();
		this.bank = new Bank(sc);
		
		setPropCome();
		setIterations();
		System.out.println();
		
		for(int i = 0; i < iterations; i++){
			int temp = r.nextInt(1, 11);  // Generates a number from 1 to 10 inclusive
			if(temp <= propCome / 10){
				System.out.println("Based on probability of " + propCome + "%");
				System.out.print("\tGive a customer name to be served: ");
				bank.customerEnters(sc.nextLine()); // Read the customer name
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
		boolean ok = IntegerChecker.IntegerChecker(propCome, 0, 100);
		while(!ok){
			System.out.print("Please provide a POSITIVE number for the probability a customer will come [0-100%]: ");
			propCome = sc.next();
			sc.nextLine(); // Clear the buffer after next()
			ok = IntegerChecker.IntegerChecker(propCome, 0, 100);
		}
		this.propCome = Integer.parseInt(propCome);
		System.out.println("The probability of a customer to be served will be calculated additionally\n");
	}
	
	private void setIterations(){
		System.out.print("How many actions will take place inside the simulation?: ");
		String iter = sc.next();
		sc.nextLine(); // Clear the buffer after next()
		boolean ok = IntegerChecker.IntegerChecker(iter, 1);
		while(!ok){
			System.out.print("Please provide a POSITIVE number for the iterations: ");
			iter = sc.next();
			sc.nextLine(); // Clear the buffer after next()
			ok = IntegerChecker.IntegerChecker(iter, 1);
		}
		this.iterations = Integer.parseInt(iter);
	}
}
