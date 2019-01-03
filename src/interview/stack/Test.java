package interview.stack;

public class Test {

    public static void main(String[] args) {
        // QueueUsingStacks queueUsingStacks = new QueueUsingStacks();
        //
        // queueUsingStacks.add(1);
        // queueUsingStacks.add(2);
        // queueUsingStacks.add(3);
        // queueUsingStacks.remove();
        // queueUsingStacks.add(4);
        // queueUsingStacks.add(5);
        // queueUsingStacks.remove();

        // MinStack stack = new MinStack();
        //
        // stack.push(10);
        // stack.push(20);
        // stack.push(30);
        // stack.push(40);
        // stack.push(50);
        // stack.getMin();
        // stack.push(70);
        // stack.pop();
        // stack.push(230);
        // stack.push(1);

        // stack.getMin();

        // StackUsingQueues stackUsingQueues = new StackUsingQueues();
        // stackUsingQueues.push(10);
        // stackUsingQueues.push(120);
        // stackUsingQueues.pop();
        // stackUsingQueues.push(30);
        // stackUsingQueues.push(50);
        // stackUsingQueues.push(80);
        // stackUsingQueues.pop();
        // stackUsingQueues.push(10);
        // stackUsingQueues.push(10);

        MiddleElementStack middleElementStack = new MiddleElementStack();
        middleElementStack.push(1);
        middleElementStack.push(2);
        // middleElementStack.push(3);
        middleElementStack.push(4);
        middleElementStack.push(5);
        middleElementStack.push(6);
        middleElementStack.getMiddle();
        middleElementStack.deleteMiddleElement();
        middleElementStack.getMiddle();
        middleElementStack.push(7);
        middleElementStack.push(8);
        middleElementStack.push(9);
        middleElementStack.getMiddle();
    }
}

