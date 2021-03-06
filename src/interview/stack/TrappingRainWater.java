package interview.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class TrappingRainWater {

  public static void main(String[] args) {
    int[] array = {9, 1, 4, 2, 0, 3};
    System.out.println(trap(array));
    System.out.println(trapUsingTwoPointers(array));

    array = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    System.out.println(trap(array));
    System.out.println(trapUsingTwoPointers(array));
  }

  /**
   * Solution: 1 Using Stack
   * https://leetcode.com/problems/trapping-rain-water/solution/
   */
  public static int trap(int[] inputArray) {
    Stack<Integer> stack = new Stack<>();
    int i = 0, ans = 0;

    while (i < inputArray.length) {
      while (!stack.isEmpty() && (inputArray[i] > inputArray[stack.peek()])) {
        int top = stack.pop();

        if (stack.isEmpty()) {
          break;
        }
        int dist = i - stack.peek() - 1;
        int boundedHeight = Math.min(inputArray[i], inputArray[stack.peek()]) - inputArray[top];
        ans += dist * boundedHeight;
      }
      stack.push(i++);
    }
    return ans;
  }

  /**
   * Solution: 2
   * Using Two pointers
   */
  public static int trapUsingTwoPointers(int[] height) {
    int left = 0, right = height.length - 1, leftMax = 0, rightMax = 0, ans = 0;

    while (left < right) {
      if (height[left] < height[right]) {
        if (height[left] >= leftMax) {
          leftMax = height[left];
        } else {
          ans += (leftMax - height[left]);
        }
        left++;
      } else {
        if (height[right] >= rightMax) {
          rightMax = height[right];
        } else {
          ans += (rightMax - height[right]);
        }
        right--;
      }
    }
    return ans;
  }
}
