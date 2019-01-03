package interview.Array;

import java.util.Arrays;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.

 Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a
 queen and an empty space respectively.
 * https://www.youtube.com/watch?v=xouin83ebxE
 */
public class NQueenProblem {

    class Position {
        int row, col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public Position[] solveNQueenOneSolution(int n) {
        Position[] positions = new Position[n];
        boolean hasSolution = solveNQueenOneSolutionUtil(n, 0, positions);
        if (hasSolution) {
            return positions;
        } else {
            return new Position[0];
        }
    }

    private boolean solveNQueenOneSolutionUtil(int n, int row, Position[] positions) {

        if (n == row) {
            return true;
        }

        for (int col = 0; col < n; col++) {
            boolean foundSafe = true;

            //check if this row and col is not under attack from any previous queen.
            for (int queen = 0; queen < row; queen++) {
                if (positions[queen].col == col || positions[queen].row - positions[queen].col == row - col ||
                    positions[queen].row + positions[queen].col == row + col) {
                    foundSafe = false;
                    break;
                }
            }

            if (foundSafe) {
                positions[row] = new Position(row, col);
                if (solveNQueenOneSolutionUtil(n, row + 1, positions)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        NQueenProblem s = new NQueenProblem();
        Position[] positions = s.solveNQueenOneSolution(6);
        Arrays.stream(positions).forEach(position -> System.out.println(position.row + " " + position.col));
    }
}
