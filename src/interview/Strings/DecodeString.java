package interview.Strings;

import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/decode-string-recursively-encoded-count-followed-substring/
 * An encoded string (s) is given, the task is to decode it.
 *
 * Examples:
 *
 * Input : str[] = "1[b]"
 * Output : b
 *
 * Input : str[] = "2[ab]"
 * Output : abab
 *
 * Input : str[] = "2[a2[b]]"
 * Output : abbabb
 *
 * Input : str[] = "3[b2[ca]]"
 * Output : bcacabcacabcaca
 */
public class DecodeString {

    public static void main(String[] args) {
        System.out.println(decode("3[b2[ca]]"));
        System.out.println(decode("2[a2[b]]"));
    }

    private static String decode(String encodedStr) {

        String resultStr = "";
        String tempStr = "";
        Stack<Integer> integerStack = new Stack<>();
        Stack<Character> stringStack = new Stack<>();

        for (int i = 0; i < encodedStr.length(); i++) {

            int count = 0;
            while (Character.isDigit(encodedStr.charAt(i))) {
                count = count * 10 + (encodedStr.charAt(i) - '0');
                i++;
            }

            if (encodedStr.charAt(i) == '[') {
                if (count > 0) {
                    integerStack.push(count);
                } else {
                    integerStack.push(1);
                }
                stringStack.push(encodedStr.charAt(i));
            } else if (encodedStr.charAt(i) == ']') {
                resultStr = "";
                tempStr = "";
                while (!stringStack.isEmpty() && stringStack.peek() != '[') {
                    tempStr = stringStack.pop() + tempStr;
                }

                count = integerStack.pop();
                for (int j = 0; j < count; j++) {
                    resultStr += tempStr;
                }

                if (!stringStack.isEmpty()) {
                    // pop '[' character
                    stringStack.pop();
                }

                for (int k = 0; k < resultStr.length(); k++) {
                    stringStack.push(resultStr.charAt(k));
                }
            } else {
                stringStack.push(encodedStr.charAt(i));
            }
        }

        resultStr = "";
        while (!stringStack.isEmpty()) {
            resultStr = stringStack.pop() + resultStr;
        }

        return resultStr;
    }
}
