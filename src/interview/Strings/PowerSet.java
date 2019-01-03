package interview.Strings;

import java.util.HashSet;
import java.util.Set;

public class PowerSet {

    public static void main(String[] args) {
        Set<String> result = powerSet("abcde");
        int counter =0;
        for(String each:result){
            System.out.println(each);
            ++counter;
        }
        System.out.println(counter);
    }
    public static final Set<String> powerSet(String input){
        Set<String> all = new HashSet<>();
        Set<String> temp = new HashSet<>();
        all.add("");

        for(int i=0;i< input.length() ; i++){
            for (String each:all){
                temp.add(each + input.charAt(i));
            }
            all.addAll(temp);
        }
        return all;
    }
}

// while(node != null){
//     newNode
//     map.put(node.newNode);
//     }
//
//     while(node != null){
//     oneNewNode = map.get(node);
//     toBePointed = map.get(node.next);
//     oneNewNode.next = toBePointedl
//     node = node.next;
//     }