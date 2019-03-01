package interview.Array;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LowestMaxVisitsNumberInArray {

  static class MaxMin {

    static int max;

    static int min;
  }

  static class StartEnd {

    int start;

    int end;

    StartEnd(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  // Get Lowest starting and Highest ending from the given input
  private static void getMaxMinInArray(List<Integer> inputList) {
    MaxMin.max = inputList.stream().mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new);
    MaxMin.min = inputList.stream().mapToInt(v -> v).min().orElseThrow(NoSuchElementException::new);
  }

  // Construct the Class with start and End point from the list of input
  private static List<StartEnd> constructInput(List<Integer> inputList) {
    List<StartEnd> outputList = new ArrayList<>(inputList.size());
    for (int i = 0; i < inputList.size() - 1; i++) {
      int start = inputList.get(i);
      int end = inputList.get(i + 1);
      StartEnd startEnd = new StartEnd(start, end);
      outputList.add(startEnd);
    }
    return outputList;
  }

  // populate only starting and ending array values
  private static int[] populateArray(List<StartEnd> inputList) {
    int[] outputArray = new int[MaxMin.max - MaxMin.min + 2];
    for (StartEnd startEnd : inputList) {

      // If start point less than end point, set start point array value as 1 and subtract 1 from after end point
      if (startEnd.start < startEnd.end) {
        outputArray[startEnd.start - MaxMin.min] += 1;
        outputArray[startEnd.end - MaxMin.min + 1] -= 1;
      } else {
        outputArray[startEnd.end - MaxMin.min] += 1;
        outputArray[startEnd.start - MaxMin.min + 1] -= 1;
      }
    }
    return outputArray;
  }

  private static int getLowestMaxVistis(int[] arrayVals) {
    int lowestIndex = -1;
    int maxVisits = -1;
    int runningSum = 0;

    // Keep sum the array values
    for (int i = 0; i < arrayVals.length; i++) {
      runningSum += arrayVals[i];
      if (runningSum > maxVisits) {
        maxVisits = runningSum;
        lowestIndex = i;
      }
    }
    return lowestIndex + MaxMin.min;
  }

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(5);
    list.add(4);
    list.add(3);
    list.add(6);
    list.add(2);
    long smillis = System.currentTimeMillis();

    getMaxMinInArray(list);
    List<StartEnd> modifiedList = constructInput(list);
    int[] visitedArrays = populateArray(modifiedList);
    int lowestMaxVisit = getLowestMaxVistis(visitedArrays);

    long emillis = System.currentTimeMillis();
    System.out.println("Time taken---" + (emillis - smillis));
    System.out.println("Here is the highest---" + lowestMaxVisit);
  }
}
