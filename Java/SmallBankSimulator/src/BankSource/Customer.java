package BankSource;
import BankSource.Toolkits.IntegerChecker;
import java.io.Serializable;
import java.time.Instant;
import java.util.Scanner;

public class Customer implements Comparable<Customer>, Serializable { //FINAL FORM, STABLE AND CLEAN
	
	private String name;
	private Integer priority; //0-No appointment, 1-Appointment, 2-VIP
	private Instant checkInTime;
	
	public Customer() {
		this.checkInTime = Instant.now();
	}
	
	@Override
	public int compareTo(Customer o) {
		if(o.getPriority().compareTo(this.priority) != 0)
			return o.getPriority().compareTo(this.priority);
		else
			return this.getCheckInTime().compareTo(o.checkInTime);
	}
	
	Integer getPriority() {
		return priority;
	}
	
	Instant getCheckInTime(){ return checkInTime;}
	
	public String getName(){
		return this.name;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	void setPriority(Scanner sc) {
		System.out.print("Provide a positive number for priority (1-No appointment, 2-Appointment, 3-VIP): ");
		String priority = sc.next();
		sc.nextLine();
		boolean ok = IntegerChecker.checker(priority,1,3);
		while (!ok){
			System.out.print("Please provide a POSITIVE number for priority (1-No appointment, 2-Appointment, 3-VIP): ");
			priority = sc.next();
			sc.nextLine(); //cleans the invalid token with the enter inside
			ok = IntegerChecker.checker(priority,1,3);
		}
		this.priority = Integer.parseInt(priority);
	}
	
	@Override
	public String toString() {
		return  name +"(" + priority+")";
	}
}
