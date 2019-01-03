package interview.stack;

import java.util.Stack;

public class QueueUsingStacks {

    Stack stack1 = new Stack();

    Stack stack2 = new Stack();

    public void add(int x) {

        stack1.push(x);
    }

    public void remove() {

        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        System.out.println(stack2.pop());
    }

    // Implement queues using stack
    // GetMin() using stack
    //Implement two stacks in an array
    //Implement Stack using Queues
    // Design a stack with operations on middle element
}
