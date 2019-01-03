package interview.stack;

import java.util.Stack;

public class MinStack {

    Stack minStack = new Stack();

    Stack stack = new Stack();

    public void push(int x) {
        int z = 0;
        if (minStack.isEmpty()) {
            minStack.push(x);
        }
        if (!minStack.isEmpty()) {
            z = (int) minStack.peek();
        }

        if (x <= z) {
            minStack.push(x);
        } else {
            minStack.push(z);
        }

        stack.push(x);
    }

    public void getMin() {

        System.out.println(minStack.peek());
    }

    public void pop() {
        int z = (int) minStack.peek();
        int top = (int) stack.peek();

        if (top == z) {
            minStack.pop();
        }

        stack.pop();
    }
}
