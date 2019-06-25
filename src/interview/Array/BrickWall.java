package interview.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/brick-wall/
 *
 * There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the
 * same height but different width. You want to draw a vertical line from the top to the bottom and cross the least
 * bricks.
 *
 * The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each
 * brick in this row from left to right.
 *
 * If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how
 * to draw the line to cross the least bricks and return the number of crossed bricks.
 *
 * You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously
 * cross no bricks.
 *
 *
 *
 * Example:
 *
 * Input: [[1,2,2,1],
 * [3,1,2],
 * [1,3,2],
 * [2,4],
 * [3,1,2],
 * [1,3,1,1]]
 *
 * Output: 2
 */
public class BrickWall {

  public static void main(String[] args) {
    BrickWall wall = new BrickWall();

    List<List<Integer>> inputList = new ArrayList<>();
    inputList.add(Arrays.asList(1, 2, 2, 1));
    inputList.add(Arrays.asList(3, 1, 2));
    inputList.add(Arrays.asList(1, 3, 2));
    inputList.add(Arrays.asList(2, 4));
    inputList.add(Arrays.asList(3, 1, 2));
    inputList.add(Arrays.asList(1, 3, 1, 1));
    System.out.println(wall.leastBricks(inputList));
  }

  public int leastBricks(List<List<Integer>> wall) {

    if (wall == null || wall.size() == 0) {
      return 0;
    }

    int count = 0;
    Map<Integer, Integer> mapValues = new HashMap<>();

    for (List<Integer> row : wall) {
      int length = 0;
      for (int i = 0; i < row.size() - 1; i++) {
        length += row.get(i);
        mapValues.put(length, mapValues.getOrDefault(length, 0) + 1);
        count = Math.max(count, mapValues.get(length));
      }
    }
    return wall.size() - count;
  }
}
