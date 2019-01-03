package interview.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of time intervals in any order, merge all overlapping intervals into one
 * and output the result which should have only mutually exclusive intervals.
 * Let the intervals be represented as pairs of integers for simplicity.
 *
 For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8} }.
 The intervals {1,3} and {2,4} overlap with each other, so they should be merged and become {1, 4}.
 Similarly {5, 7} and {6, 8} should be merged and become {5, 8}

 https://www.programcreek.com/2012/12/leetcode-merge-intervals/

 */
public class MergeIntervals {

    public static void main(String[] args) {

        Interval interval1 = new Interval(1, 3);
        Interval interval2 = new Interval(1, 4);
        Interval interval3 = new Interval(2, 4);
        Interval interval4 = new Interval(5, 7);
        Interval interval5 = new Interval(6, 8);
        List<Interval> intervalList = new ArrayList<>();
        intervalList.add(interval1);
        intervalList.add(interval3);
        intervalList.add(interval4);
        intervalList.add(interval5);
        intervalList.add(interval2);

        List<Interval> result = merge(intervalList);
        for (Interval res : result) {
            System.out.println("[" + res.getStart() + ", " + res.getEnd() + "]");
        }
    }

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();

        if (intervals == null || intervals.size() == 0) {
            return result;
        }

        // Can be sorted simply like below
        //Collections.sort(intervals, Comparator.comparingInt(Interval::getStart));

        Collections.sort(intervals, (i1, i2) -> {
            if (i1.start != i2.start) {
                return i1.start - i2.start;
            } else {
                return i1.end - i2.end;
            }
        });

        Interval pre = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (curr.start > pre.end) {
                result.add(pre);
                pre = curr;
            } else {
                Interval merged = new Interval(pre.start, Math.max(pre.end, curr.end));
                pre = merged;
            }
        }
        result.add(pre);

        return result;
    }

    static class Interval {

        int start;

        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }

}