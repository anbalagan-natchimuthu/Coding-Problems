package interview.LinkedList;

public class ElementsSwap {

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
        swapNodes(n1, 1, 3);
        printList(n1);
    }

    public static void swapNodes(Node head, int x, int y) {
        // Nothing to do if x and y are same
        if (x == y) {
            return;
        }

        // Search for x (keep track of prevX and CurrX)
        Node prevX = null, currX = head;
        while (currX != null && currX.data != x) {
            prevX = currX;
            currX = currX.next;
        }

        // Search for y (keep track of prevY and currY)
        Node prevY = null, currY = head;
        while (currY != null && currY.data != y) {
            prevY = currY;
            currY = currY.next;
        }

        // If either x or y is not present, nothing to do
        if (currX == null || currY == null) {
            return;
        }

        // If x is not head of linked list
        if (prevX != null) {
            prevX.next = currY;
        } else //make y the new head
        {
            head = currY;
        }

        // If y is not head of linked list
        if (prevY != null) {
            prevY.next = currX;
        } else // make x the new head
        {
            head = currX;
        }

        // Swap next pointers
        Node temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;
    }

    public static void printList(Node head) {
        Node tNode = head;
        while (tNode != null) {
            System.out.print(tNode.data + " ");
            tNode = tNode.next;
        }
    }
}


// 1,2,3,4,5,6
//
//
// prevX= null
// currX= 1;
// prevY = 2
// currY = 3
//
// head = 3
// temp = 2;
// 2 >4
// 4 >2
//
// 3,