package interview.Strings;

import java.util.Stack;

public class ReverseWordOfString {

    public static void main(String[] args) {

        String input = "This is a";
        reverseWords(input);
        reverseWordsInString("   This  is  a");
        reverseWordsUsingStack("   This  is      a");
        naga();
    }


    private static void naga() {
        String s = "vsbds    sbsbds hsdbsd hsdsd";
        char[] sa = s.toCharArray();
        int j=0;
        for(int i=0; i<sa.length; i++){

            if(j<i && sa[i] == ' '){
                //call the swap function
                if(j == 0){
                    swap(sa,i-1,j);
                }
                else{
                    swap(sa,i,j);
                }

                j = i;
            }
            if(sa[i] == ' '){
                j = i;
            }

            if(sa[i] != ' ' && i == sa.length-1){
                swap(sa,i,j+1);
            }
        }
        System.out.println();
        System.out.println("old:" + s +":");
        System.out.println("new:" + new String(sa) + ":");
    }

    static void swap(char[] sa, int i, int j){
        while(j<i){
            char temp = sa[j];
            sa[j] = sa[i];
            sa[i] = temp;
            j++;
            i--;
        }
    }

    private static void reverseWordsInString(String inputString) {
        char[] inputArr = inputString.toCharArray();
        int startIndex, endIndex;
        char ch;

        for (int i = 0; i < inputArr.length; i++) {
            ch = inputArr[i];
            if (ch != ' ') {
                startIndex = i;
                while (ch != ' ') {
                    ch++;
                }
                endIndex = i;
                reverse(inputArr, startIndex, endIndex);
            } else {
                ch++;
            }
        }

        System.out.print("::");
        for (char cha : inputArr) {
            System.out.print(cha);
        }
        System.out.println("::");
    }

    private static void reverseWords(String sentence) {

        char[] wordArr = sentence.toCharArray();
        reverse(wordArr, 0, wordArr.length - 1);
        int k = 0;
        for (int i = 0; i < wordArr.length; i++) {
            if (wordArr[i] == ' ') {
                reverse(wordArr, k, i-1);
                k = i + 1;
            }
            if (i == wordArr.length - 1) {
                reverse(wordArr, k, i);

                k = i + 1;
            }
        }

        System.out.print("::");
        for (int p = 0; p < wordArr.length; p++) {
            System.out.print(wordArr[p]);
        }
        System.out.println("::");
    }

    private static void reverse(char[] wordArr, int i, int j) {

        char temp;

        while (i <= j) {
            temp = wordArr[i];
            wordArr[i] = wordArr[j];
            wordArr[j] = temp;
            j--;
            i++;
        }
    }


        // reverses individual words of a string
       private static void reverseWordsUsingStack(String str)
        {
            Stack<Character> st=new Stack<Character>();

            // Traverse given string and push all
            // characters to stack until we see a space.
            for (int i = 0; i < str.length(); ++i) {
                if (str.charAt(i) != ' ')
                    st.push(str.charAt(i));

                    // When we see a space, we print
                    // contents of stack.
                else {
                    while (st.empty() == false) {
                        System.out.print(st.pop());

                    }
                    System.out.print(" ");
                }
            }

            // Since there may not be space after
            // last word.
            while (st.empty() == false) {
                System.out.print(st.pop());

            }
        }
}
