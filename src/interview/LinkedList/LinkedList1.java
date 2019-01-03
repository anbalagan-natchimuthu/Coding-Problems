package interview.LinkedList;

/**
 * Created by 212438472 on 1/23/18.
 */
public class LinkedList1 {
    Node rootNode;

    public void insert(int[] inputArray) {

        Node currNode = null;
        for (int newNodeValue : inputArray) {
            Node newNode = new Node(newNodeValue);
            if (rootNode == null) {
                rootNode = newNode;
                currNode = rootNode;
                continue;
            }

            // Approach 1
            currNode.nextNode = newNode;
            currNode = currNode.nextNode;

            // Approach 2
           /* Node currNode = rootNode;

            while (currNode.nextNode != null) {
                currNode = currNode.nextNode;
            }
            currNode.nextNode = newNode;*/
        }
    }

    public void insertInMiddle(int[] inputArray, int middleValue) {

        if (inputArray.length == 0) {
            rootNode = new Node(middleValue);
            return;
        }

        Node currNode = null;
        int middlePosition = (inputArray.length / 2 );
        for (int i= 0; i < inputArray.length; i++) {
            Node newNode = new Node(inputArray[i]);
            if (rootNode == null) {
                if (i == middlePosition) {
                    rootNode = new Node(middleValue);
                    rootNode.nextNode = newNode;
                    break;
                } else {
                    rootNode = newNode;
                    currNode = rootNode;
                    continue;
                }
            }

            if (i == middlePosition) {
                currNode.nextNode = new Node(middleValue);
                currNode = currNode.nextNode;
            }
            currNode.nextNode = newNode;
            currNode = currNode.nextNode;
        }
    }

    public void deleteNode(int deleteNodeVal) throws Exception {
        boolean deleted = false;
        if (rootNode != null && rootNode.dataValue == deleteNodeVal) {
            deleted = true;
            rootNode = rootNode.nextNode;
        }
        Node currNode = rootNode;

        while (currNode != null) {
            if (currNode.nextNode != null && currNode.nextNode.dataValue == deleteNodeVal) {
                deleted = true;
                currNode.nextNode = currNode.nextNode.nextNode;
            } else {
                currNode = currNode.nextNode;
            }
        }

        if (!deleted) {
            throw new Exception("Node not present in Linked list");
        }
    }

    public void printValue() {
        Node currNode = rootNode;
        while(currNode != null) {
            System.out.print(currNode.dataValue + " ");
            currNode = currNode.nextNode;
        }
    }

    public static void main(String args[]) throws Exception{

        LinkedList1 linkedList =  new LinkedList1();

        //int[] inputArray = {10, 20, 30, 40, 50};

        //linkedList.insert(inputArray);
        //linkedList.printValue();

        LinkedList1 l1 = new LinkedList1();
        l1.insertInMiddle(new int[] {10}, 20);
        l1.printValue();

        System.out.println();
        System.out.println("______________________________");

        LinkedList1 l2 = new LinkedList1();
        l2.insertInMiddle(new int[] {10, 20}, 15);
        l2.printValue();

        System.out.println();
        System.out.println("______________________________");

        LinkedList1 l3 = new LinkedList1();
        l3.insertInMiddle(new int[] {}, 15);
        l3.printValue();

        System.out.println();
        System.out.println("______________________________");

        LinkedList1 deleteLinkedList =  new LinkedList1();
        deleteLinkedList.insert(new int[]{10});
        deleteLinkedList.deleteNode(10);
        deleteLinkedList.printValue();

        System.out.println();
        System.out.println("______________________________");

        LinkedList1 deleteLinkedList1 =  new LinkedList1();
        deleteLinkedList1.insert(new int[]{10, 20});
        deleteLinkedList1.deleteNode(10);
        deleteLinkedList1.printValue();

        System.out.println();
        System.out.println("______________________________");

        LinkedList1 deleteLinkedList2 =  new LinkedList1();
        deleteLinkedList2.insert(new int[]{});
        deleteLinkedList2.deleteNode(20);
        deleteLinkedList2.printValue();
    }

    public class Node {
        private Node nextNode;
        private int dataValue;

        public Node(int dataValue) {
            this.dataValue = dataValue;
            nextNode = null;
        }
    }
}
