package interview.Array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 *
 * There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the
 * start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence
 * the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at
 * most 104 balloons.
 *
 * An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend
 * bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An
 * arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be
 * shot to burst all balloons.
 *
 * Example:
 *
 * Input:
 * [[10,16], [2,8], [1,6], [7,12]]
 *
 * Output:
 * 2
 *
 * Explanation:
 * One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x
 * = 11 (bursting the other two balloons).
 */
public class FindMinArrowShots {

  /**
   * Solution 1
   * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/discuss/93703/Share-my-explained-Greedy
   * -solution
   */
  public int findMinArrowShots(int[][] points) {
    if (points == null || points.length == 0) {
      return 0;
    }
    Arrays.sort(points, Comparator.comparingInt(a -> a[0]));

    int[] point = points[0];
    int count = 1;
    for (int i = 1; i < points.length; i++) {
      if (points[i][0] > point[1]) {
        count++;
        point = points[i];
      }
    }
    return count;
  }

  public static void main(String[] args) {
    FindMinArrowShots shots = new FindMinArrowShots();

    int[][] input = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
    System.out.println(shots.findMinArrowShots(input));

    input = new int[][] {{7, 10}, {1, 5}, {3, 6}, {2, 4}, {1, 4}};
    System.out.println(shots.findMinArrowShots(input));
  }
}
