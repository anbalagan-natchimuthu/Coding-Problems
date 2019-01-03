package interview.Strings;

public class SecondFreqCharacter {

    public static void main(String[] args) {
        String input = "aabababa";

        char[] inputArray = input.toCharArray();
        int[] countArray = new int[26];
        for (int i = 0; i < inputArray.length; i++) {
            countArray[inputArray[i] - 'a']++;
        }
        int maxCount = 0;
        int secondMaxCount = 0;
        for (int i = 0; i < countArray.length; i++) {
            if (countArray[i] > maxCount) {

                secondMaxCount = maxCount;
                maxCount = countArray[i];
            } else if (countArray[i] > secondMaxCount) {
                secondMaxCount = countArray[i];
            }
        }
        System.out.println("secondMaxCount " + secondMaxCount);
    }
}
