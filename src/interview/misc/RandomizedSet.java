package interview.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import sun.jvm.hotspot.utilities.Assert;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * https://leetcode.com/problems/insert-delete-getrandom-o1/discuss/85425/Java-Solution-(Beats-99.20)
 * -Using-HashMap-and-ArrayList-with-Explanation
 *
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of
 * being returned.
 * Example:
 *
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 *
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 *
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 *
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 *
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 *
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 *
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 *
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 */
public class RandomizedSet {

  //The Map contains the mapping between the value and its index in the ArrayList.
  Map<Integer, Integer> map;
  //The List is used to store numbers and serve the getRandom() method.
  List<Integer> list;

  public RandomizedSet() {
    map = new HashMap<>();
    list = new ArrayList<>();
  }

  /**
   * Inserts a value to the set. Returns true if the set did not already contain the specified element.
   */
  public boolean insert(int value) {
    if (map.containsKey(value)) {
      return false;
    }
    map.put(value, list.size());
    list.add(value);
    return true;
  }

  /**
   * Removes a value from the set. Returns true if the set contained the specified element.
   * ArrayList's remove method is O(n) if you remove from random location. To overcome that, we swap the values
   * between (randomIndex, lastIndex) and always remove the entry from the end of the list. After the swap,
   * you need to update the new index of the swapped value (which was previously at the end of the list) in the map.
   */
  public boolean remove(int value) {
    int index = map.getOrDefault(value, -1);
    if (index == -1) {
      return false;
    }

    Collections.swap(list, index, list.size() - 1);
    int swappedWith = list.get(index);
    map.put(swappedWith, index);

    list.remove(list.size() - 1);
    map.remove(value);
    return true;
  }

  public int getRandom() {
    Random random = new Random();
    return list.get(random.nextInt(list.size()));
  }

  public static void main(String[] args) {
    RandomizedSet randomizedSet = new RandomizedSet();
    Assert.that(randomizedSet.insert(1) == true, "Expected value 'true'");
    Assert.that(randomizedSet.remove(2) == false, "Expected value 'false'");
    Assert.that(randomizedSet.insert(2) == true, "Expected value 'true'");
    int randomVal = randomizedSet.getRandom();
    Assert.that(randomVal == 1 || randomVal == 2, "Expected value 'true'");
    Assert.that(randomizedSet.remove(1) == true, "Expected value 'true'");
    Assert.that(randomizedSet.insert(2) == false, "Expected value 'false'");
    randomVal = randomizedSet.getRandom();
    Assert.that(randomVal == 2, "Expected value 'true'");
  }
}
