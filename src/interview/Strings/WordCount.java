package interview.Strings;

import java.util.Arrays;

/**
 * Created by 212438472 on 3/30/18.
 */
public class WordCount {

    public static void main(String[] args) {
        wordCounts("The quick brown fox jumps, over the lazy dog; The quick brown fox jumps, over the lazy dog; yyy");
    }

    public static void wordCounts(String s) {
        if (null == s) {
            return;
        }

        String[] strings = s.replaceAll("[\\.:;,]", "").split("\\s");

        Arrays.sort(strings);
        int count = 1;

        for (int i = 1; i < strings.length; i++) {
            if (strings[i].equals(strings[i - 1])) {
                ++count;
            } else {
                System.out.println(strings[i - 1] + " --> " + count + " time(s)");
                count = 1;
            }
        }
        System.out.println(strings[strings.length - 1] + " --> " + count + " time(s)");
    }
}
