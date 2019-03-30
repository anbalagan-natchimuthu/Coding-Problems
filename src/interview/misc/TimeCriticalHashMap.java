package interview.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/discuss/interview-experience/189573/Big-mistake-with-Google-telephonic-first-round-Software
 * -Engineer-position
 *
 * you have to implement a time critical hashmap, such that you are given input as: key, value & time-limit.
 * If the time limit gets over and then the hashmap must not return any value. This should be strictly
 * implemented as an Object Oriented code.
 */
public class TimeCriticalHashMap {

  class TimeHashMap<K, V> {

    Map<K, Pair<V, Long>> mapValues = new HashMap<>();
    long limit;

    TimeHashMap(long limit) {
      this.limit = limit;
    }

    public V get(K key) {
      if (!mapValues.containsKey(key)) {
        return null;
      }

      Pair<V, Long> pair = mapValues.get(key);

      if (System.currentTimeMillis() - pair.time <= limit) {
        return pair.value;
      }

      mapValues.remove(key);
      return null;
    }

    public V put(K key, V value) {
      Pair<V, Long> pair = mapValues.put(key, new Pair(value, System.currentTimeMillis()));
      return pair != null ? pair.value : null;
    }
  }

  class Pair<V, Long> {

    private V value;
    private Long time;

    Pair(V value, Long time) {
      this.value = value;
      this.time = time;
    }
  }
}
