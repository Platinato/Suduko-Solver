import java.util.*;

public class Suduko {
    
    public static boolean isValidPuzzle(int[][] sudoku_grid) {
        // check rows
        for (int row = 0; row < sudoku_grid.length; row++) {
            boolean[] check = new boolean[9];
            for (int col = 0; col < sudoku_grid[row].length; col++) {
                if (sudoku_grid[row][col] != 0) {
                    if (check[sudoku_grid[row][col] - 1]) {
                        return false;
                    }
                    check[sudoku_grid[row][col] - 1] = true;
                }
            }
        }
    
        // check columns
        for (int col = 0; col < sudoku_grid.length; col++) {
            boolean[] check = new boolean[9];
            for (int row = 0; row < sudoku_grid.length; row++) {
                if (sudoku_grid[row][col] != 0) {
                    if (check[sudoku_grid[row][col] - 1]) {
                        return false;
                    }
                    check[sudoku_grid[row][col] - 1] = true;
                }
            }
        }
    
        // check 3x3 sub-grids
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boolean[] check = new boolean[9];
                for (int row = i * 3; row < i * 3 + 3; row++) {
                    for (int col = j * 3; col < j * 3 + 3; col++) {
                        if (sudoku_grid[row][col] != 0) {
                            if (check[sudoku_grid[row][col] - 1]) {
                                return false;
                            }
                            check[sudoku_grid[row][col] - 1] = true;
                        }
                    }
                }
            }
        }
    
        return true;
    }
    

    
    
    public static boolean solve(int[][] sudoku_grid) {
        
        for (int row = 0; row < sudoku_grid.length; row++) {
            for (int col = 0; col < sudoku_grid[row].length; col++) {
                if (sudoku_grid[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(sudoku_grid, row, col, num)) {
                            sudoku_grid[row][col] = num;
                            if (solve(sudoku_grid)) {
                                return true;
                            } else {
                                sudoku_grid[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isValid(int[][] sudoku_grid, int row, int col, int num) {
        // check row
        for (int c = 0; c < sudoku_grid[row].length; c++) {
            if (sudoku_grid[row][c] == num) {
                return false;
            }
        }
        
        // check column
        for (int r = 0; r < sudoku_grid.length; r++) {
            if (sudoku_grid[r][col] == num) {
                return false;
            }
        }
        
        // check 3x3 box
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (sudoku_grid[r + boxRow][c + boxCol] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    
    public static void getSudoku(int[][] sudoku_grid) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the values of the grid.\t\t");
        System.out.println("Enter \'0\' for blanks");

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                // System.out.println("Enter the value for the cell at row " + (i+1) + " and column " + (j+1) + ":");
                sudoku_grid[i][j] = input.nextInt();
            }
        }

        System.out.println("\n");

        input.close();
    }

    
    public static void main(String[] args) {
        
        int[][] sudoku_grid = new int[9][9];
        getSudoku(sudoku_grid);
        
        if(isValidPuzzle(sudoku_grid)) {
            System.out.println("This is the valid one! Preparing to solve it.");
        
            if (solve(sudoku_grid)) {
                for (int row = 0; row < sudoku_grid.length; row++) {
                    for (int col = 0; col < sudoku_grid[row].length; col++) {
                        System.out.print(sudoku_grid[row][col] + " ");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solution found.");
            }
            
        } else {
            System.out.println("This is not the valid input!");
            return;
        }
        
        return;
    }
}
