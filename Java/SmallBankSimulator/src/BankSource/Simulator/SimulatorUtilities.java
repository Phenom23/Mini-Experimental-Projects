package BankSource.Simulator;
import BankSource.Toolkits.IntegerChecker;
import java.util.Scanner;

public class SimulatorUtilities { //FINAL FORM, STABLE AND CLEAN - Utility class
	
	protected static int IterationsGrabber(){
		Scanner sc = new Scanner(System.in);
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
		return Integer.parseInt(iter);
	}
	
	protected static int PropabilityGrabber(){
		Scanner sc = new Scanner(System.in);
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
		System.out.println("The probability of a customer to be served will be calculated additionally\n");
		return Integer.parseInt(propCome);
		
	}
}
