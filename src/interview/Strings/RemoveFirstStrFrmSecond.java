package interview.Strings;

public class RemoveFirstStrFrmSecond {

    public static void main(String[] args) {
        removeFirstStringFromSecond("geeksForgeeks", "maSkF");
    }

    // geeksForgeeks
    //ks
    // geeforgee

    //test string
    //mask
    //tet tring

    private static void removeFirstStringFromSecond(String input1, String input2) {

        //Put input2 chars in count array
        // remove occurance of character of input 2 from 1 and keep adding the remaining
        int[] count2 = new int[256];
        int[] count1 = new int[256];
        for (int i = 0; i < input2.length(); i++) {
            count2[input2.charAt(i)]++;
        }

        for (int i = 0; i < input1.length(); i++) {
            count1[input1.charAt(i)]++;
        }

        for (int i = 0; i < count1.length; i++) {
            if (count1[i] != 0 && count2[i] != 0) {
                count1[i] = 0;
            }
        }
        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < input1.length(); i++) {
            if (count1[input1.charAt(i)] != 0) {
                finalString.append(input1.charAt(i));
            }
        }

        System.out.println("Final String is " + finalString.toString());
    }
}
