package interview.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 212438472 on 3/30/18.
 */
public class EmailPattern {

    public static void main(String[] args) {
        checkEmailPattern("abc.efg-900@gmail.co.ad.eu");
        checkEmailPattern("nikos+mylist@gmail.com");
    }

    public static void checkEmailPattern(String input) {

        try {
            String pattern = "^[_a-zA-Z0-9-\\+]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-z]{2,})$";

            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(input);

            System.out.println(m.matches());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
