package interview.stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueues {

    Queue queue1 = new LinkedList<>();

    Queue queue2 = new LinkedList<>();

    public void push(int x) {

        queue1.add(x);
    }

    public void pop() {

        while (queue1.size() != 1) {
            queue2.add(queue1.remove());
        }

        System.out.println(queue1.remove());
    }
}
