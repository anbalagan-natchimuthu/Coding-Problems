package interview.Strings;

// Check for numbers as well digits


public class CheckPalindrome {

    public static void main(String[] args) {
        System.out.println(checkIsPalindrome("A man, a plan, a canal: Panama"));

        String input2 = "abc,.@cbaa!!!";
        boolean result = checkPalindrome(input2);
        System.out.println(result);
        String input3 = "A man, a plan, a canal: Panama";
        boolean palRes = checkPalindrome(input3);
        System.out.println(palRes);
    }

    /**
     * check Palindrome with Special characters
     * @param input
     * @return
     */
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

    private static boolean checkPalindrome(String input) {
        char[] charArr = input.toLowerCase().toCharArray();
        int i = 0;
        int j = input.length() - 1;
        while (i <= j) {
            if (!(charArr[i] >= 'a' && charArr[i] <= 'z')) {
                i++;
            } else if (!(charArr[j] >= 'a' && charArr[j] <= 'z')) {
                j--;
            } else if ((charArr[i] >= 'a' && charArr[i] <= 'z') && (charArr[j] >= 'a' && charArr[j] <= 'z')) {
                if (charArr[i] != charArr[j]) {
                    return false;
                }
                i++;
                j--;
            }
        }
        return true;
    }
}
