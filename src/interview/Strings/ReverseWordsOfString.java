package interview.Strings;

public class ReverseWordsOfString {

    public static void main(String[] args) {
        String input = "This is a";
        reverseSentence(input);
    }

    //a si sihT
    private static void reverseSentence(String input) {
        char[] inputArr = input.toCharArray();
        reverse(inputArr, 0, inputArr.length - 1);
        for (int p = 0; p < inputArr.length; p++) {
            System.out.print(inputArr[p]);
        }
        System.out.println();
        System.out.println("***");
        int k = 0;
        for (int i = 0; i < input.length(); i++) {
            if (inputArr[i] == ' ') {
                reverse(inputArr, k, i - 1);
                k = i + 1;
            }
        }
        reverse(inputArr, k, inputArr.length - 1); // reverse the last word

        for (int p = 0; p < inputArr.length; p++) {
            System.out.print(inputArr[p]);
        }
    }

    private static void reverse(char[] partArr, int start, int end) {
        char temp;
        while (start <= end) {
            temp = partArr[start];
            partArr[start] = partArr[end];
            partArr[end] = temp;
            start++;
            end--;
        }
    }
}
