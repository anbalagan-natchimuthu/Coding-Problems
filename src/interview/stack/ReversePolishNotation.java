package interview.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * Note:
 *
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate to a result and there
 * won't be any divide by zero operation.
 * Example 1:
 *
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 *
 * Input: ["4", "13", "5", "/", "+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 *
 * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * Output: 22
 * Explanation:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class ReversePolishNotation {

  public static void main(String[] args) {
    ReversePolishNotation rpn = new ReversePolishNotation();
    System.out.println(rpn.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
    System.out.println(rpn.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    System.out.println(rpn.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
  }

  public int evalRPN(String[] tokens) {
    Stack<Integer> intStack = new Stack<>();

    for (String str : tokens) {
      if (str.equals("+")) {
        intStack.add(intStack.pop() + intStack.pop());
      } else if (str.equals("-")) {
        int b = intStack.pop();
        int a = intStack.pop();
        intStack.add(a - b);
      } else if (str.equals("*")) {
        intStack.add(intStack.pop() * intStack.pop());
      } else if (str.equals("/")) {
        int b = intStack.pop();
        int a = intStack.pop();
        intStack.add(a / b);
      } else {
        intStack.add(Integer.parseInt(str));
      }
    }
    return intStack.pop();
  }
}
