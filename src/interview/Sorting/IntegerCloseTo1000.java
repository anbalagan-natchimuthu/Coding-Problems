package interview.Sorting;

//Write a method which given two integers, returns the integer that is closest to 1000.

public class IntegerCloseTo1000 {

	public static void main(String[] args) {
		
		System.out.println(findClosestNumber(100, 100));
		System.out.println(findClosestNumber(1001, 1050));
		System.out.println(findClosestNumber(-999, 900));
		System.out.println(findClosestNumber(-10, -100));
		System.out.println(findClosestNumber(0, -1));
		
	}
	
	/**
	 * Function to return closest number of 1000
	 * @param number1 int
	 * @param number2 int
	 * @return int
	 */
	public static int findClosestNumber(int number1, int number2) {
		
		// Subtract the numbers from 1000 and check the difference
		if( Math.abs(number1-1000) <= Math.abs(number2-1000)) {
			return number1;
		} else {
			return number2;
		}
	}

}
