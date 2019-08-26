package interview.tree;

/**
 * https://www.youtube.com/watch?v=YDf982Lb84o
 * http://www.geeksforgeeks.org/program-nth-catalan-number/
 * https://www.geeksforgeeks.org/applications-of-catalan-numbers/
 * Count number of binary search tree created for array of size n
 */
public class CountNumberOfTreesInBST {
    public int countTrees(int n){
        int T[] = new int[n+1];
        T[0] = 1;
        T[1] = 1;
        for(int i=2; i <= n; i++){
            for(int j=0; j <i; j++){
                T[i] += T[j]*T[i-j-1];
            }
        }
        return T[n];
    }

    public static void main(String args[]){
        CountNumberOfTreesInBST cnt = new CountNumberOfTreesInBST();
        System.out.println(cnt.countTrees(3));
        System.out.println(cnt.countTrees(4));
    }
}
