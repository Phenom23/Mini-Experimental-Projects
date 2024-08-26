package BankSource.Atm;
import java.io.Serializable;

public class AtmCustomer implements Serializable { //FINAL FORM, STABLE AND CLEAN
	
	private String name;
	private String passkey;
	private Double balance = 0.0;
	
	public AtmCustomer(String name, String passkey){
		this.name = name;
		this.passkey = passkey;
	}
	
	public String getName() {
		return name;
	}
	String getPasskey() {
		return passkey;
	}
	Double getBalance() { return balance;}
	void setBalance(Double balance) { this.balance = balance;}
	
	@Override
	public String toString() {
		return "| Name: "+this.name+" | Passkey: "+this.passkey+" | Balance: "+this.balance+" euros |";
	}
}
