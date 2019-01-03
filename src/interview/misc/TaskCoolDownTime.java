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
//     ans: 11 (A B C . . A .C B D A )
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskCoolDownTime {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 1, 1, 3, 4 };
        taskTimeWithMaintainingSequenece(arr, 2);
        tasKWithReordering(arr, 2);
    }

    private static void taskTimeWithMaintainingSequenece(int[] arr, int cooldownTime) {
        StringBuilder sbr = new StringBuilder();
        Map<Integer, Integer> lastSeenMap = new HashMap<>();
        int lastSeen = 0;
        int finalCounter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!lastSeenMap.containsKey(arr[i])) {
                lastSeenMap.put(arr[i], i);
                sbr.append(arr[i]);
            } else {
                lastSeen = lastSeenMap.get(arr[i]);
                if (lastSeen < cooldownTime) {

                    for (int j = 0; j < (cooldownTime - lastSeen); j++) {
                        sbr.append("_");
                    }
                }
                sbr.append(arr[i]);
                lastSeenMap.put(arr[i], i);
            }
        }

        System.out.println("SBr " + sbr.toString());
    }

    private static void tasKWithReordering(int[] arr, int cooldownTime) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!countMap.containsKey(arr[i])) {
                countMap.put(arr[i], 1);
            } else {
                count = countMap.get(arr[i]);
                countMap.put(arr[i], ++count);
            }
        }

        Queue<Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        });
        maxHeap.addAll(countMap.entrySet());

        StringBuilder finalResult = new StringBuilder();
        List<Entry<Integer, Integer>> remainingElemt = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            int i = 0;
            remainingElemt.clear();
            while (i <= cooldownTime && !maxHeap.isEmpty()) {
                Map.Entry<Integer, Integer> current = maxHeap.poll();
                finalResult.append(current.getKey());
                current.setValue(current.getValue() - 1);
                remainingElemt.add(current);
                i++;
            }

            while (i <= cooldownTime) {//i <= k, not i < k !!!
                finalResult.append("_");
                i++;//remember to add i++ !!!
            }

            for (Map.Entry<Integer, Integer> e : remainingElemt) {//O(klogk) time
                if (e.getValue() > 0) {
                    maxHeap.offer(e);
                }
            }
        }
        System.out.println("finalResult " + finalResult.toString());
    }

    /**
     * Find the task that appears for the most time
     * Use a map to count the number of the times the task appears  then get the maximum count
     * the result is decided by the maximum count and the number of tasks with maximum count
     * <p>
     * two conditions:
     * 1.  5 4 _ _ _ 5 4 _ _ _ 5 4 _ _ _ 5 4  the rest tasks cannot fill the empty slots
     * 5 4 3 2 _ 5 4 3 2 _ 5 4 _ _ _ 5 4
     * the answer is (maxCount - 1) * (interval + 1) + CountOfMax
     * 1. 5 4 _ _ _ 5 4 _ _ _ 5 4 _ _ _ 5 4  the rest tasks cannot fill the empty slots
     * 5 4 3 2 1 6 5 4 3 2 1 6 5 4 6 _ _ 5 4
     * the answer is the length of the nums
     * the task which does not have max count first fills the empty slots and then just insert any valid place
     */

    //output a sequence of tasks that takes least time:O(maxFrequency*klogk) time,O(n) space,n is number of
    // unique tasks
    private static String taskSchedule4(String str, int k) {
        StringBuilder rearranged = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }

        Queue<Entry<Character, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        });
        maxHeap.addAll(map.entrySet());//O(nlogn) time, O(n) space
        ArrayList<Entry<Character, Integer>> temp = new ArrayList<>();//O(k) space

        while (!maxHeap.isEmpty()) {//O(max frequency) time
            int i = 0;
            temp.clear();
            while (i <= k && !maxHeap.isEmpty()) {//O(k) time
                Map.Entry<Character, Integer> curr = maxHeap.poll();
                rearranged.append(curr.getKey());
                curr.setValue(curr.getValue() - 1);
                temp.add(curr);
                i++;
            }

            //add this code if we wanna add _ to show that we need to wait for cooldown, eg.AABB, 2 -> AB_AB
            while (i <= k) {//i <= k, not i < k !!!
                rearranged.append("_");
                i++;//remember to add i++ !!!
            }

            for (Map.Entry<Character, Integer> e : temp) {//O(klogk) time
                if (e.getValue() > 0) {
                    maxHeap.offer(e);
                }
            }
        }

        //add this code if we add "_" to the string, we need to delete all the "_" at the tail, eg.A__A__ -> A__A
        for (int i = rearranged.length() - 1; i >= 0 && rearranged.charAt(i) == '_'; i--) {
            rearranged.deleteCharAt(i);
        }

        return rearranged.toString();
    }
}

//AABCDA

