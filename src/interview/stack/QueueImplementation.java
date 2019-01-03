package interview.stack;

public class QueueImplementation {

    Node head;

    Node tail;

    public static void main(String[] args) {

    }

    private void add(Node newNode) {
        if (head == null || tail == null) {

        }
        tail.next = newNode;
        newNode = tail;
    }

    private Node remove() {

        Node temp = new Node(head.data);
        head = head.next;
        return temp;
    }
}
