package interview.Array;

import java.util.Arrays;

/**
 * Given job start and end times of all the job for a day, find the minimum number of people required for the job so
 * that no job overlap for a person. We are given two arrays which represent start and end times of jobs. Examples:
 * Input:  start[]  = {9:00,  9:40, 9:50,  11:00, 15:00, 18:00}
 * end[]  =   {9:10, 12:00, 11:20, 11:30, 19:00, 20:00}
 * Output: 3
 */
public class MinimumPerson {

  public static void main(String[] args) {
    int[] startTimings = new int[]{900, 940, 950, 1100, 1500, 1800};
    int[] endTimings = new int[]{910, 1200, 1120, 1130, 1900, 2000};
    findMinimumPerson(startTimings, endTimings);
    findMinimumPersonEfficient(startTimings, endTimings);
  }

  /**
   * SOLUTION : 1
   */
  private static void findMinimumPerson(int[] startTimings, int[] endTimings) {

         /*  Lambda expression to find min value in multi dimensional array

         int min = Arrays.stream(points).min((int[] point1, int[] point2) -> {
            return point1[0] - point2[0];
        }).get()[0];

        int min = Arrays.stream(points).min(Comparator.comparingInt(point1 -> point1[0])).get()[0];
        int max = Arrays.stream(points).min(Comparator.comparingInt(point1 -> point1[0])).get()[0];
        */

    int minTime = Arrays.stream(startTimings).min().getAsInt();
    int maxTime = Arrays.stream(endTimings).max().getAsInt();

    int[] outputArray = new int[maxTime - minTime + 1];

    for (int i = 0; i < startTimings.length; i++) {
      int indexVal = startTimings[i] - minTime;
      outputArray[indexVal] += 1;
    }

    for (int i = 0; i < endTimings.length; i++) {
      int indexVal = endTimings[i] - minTime;
      outputArray[indexVal] -= 1;
    }

    int maxPersonRequired = 0;
    int runningSeq = 0;
    for (int i = 0; i < outputArray.length; i++) {
      runningSeq += outputArray[i];
      if (runningSeq > maxPersonRequired) {
        maxPersonRequired = runningSeq;
      }
    }
    System.out.println(maxPersonRequired);
  }

  /**
   * SOLUTION : 2
   */
  private static void findMinimumPersonEfficient(int[] startTimings, int[] endTimings) {

    Arrays.sort(startTimings);
    Arrays.sort(endTimings);
    int guests_in = 0, maxGuests = 0, time = 0;
    int i = 0, j = 0;

    while (i < startTimings.length && j < endTimings.length) {
      if (startTimings[i] <= endTimings[j]) {
        guests_in++;
        if (guests_in > maxGuests) {
          maxGuests = guests_in;
          time = startTimings[i];
        }
        i++;
      } else {
        guests_in--;
        j++;
      }
    }

    System.out.println("Max Person Required " + maxGuests + " at time :" + time);
  }
}
