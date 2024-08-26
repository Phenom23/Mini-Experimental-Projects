package FileMethods;
import BankSource.Bank;
import java.io.*;

public class SaveInstance {
	public static void SaveInstance(Bank bank){ //FINAL FORM, STABLE AND CLEAN
		try{
			File f = new File("cashiers.bin");
			try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)))){
				oos.writeObject(bank);
			}
		}catch (IOException e){
			System.err.println("File not found/error");
		}
	}
}
