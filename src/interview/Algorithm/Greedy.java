package interview.Algorithm;

/**
 * Created by 212438472 on 8/26/18.
 */
public class Greedy {

    /*
    https://www.geeksforgeeks.org/activity-selection-problem-greedy-algo-1/
    You are given n activities with their start and finish times. Select the maximum number of activities
    that can be performed by a single person, assuming that a person can only work on a single activity at a time.
        Example:

    Example 1 : Consider the following 3 activities sorted by
    by finish time.
        start[]  =  {10, 12, 20};
    finish[] =  {20, 25, 30};
    A person can perform at most two activities. The
    maximum set of activities that can be executed
    is {0, 2} [ These are indexes in start[] and
    finish[] ]

    Example 2 : Consider the following 6 activities
    sorted by by finish time.
        start[]  =  {1, 3, 0, 5, 8, 5};
    finish[] =  {2, 4, 6, 7, 9, 9};
    A person can perform at most four activities. The
    maximum set of activities that can be executed
    is {0, 1, 3, 4} [ These are indexes in start[] and
    finish[] ]*/

    // Prints a maximum set of activities that can be done by a single
    // person, one at a time.
    //  startTimings[] -->  An array that contains start time of all activities
    //  endTimings[] -->  An array that contains finish time of all activities
    public static int printMaxActivities(int[] startTimings, int[] endTimings) {
        int count = 0;

        // Assume that all activities are sorted by End timings
        System.out.println("Following activities are selected : ");

        // The first activity always gets selected
        int i = 0;
        System.out.print(i+" ");
        count ++;

        for (int j = 1; j < endTimings.length; j++) {

            // If this activity has start time greater than or
            // equal to the finish time of previously selected
            // activity, then select it
            if (startTimings[j] >= endTimings[i]) {
                System.out.print(j+" ");
                count++;
                i = j;
            }
        }
        System.out.println();
        return count;
    }

     public static void main(String[] args)
    {
        int s[] =  {1, 3, 0, 5, 8, 5};
        int f[] =  {2, 4, 6, 7, 9, 9};

        System.out.println("No.Of activities Performed:: " + printMaxActivities(s, f));
    }

}
