import java.io.*;
import java.util.*;

public class Suduko {
    
    public static boolean isValidSudoku(int[][] board) {
        Map<Integer, Set<Integer>> row = new HashMap<>();
        Map<Integer, Set<Integer>> col = new HashMap<>();
        Map<Integer, Set<Integer>> box = new HashMap<>();
        
        for (int i = 0, p = -1; i < 9; i++, p = -1) {
            if (i % 3 == 0) {
                box.clear();
            }
            
            for (int j = 0; j < 9; j++) {
                // column
                if (board[i][j] != 0 && col.getOrDefault(j, new HashSet<>()).contains(board[i][j])) {
                    return false;
                } else {
                    col.computeIfAbsent(j, k -> new HashSet<>()).add(board[i][j]);
                }

                // row
                if (board[i][j] != 0 && row.getOrDefault(i, new HashSet<>()).contains(board[i][j])) {
                    return false;
                } else {
                    row.computeIfAbsent(i, k -> new HashSet<>()).add(board[i][j]);
                }

                if (j % 3 == 0) {
                    p++;
                }
    
                if (board[i][j] != 0 && box.getOrDefault(p, new HashSet<>()).contains(board[i][j])) {
                    return false;
                } else {
                    box.computeIfAbsent(p, k -> new HashSet<>()).add(board[i][j]);
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
        
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.println("Enter the value for the cell at row " + (i+1) + " and column " + (j+1) + ":");
                sudoku_grid[i][j] = input.nextInt();
            }
        }
    }

    
    public static void main(String[] args) {
        
        
        // int[][] sudoku_grid = new int[9][9];
        // getSudoku(sudoku_grid);
        
        
        int[][] sudoku_grid = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        
        if(isValidSudoku(sudoku_grid)) {
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
