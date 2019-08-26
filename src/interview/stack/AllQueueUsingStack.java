package interview.stack;

import java.util.Stack;

public class AllQueueUsingStack<E> {

    private Stack<E> stack = new Stack<>();

    private Stack<E> stack1 = new Stack<>();

    public static void main(String[] args) {
        AllQueueUsingStack<Integer> queueUsingStacks = new AllQueueUsingStack<>();

        queueUsingStacks.add(1);
        queueUsingStacks.add(2);
        queueUsingStacks.add(3);
        System.out.println(queueUsingStacks.remove());
        queueUsingStacks.add(4);
        queueUsingStacks.add(5);
        System.out.println(queueUsingStacks.remove());
        System.out.println(queueUsingStacks.remove());
        System.out.println(queueUsingStacks.remove());
        System.out.println(queueUsingStacks.remove());
        System.out.println(queueUsingStacks.remove());
    }

    public void add(E x) {

        stack.push(x);
    }

    public E remove() {
        while (!stack.isEmpty()) {
            stack1.push(stack.pop());
        }
        //System.out.println(stack1.peek());
        E value = null;
        if (!stack1.isEmpty()) {
            value = stack1.pop();
        }
        while (!stack1.isEmpty()) {
            stack.push(stack1.pop());
        }
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
