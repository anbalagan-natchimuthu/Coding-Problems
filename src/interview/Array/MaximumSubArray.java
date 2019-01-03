package interview.Array;

/**
 * PROBLEM 1:
 */
public class MaximumSubArray {
	
		public static int maxSubArray(int[] array) {
			if(array.length == 0) return -1;
			else if(array.length == 1) 
				return array[0];
			else
			{	
				int maxSum = array[0];
				int current_max = array[0];				
				for(int i = 1; i < array.length; i++)
				{					
					current_max = Math.max(array[i], current_max + array[i]); 					
					maxSum = Math.max(maxSum, current_max);
				}				
				return maxSum;
			}
		}	
		
		/**
		 * 
		 * Fun time - Breaking the code
			1. NULL array, passing size as zero.
			2. Array with all negative numbers.
			3. Array with all positive numbers.
			4. Array with all zeroes or something like {-5, 4, 0, 0, -3}. Zero should be part of largest sub array indices or not. 
				Similarly for something like {30, -40,-50, 10, 20}, which one should be returned. Above code ignores 0 and always 
				returns the first largest sub array in case of ambiguity.
			5. Have a case where a negative numbers are part of return value. {-100, 2, 10, -10, 100}.
			6. Have an array with all negative numbers and first element is minimum, {-2, -10, -3, -4, -5}. 
		 *
		 */
		public static int maxSubArray1(int[] A) {
			int currentSum = 0;
			int maxSum = A[0];
			int rightIdx = 0;
		    int leftIdx = 0;
		    int tmpLeftIdx = 0;
	 
			for (int i = 0; i < A.length; i++) {
				currentSum += A[i];
				// If it is currentSum > maxSum, it will consider 
				// 1. returns first largest sub array in case of ambiguity e.g. 30, -40,-50, 10, 20. it will return 30.
				// 2. ignores 0(zeros)  e.g. -5, 4, 0, 0, -3. it will return just 4.
				if (currentSum >= maxSum)
		        {
					maxSum = currentSum;
		            leftIdx = tmpLeftIdx;
		            rightIdx = i;
		        }
		        if (currentSum < 0)
		        {
		            currentSum = 0;
		            tmpLeftIdx = i+1;
		        }
			}
			
			System.out.print("Sub Array Elements: ");
			for(int i=leftIdx; i<=rightIdx; i++) {
				System.out.print(A[i] + " ");
			}
			
			System.out.println();
	 
			return maxSum;
		}
		
		public static void main(String args[]) {			
			
			// Ambiguous sub array
			int a[] = new int[]{30, -40,-50, 10, 20} ;
			//System.out.println(maxSubArray(a));
			System.out.println("Max Sum: " + maxSubArray1(a));
			
			//Zero elements in the array
			int b[] = new int[]{-5, 4, 0, 0, -3} ;
			System.out.println("Max Sum: " + maxSubArray1(b));

			// negative numbers are part of return value
			int c[] = new int[]{-100, 2, 10, -10, 100} ;
			System.out.println("Max Sum: " + maxSubArray1(c));			
			
			//all negative numbers
			int d[] = new int[]{-2, -10, -1, -4, -5} ;
			System.out.println("Max Sum: " + maxSubArray1(d));
			
			//all positive numbers
			int e[] = new int[]{2, 0, 0, 6, 7} ;
			System.out.println("Max Sum: " + maxSubArray1(e));
			
			int f[] = new int[]{2,4,-5,6,8,12,-5,3,-4,49,45,-11,2,99};
			System.out.println("Max Sum: " + maxSubArray1(f));
			
		}

	/**
	 * PROBLEM 2:
	 */
	public static class MergeArray {

        public static void main(String[] args) {
            // TODO Auto-generated method stub

            int array1[] = new int[10];
            int array2[] = new int[] { 23, 25, 35, 65, 78};

            for(int i=0; i<5; i++) {
                array1[i] = (i*10) + 20;
            }

            int temp [] = mergearray(array1, array2);
            for(int i=0; i< temp.length; i++) {
                System.out.print("  " + temp[i]);
            }
        }

        public static int[] mergearray (int[] arrayA, int[] arrayB) {

			for (int i = 0; i < arrayA.length; i++) {
				System.out.print(arrayA[i] + " ");
			}
			System.out.println();
			for (int i = 0; i < arrayB.length; i++) {
				System.out.print(arrayB[i] + " ");
			}

			System.out.println(arrayA.length + ":" + arrayB.length);
			int array1Length = arrayA.length - 1;
			int x = arrayB.length - 1;
			int y = x;

			while (x >= 0 && y >= 0) {
				if (arrayA[x] >= arrayB[y]) {
					arrayA[array1Length--] = arrayA[x--];
				} else {
					arrayA[array1Length--] = arrayB[y--];
				}
			}

			while(x >= 0) arrayA[array1Length--] = arrayA[x--];
			while(y >= 0) arrayA[array1Length--] = arrayB[y--];

			return arrayA;
		}

    }
}
