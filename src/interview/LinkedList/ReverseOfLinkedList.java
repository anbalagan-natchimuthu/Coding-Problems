package interview.LinkedList;

import java.util.Stack;

public class ReverseOfLinkedList {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        reverseOfLinkedList(n1);
        Node rev = reverse(n1);
    }

    private static void reverseOfLinkedList(Node node) {

        if (node == null) {
            return;
        }

        // While traversing push in stack in first pass
        // Next pass keep popping from stack until stack is empty

        if (node.next == null) {
            System.out.println("Single element in list");
            System.out.println(node.data);
            return;
        }
        Stack stack = new Stack();
        while (node != null) {
            stack.push(node.data);
            node = node.next;
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    private static Node reverse(Node head) {
        Node prev = null;
        Node curr = head;
        Node next = head.next;

        while (next != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next.next;
        }

        return prev;
    }

    // private static Node reversePair(Node head){
    //     Node prev = null;
    //     Node curr = head;
    //     Node next = head.next;
    //     boolean rev = true;
    //     while(rev){
    //         curr.next = prev;
    //         prev = next;
    //         rev = false;
    //     }
    // }
}

// 1>2>3>4
// null < 1 < 2
//prev > curr > next
//

//prev = curr.next;


