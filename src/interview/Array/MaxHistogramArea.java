package interview.Array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * PROBLEM: 1 https://www.youtube.com/watch?v=ZmnqCZp9bBs
 * Find the largest rectangular area possible in a given histogram where the largest rectangle can be made of a
 * number of contiguous bars. For simplicity, assume that all bars have same width and the width is 1 unit.
 *
 * For example, consider the following histogram with 7 bars of heights {6, 2, 5, 4, 5, 1, 6}. The largest possible
 * rectangle possible is 12 (see the below figure, the max area rectangle is highlighted in red).
 */
public class MaxHistogramArea {

    public static void main(String[] args) {
        int[] input = new int[] { 6, 2, 5, 4, 5, 1, 6 };
        MaxHistogramArea maxHistogramArea = new MaxHistogramArea();
        System.out.println(maxHistogramArea.maxRectangularArea(input));

        input = new int[] { 3, 2, 3, 2 };
        System.out.println(maxHistogramArea.maxRectangularArea(input));

        int inputArray[][] = { { 1, 1, 1, 0 }, { 1, 1, 1, 1 }, { 0, 1, 1, 0 }, { 0, 1, 1, 1 }, { 1, 0, 0, 1 },
            { 1, 1, 1, 1 } };
        System.out.println(maxHistogramArea.getMaximumSizeRectangle(inputArray));
    }

    private int maxRectangularArea(int[] inputArray) {
        Deque<Integer> stack = new LinkedList<>();
        int maxArea = -1;
        int top = 0;
        int area = 0;
        int i = 0;

        if (inputArray == null || inputArray.length < 1) {
            return 0;
        }

        while (i < inputArray.length) {
            // If this bar is higher than the bar on top stack, push it to stack
            if (stack.isEmpty() || inputArray[stack.peekFirst()] <= inputArray[i]) {
                stack.addFirst(i++);
            }
            // If this bar is lower than top of stack,
            // then calculate area of rectangle with stack
            // top as the smallest (or minimum height) bar.
            // 'i' is 'right index' for the top and element
            // before top in stack is 'left index'
            else {
                top = stack.pollFirst();
                if (stack.isEmpty()) {
                    area = i * inputArray[top];
                } else {
                    area = inputArray[top] * (i - stack.peekFirst() - 1);
                }
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }

        while (!stack.isEmpty()) {
            top = stack.pollFirst();
            if (stack.isEmpty()) {
                area = i * inputArray[top];
            } else {
                area = inputArray[top] * (i - stack.peekFirst() - 1);
            }
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }

    /**
     * PROBLEM: 2 https://www.youtube.com/watch?v=g8bSdXCG-lA
     *
     * Given a 2D matrix of 0s and 1s. Find largest rectangle of all 1s in this matrix.
     *
     * Maintain a temp array of same size as number of columns. Copy first row to this temp array and find largest
     * rectangular area for histogram. Then keep adding elements of next row to this temp array if they are not zero. If
     * they are zero then put zero there. Every time calculate max area in histogram.
     *
     * Time complexity - O(rows*cols) Space complexity - O(cols) - if number of cols is way higher than rows then do
     * this process for rows and not columns.
     */
    private int getMaximumSizeRectangle(int[][] inputMatrix) {

        int[] resultArray = new int[inputMatrix[0].length];
        int maxArea = -1;

        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix[0].length; j++) {
                if (inputMatrix[i][j] == 0) {
                    resultArray[j] = 0;
                } else {
                    resultArray[j] += inputMatrix[i][j];
                }
            }
            maxArea = Math.max(maxArea, maxRectangularArea(resultArray));
        }

        return maxArea;
    }
}
