package interview.Array;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PairElements {

	public static void main(String[] args) {
		int[] inputArray = new int[] {2, 4, 1, -3, -2, 7, 8, 5, 0, 4};
		findPairElements(inputArray, 5);
		getSumPairs(inputArray, 5);
	}
	
	public static void findPairElements(int[] inputElemtns, int targetElement) {
		
		LinkedList<Integer> orderedValues = new LinkedList<Integer>();		
		for(int i=0; i<inputElemtns.length; i++) {
			orderedValues.add(inputElemtns[i]);
		}
		Collections.sort(orderedValues);
		int k = 1;
		for(int i=0; i<orderedValues.size(); i++) {			
			int currentElement = orderedValues.pop();
			int expectElement = targetElement - currentElement;				
			if(orderedValues.contains(expectElement)) {
				System.out.println("Pair:" + k++ +":" + currentElement +"," + expectElement);
			}
		}
	}
	
	public static void getSumPairs(int[] input, int k) {
		Map<Integer, Integer> pairs = new HashMap<Integer, Integer>();
		for (int i = 0; i < input.length; i++) {
			if (pairs.containsKey(input[i])) System.out.println(input[i] + ", " + pairs.get(input[i]));
			else pairs.put(k - input[i], input[i]);
		}
		
		int temp = 0;
		int key=0;
		for(Map.Entry<Integer, Integer> entry: pairs.entrySet()) {
			
			if(entry.getKey() > entry.getValue()) {
				temp = entry.getKey() - entry.getValue();
			} else {
				temp = entry.getValue() - entry.getKey();
			}
			
			if(temp == 0) {
				temp = entry.getKey() - entry.getValue();
				key =entry.getKey();
			} else if(entry.getKey() - entry.getValue() < temp){
				temp = entry.getKey() - entry.getValue();
				key =entry.getKey();
			}
		}
			System.out.println(pairs.get(key) +":" + key);
	}


}
