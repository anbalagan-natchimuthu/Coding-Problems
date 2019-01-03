package interview.Strings;

public class PatternMatching {

    public static void main(String[] args) {

    }

    //
    //To understand this solution, you can use s="aab" and p="*ab".
    // s = aab p = ?ab
    //
   // if ? pick single char from s
    // if letters then char in s should be same
    // if *
    // if begin then look for string encounter till ? or end of length
    private static void patternMatching(String input, String pattern) {

        char[] inputCharArray = input.toCharArray();
        char[] patternCharArray = pattern.toCharArray();
        for(int i= 0; i < inputCharArray.length ; i++){

        }
    }
}
