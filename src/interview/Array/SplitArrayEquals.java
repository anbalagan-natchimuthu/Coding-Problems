package interview.Array;

//Write a method which takes an array of integers. The method should return true if there is a way to split the array
//in two so that the sum of the numbers on one side of the split equals the sum of the numbers on the other side.

public class SplitArrayEquals {

	public static void main(String[] args) {		

		System.out.println(canSplitArrayEqual(new int[]{20, 30, 12, 31, 31}));
		System.out.println(canSplitArrayEqual(new int[]{10,20,30,40}));
		System.out.println(canSplitArrayEqual(new int[]{10,20,30}));
		System.out.println(canSplitArrayEqual(new int[]{100,200,300,600}));
		System.out.println(canSplitArrayEqual(new int[]{-1, -2, -3, -6, 0, 0}));
	}
	
	/**
	 * Function to check if array can be splitted equally on both sides
	 * @param inputArray int[]
	 * @return boolean
	 */
	public static boolean canSplitArrayEqual(int[] inputArray) {
		
	    int total = 0;
	    
	    // get the input array length
	    int inputLength = inputArray.length;
	    
	    //sum each element in the array 
	    for(int i=0; i<inputLength; i++) {
	    	total += inputArray[i];
	    }
	    
	    //Initialize left side value as zero
	    int left = 0;
	    //Initialize right side value with total sum
	    int right = total;
	    
	    //Iterate the array from start to end until left side sum is not matching with right side sum 
	    for (int i = 0; left != right && i < inputLength; i++) {
	    	//Add the current array element value to left side
	        left += inputArray[i];
	        //subtract the current element value from right side total 
	        right -= inputArray[i];
	    }
	    
	    //At the end of for loop, if left side sum is matching with right side sum, then we can split the array equally
	    return left == right;
	}
}
