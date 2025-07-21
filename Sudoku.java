public class SudokuSolver {

    static final int N = 9;

    public static boolean isSafe(int[][] board, int row, int col, int num) {
        for (int x = 0; x < N; x++) {
            if (board[row][x] == num || board[x][col] == num ||
                board[3 * (row / 3) + x / 3][3 * (col / 3) + x % 3] == num)
                return false;
        }
        return true;
    }

    public static boolean solveSudoku(int[][] board, int row, int col) {
        if (row == N - 1 && col == N)
            return true;
        if (col == N) {
            row++;
            col = 0;
        }
        if (board[row][col] != 0)
            return solveSudoku(board, row, col + 1);

        for (int num = 1; num <= N; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board, row, col + 1))
                    return true;
                board[row][col] = 0; // backtrack
            }
        }
        return false;
    }

    public static void printBoard(int[][] board) {
        for (int r = 0; r < N; r++) {
            for (int d = 0; d < N; d++) {
                System.out.print(board[r][d] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0},
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };

        if (solveSudoku(board, 0, 0))
            printBoard(board);
        else
            System.out.println("No solution exists.");
    }
}
