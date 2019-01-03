package interview.Sorting;

/**
 * https://www.youtube.com/watch?v=iMT7gTPpaqw&index=4&list=PLj8W7XIvO93qVnnXxyeWmCSvMFqRBP4Jw
 */
public class MergeSort {
    
    //private int[] array;
    private int[] tempMergArr;
    //private int length;
 
    public static void main(String a[]){
         
        int[] inputArr = {15, 7, 2, 5, 10, 12, 12, 12, 45};
        MergeSort mms = new MergeSort();
        mms.sort(inputArr);
        for(int i:inputArr){
            System.out.print(i);
            System.out.print(" ");
        }
    }
     
    public void sort(int inputArr[]) {
        //this.array = inputArr;
        int length = inputArr.length;
        this.tempMergArr = new int[length];
        doMergeSort(inputArr, 0, inputArr.length - 1);
    }
 
    private void doMergeSort(int[] inputArr, int lowerIndex, int higherIndex) {
         
        if (lowerIndex < higherIndex) {
            int middle = (lowerIndex + higherIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(inputArr, lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(inputArr, middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(inputArr, lowerIndex, middle, higherIndex);
            //merge(inputArr, lowerIndex, middle, higherIndex);
        }
    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int arr[], int lowerIndex, int middleIndex, int higherIndex) {
        // Find sizes of two subarrays to be merged
        int n1 = middleIndex - lowerIndex + 1;
        int n2 = higherIndex - middleIndex;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[lowerIndex + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[middleIndex + 1 + j];
        }


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = lowerIndex;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
 
    private void mergeParts(int[] inputArray, int lowerIndex, int middle, int higherIndex) {

        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = inputArray[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                inputArray[k++] = tempMergArr[i++];
            } else {
                inputArray[k++] = tempMergArr[j++];
            }
        }
        while (i <= middle) {
            inputArray[k++] = tempMergArr[i++];
        }

        while (j <= higherIndex) {
            inputArray[k++] = tempMergArr[j++];
        }
    }
}
