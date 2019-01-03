package interview.Strings;

public class ToDoString {

    //reverse of words in a string

    public static void main(String[] args) {
        //reverseWordsOfString("This is a test string");
        String input = "This is";
        reverseWords(input.toCharArray());

        //reverserWord("Payal");
    }

    private static void reverseWordsOfString(String input) {
        StringBuilder outputStr = new StringBuilder();
        char[] inputArray = input.toCharArray();

        int endIndex = inputArray.length - 1;
        int i = inputArray.length - 1;

        while (i >= 0) {

            if (inputArray[i] == ' ' || i == 0) {
                if (endIndex == inputArray.length - 1) {
                    outputStr.append(input.substring(i)).append(" ");
                } else {
                    outputStr.append(input.substring(i, endIndex)).append(" ");
                }
                endIndex = i;
            }
            i--;
        }

        System.out.println(outputStr.toString());
    }

    private static String reverseWord(String word) {
        char[] wordArray = word.toCharArray();
        int i = 0;
        int j = wordArray.length - 1;
        while (i <= j) {

            char temp = wordArray[i];
            wordArray[i] = wordArray[j];
            wordArray[j] = temp;
            i++;
            j--;
        }

        StringBuilder reverserdWord = new StringBuilder();
        System.out.println("Revered word is ");
        for (int k = 0; k < wordArray.length - 1; k++) {
            System.out.print(wordArray[k]);
            reverserdWord.append(wordArray[k]);
        }

        return reverserdWord.toString();
    }

    public static void reverseWords(char[] s) {
        int j = 0;
        reverse(s, 0, s.length - 1);

        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, j, i - 1);

                j = i + 1;
            }
            if (i == s.length - 1) {
                reverse(s, j, i);

                j = i + 1;
            }
        }

        for (int k = 0; k < s.length; k++) {
            System.out.println(s[k]);
        }
    }

    public static void reverse(char[] s, int i, int j) {
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

    // private static void reverserWord(String word) {
    //     char[] charArr = word.toCharArray();
    //     int j = word.length() - 1;
    //     char temp;
    //     int i = 0;
    //     while (i <= j) {
    //         temp = charArr[i];
    //         charArr[i] = charArr[j];
    //         charArr[j] = temp;
    //         j--;
    //         i++;
    //         System.out.println(charArr[i]);
    //     }
    //
    //     // for (int k = 0; k < charArr.length; k++) {
    //     //     System.out.println(charArr[k]);
    //     // }
    // }
}

// This is
// si siht
// is this

// Rotate Array Logic
// Get the kth postion/
// reverse first to kth
//reverse kth to end
// reverse entire array