package interview.LinkedList;

/**
 * https://www.geeksforgeeks.org/swap-nodes-in-a-linked-list-without-swapping-data/
 *
 * Given a linked list and two keys in it, swap nodes for two given keys. Nodes should be swapped by changing links.
 * Swapping data of nodes may be expensive in many situations when data contains many fields.
 * It may be assumed that all keys in linked list are distinct.
 *
 * Examples:
 *
 * Input:  10->15->12->13->20->14,  x = 12, y = 20
 * Output: 10->15->20->13->12->14
 *
 * Input:  10->15->12->13->20->14,  x = 10, y = 20
 * Output: 20->15->12->13->10->14
 *
 * Input:  10->15->12->13->20->14,  x = 12, y = 13
 * Output: 10->15->13->12->20->14
 */
public class SwapElementsOfLinkedList {

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

        Node returnHead = swapNodes(n1, 2, 5);

        while (returnHead != null) {
            System.out.print(returnHead.data + "->");
            returnHead = returnHead.next;
        }


    }

    private static Node swapNodes(Node n1, int x, int y) {

        Node head = n1;

        // Nothing to do if x and y are same
        if (x == y) return null;

        // Search for x (keep track of prevX and CurrX)
        Node prevX = null, currX = head;
        while (currX != null && currX.data != x)
        {
            prevX = currX;
            currX = currX.next;
        }

        // Search for y (keep track of prevY and currY)
        Node prevY = null, currY = head;
        while (currY != null && currY.data != y)
        {
            prevY = currY;
            currY = currY.next;
        }

        // If either x or y is not present, nothing to do
        if (currX == null || currY == null)
            return null;

        // If x is not head of linked list
        if (prevX != null)
            prevX.next = currY;
        else //make y the new head
            head = currY;

        // If y is not head of linked list
        if (prevY != null)
            prevY.next = currX;
        else // make x the new head
            head = currX;

        // Swap next pointers
        Node temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;

        return head;
    }
}
