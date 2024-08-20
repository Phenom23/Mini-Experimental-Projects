package FileMethods;
import BankSource.Bank;
import java.io.*;

public class RestoreInstance {
	public static Bank RestoreInstance(){
		try{
			File f = new File("cashiers.bin");
			try(ObjectInputStream oos = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)))){
				return (Bank) oos.readObject();
			}
		}catch (IOException e){
			System.err.println("File not found/error");
		} catch (ClassNotFoundException e) {
			System.err.println("A type error occurred");
		}
		return null;
	}
	
}
