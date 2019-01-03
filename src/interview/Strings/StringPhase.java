package interview.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StringPhase {

    public static void main(String[] args) {
        // List<String> allSentences = new ArrayList<>();
        // String sentence1 = "jim likes mary";
        // String sentence2 = "kate likes tom";
        // String sentence3 = "tom does not like jim";
        //
        // allSentences.add(sentence1);
        // allSentences.add(sentence2);
        // allSentences.add(sentence3);

        // List<String> allqueries = new ArrayList<>();
        // String query1 = "jim tom";
        // String query2 = "likes";
        String[] allSentences = { "jim likes mary", "kate likes tom", "tom does not like jim" };
        String[] allqueries = { "jim tom", "likes" };
        countIndex(allSentences, allqueries);
    }

    public static void countIndex(String[] allSentences, String[] queuerie) {

        List<HashMap<String, Integer>> allWordCountList = new ArrayList<>();
        HashMap<String, Integer> sentenceWordCountMap;
        String[] sentenceArray;
        for (String sentence : allSentences) {
            sentenceArray = sentence.split(" ");
            sentenceWordCountMap = new HashMap<>();
            for (String word : sentenceArray) {
                if (sentenceWordCountMap.containsKey(word)) {
                    int count = sentenceWordCountMap.get(word);
                    sentenceWordCountMap.put(word, count++);
                } else {
                    sentenceWordCountMap.put(word, 1);
                }
            }
            allWordCountList.add(sentenceWordCountMap);
        }

        for (int i = 0; i < allWordCountList.size(); i++) {
            for (int j = 0; j < queuerie.length; j++) {
                int k = getMinFrequencies(allWordCountList.get(i), queuerie[j]);
                if (k == Integer.MAX_VALUE) {
                    System.out.println("Not found");
                }
                for (int m = 0; m < k; m++) {
                    System.out.print(i);
                }
            }
            System.out.println();
        }
    }

    private static int getMinFrequencies(HashMap<String, Integer> sentenceWordCountMap, String query) {
        String[] words = query.split(" ");
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            int localMin = 0;
            if (sentenceWordCountMap.containsKey(words[i])) {
                localMin = sentenceWordCountMap.get(words[i]);
            } else {
                return -1;
            }

            if (localMin < min) {
                min = localMin;
            }
        }
        return min;
    }
}
