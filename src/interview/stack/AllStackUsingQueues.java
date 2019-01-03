package interview.stack;

import java.util.LinkedList;
import java.util.Queue;

public class AllStackUsingQueues {

    private static Queue queue1 = new LinkedList();

    private static Queue queue2 = new LinkedList();

    public static void main(String[] args) {
        AllStackUsingQueues stackUsingQueues = new AllStackUsingQueues();
        stackUsingQueues.push(10);
        stackUsingQueues.push(120);
        stackUsingQueues.pop();
        stackUsingQueues.push(30);
        stackUsingQueues.push(50);
        stackUsingQueues.push(80);
        stackUsingQueues.pop();
        stackUsingQueues.push(10);
        stackUsingQueues.push(10);
    }

    public static void push(int x) {
        queue1.add(x);
    }

    public static int pop() {
        while (queue1.size() != 1) {
            queue2.add(queue1.remove());
        }

        System.out.println(queue1.peek());
        return (int) queue1.remove();
    }
}


