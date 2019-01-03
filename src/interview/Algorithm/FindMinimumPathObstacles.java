package interview.Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 212438472 on 10/28/18.
 */
public class FindMinimumPathObstacles {

    public static void main(String[] args) {
        List<Integer> row1 = new ArrayList<>(Arrays.asList(1, 1, 0, 1, 1));
        List<Integer> row2 = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 0));
        List<Integer> row3 = new ArrayList<>(Arrays.asList(1, 0, 9, 0, 1));
        List<Integer> row4 = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1));

        List<List<Integer>> inputList = new ArrayList<>();
        inputList.add(row1);
        inputList.add(row2);
        inputList.add(row3);
        inputList.add(row4);
        System.out.println(minimumPath(inputList));
    }

    private static int minimumPath(List<List<Integer>> paths) {

        int[][] resultTable = new int[paths.size()][paths.get(0).size()];

        // Fill first column values which depends on previous row first column value
        for (int i = 1; i < paths.size(); i++) {
            int column_zero = paths.get(i).get(0);
            if (column_zero != 0) {
                if (column_zero == 9) {
                    return 1 + resultTable[i - 1][0];
                } else {
                    if (i ==1) {
                        resultTable[i][0] =  1 + resultTable[i - 1][0];
                    } else {
                        resultTable[i][0] = resultTable[i - 1][0] == 0 ? 0 : 1 + resultTable[i - 1][0];
                    }
                }
            }
        }

        // Fill first row values which depends on previous column of first row value
        for (int i = 1; i < paths.get(0).size(); i++) {
            int row_zero = paths.get(0).get(i);
            if (row_zero != 0) {
                if (row_zero == 9) {
                    return 1 + resultTable[0][i - 1];
                } else {
                    if (i==1) {
                        resultTable[0][i] = 1 + resultTable[0][i - 1];
                    } else {
                        resultTable[0][i] = resultTable[0][i - 1] == 0 ? 0 : 1 + resultTable[0][i - 1];
                    }
                }
            }
        }



        for(int i = 1; i <paths.size(); i++) {
            for(int j = 1; j <paths.get(0).size(); j++) {
                if (paths.get(i).get(j) == 9) {
                    findMinimum(resultTable, i, j);
                    return resultTable[i][j];
                } else {
                    findMinimum(resultTable, i, j);
                }
            }
        }
        for(int i= 0; i < resultTable.length; i++) {
            for (int j= 0; j < resultTable[i].length; j++) {
                System.out.print(resultTable[i][j] + "  ");
            }
            System.out.println();
        }


        return -1;
    }

    private static void findMinimum(int[][] resultTable, int i, int j) {
        if (resultTable[i-1][j]  == 0 && resultTable[i][j-1] > 0) {
            resultTable[i][j] = 1 + resultTable[i][j-1];
        } else if (resultTable[i-1][j]  > 0 && resultTable[i][j-1] == 0) {
            resultTable[i][j] = 1 + resultTable[i-1][j];
        } else if (resultTable[i-1][j]  > 0 && resultTable[i][j-1] > 0) {
            resultTable[i][j] = 1 + Math.min(resultTable[i - 1][j], resultTable[i][j - 1]);
        }
    }
}
