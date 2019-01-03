package interview.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Permutation {

    public static void main(String[] args) {

        /* https://www.youtube.com/watch?v=nYFd7VHKyWQ&t=3s */
        Permutation permutation = new Permutation();
        permutation.permute("abcd".toCharArray()).forEach(s -> System.out.println(s));

        System.out.println("-------------------------------------------");
        String input = "abcd";
        Set<String> permList = permutationFinder(input);
        System.out.println(permList.size());
        for (String each : permList) {
            System.out.println(each);
        }
        System.out.println("*******************************************");
        input = "abc";
        permute(input, 0, input.length() -1);

        System.out.println(checkAnagram("aab!@#cd", "@#bc!daa"));
        System.out.println(permutation("#!@aabcd", "bcd#@aa!"));
        System.out.println(permutationUsingHashMap("aab$%^cd", "b$cd^a%a"));
        System.out.println(permutation(null, ""));
        System.out.println(permutation("", ""));
        System.out.println(permutation(null, null));
        System.out.println("permutationFinder::" + permutationFinder("anb"));
    }

    public List<String> permute(char input[]) {
        Map<Character, Integer> countMap = new TreeMap<>();
        for (char ch : input) {
            countMap.compute(ch, (key, val) -> {
                if (val == null) {
                    return 1;
                } else {
                    return val + 1;
                }
            });
        }
        List<String> resultList = new ArrayList<>();
        char result[] = new char[input.length];
        permuteUtil(countMap, result, 0, resultList);
        return resultList;
    }

    public void permuteUtil(Map<Character, Integer> countMap, char result[], int level, List<String> resultList) {
        if (level == result.length) {
            resultList.add(new String(result));
            return;
        }

        for(Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            int value = entry.getValue();
            if(value == 0) {
                continue;
            }
            result[level] = entry.getKey();
            countMap.put(entry.getKey(), --value);
            permuteUtil(countMap, result, level + 1, resultList);
            countMap.put(entry.getKey(), ++value);
        }
    }

    public static Set<String> permutationFinder(String str) {
        Set<String> perm = new HashSet<>();
        //Handling error scenarios
        if (str == null) {
            return null;
        } else if (str.length() == 0) {
            perm.add("");
            return perm;
        }
        char initial = str.charAt(0); // first character
        String rem = str.substring(1); // Full string without first character
        Set<String> words = permutationFinder(rem);
        for (String strNew : words) {
            for (int i = 0;i<=strNew.length();i++){
                perm.add(charInsert(strNew, initial, i));
            }
        }
        return perm;
    }

    public static String charInsert(String str, char c, int j) {
        String begin = str.substring(0, j);
        String end = str.substring(j);
        return begin + c + end;
    }



    /**
     * permutation function
     * @param str string to calculate permutation for
     * @param start starting index
     * @param end end index
     */
    private static void permute(String str, int start, int end) {
        if (start == end) {
            System.out.println(str);
        } else {
            for (int i = start; i <= end; i++) {
                str = swap(str, start, i);
                permute(str, start + 1, end);
                str = swap(str, start, i);
            }
        }
    }

    public static boolean checkAnagram(String str1, String str2) {

        if (str1.length() != str2.length())
            return false;

        char[] a = str1.toCharArray();
        char[] b = str2.toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);

        return Arrays.equals(a, b);
    }

    public static boolean permutation(String s, String t) {
        if (s == null && t == null) {
            return  true;
        }

        if ((s == null && t != null) || (s != null && t == null) || (s.length() != t.length())) {
            return false;
        }

        int[] arr = new int[256]; // assume character is ASCII
        for (int i=0; i< s.length(); i++) {
            char value = s.charAt(i);
            arr[value] ++;
        }

        for (int i=0; i< t.length(); i++) {
            int value = t.charAt(i);
            if (--arr[value] < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean permutationUsingHashMap(String s1, String s2) {
        if (s2.length() != s1.length()) {
            return false;
        }

        HashMap<Character, Integer> hashMapValue = new HashMap<>();
        Integer value = -1;
        for (char ch: s1.toCharArray()) {
            value = hashMapValue.get(ch);
            if (value == null) {
                hashMapValue.put(ch, 1);
            } else {
                hashMapValue.put(ch, ++value);
            }
        }

        for (char ch: s2.toCharArray()) {
            value = hashMapValue.get(ch);
            if (value == null || value == 0) {
                return false;
            } else {
                hashMapValue.put(ch, --value);
            }
        }

        return true;
    }

    /**
     * Swap Characters at position
     * @param a string value
     * @param i position 1
     * @param j position 2
     * @return swapped string
     */
    public static String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}
