package interview.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PROBLEM: 1
 * https://leetcode.com/problems/subsets/
 *
 * https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-
 * (Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
 *
 * Given a set of distinct String array values, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output: 2 ^ n subsets
 * [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 */
public class SubSets_Permutations {

  public static List<List<String>> subsets(String[] nums) {
    List<List<String>> resultList = new ArrayList<>();
    backtrack(resultList, new ArrayList<>(), nums, 0);
    return resultList;
  }

  private static void backtrack(List<List<String>> resultList, List<String> tempList, String[] nums, int start) {
    // It will Add Empty Array first. If you don't want, move this one into inside for loop
    resultList.add(new ArrayList<>(tempList));
    for (int i = start; i < nums.length; i++) {
      tempList.add(nums[i]);
      backtrack(resultList, tempList, nums, i + 1);
      tempList.remove(tempList.size() - 1);
    }
  }

  /**
   * PROBLEM: 2
   * https://leetcode.com/problems/subsets-ii/
   *
   * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
   * Note: The solution set must not contain duplicate subsets.
   *
   * Example:
   *
   * Input: [1,2,2]
   * Output:
   * [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
   *
   * Take the product of all the (frequencies + 1).
   * For example, in {A,B,B}, the answer is (1+1) [the number of As] * (2+1) [the number of Bs] = 6.
   * in {A, A, B, B}, count(A) = 2 and count(B) = 2. Thus the answer is (2+1) * (2+1) = 9.
   */

  public static List<String[]> findUniqueSubSets(String[] inputArr) {

    List<String[]> resultList = new ArrayList<>();
    Arrays.sort(inputArr);
    getUniqueCombinations(resultList, inputArr, new ArrayList<>(), 0);
    return resultList;
  }

  public static void getUniqueCombinations(List<String[]> resultList, String[] inputArr, List<String> tempList,
      int start) {
    resultList.add(tempList.stream().toArray(String[]::new));
    for (int i = start; i < inputArr.length; i++) {
      // skip duplicates
      if (i > start && inputArr[i].equals(inputArr[i - 1])) {
        continue;
      }
      tempList.add(inputArr[i]);
      getUniqueCombinations(resultList, inputArr, tempList, i + 1);
      tempList.remove(tempList.size() - 1);
    }
  }

  /**
   * PROBLEM: 3
   * https://leetcode.com/problems/permutations/
   *
   * Given a collection of distinct integers, return all possible permutations.
   *
   * Example:
   *
   * Input: [1,2,3]
   * Output: n! = 1 * 2 * 3 = 6
   * [
   * [1,2,3],
   * [1,3,2],
   * [2,1,3],
   * [2,3,1],
   * [3,1,2],
   * [3,2,1]
   * ]
   */
  public static List<List<Integer>> permutations(int[] inputArray) {
    List<List<Integer>> resultList = new ArrayList<>();
    printPermutations(resultList, inputArray, new ArrayList<>());
    return resultList;
  }

  public static void printPermutations(List<List<Integer>> resultList, int[] inputArray, List<Integer> tempList) {
    if (tempList.size() == inputArray.length) {
      resultList.add(new ArrayList<>(tempList));
    } else {

      for (int i = 0; i < inputArray.length; i++) {
        if (tempList.contains(inputArray[i])) {
          continue; // element already exists, skip
        }
        tempList.add(inputArray[i]);
        printPermutations(resultList, inputArray, tempList);
        tempList.remove(tempList.size() - 1);
      }
    }
  }

  /**
   * PROBLEM: 4
   * https://leetcode.com/problems/permutations-ii/
   *
   * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
   *
   * Example:
   *
   * Input: [1,1,2]
   * Output: n! / frequencies! ^ no.of times
   * e.g. {1, 1, 2, 2, 3, 3, 3} = 7! /  2! (1 and 2 occurs twice)) ^ 2 ( 1 and 2) * 3! (3 occurs 3 times) ^ 1
   * [
   * [1,1,2],
   * [1,2,1],
   * [2,1,1]
   * ]
   */
  public static List<List<Integer>> uniquePermutations(int[] inputArray) {
    List<List<Integer>> resultList = new ArrayList<>();
    Arrays.sort(inputArray);
    printUniquePermutations(resultList, inputArray, new ArrayList<>(), new boolean[inputArray.length]);
    return resultList;
  }

  public static void printUniquePermutations(List<List<Integer>> resultList, int[] inputArray, List<Integer> tempList,
      boolean[] used) {
    if (tempList.size() == inputArray.length) {
      resultList.add(new ArrayList<>(tempList));
    } else {
      for (int i = 0; i < inputArray.length; i++) {
        if (used[i] || i > 0 && inputArray[i] == inputArray[i - 1] && !used[i - 1]) {
          continue;
        }
        used[i] = true;
        tempList.add(inputArray[i]);
        printUniquePermutations(resultList, inputArray, tempList, used);
        used[i] = false;
        tempList.remove(tempList.size() - 1);
      }
    }
  }

  /**
   * PROBLEM: 5
   * https://leetcode.com/problems/combination-sum/
   *
   * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique
   * combinations in candidates where the candidate numbers sums to target.
   *
   * The same repeated number may be chosen from candidates unlimited number of times.
   *
   * Example 2:
   *
   * Input: candidates = [2,3,5], target = 8,
   * A solution set is:
   * [
   * [2,2,2,2],
   * [2,3,3],
   * [3,5]
   * ]
   */
  public static List<List<Integer>> combinationSum(int[] nums, int target) {
    List<List<Integer>> list = new ArrayList<>();
    //Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, target, 0);
    return list;
  }

  private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
    if (remain < 0) {
      return;
    } else if (remain == 0) {
      list.add(new ArrayList<>(tempList));
    } else {
      for (int i = start; i < nums.length; i++) {
        tempList.add(nums[i]);
        backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
        tempList.remove(tempList.size() - 1);
      }
    }
  }

  /**
   * PROBLEM: 6
   * https://leetcode.com/problems/combination-sum-ii/
   *
   * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations
   * in candidates where the candidate numbers sums to target.
   *
   * Each number in candidates may only be used once in the combination.
   *
   * Example 2:
   *
   * Input: candidates = [2,5,2,1,2], target = 5,
   * A solution set is:
   * [
   * [1,2,2],
   * [5]
   * ]
   */
  public static List<List<Integer>> uniqueCombinationSum(int[] nums, int target) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    getUniqueCombinationSum(list, new ArrayList<>(), nums, target, 0);
    return list;
  }

  private static void getUniqueCombinationSum(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain,
      int start) {
    if (remain < 0) {
      return;
    } else if (remain == 0) {
      list.add(new ArrayList<>(tempList));
    } else {
      for (int i = start; i < nums.length; i++) {
        if (i > start && nums[i] == nums[i - 1]) {
          continue; // skip duplicates
        }
        tempList.add(nums[i]);
        getUniqueCombinationSum(list, tempList, nums, remain - nums[i], i + 1);
        tempList.remove(tempList.size() - 1);
      }
    }
  }

  /**
   * PROBLEM: 7
   * https://leetcode.com/problems/palindrome-partitioning/
   *
   * Given a string s, partition s such that every substring of the partition is a palindrome.
   * Return all possible palindrome partitioning of s.
   *
   * Example:
   *
   * Input: "aab"
   * Output:
   * [
   * ["aa","b"],
   * ["a","a","b"]
   * ]
   */
  public static List<List<String>> getPalindromePartition(String inputStr) {
    List<List<String>> resultList = new ArrayList<>();
    findPalindromePartition(inputStr, resultList, new ArrayList<>(), 0);
    return resultList;
  }

  private static void findPalindromePartition(String inputStr, List<List<String>> resultList,
      ArrayList<String> tempList, int start) {
    if (inputStr.length() == start) {
      resultList.add(new ArrayList<>(tempList));
    } else {
      for (int i = start; i < inputStr.length(); i++) {
        if (isPalindrome(inputStr, start, i)) {
          tempList.add(inputStr.substring(start, i + 1));
          findPalindromePartition(inputStr, resultList, tempList, i + 1);
          tempList.remove(tempList.size() - 1);
        }
      }
    }
  }

  private static boolean isPalindrome(String str, int low, int high) {
    while (low < high) {
      if (str.charAt(low++) != str.charAt(high--)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {

    System.out.println("****************** Sub Sets **************************");
    String[] input = {"Carrots", "Lettuce", "Tomato"};

    List<List<String>> resultList = subsets(input);

    for (List<String> res : resultList) {
      System.out.println(res);
    }

    System.out.println("******************* Unique Sub Sets *************************");

    String[] inputArr = {"Carrots", "Lettuce", "Tomato", "Carrots"};
    List<String[]> resultArr = findUniqueSubSets(inputArr);

    for (int i = 0; i < resultArr.size(); i++) {
      System.out.println(Arrays.toString(resultArr.get(i)));
    }

    System.out.println("***************** Permutations **************************");

    int[] intArray = {1, 2, 3};
    List<List<Integer>> resArr = permutations(intArray);

    for (List<Integer> res : resArr) {
      System.out.println(res.toString());
    }

    System.out.println("************** Unique Permutations ***********************");

    int[] dupArray = {1, 1, 2};
    List<List<Integer>> dupArrayList = uniquePermutations(dupArray);

    for (List<Integer> res : dupArrayList) {
      System.out.println(res.toString());
    }

    System.out.println("***************** Combinations Sum **********************");

    int[] combSumArray = {2, 3, 6, 7};
    List<List<Integer>> combSumList = combinationSum(combSumArray, 7);

    for (List<Integer> sum : combSumList) {
      System.out.println(sum.toString());
    }

    System.out.println("***************** Unique Combinations Sum ****************");

    int[] uniqueCombSum = {2, 5, 2, 1};
    List<List<Integer>> uniqueCombSumList = uniqueCombinationSum(uniqueCombSum, 5);

    for (List<Integer> sum : uniqueCombSumList) {
      System.out.println(sum.toString());
    }

    System.out.println("***************** Palindrome Partition ******************");

    String inputStr = "aab";
    List<List<String>> partList = getPalindromePartition(inputStr);

    for (List<String> list : partList) {
      System.out.println(list.toString());
    }
  }
}
