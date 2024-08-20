package BankSource.TypeCheckers;

public class IntegerChecker {
	public static boolean IntegerChecker(String num, int lowBound, int highBound){
		try{
			if(Integer.parseInt(num)< lowBound ||
			Integer.parseInt(num) > highBound){
				throw new IllegalArgumentException();
			}
			System.out.println();
			Integer.parseInt(num);
			return true;
		}catch (IllegalArgumentException e){
			return false;
		}
	}
	
	public static boolean IntegerChecker(String num, int lowBound){
		try{
			if(Integer.parseInt(num) < lowBound){
				throw new IllegalArgumentException();
			}
			System.out.println();
			Integer.parseInt(num);
			return true;
		}catch (IllegalArgumentException e){
			return false;
		}
	}
}


