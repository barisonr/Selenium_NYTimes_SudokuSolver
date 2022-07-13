public class Solver {
    public static boolean solveBoard(int[][] sudokuBoard) { // Trying all possibilities
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard[i][j] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (isValid(k, sudokuBoard, i, j)) {
                            sudokuBoard[i][j] = k;
                            if (solveBoard(sudokuBoard)) {
                                return true;
                            } else
                                sudokuBoard[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int value, int[][] sudokuBoard, int i, int j) {
        return rowCheck(value, i, sudokuBoard) && columnCheck(value, j, sudokuBoard) &&
                boxCheck(value, i, j, sudokuBoard);
    }


    private static boolean rowCheck(int value, int row, int[][] sudokuBoard) {
        for (int j = 0; j < 9; j++) {
            if (sudokuBoard[row][j] == value) {
                return false;
            }
        }
        return true;
    }

    private static boolean columnCheck(int value, int column, int[][] sudokuBoard) {
        for (int i = 0; i < 9; i++) {
            if (sudokuBoard[i][column] == value) {
                return false;
            }
        }
        return true;
    }

    private static boolean boxCheck(int value, int row, int column, int[][] sudokuBoard) {
        int rowStart = row - (row % 3);
        int columnStart = column - (column % 3);

        for (int k = rowStart; k < rowStart + 3; k++) {
            for (int l = columnStart; l < columnStart + 3; l++) {
                if (sudokuBoard[k][l] == value) {
                    return false;
                }
            }
        }
        return true;
    }
}
