package interview.Array;

import java.util.ArrayList;

public class DutchNationalFlagProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			String input[] = new String[] {"M", "M", "L", "H", "H", "L", "M", "M", "L", "M", "H", "H", "H", "L", "L", "M", "M", "M"};
			sortValues(input);
			
			 int[] a = new int[] {1,3,2,1,3,2,2,1,3};
			 int output[] = sort123s(a);
			 
			 for(int i=0; i<output.length; i++) {
				 System.out.print(output[i] + " ");
			 }
	}

	/**
	 * Given an array A[] consisting 1s, 2s and 3s, write a function that sorts A[].
	 * The functions should put all 3s first, then all 2s and all 1s in last.
	 */
	public static int[] sort123s(int [] arr)
	{
		int smaller = 0;
		int bigger = arr.length-1;
		
		int current = 0;
		while(current <= bigger)
		{
			if(arr[current] == 3)
			{
				if(current == smaller) {
					current++;
				} else {
					swap(arr, current, smaller);
				}
				smaller++;
			}
			else if(arr[current] == 1)
			{
				swap(arr, current, bigger--);
			}
			else
			{
				current++;
			}
		}
		
		return arr;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	private static void sortValues(String[] s) {
        int hIndex = 0, mIndex = 0, lIndex = 0;
        //storing result 
        ArrayList<String> res = new ArrayList<String>();
 
        for (int i = 0; i < s.length; i++) {
            if(s[i].equals("H")) {
                res.add(hIndex, s[i]);
                hIndex++;
                lIndex++;
                mIndex++;
            } else if (s[i].equals("M")) {
                res.add(mIndex, s[i]);
                mIndex++;
                lIndex++;
            } else {
                res.add(lIndex, s[i]);
                lIndex++;
            }
        }
        System.out.println(res);
    }
}
