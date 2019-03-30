package interview.Strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AllString {

  public static void main(String[] args) {

    String input = "abc,def*$g";
    reverseStringWithSplCharsAsIs(input);

    int cout = maxOccuringChar("tesstt");
    System.out.println(cout);

    boolean isPalin = isPalindrome("AnbnA");
    System.out.println("IsPalindrome " + isPalin);

    int arr[] = new int[]{1, 5, 3, 18, 25, 5, 26};
    int minDiff = findMinDifferenceInArray(arr);
    System.out.println("MinDiff " + minDiff);
  }

  public static void reverseStringWithSplCharsAsIs(String input) {

    char[] charArray = input.toCharArray();
    int i = 0, j = input.length() - 1;
    char temp;

    while (i <= j) {
      if ((charArray[i] >= 'a' && charArray[i] <= 'z') && charArray[j] >= 'a' && charArray[j] <= 'z') {

        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        i++;
        j--;
      } else {
        if (charArray[i] < 'a' || charArray[i] > 'z') {
          i++;
        }
        if (!(charArray[j] >= 'a' && charArray[j] <= 'z')) {
          j--;
        }
      }
    }
    //for (int k = 0; k < charArray.length; k++) {
    System.out.println("reverseStringWithSplCharsAsIs:" + String.valueOf(charArray));
    //}
    System.out.println();
  }

  public static int maxOccuringChar(String input) {

    // Approach 1 : Using char[]
    char[] inputArr = input.toCharArray();
    //Approach 2 : Using HashMap
    Map<Character, Integer> hashMap = new HashMap<>();
    int[] resultArr = new int[52];
    int max = 0;
    int maxPosition = 0;

    for (char ch : inputArr) {
      resultArr[ch - 'A']++;
      hashMap.compute(ch, (key, value) -> {
        if (value == null) {
          return 1;
        } else {
          return value + 1;
        }
      });
    }

    for (int i = 0; i < resultArr.length; i++) {
      if (resultArr[i] > max) {
        max = resultArr[i];
        maxPosition = i;
      }
    }
    System.out.println("Character repeated Max times::" + (char) (maxPosition + (int) 'A') + ":: times " + max);

    max = 0;
    char resultChar = '\u0000';
    for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
      if (entry.getValue() > max) {
        resultChar = entry.getKey();
        max = entry.getValue();
      }
    }
    System.out.println("Character repeated Max times Using Hashmap::" + resultChar);
    return resultChar;
  }

  //
  public static boolean isPalindrome(String input) {
    int i = 0;
    int j = input.length() - 1;

    while (i < j) {
      if (input.charAt(i) == input.charAt(j)) {
        i++;
        j--;
      } else {
        return false;
      }
    }
    return true;
  }

  public static int findMinDifferenceInArray(int[] arr) {

    Arrays.sort(arr);
    int minDiff = Integer.MAX_VALUE;
    for (int i = 0; i < arr.length - 1; i++) {
      if ((arr[i + 1] - arr[i]) <= minDiff) {
        minDiff = arr[i + 1] - arr[i];
      }
    }
    return minDiff;
  }
}
