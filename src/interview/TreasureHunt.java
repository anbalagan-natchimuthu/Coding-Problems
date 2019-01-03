package interview;

public class TreasureHunt {

    public static void main(String[] args) {
        treasure("r 5 r r l");
    }

    static void treasure(String s) {

        int x = 0;
        int y = 0;
        String currentD = "n";
        String[] sa = s.split(" ");
        for (String s1 : sa) {
            if (Character.isDigit(s1.charAt(0))) {
                if (currentD.equals("n")) {
                    y += Integer.parseInt(s1);
                } else if (currentD.equals("s")) {
                    y -= Integer.parseInt(s1);
                } else if (currentD.equals("e")) {
                    x += Integer.parseInt(s1);
                } else if (currentD.equals("w")) {
                    x -= Integer.parseInt(s1);
                }
            } else {
                if (currentD.equals("n")) {
                    if (s1.equals("r")) {
                        currentD = "e";
                    } else {
                        currentD = "w";
                    }
                } else if (currentD.equals("s")) {
                    if (s1.equals("r")) {
                        currentD = "w";
                    } else {
                        currentD = "e";
                    }
                } else if (currentD.equals("e")) {
                    if (s1.equals("r")) {
                        currentD = "s";
                    } else {
                        currentD = "n";
                    }
                } else if (currentD.equals("w")) {
                    if (s1.equals("r")) {
                        currentD = "n";
                    } else {
                        currentD = "s";
                    }
                }
            }
        }
        System.out.println(x + "," + y);
    }
}
