package interview.misc;

import java.util.HashMap;

import sun.jvm.hotspot.utilities.Assert;

/**
 * Solution: 1
 * https://leetcode.com/problems/lru-cache/solution/
 *
 * * https://www.programcreek.com/2013/03/leetcode-lru-cache-java/
 * *
 * * Design and implement a data structure for Least Recently Used (LRU) cache.
 * * It should support the following operations: get and set.
 * *
 * * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 * * it should invalidate the least recently used item before inserting a new item.
 * *
 * * Example:
 * *
 * * LRUCache cache = new LRUCache( 2 / capacity / );
 * *
 * * cache.put(1,1);
 * * cache.put(2,2);
 * * cache.get(1);       // returns 1
 * * cache.put(3,3);    // evicts key 2
 * * cache.get(2);       // returns -1 (not found)
 * * cache.put(4,4);    // evicts key 1
 * * cache.get(1);       // returns -1 (not found)
 * * cache.get(3);       // returns 3
 * * cache.get(4);       // returns 4
 */

public class LRUCache {

  int capacity;
  HashMap<Integer, Node> map = new HashMap<>();
  Node head = null;
  Node end = null;

  public LRUCache(int capacity) {
    this.capacity = capacity;
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      Node n = map.get(key);
      remove(n);
      setHead(n);
      return n.value;
    }
    return -1;
  }

  public void put(int key, int value) {
    if (map.containsKey(key)) {
      Node old = map.get(key);
      old.value = value;
      remove(old);
      setHead(old);
    } else {
      Node created = new Node(key, value);
      if (map.size() >= capacity) {
        map.remove(end.key);
        remove(end);
        setHead(created);
      } else {
        setHead(created);
      }

      map.put(key, created);
    }
  }

  public void setHead(Node n) {
    n.next = head;
    n.pre = null;

    if (head != null) {
      head.pre = n;
    }

    head = n;

    if (end == null) {
      end = head;
    }
  }

  public void remove(Node n) {
    if (n.pre != null) {
      n.pre.next = n.next;
    } else {
      head = n.next;
    }

    if (n.next != null) {
      n.next.pre = n.pre;
    } else {
      end = n.pre;
    }
  }

  class Node {

    int key;
    int value;
    Node pre;
    Node next;

    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  public static void main(String[] args) {
    LRUCache cache = new LRUCache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    Assert.that(cache.get(1) == 1, "failed: Expected 1"); // returns 1
    cache.put(3, 3);    // evicts key 2
    Assert.that(cache.get(2) == -1, "failed: Expected -1");       // returns -1 (not found)
    cache.put(4, 4);    // evicts key 1
    Assert.that(cache.get(1) == -1, "failed: Expected -1");       // returns -1 (not found)
    Assert.that(cache.get(3) == 3, "failed: Expected 3");       // returns 3
    Assert.that(cache.get(4) == 4, "failed: Expected 4");
  }
}

