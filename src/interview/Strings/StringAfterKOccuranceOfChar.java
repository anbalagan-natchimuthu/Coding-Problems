package interview.Strings;

public class StringAfterKOccuranceOfChar {

    public static void main(String[] args) {
        stringAftrKOccuranceOfChar("This is demo string", 3, 'i');
        stringAftrKOccuranceOfChar("geeks for geeks", 2, 'e');
    }

    private static void stringAftrKOccuranceOfChar(String input, int charOccur, char inputChar) {

        // Input  :  str = "This is demo string"
        // char = i,
        //     count = 3
        // Output :  ng

        // loop through string if matches inputChar then increment count by1 till u reach charOccur
        // once done print rest of the string of the input
        int countCharOccurance = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == inputChar) {
                countCharOccurance++;
                if (countCharOccurance == charOccur) {
                    System.out.println(input.substring(i + 1, input.length()));
                }
            }
        }
    }
}
