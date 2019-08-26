package interview.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * Solution: 1
 * https://leetcode.com/problems/two-sum-iii-data-structure-design/
 *
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 *
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 *
 * Example 1:
 *
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 * Example 2:
 *
 * add(3); add(1); add(2);
 * find(3) -> true
 * find(6) -> false
 *
 */
public class TwoSum {

  Map<Integer, Integer> map = new HashMap<>();

  public static void main(String[] args) {
    TwoSum sum = new TwoSum();
    sum.add(3);
    sum.add(1);
    sum.add(2);
    System.out.println(sum.find(3));
    System.out.println(sum.find(6));
  }

  public void add(int val) {
    map.compute(val, (k, v) -> {
      if (v == null) {
        return 1;
      } else {
        return v+1;
      }
    });
  }

  public boolean find (int val) {
    for (int key : map.keySet()) {
      int res = val - key;
      if (map.containsKey(res)) {
        if (res != key || map.get(res) > 1) {
          return true;
        }
      }
    }
    return false;
  }

}


  /**
   * Solution: 2

  Set<Integer> sum;
  Set<Integer> num;

  TwoSum(){
    sum = new HashSet<>();
    num = new HashSet<>();
  }
  // Add the number to an internal data structure.
  public void add(int number) {
    if(num.contains(number)){
      sum.add(number * 2);
    }else{
      Iterator<Integer> iter = num.iterator();
      while(iter.hasNext()){
        sum.add(iter.next() + number);
      }
      num.add(number);
    }
  }

  // Find if there exists any pair of numbers which sum is equal to the value.
  public boolean find(int value) {
    return sum.contains(value);
  }

   */

