package interview.Strings;

import java.util.Stack;

public class ReversePolishNotation {

    public static void main(String[] args) {

        String[] inputArray = { "2", "1", "+", "3", "*" };
        reversePolishComputation(inputArray);
    }

    private static void reversePolishComputation(String[] inputArray) {

        Stack stack = new Stack();

        for (int i = 0; i < inputArray.length; i++) {

            if (inputArray[i] == "/" || inputArray[i] == "+" || inputArray[i] == "*" || inputArray[i] == "-") {

                int a = Integer.parseInt((String) stack.pop());
                int b = Integer.parseInt((String) stack.pop());
                int c = Integer.MAX_VALUE;
                switch (inputArray[i]) {
                    case "/":
                        c = b / a;
                        break;
                    case "*":
                        c = b * a;
                        break;
                    case "+":
                        c = b + a;
                        break;
                    case "-":
                        c = b - a;
                        break;
                    default:

                        break;
                }

                stack.push(String.valueOf(c));
            } else {

                stack.push(inputArray[i]);
            }
        }
        System.out.println(stack.pop());
    }
}

// ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
//["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9