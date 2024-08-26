package BankSource.Toolkits;

public class DoubleChecker {//FINAL FORM, STABLE AND CLEAN
	
	//Just a utility class, in which, there are 2 overloaded methods,
	// that make sure the number can parse from String to Double, and it is between bounds.
	
	public static boolean checker (String num, double lowBound){
		try{
			double temp = Double.parseDouble(num);
			if( temp < lowBound ){
				throw new IllegalArgumentException();
			}
			return true;
		}catch (IllegalArgumentException e){
			return false;
		}
	}
	
	public static boolean checker (String num, double lowBound, double upperBound){
		try{
			double temp = Double.parseDouble(num);
			if( temp < lowBound || temp> upperBound ){
				throw new IllegalArgumentException();
			}
			return true;
		}catch (IllegalArgumentException e){
			return false;
		}
	}
}
