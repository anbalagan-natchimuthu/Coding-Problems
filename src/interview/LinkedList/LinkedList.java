package interview.LinkedList;

public class LinkedList {

  public static void main(String[] args) {
    CrunchifyLinkedList lList = new CrunchifyLinkedList();

    // add elements to LinkedList
    lList.add("10");
    lList.add("20");
    lList.add("30");
    lList.add("40");
    lList.add("50");
    lList.add("60");

    System.out.println("lList - print linkedlist: " + lList);
    System.out.println("lList.size() - print linkedlist size: " + lList.size());
    System.out.println("lList.get(3) - get 3rd element: " + lList.get(3));
    // System.out.println("lList.remove(2) - remove 2nd element: " + lList.remove(2));

    System.out.println("*************Rotated Linked List***********");

    CrunchifyLinkedList.Node headNode = lList.RotateLinkedList(3);
    while (headNode != null) {
      System.out.print("-->" + headNode.getData());
      headNode = headNode.getNext();
    }
    System.out.println();
  }
}

class CrunchifyLinkedList {

  // reference to the head node.
  private Node head;
  private int listCount;

  public Node getHead() {
    return head;
  }

  // LinkedList constructor
  public CrunchifyLinkedList() {
    // this is an empty list, so the reference to the head node
    // is set to a new node with no data
    head = null;
    listCount = 0;
  }

  public void add(Object data)
  // appends the specified element to the end of this list.
  {
    Node crunchifyTemp = new Node(data);
    if (head == null) {
      head = crunchifyTemp;
      return;
    }
    Node crunchifyCurrent = head;
    // starting at the head node, crawl to the end of the list
    while (crunchifyCurrent.getNext() != null) {
      crunchifyCurrent = crunchifyCurrent.getNext();
    }
    // the last node's "next" reference set to our new node
    crunchifyCurrent.setNext(crunchifyTemp);
    listCount++;// increment the number of elements variable
  }

  public void add(Object data, int index)
  // inserts the specified element at the specified position in this list
  {
    Node crunchifyTemp = new Node(data);
    Node crunchifyCurrent = head;
    // crawl to the requested index or the last element in the list,
    // whichever comes first
    for (int i = 1; i < index && crunchifyCurrent.getNext() != null; i++) {
      crunchifyCurrent = crunchifyCurrent.getNext();
    }
    // set the new node's next-node reference to this node's next-node
    // reference
    crunchifyTemp.setNext(crunchifyCurrent.getNext());
    // now set this node's next-node reference to the new node
    crunchifyCurrent.setNext(crunchifyTemp);
    listCount++;// increment the number of elements variable
  }

  public Node RotateLinkedList(int length) {
    return Rotate(head, length);
  }

  private static Node Rotate(Node head, int length) {
    if (length == 0 || null == head) {
      return head;
    }

    // Let us understand the below code for example k = 4 and
    // list = 10->20->30->40->50->60.
    Node current = head;

    // current will either point to kth or null after this loop.
    // current will point to node 40 in the above example
    while (length > 1 && current != null) {
      current = current.getNext();
      length--;
    }

    // If current is null, k is greater than or equal to count
    // of nodes in linked list. Don't change the list in this case
    if (current == null) {
      return head;
    }

    // current points to kth node. Store it in a variable.
    // kthNode points to node 40 in the above example
    Node kthNode = current;

    // current will point to last node after this loop
    // current will point to node 60 in the above example
    while (current.getNext() != null) {
      current = current.getNext();
    }

    // Change next of last node to previous head
    // Next of 60 is now changed to node 10
    current.setNext(head);

    // Change head to (k+1)th node
    // head is now changed to node 50
    head = kthNode.getNext();

    // change next of kth node to null
    // next of 40 is now null
    kthNode.setNext(null);

    return head;
  }

  public Object get(int index)
  // returns the element at the specified position in this list.
  {
    // index must be 1 or higher
    if (index <= 0) {
      return null;
    }

    Node crunchifyCurrent = head.getNext();
    for (int i = 1; i < index; i++) {
      if (crunchifyCurrent.getNext() == null) {
        return null;
      }

      crunchifyCurrent = crunchifyCurrent.getNext();
    }
    return crunchifyCurrent.getData();
  }

  public boolean remove(int index)
  // removes the element at the specified position in this list.
  {
    // if the index is out of range, exit
    if (index < 1 || index > size()) {
      return false;
    }

    Node crunchifyCurrent = head;
    for (int i = 1; i < index; i++) {
      if (crunchifyCurrent.getNext() == null) {
        return false;
      }

      crunchifyCurrent = crunchifyCurrent.getNext();
    }
    crunchifyCurrent.setNext(crunchifyCurrent.getNext().getNext());
    listCount--; // decrement the number of elements variable
    return true;
  }

  public int size()
  // returns the number of elements in this list.
  {
    return listCount;
  }

  public String toString() {
    Node crunchifyCurrent = head;
    String output = "";
    while (crunchifyCurrent != null) {
      output += "[" + crunchifyCurrent.getData().toString() + "]";
      crunchifyCurrent = crunchifyCurrent.getNext();
    }
    return output;
  }

  public class Node {

    // reference to the next node in the chain,
    // or null if there isn't one.
    Node next;
    // data carried by this node.
    // could be of any type you need.
    Object data;

    // Node constructor
    public Node(Object dataValue) {
      next = null;
      data = dataValue;
    }

    // another Node constructor if we want to
    // specify the node to point to.
    public Node(Object dataValue, Node nextValue) {
      next = nextValue;
      data = dataValue;
    }

    // these methods should be self-explanatory
    public Object getData() {
      return data;
    }

    public void setData(Object dataValue) {
      data = dataValue;
    }

    public Node getNext() {
      return next;
    }

    public void setNext(Node nextValue) {
      next = nextValue;
    }
  }
}