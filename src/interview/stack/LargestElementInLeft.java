package interview.stack;

import java.util.Arrays;
import java.util.Stack;

public class LargestElementInLeft {

  /**
   * PROBLEM: 1
   * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many
   * days you would have to wait until a warmer temperature. If there is no future day for which this is possible,
   * put 0 instead.
   *
   * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1,
   * 4, 2, 1, 1, 0, 0].
   *
   * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the
   * range [30, 100].
   */

  public static int[] dailyTemperatures(int[] temperatures) {
    Stack<Integer> stack = new Stack<>();
    int[] result = new int[temperatures.length];

    for (int i = temperatures.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
        stack.pop();
      }

      result[i] = stack.isEmpty() ? 0 : stack.peek() - i;

      stack.add(i);
    }
    return result;
  }

  /**
   * PROBLEM : 2
   * Given an array of unique positive integers, for each integer, return the first larger element in the array to
   * the left. If there's no larger element, return -1 for it.
   * Example:
   * arr = [70, 60, 40, 50, 35, 90, 10]
   * ans = [-1, 70, 60, 60, 50, -1, 90]
   */

  public static int[] firstLargestElement(int[] arr) {
    Stack<Integer> stack = new Stack<>();
    int[] result = new int[arr.length];

    for (int i = 0; i < arr.length; i++) {

      while (!stack.isEmpty() && arr[i] > stack.peek()) {
        stack.pop();
      }

      result[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(arr[i]);
    }

    return result;
  }

  public static void main(String[] args) {
    int[] temps = dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
    System.out.println(Arrays.toString(temps));

    int[] largest = firstLargestElement(new int[]{70, 60, 40, 50, 35, 90, 10});
    System.out.println(Arrays.toString(largest));
  }
}
