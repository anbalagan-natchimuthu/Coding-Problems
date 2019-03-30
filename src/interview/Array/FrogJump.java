package interview.Array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/frog-jump/
 *
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone.
 * The frog can jump on a stone, but it must not jump into the water.
 *
 * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the
 * river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1
 * unit.
 *
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the
 * frog can only jump in the forward direction.
 *
 * Note:
 *
 * The number of stones is ≥ 2 and is < 1,100.
 * Each stone's position will be a non-negative integer < 231.
 * The first stone's position is always 0.
 * Example 1:
 *
 * [0,1,3,5,6,8,12,17]
 *
 * There are a total of 8 stones.
 * The first stone at the 0th unit, second stone at the 1st unit,
 * third stone at the 3rd unit, and so on...
 * The last stone at the 17th unit.
 *
 * Return true. The frog can jump to the last stone by jumping
 * 1 unit to the 2nd stone, then 2 units to the 3rd stone, then
 * 2 units to the 4th stone, then 3 units to the 6th stone,
 * 4 units to the 7th stone, and 5 units to the 8th stone.
 */
public class FrogJump {

  public static boolean canCross(int[] stones) {
    HashMap<Integer, Set<Integer>> map = new HashMap<>();
    for (int i = 0; i < stones.length; i++) {
      map.put(stones[i], new HashSet<>());
    }
    map.get(0).add(0);
    for (int i = 0; i < stones.length; i++) {
      for (int k : map.get(stones[i])) {
        for (int step = k - 1; step <= k + 1; step++) {
          if (step > 0 && map.containsKey(stones[i] + step)) {
            map.get(stones[i] + step).add(step);
          }
        }
      }
    }
    return map.get(stones[stones.length - 1]).size() > 0;
  }

  public static void main(String[] args) {
    System.out.println(canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
    System.out.println(canCross(new int[]{0, 1, 2, 3, 4, 8, 9, 11}));
  }
}
