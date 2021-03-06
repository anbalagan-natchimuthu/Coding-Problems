package interview.Array;

import static java.lang.Double.NaN;

public class Sqrt {
	// define method header
	public static double sqrt(double a) {
		// firstly check if a is non-negative value
		if (a < 0)
			return NaN;
		// also check if a==0 or a==1 because in these two cases sqrt(a) = a
		if (a == 0 || a == 1)
			return a;

		// now start the core part of the code
		double precision = 0.00001;// define the precision, so we stop when
									// precision is achieved
		double start = 0;
		double end = a;
		// we define these two start/end values because usually 0<sqrt(a)<a
		// however, if a<1; then 0<a<sqrt(a)
		if (a < 1)
			end = 1;

		// define a loop to continue if the precision is not yet achieved
		while (end - start > precision) {
			double mid = (start + end) / 2;
			double midSqr = mid * mid;
			if (midSqr == a)
				return mid;// we find the exact sqrt value!
			else if (midSqr < a) 
				start = mid;// we shift our focus to bigger half
			else
				end = mid;// shift focus to smaller half
		}  

		// if we did not find exact sqrt value, we return the approxiated value
		// with the defined precision
		return (start + end) / 2;
	}

	/**
	 * https://www.geeksforgeeks.org/write-a-c-program-to-calculate-powxn/
	 */
	public static double power(double x, int y) {

		double temp;
		if (y == 0) {
			return 1;
		}
		temp = power(x, y / 2);

		if (y % 2 == 0) {
			return temp * temp;
		} else {
			if (y > 0) {
				return x * temp * temp;
			} else {
				return (temp * temp) / x;
			}
		}
	}
	
	public static void main(String args[]) {
		System.out.println(sqrt(-9) + "::" + Math.sqrt(-9));
		System.out.println(sqrt(0.3));
		System.out.println(sqrt(1.8));
		System.out.println(sqrt(25));
		System.out.println(sqrt(23));
		System.out.println(sqrt(4));

		System.out.println(power(4, 2) + "::" + Math.pow(4, 2));
		System.out.println(power(4.2, 2) + "::" + Math.pow(4.2, 2));
		System.out.println(power(2, -3) + "::" + Math.pow(2, -3));
	}
}
