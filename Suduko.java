import java.util.*;

public class Suduko {
    
    public static boolean isValidInput(int[][] grid) {
        
        // check columns
        for (int col = 0; col < grid.length; col++) {
            boolean[] visited = new boolean[9];
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] != 0) {
                    if (visited[grid[row][col] - 1]) {
                        return false;
                    }
                    visited[grid[row][col] - 1] = true;
                }
            }
        }
        
        // check rows
        for (int row = 0; row < grid.length; row++) {
            boolean[] visited = new boolean[9];
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] != 0) {
                    if (visited[grid[row][col] - 1]) {
                        return false;
                    }
                    visited[grid[row][col] - 1] = true;
                }
            }
        }

        // check 3x3 sub-grids
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boolean[] visited = new boolean[9];
                for (int row = i * 3; row < i * 3 + 3; row++) {
                    for (int col = j * 3; col < j * 3 + 3; col++) {
                        if (grid[row][col] != 0) {
                            if (visited[grid[row][col] - 1]) {
                                return false;
                            }
                            visited[grid[row][col] - 1] = true;
                        }
                    }
                }
            }
        }
    
        return true;
    }
    

    
    
    public static boolean solve(int[][] grid) {
        
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(grid, row, col, num)) {
                            grid[row][col] = num;

                            // recursive call to check 
                            if (solve(grid)) {
                                return true;
                            } else {
                                grid[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isValid(int[][] grid, int row, int col, int num) {
        // check row
        for (int c = 0; c < grid[row].length; c++) {
            if (grid[row][c] == num) {
                return false;
            }
        }
        
        // check column
        for (int r = 0; r < grid.length; r++) {
            if (grid[r][col] == num) {
                return false;
            }
        }
        
        // check 3x3 box
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (grid[r + boxRow][c + boxCol] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    
    public static void getSudoku(int[][] grid) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the values of the grid.\t\t");
        System.out.println("Enter \'0\' for blanks");

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                // System.out.println("Enter the value for the cell at row " + (i+1) + " and column " + (j+1) + ":");
                grid[i][j] = sc.nextInt();
            }
        }

        System.out.println("\n");

        sc.close();
    }

    
    public static void main(String[] args) {
        
        int[][] grid = new int[9][9];
        getSudoku(grid);
        
        if(isValidInput(grid)) {
            System.out.println("This is the valid one! Preparing to solve it.");
        
            if (solve(grid)) {
                for (int row = 0; row < grid.length; row++) {
                    for (int col = 0; col < grid[row].length; col++) {
                        System.out.print(grid[row][col] + " ");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solution found.");
            }
            
        } else {
            System.out.println("This is not the valid input!");
        }
        
        return;
    }
}
