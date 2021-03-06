package interview.DP;

/**
 * https://www.youtube.com/watch?v=3hcaVyX00_4
 */
public class EggDropping {

    public int calculate(int eggs, int floors) {

        int T[][] = new int[eggs + 1][floors + 1];
        for (int i = 0; i <= floors; i++) {
            T[1][i] = i;
        }

        int counts;
        for (int e = 2; e <= eggs; e++) {
            for (int f = 1; f <= floors; f++) {
                T[e][f] = Integer.MAX_VALUE;
                for (int k = 1; k <= f; k++) {
                    counts = 1 + Math.max(T[e - 1][k - 1], T[e][f - k]);
                    if (counts < T[e][f]) {
                        T[e][f] = counts;
                    }
                }
            }
        }
        return T[eggs][floors];
    }

    public static void main(String args[]) {
        EggDropping ed = new EggDropping();
        int r = ed.calculate(3, 100);
        System.out.println(r);
    }
}
