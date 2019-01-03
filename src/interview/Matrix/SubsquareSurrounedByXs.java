package interview.Matrix;

/*
    * https://www.youtube.com/watch?v=vi_1eHCsR9A
    *
    * Find maximum subsquare in a matrix made up of Xs and Os such that all four sides of subsquare are Xs.
    * It does not matter what is inside the subsquare. All 4 sides should be made up entirely of Xs
    *
    * e.g
    * 0 0 0 0 0 X         0,0  0,0  0,0  0,0  0,0  1,1
    * 0 X 0 X X X         0,0  1,1  0,0  1,1  1,2  2,3
    * 0 X 0 X 0 X         0,0  2,1  0,0  2,1  0,0  3,1
    * 0 X X X X X         0,0  3,1  1,2  3,3  1,4  4,5
    * 0 0 0 0 0 0         0,0  0,0  0,0  0,0  0,0  0,0
    *
    * Output of above program should be 3
    *
    * Solution
    * Have another matrix which is capable of holding 2 values hori and ver.
    * Ver stores how far vertically you can see Xs. Hori stores how far horizontally you can see Xs.
    * Once this matrix is build look for biggest subsquare by getting min of hori and ver at each point and checking
    * if subsquare can be formed from value min to 1.
    *
    * Test cases:
    * Matrix entirely made up of Xs
    * Matrix entirely made up of Os
    * Matrix with Xs and Os but maximum subsquare is length 1
    *
    * https://github.com/mission-peace/interview/blob/259077bacabdbb5b6a0e918cd8dfe5eabca3300f/src/com/interview/dynamic/SubsquareSurrounedByXs.java
    */
public class SubsquareSurrounedByXs {

    private class Cell {
        int vert;
        int hori;
    }

    private int getMaximumSquareWithSideX(char[][] inputMatrix) {
        Cell[][] resultMatrix = new Cell[inputMatrix.length][inputMatrix[0].length];

        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix[0].length; j++) {
                resultMatrix[i][j] = new Cell();
            }
        }

        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix[0].length; j++) {
                if (inputMatrix[i][j] == 'X') {
                    if (i == 0 && j == 0) {
                        resultMatrix[i][j].hori = 1;
                        resultMatrix[i][j].vert = 1;
                    }
                    else if (i == 0) {
                        resultMatrix[i][j].hori = resultMatrix[i][j-1].hori + 1;
                        resultMatrix[i][j].vert = 1;
                    }
                    else if (j == 0) {
                        resultMatrix[i][j].vert = resultMatrix[i-1][j].vert + 1;
                        resultMatrix[i][j].hori = 1;
                    } else {
                        resultMatrix[i][j].hori = resultMatrix[i][j-1].hori + 1;
                        resultMatrix[i][j].vert = resultMatrix[i-1][j].vert + 1;
                    }
                }
            }
        }

        for(int i=0; i < resultMatrix.length; i++){
            for(int j=0; j < resultMatrix[0].length; j++){
                System.out.print(resultMatrix[i][j].vert + "," + resultMatrix[i][j].hori+ "  ");
            }
            System.out.println();
        }

        int max = 0;
        //start iterating from bottom right corner and find min of hori or ver at every cell.
        //If this is greater than 1 then see if you can find a number between this min and 1
        //such that on left's ver and top's hori is greater greater than or equal to k.
        for (int i = inputMatrix.length -1 ; i >=0; i--) {
            for (int j = inputMatrix[0].length - 1; j >= 0; j--) {
                int min = Math.min(resultMatrix[i][j].hori, resultMatrix[i][j].vert);

                if (min > max) {
                    for (int k = min; k > max; k--) {
                        if (resultMatrix[i][j - k + 1].vert >= k && resultMatrix[i - k + 1][j].hori >= k) {
                            max = k;
                            break;
                        }
                    }
                }
            }
        }
        return max;
    }

    public static void main(String args[]){
        char[][] input = {{'X','O','O','O','O','O'},
            {'O','O','O','O','O','O'},
            {'X','X','X','X','O','O'},
            {'X','X','X','X','X','O'},
            {'X','O','O','X','X','O'},
            {'X','O','X','X','X','O'}};

        char [][] input1 = {{'O', 'O', 'O', 'O', 'O', 'X'},
            {'O', 'X', 'O', 'X', 'X', 'X'},
            {'O', 'X', 'O', 'X', 'O', 'X'},
            {'O', 'X', 'X', 'X', 'X', 'X'},
            {'O', 'O', 'O', 'O', 'O', 'O'},
        };

        char [][] input2 = {{'O', 'O', 'X', 'O', 'X'},
            {'O', 'X', 'X', 'O', 'X'},
            {'O', 'X', 'O', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X'},
            {'O', 'X', 'X', 'X', 'O'},
        };

        SubsquareSurrounedByXs ss = new SubsquareSurrounedByXs();
        System.out.println(ss.getMaximumSquareWithSideX(input));
        System.out.println(ss.getMaximumSquareWithSideX(input1));
        System.out.println(ss.getMaximumSquareWithSideX(input2));
    }
}
