package BankSource.Atm;
import java.io.Serializable;
import java.util.Objects;

public class AtmCustomer implements Serializable { //FINAL FORM, STABLE AND CLEAN
	
	private String name;
	private String passkey;
	private Double balance = 0.0;
	
	public AtmCustomer(String name, String passkey){
		this.name = name;
		this.passkey = passkey;
	}
	
	public AtmCustomer(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public String getPasskey() {
		return passkey;
	}
	public Double getBalance() { return balance;}
	public void setBalance(Double balance) { this.balance = balance;}
	
	
	@Override
	public String toString() {
		return "| Name: " + this.name + " | Passkey: " + this.passkey + " | Balance: " + this.balance + " euros |";
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AtmCustomer that = (AtmCustomer) o;
		return Objects.equals(getName(), that.getName());
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(getName());
	}
}
