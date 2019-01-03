package interview;

public class DoubleSqrt {

	/*
	 * Let's say there is a double square number X, which can be expressed as the sum of two perfect squares, for example, 10 is double square because 10 = 3^2 + 1^2 
	 * Determine the number of ways which it can be written as the sum of two squares
	 */
	public static void main(String[] args) {
		findSqrt(20);
	}
	
	public static void findSqrt(int number) {
		int j = (int) Math.sqrt(number);
		
		int i=0;		
		
		while(i<=j) {
			int square = (i * i) + (j*j);
			
			if(square == number) {
				System.out.println(i + " : " + j);
				i ++;
				j--;				
			} else if(square<number) {
				i++;
			} else {
				j--;
			}
		}
	}

}
