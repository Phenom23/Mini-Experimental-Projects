package BankSource.Toolkits;

public class IntegerChecker { //FINAL FORM, STABLE AND CLEAN
	
	//Just a utility class, in which, there are 2 overloaded methods,
	// that make sure the number can parse from String to Integer, and it is between bounds.
	
	public static boolean checker(String num, int lowBound, int highBound){
		try{
			int temp  =  Integer.parseInt(num);
			if(temp < lowBound || temp > highBound){
				throw new IllegalArgumentException();
			}
			return true;
		}catch (IllegalArgumentException e){
			return false;
		}
	}
	
	public static boolean checker(String num, int lowBound){
		try{
			int temp  =  Integer.parseInt(num);
			if( temp < lowBound){
				throw new IllegalArgumentException();
			}
			return true;
		}catch (IllegalArgumentException e){
			return false;
		}
	}
}


