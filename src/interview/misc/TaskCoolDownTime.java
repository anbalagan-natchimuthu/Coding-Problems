package interview.misc;
// Given an array of task and k wait time for which a repeated task needs to wait k time to execute again. return
// overall unit time it will take to complete all the task.
//     Example:
//     1. A B C D and k = 3
//     ans: 4 (execute order A B C D)
//     2. A B A D and k = 3
//     ans: 6 (execute order A B . . A D)
//     3. A A A A and k =3
//     ans: 13 (A . . . A . . . A . . . A)
//     4. A B C A C B D A and k = 4
//     ans: 11 (A B C . . A . C B D A )
// Given a series of tasks, different tasks may belong to different types. These tasks need to be placed in the CPU.
// Running the same type is a matter of cooling time. . . After a long period of time, it took several examples to
// understand
//     Similar to an OS. Give you a single-threaded scheduler, and eg. 4 kinds of thread:1,2,3,4, Cooldown: 3,
//     In multithreading, the same type of thread waits for a thread to run after the cooldown period has elapsed,
// and how many time slots are used by the last scheduler.
//
//     For example, thread: 1, 2, 1, 1, 3, 4; Cooling time: 2 time slot, scheduler should be like this: 1, 2, _, 1,
// _, _, 1, 3, 4, finally Back 9.

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskCoolDownTime {

  public static void main(String[] args) {
    int[] arr = {1, 2, 1, 1, 3, 4};
    char[] charArr = {'A', 'A', 'A', 'B', 'B', 'B'};
    leastInterval(charArr, 2);
    tasKWithReordering(arr, 2);
    taskTimeWithMaintainingSequenece(arr, 2);
  }

  /**
   * Version: 0: Task should maintain Sequence Order.
   * https://www.youtube.com/watch?v=LiF2vM_IoAg
   */
  private static void taskTimeWithMaintainingSequenece(int[] arr, int cooldownTime) {
    StringBuilder sbr = new StringBuilder();
    Map<Integer, Integer> lastSeenMap = new HashMap<>();
    int lastSeen = 0;
    for (int i = 0; i < arr.length; i++) {
      if (!lastSeenMap.containsKey(arr[i])) {
        lastSeenMap.put(arr[i], i);
        sbr.append(arr[i]);
      } else {
        lastSeen = lastSeenMap.get(arr[i]);
        if (i - lastSeen - 1 < cooldownTime) {

          for (int j = 0; j < cooldownTime - (i - lastSeen - 1); j++) {
            sbr.append("_");
          }
        }
        sbr.append(arr[i]);
        lastSeenMap.put(arr[i], i);
      }
    }

    System.out.println("SBr " + sbr.toString());
  }

  /**
   * Version: 1: Input char array and output print total no.of time.
   * Tasks could be done without original order
   * https://www.youtube.com/watch?v=LiF2vM_IoAg
   */
  public static int leastInterval(char[] tasks, int n) {
    int[] map = new int[26];
    for (char c : tasks) {
      map[c - 'A']++;
    }
    PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
    for (int f : map) {
      if (f > 0) {
        queue.add(f);
      }
    }
    int time = 0;
    while (!queue.isEmpty()) {
      int i = 0;
      List<Integer> remainingElemt = new ArrayList<>();
      while (i <= n) {
        if (!queue.isEmpty()) {
          if (queue.peek() > 1) {
            remainingElemt.add(queue.poll() - 1);
          } else {
            queue.poll();
          }
        }
        time++;
        if (queue.isEmpty() && remainingElemt.size() == 0) {
          break;
        }
        i++;
      }
      for (int l : remainingElemt) {
        queue.add(l);
      }
    }
    System.out.println("time::" + time);
    return time;
  }

  /**
   * Version: 2: Input int array and output print task ID as well.
   */
  private static void tasKWithReordering(int[] arr, int cooldownTime) {

    Map<Integer, Integer> countMap = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      countMap.compute(arr[i], (key, value) -> {
        if (value == null) {
          return 1;
        } else {
          return value + 1;
        }
      });
    }

    Queue<Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(
        (entry1, entry2) -> entry2.getValue() - entry1.getValue());
    maxHeap.addAll(countMap.entrySet());

    StringBuilder finalResult = new StringBuilder();
    List<Entry<Integer, Integer>> remainingElemt = new ArrayList<>();

    while (!maxHeap.isEmpty()) {
      remainingElemt.clear();
      int i = 0;

      while (i <= cooldownTime) {
        if (!maxHeap.isEmpty()) {
          if (maxHeap.peek().getValue() > 1) {
            Map.Entry<Integer, Integer> current = maxHeap.poll();
            finalResult.append(current.getKey());
            current.setValue(current.getValue() - 1);
            remainingElemt.add(current);
          } else {
            finalResult.append(maxHeap.poll().getKey());
          }
        } else {
          finalResult.append("_");
        }

        if (maxHeap.isEmpty() && remainingElemt.size() == 0) {
          break;
        }
        i++;
      }

      for (Entry<Integer, Integer> element : remainingElemt) {
        maxHeap.offer(element);
      }
    }
    System.out.println("finalResult " + finalResult.toString());
  }
}