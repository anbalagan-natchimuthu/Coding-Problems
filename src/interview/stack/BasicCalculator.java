package interview.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/discuss/62361/Iterative-Java-solution-with-stack
 *
 * https://leetcode.com/problems/basic-calculator-iii/
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative
 * integers and empty spaces .
 *
 * Example 1:
 *
 * Input: "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 */
public class BasicCalculator {

  public static int calculate(String s) {
    if (s == null) {
      return 0;
    }
    Queue<Character> q = new LinkedList<>();
    for (int i = 0; i < s.length(); i++) {
      q.offer(s.charAt(i));
    }
    q.offer('+');
    return cal(q);
  }

  private static int cal(Queue<Character> q) {
    char sign = '+';
    int num = 0;
    Stack<Integer> stack = new Stack<>();
    while (!q.isEmpty()) {
      char c = q.poll();
      if (c == ' ') {
        continue;
      }
      if (Character.isDigit(c)) {
        num = 10 * num + c - '0';
      } else if (c == '(') {
        num = cal(q);
      } else {
        if (sign == '+') {
          stack.push(num);
        } else if (sign == '-') {
          stack.push(-num);
        } else if (sign == '*') {
          stack.push(stack.pop() * num);
        } else if (sign == '/') {
          stack.push(stack.pop() / num);
        }
        num = 0;
        sign = c;
        if (c == ')') {
          break;
        }
      }
    }
    int sum = 0;
    while (!stack.isEmpty()) {
      sum += stack.pop();
    }
    return sum;
  }

  public static void main(String[] args) {
    System.out.println(calculate("-3+(1+(4+5+2)-3)+( 6 + 8)"));
    System.out.println(calculate("(2+6* 3+5- (3*14/7+2)*5)+3"));
    System.out.println(calculate("1 + 1"));
    System.out.println(calculate(" 6-4 / 2 "));
  }
}
