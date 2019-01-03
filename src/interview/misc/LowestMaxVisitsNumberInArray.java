package interview.misc;

import java.util.ArrayList;
import java.util.List;

public class LowestMaxVisitsNumberInArray {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(2);
        list.add(7);
        list.add(3);
        list.add(10);
        int[] arr = new int[10];
        List<Integer> list1 = new ArrayList<>();
        long smillis = System.currentTimeMillis() % 1000;
        for (int i = 0; i < list.size() - 1; i++) {
            int start = list.get(i) - 1;
            int end = list.get(i + 1) - 1;
            if (start > end) {
                int temp = start;
                start = end;
                end = temp;
            }
//            for(int j=start; j<=end; j++){
//                arr[j]++;
//            }
            arr[start]++;
            arr[end]++;
            for (int j = 0; j < list1.size(); j++) {
                if (list1.get(j) > start && list1.get(j) < end) {
                    arr[list1.get(j)]++;
                }
            }
            list1.add(list.get(i));
        }
        int maxVisits = -1;
        int index = -1;
        for (int k = 0; k < arr.length; k++) {
            if (arr[k] > maxVisits) {
                index = k;
                maxVisits = arr[k];
            }
        }
        long emillis = System.currentTimeMillis() % 1000;
        System.out.println("Time taken---" + (emillis - smillis));
        System.out.println("Here is the highest---" + (index + 1));
    }
}
