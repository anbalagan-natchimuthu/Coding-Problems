package interview.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/asteroid-collision/
 *
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning
 * right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If
 * both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 * Example 1:
 * Input:
 * asteroids = [5, 10, -5]
 * Output: [5, 10]
 * Explanation:
 * The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 */
public class AsteroidCollision {

  public static int[] asteroidCollision(int[] a) {
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < a.length; i++) {
      if (stack.isEmpty() || a[i] > 0) {
        stack.push(a[i]);
        continue;
      }

      while (true) {
        int prev = stack.peek();
        if (prev < 0) {
          stack.push(a[i]);
          break;
        }
        if (prev == -a[i]) {
          stack.pop();
          break;
        }
        if (prev > -a[i]) {
          break;
        }
        stack.pop();
        if (stack.isEmpty()) {
          stack.push(a[i]);
          break;
        }
      }
    }

    int[] res = new int[stack.size()];
    for (int i = stack.size() - 1; i >= 0; i--) {
      res[i] = stack.pop();
    }

    return res;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(asteroidCollision(new int[] {-2, -1, 1, 2, -1, -4})));
    System.out.println(Arrays.toString(asteroidCollision(new int[] {5, 10, -5})));
    System.out.println(Arrays.toString(asteroidCollision(new int[] {8, -8})));
    System.out.println(Arrays.toString(asteroidCollision(new int[] {10, 2, -5})));
    System.out.println(Arrays.toString(asteroidCollision(new int[] {-2, -1, 1, 2})));
  }
}
