package interview.stack;

import java.util.Stack;

public class AllStackQueue {

    private static Stack minStack = new Stack();

    private static Stack originalStack = new Stack();

    public static void main(String[] args) {
        AllStackQueue stack = new AllStackQueue();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.getMin();
        stack.push(70);
        stack.pop();
        stack.push(230);
        stack.push(1);

        stack.getMin();
    }

    public static void push(int x) {
        if (minStack.isEmpty() && originalStack.isEmpty()) {
            minStack.push(x);
        }

        if (!originalStack.isEmpty()) {

            int m = (int) minStack.peek();
            if (x <= m) {
                minStack.push(x);
            } else {
                minStack.push(m);
            }
        }
        originalStack.push(x);
    }

    public static int pop() {
        int popValue = 0;
        if (!originalStack.isEmpty() && !minStack.isEmpty()) {
            popValue = (int) originalStack.peek();
            if (popValue == (int) minStack.peek()) {
                minStack.pop();
            }

            return (int) originalStack.pop();
        }
        return popValue;
    }

    public static int getMin() {
        return (int) minStack.peek();
    }
}
