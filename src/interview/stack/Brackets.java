package interview.stack;

import java.util.Stack;

public class Brackets {

    public static void main(String[] args) {

        boolean result = isBracketMatching("()))((()");
        System.out.println(result);
    }

    private static boolean isBracketMatching(String input) {
        Stack leftBraceStack = new Stack();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                leftBraceStack.push(input.charAt(i));
            } else if (input.charAt(i) == ')') {
                if (!leftBraceStack.isEmpty()) {
                    leftBraceStack.pop();
                } else {
                    return false;
                }
            }
        }

        if (leftBraceStack.isEmpty()) {
            return true;
        }

        return false;
    }
}
