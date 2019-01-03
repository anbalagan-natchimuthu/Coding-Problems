package interview.Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

public class StudentSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> inputValues = new HashMap<String, Integer>();
		inputValues.put("Student1", new Integer(25));
		inputValues.put("Student2", new Integer(75));
		inputValues.put("Student4", new Integer(10));
		inputValues.put("Student8", new Integer(100));
		inputValues.put("Student5", new Integer(100));
		inputValues.put("Student3", new Integer(35));
		
		TreeMap<String, Integer> sort = new TreeMap<String, Integer>(inputValues);
		NavigableMap<String, Integer> sorted = sort.descendingMap();
		int j=0;
		for(Map.Entry<String, Integer> student : sorted.entrySet()) {
			System.out.println(student.getKey());
			j++;
			if(j==3) break;
		}	
		
		
		String[] student = findTopThreeStudent(inputValues).get(0);
		for(int i=0; i<student.length; i++) {
			System.out.println(student[i]);
		}
	}
	
	public static <K extends Comparable, V extends Comparable>  ArrayList<String[]> findTopThreeStudent(HashMap<K, V> students) {
		
		Map<K, V> sortedMap = sortByValue(students);
		ArrayList<String[]> output= new ArrayList<String[]>();
		int i=0;
		String[] value =  new String[3];
		
		for(Map.Entry<K, V> student : sortedMap.entrySet()) {
			value[i++] = student.getKey().toString();
			if(i==3) break;
		}
		output.add(value);
		return output;
	}
	
	public static <K extends Comparable<V>, V extends Comparable<V>> Map<K,V> sortByValue (Map<K, V> studentList) {
		
		java.util.LinkedList<Map.Entry<K,V>> entries = new java.util.LinkedList<Map.Entry<K, V>>(studentList.entrySet());
		
		Collections.sort(entries, new Comparator<Entry<K, V>>() {
			
			public int compare(Map.Entry<K, V> student1, Map.Entry<K, V> student2) {
				return student2.getValue().compareTo(student1.getValue());
			}
		});
		
		Map<K, V> sortedValues = new LinkedHashMap<K, V> ();
		for(Map.Entry<K, V> entry : entries) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
			sortedValues.put(entry.getKey(), entry.getValue());
		}
		
		return sortedValues;
		
	}
	
public static <K extends Comparable, V extends Comparable>  ArrayList<String[]> findTopThreeStudentWithoutGeneric(HashMap<K, V> students) {
		
		Map<K, V> sortedMap = sortByValue(students);
		ArrayList<String[]> output= new ArrayList<String[]>();
		int i=0;
		String[] value =  new String[3];
		
		for(Map.Entry<K, V> student : sortedMap.entrySet()) {
			value[i++] = student.getKey().toString();
			if(i==3) break;
		}
		output.add(value);
		return output;
	}
	
	public static Map<String,Integer> sortByValueWithoutGeneric (Map<String, Integer> studentList) {

		java.util.LinkedList<Map.Entry<String,Integer>> entries = new java.util.LinkedList<Map.Entry<String, Integer>>(studentList.entrySet());

		entries.sort(Map.Entry.comparingByValue());
		
		/*Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
			
			public int compare(Map.Entry<String, Integer> student1, Map.Entry<String, Integer> student2) {
				return student2.getValue().compareTo(student1.getValue());
			}
		});*/
		
		Map<String, Integer> sortedValues = new LinkedHashMap<String, Integer> ();
		for(Map.Entry<String, Integer> entry : entries) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
			sortedValues.put(entry.getKey(), entry.getValue());
		}
		
		return sortedValues;
		
	}

}
