package FileMethods;
import BankSource.Bank;
import java.io.*;
import java.util.ArrayDeque;


public class RestoreInstance {
	public static Bank RestoreInstance(){ //FINAL FORM, STABLE AND CLEAN
		try{
			File f = new File("cashiers.bin");
			try(ObjectInputStream oos = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)))){
				Object obj;
				while ((obj = oos.readObject()) instanceof Bank)
					return (Bank) obj; //futureproof structure for making multiple instance sessions... to be made
			}
		}catch (IOException e){
			System.err.println("File not found/error");
		} catch (ClassNotFoundException e) {
			System.err.println("A type error occurred");
		}
		return null;
	}
}


