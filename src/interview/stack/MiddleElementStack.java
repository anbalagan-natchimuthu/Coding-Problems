package interview.stack;

import java.util.Stack;

public class MiddleElementStack {

    Stack stack = new Stack();

    Stack midstack = new Stack();

    int count = 0;

    int mid = 0;

    public void push(int x) {
        count++;
        stack.push(x);
    }

    public void pop() {
        count--;
        stack.pop();
    }

    public void getMiddle() {
        mid = count / 2;
        int counter = 0;
        while (counter < mid) {
            midstack.push(stack.pop());
            counter++;
        }

        System.out.println(stack.peek());

        while (!midstack.isEmpty()) {
            stack.push(midstack.pop());
            counter--;
        }
    }

    public void deleteMiddleElement() {
        mid = count / 2;
        int counter = 0;
        while (counter < mid) {
            midstack.push(stack.pop());
            counter++;
        }

        if (counter == mid) {
            System.out.println("Deleted element " + stack.peek());
            stack.pop();
            count--;
            counter--;
        }

        while (counter > 0) {
            stack.push(midstack.pop());
            counter--;
        }
    }
}


