package interview.Strings;

// Check for numbers as well digits


public class CheckPalindromeWithSplChars {

    public static void main(String[] args) {
        System.out.println(checkIsPalindrome("A man, a plan, a canal: Panama"));
    }

    private static boolean checkIsPalindrome(String input) {
        char[] inputArr = input.toLowerCase().toCharArray();
        int j = input.length() - 1;
        int i = 0;
        while (i <= j) {
            if (!(inputArr[i] >= 'a' && inputArr[i] <= 'z')) {
                i++;
            }
            if (!(inputArr[j] >= 'a' && inputArr[j] <= 'z')) {
                j--;
            }

            if ((inputArr[i] >= 'a' && inputArr[i] <= 'z') && (inputArr[j] >= 'a' && inputArr[j] <= 'z')) {
                if (inputArr[i] == inputArr[j]) {
                    j--;
                    ++i;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
