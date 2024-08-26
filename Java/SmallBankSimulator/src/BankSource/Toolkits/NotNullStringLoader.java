package BankSource.Toolkits;
import java.util.Scanner;

public class NotNullStringLoader { //FINAL FORM, STABLE AND CLEAN
	
	private String stringTemp; //It gets a value if is not whitespace, and if the user doesn't want to exit
	private Boolean wannaExit; //If it is true, then stringTemp remains empty
	
	
	public NotNullStringLoader(Scanner sc, String prompt){ //it is versatile, it gets a prompt
		
		wannaExit = false; //for premature exiting
		System.out.print(prompt+" (type 'exit' for exiting): ");
		
		while((stringTemp = (sc.nextLine().trim())).isBlank()){ //blank check
			System.out.print("[Input was EMPTY] "+ prompt+" (type 'exit' for exiting): ");
		}
		if(stringTemp.equalsIgnoreCase("exit")){
			System.out.println("\nExiting");
			wannaExit = true;
		}
	}
	
	//First you need to see if user wanted premature exiting
	public Boolean getWannaExit(){ //indicator for premature exit
		return wannaExit;
	}
	
	//If no premature exiting occurred, you can get the filtered, TRIMMED and null safe name
	public String getString(){ return this.stringTemp;}
}
