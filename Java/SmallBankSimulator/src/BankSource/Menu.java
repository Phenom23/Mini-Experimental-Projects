package BankSource;
import FileMethods.RestoreInstance;
import FileMethods.SaveInstance;
import java.util.Scanner;

public class Menu {
	
	private Bank bank;
	public Menu() {
		String option = "8"; //8 is placeholder, just so the while parsing doesn't brake apart
		Scanner sc = new Scanner(System.in);
		bank = new Bank(sc);
		while(Integer.parseInt(option)!=7) {
			System.out.println("\nAction Menu:");
			System.out.println("======================");
			System.out.println("1 - Add a customer");
			System.out.println("2 - Serve a customer");
			System.out.println("3 - Run a semiRandom Simulation (creates a new Bank too)");
			System.out.println("4 - Save");
			System.out.println("5 - Restore ");
			System.out.println("6 - See the current state");
			System.out.println("7 - Exit");
			System.out.print("\nGive an option: ");
			option = sc.next();
			sc.nextLine();
			
			switch (option){
				case "1":
					Customer customer = new Customer();
					System.out.print("Give a customer name to be served: ");
					customer.setName(sc.nextLine());
					customer.setPriority(sc);
					bank.customerEnters(customer);
					break;
				case "2":
					bank.customerServed();
					break;
				case "3":
					Simulator sim = new Simulator(sc);
					this.bank = sim.getBank();
					break;
				case "4":
					SaveInstance.SaveInstance(bank);
					System.out.println("The instance of the current Bank has been saved successfully!");
					break;
				case "5":
					bank = RestoreInstance.RestoreInstance();
					System.out.println("The instance of the current Bank has been restored successfully!");
					break;
				case "6":
					System.out.println(bank);
					break;
				case "7":
					System.out.println("Goodbye");
					break;
				default:
					System.out.println("Please enter a valid option!!");
					option = "8";
			}
		}
	}
}
