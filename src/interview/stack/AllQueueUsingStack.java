package interview.stack;

import java.util.Stack;

public class AllQueueUsingStack {

    private static Stack stack = new Stack();

    private static Stack stack1 = new Stack();

    public static void main(String[] args) {
        AllQueueUsingStack queueUsingStacks = new AllQueueUsingStack();

        queueUsingStacks.add(1);
        queueUsingStacks.add(2);
        queueUsingStacks.add(3);
        queueUsingStacks.remove();
        queueUsingStacks.add(4);
        queueUsingStacks.add(5);
        queueUsingStacks.remove();
    }

    public static void add(int x) {

        stack.push(x);
    }

    public static int remove() {
        while (!stack.isEmpty()) {
            stack1.push(stack.pop());
        }
        System.out.println(stack1.peek());
        int value = (int) stack1.pop();
        // while (!stack1.isEmpty()) {
        //     stack.push(stack1.pop());
        // }
        return value;
    }
}

//3,2
// Queue FIFO
// Stack LIFO

//topological
//mediam of stream
//dfs
//bfs
//lru
//permutation
//powerset
//merge intervals
