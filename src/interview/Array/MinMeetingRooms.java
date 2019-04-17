package interview.Array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 *
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: 1
 */
public class MinMeetingRooms {

  public static void main(String[] args) {
    MinMeetingRooms meetingRooms = new MinMeetingRooms();
    MinMeetingRooms.Interval[] interval = new MinMeetingRooms.Interval[4];
    interval[0] = new MinMeetingRooms.Interval(0, 3);
    interval[1] = new MinMeetingRooms.Interval(2, 5);
    interval[2] = new MinMeetingRooms.Interval(6, 8);
    interval[3] = new MinMeetingRooms.Interval(8, 10);
    // System.out.println(meetingRooms.minMeetingRooms(interval));

    MinMeetingRooms.Interval[] interval1 = new MinMeetingRooms.Interval[5];
    interval1[0] = new MinMeetingRooms.Interval(1, 3);
    interval1[1] = new MinMeetingRooms.Interval(2, 5);
    interval1[2] = new MinMeetingRooms.Interval(3, 4);
    interval1[3] = new MinMeetingRooms.Interval(6, 8);
    interval1[4] = new MinMeetingRooms.Interval(4, 6);
    System.out.println(meetingRooms.minMeetingRooms(interval1));
  }

  public int minMeetingRooms(Interval[] intervals) {
    if (intervals.length == 0) {
      return 0;
    }
    //Arrays.sort(intervals, Comparator.comparingInt(Interval::getStart));
    Arrays.sort(intervals, (a, b) -> {
      return a.start - b.start;
    });

    // Sort it by end so that minimumInterval will check against lowest ending time
    //PriorityQueue<Interval> pq = new PriorityQueue<>(Comparator.comparingInt(Interval::getEnd));
    PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.end - b.end);

    pq.offer(intervals[0]);
    int rooms = 1;
    for (int i = 1; i < intervals.length; i++) {
      Interval current = intervals[i];
      if (current.start >= pq.peek().end) {
        Interval minimumInterval = pq.poll();
        minimumInterval = new Interval(minimumInterval.start, intervals[i].end);
        pq.offer(minimumInterval);
      } else {
        rooms++;
        pq.offer(intervals[i]);
      }
    }
    while (!pq.isEmpty()) {
      Interval res = pq.poll();
      System.out.println(res.start + "::" + res.end);
    }
    return rooms;
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
