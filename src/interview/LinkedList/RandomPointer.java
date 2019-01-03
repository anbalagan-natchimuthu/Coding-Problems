package interview.LinkedList;

import java.util.HashMap;
import java.util.Map;

public class RandomPointer {

    public static void main(String[] args) {
        RandomNode n1 = new RandomNode(1);
        RandomNode n2 = new RandomNode(2);
        RandomNode n3 = new RandomNode(3);
        RandomNode n4 = new RandomNode(4);
        RandomNode n5 = new RandomNode(5);
        RandomNode n6 = new RandomNode(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        n1.random = n3;
        n2.random = n4;
        n3.random = n5;
        n4.random = n6;
        n5.random = n1;
        n6.random = n2;

        RandomNode finalNode = copyLLRandomPointer(n1);
        while (finalNode != null) {
            System.out.println(finalNode.data + " Random " + finalNode.random.data);
            finalNode = finalNode.next;
        }
        // copyRandom(n1);
    }

    private static RandomNode copyLLRandomPointer(RandomNode head) {
        RandomNode dummy = new RandomNode(0), cur = dummy;
        Map<RandomNode, RandomNode> map = new HashMap<>();
        while (head != null) {
            RandomNode newNode = null;
            if (map.containsKey(head)) {
                newNode = map.get(head);
            } else {
                newNode = new RandomNode(head.data);
                map.put(head, newNode);
            }
            if (head.random != null) // ATTENTION
            {
                if (map.containsKey(head.random)) {
                    newNode.random = map.get(head.random);
                } else {
                    newNode.random = new RandomNode(head.random.data);
                    map.put(head.random, newNode.random);
                }
            }
            cur.next = newNode;
            cur = cur.next;
            head = head.next;
        }
        return dummy.next;
    }

    private static void copyRandom(RandomNode node) {

        RandomNode dummy = new RandomNode(0);
        RandomNode curr = dummy;

        Map<RandomNode, RandomNode> randomNodeHashMap = new HashMap<>();

        while (node != null) {
            if (randomNodeHashMap.containsKey(node)) {
                curr = randomNodeHashMap.get(node);
            } else {
                curr = new RandomNode(node.data);
                randomNodeHashMap.put(node, curr);
            }
            node = node.next;
        }
        RandomNode rNode = curr;
        for (RandomNode entry : randomNodeHashMap.keySet()) {
            rNode = randomNodeHashMap.get(entry);
            rNode.random = entry.random;
        }
        curr.next = rNode;
        rNode = rNode.next;
        //head = head.next;
    }

    // public static final LinkedList clone(LinkedList input){
    //     LinkedList  head = new LinkedList(input.data);
    //     LinkedList prev, curr = head;
    //
    //     while (input.next != null){
    //         input = input.next;
    //         prev = curr;
    //         curr = new LinkedList(input.data);
    //         prev.next = curr;
    //     }
    //     return head;
    // }
}

