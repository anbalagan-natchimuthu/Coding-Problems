package interview.Strings;

public class ReverseWordsOfString {

  public static void main(String[] args) {
    String input = "the sky is blue";
    reverseSentence(input);

    input = "  hello world!  ";
    reverseSentence(input);

    input = "a good   example";
    reverseSentence(input);
  }

  //a si sihT
  private static void reverseSentence(String input) {
    // Reverse whole string
    char[] inputArr = input.toCharArray();
    reverse(inputArr, 0, inputArr.length - 1);

    // Reverse word by word
    int k = 0;
    for (int i = 0; i < inputArr.length; i++) {
      if (inputArr[i] == ' ') {
        reverse(inputArr, k, i - 1);
        k = i + 1;
      }
    }
    reverse(inputArr, k, inputArr.length - 1); // reverse the last word

    // Remove additional spaces
    int i = 0, j = 0;

    while (j < inputArr.length - 1) {
      if (inputArr[j] == ' ' && inputArr[j] == inputArr[j + 1]) {
        j++;
      } else {
        inputArr[i++] = inputArr[j++];
      }
    }

    System.out.print("::");
    String finalString = new String(inputArr).substring(0, i+1);
    System.out.print(finalString);
    System.out.println("::");
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
