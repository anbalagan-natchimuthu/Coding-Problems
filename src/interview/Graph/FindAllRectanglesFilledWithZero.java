package interview.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/find-rectangles-filled-0/
 *
 * We have one 2D array, filled with zeros and ones. We have to find the starting point and ending point of all
 * rectangles filled with 0. It is given that rectangles are separated and do not touch each other however they
 * can touch the boundary of the array.A rectangle might contain only one element.
 *
 * Examples:
 *
 * input = [
 *             [1, 1, 1, 1, 1, 1, 1],
 *             [1, 1, 1, 1, 1, 1, 1],
 *             [1, 1, 1, 0, 0, 0, 1],
 *             [1, 0, 1, 0, 0, 0, 1],
 *             [1, 0, 1, 1, 1, 1, 1],
 *             [1, 0, 1, 0, 0, 0, 0],
 *             [1, 1, 1, 0, 0, 0, 1],
 *             [1, 1, 1, 1, 1, 1, 1]
 *         ]
 *
 *
 * Output:
 * [
 *   [2, 3, 3, 5], [3, 1, 5, 1], [5, 3, 6, 5]
 * ]
 *
 * Explanation:
 * We have three rectangles here, starting from
 * (2, 3), (3, 1), (5, 3)
 *
 * Input = [
 *             [1, 0, 1, 1, 1, 1, 1],
 *             [1, 1, 0, 1, 1, 1, 1],
 *             [1, 1, 1, 0, 0, 0, 1],
 *             [1, 0, 1, 0, 0, 0, 1],
 *             [1, 0, 1, 1, 1, 1, 1],
 *             [1, 1, 1, 0, 0, 0, 0],
 *             [1, 1, 1, 1, 1, 1, 1],
 *             [1, 1, 0, 1, 1, 1, 0]
 *         ]
 *
 *
 * Output:
 * [
 *   [0, 1, 0, 1], [1, 2, 1, 2], [2, 3, 3, 5],
 *   [3, 1, 4, 1], [5, 3, 5, 6], [7, 2, 7, 2],
 *   [7, 6, 7, 6]
 * ]
 */
public class FindAllRectanglesFilledWithZero {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 0, 1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 0}
        };
        findAllRectangles(arr);
    }

    public static List<List<String>> findAllRectangles(int [][] arr){
        List<List<String>> result = new ArrayList<>();
         for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[i].length; j++){
                if(arr[i][j] == 0){
                    int[] end = endIndex(arr, i, j);
                    System.out.print("Start Index is ("+i+","+j+")  ");
                    System.out.println("End Index is ("+end[0]+","+end[1]+")");
                }
            }
        }
        return result;
    }

    public static int[] endIndex(int[][] arr, int i, int j) {
        int m = i;
        int n = j;

        // Traverse row wise
        while(m < arr.length) {
            if (arr[m][j] == 1) {
                break;
            }

            // Traverse column wise
            for (n = j; n < arr[0].length; n++) {
                if (arr[m][n] == 1) {
                    break;
                }
                // fill rectangle elements with any number so that we can exclude
                // next time
                arr[m][n] = 5;
            }
            m++;
        }
        return new int[] {m - 1, n-1};
    }
}
