import java.util.*;

public class Sudoku {

    /*  Each individual 3x3 is called a unit, where units are labeled 0 - 8 starting from the top left corner, and ending in the bottom right.
    -------------------------
    | 0 0 0 | 1 1 1 | 2 2 2 |
    | 0 0 0 | 1 1 1 | 2 2 2 |
    | 0 0 0 | 1 1 1 | 2 2 2 |
    -------------------------
    | 3 3 3 | 4 4 4 | 5 5 5 |
    | 3 3 3 | 4 4 4 | 5 5 5 |
    | 3 3 3 | 4 4 4 | 5 5 5 |
    -------------------------
    | 6 6 6 | 7 7 7 | 8 8 8 |
    | 6 6 6 | 7 7 7 | 8 8 8 |
    | 6 6 6 | 7 7 7 | 8 8 8 |
    -------------------------

    To generate a new puzzle with a unique solution the program will generate a full grid of numbers that satisfy sudoku rules. Then, values will be
    removed one at a time. Each time, the puzzle will be resolved by the computer to insure there is still only one unique solution. This process will
    repeat untill enough values are removed to satisfy the specified difficulty.

    To initially generate the full solved puzzle, three diagonal units (0, 4, 8) will be filled with a random value 1-9, as these units are independant
    of eachother. Then, program will iterate through every non-solved index and try to insert numbers 1-9. When one of these is valid, it will recursively
    find the next unsolved index and repeat. If the current recursion thread dead-ends, it will undo the changes one at a time and try another value. 
    */
    
    public int[][] displayPuzzle = new int[9][9]; //new array to contain the generated, incomplete puzzle.
    public int[][] completePuzzle = new int[9][9]; //new array to contain the completed puzzle which can be compared aginst when user is solving.

    Sudoku() {
        generatePuzzle();
    }

    public void generatePuzzle() {
        generateUnitRandom(0, completePuzzle);
        generateUnitRandom(4, completePuzzle);
        generateUnitRandom(8, completePuzzle);

        solve(completePuzzle);
        copyGrid(completePuzzle,displayPuzzle);
        removeValues(56,displayPuzzle);
    }

    public void generateUnitRandom(int unit, int[][] grid) { //Generates a unit (without respect to other units) and adds it to the completed puzzle.
        int row = 0;
        int col = 0;
        ArrayList<Integer> values = new ArrayList<Integer>(List.of(1,2,3,4,5,6,7,8,9));
        Collections.shuffle(values);

        switch (unit) {
            case 0: 
                row = 0;
                col = 0;
                break;
            case 1:
                row = 0;
                col = 3;
                break;
            case 2:
                row = 0;
                col = 6;
                break;
            case 3:
                row = 3;
                col = 0;
                break;
            case 4:
                row = 3;
                col = 3;
                break;
            case 5:
                row = 3;
                col = 6;
                break;
            case 6:
                row = 6;
                col = 0;
                break;
            case 7:
                row = 6;
                col = 3;
                break;
            case 8:
                row = 6;
                col = 6;
                break;
        }

        for (int i = 0; i < 9; i++) {
            grid[(i/3) + row][(i%3) + col] = values.get(i);
        }
    }

    public boolean checkValidity(int val, int row, int col, int[][] grid) {
        //System.out.println(checkRow(val,row) && checkCol(val,col) && checkUnit(val,row,col));
        return checkRow(val,row, grid) && checkCol(val,col, grid) && checkUnit(val,row,col, grid);
    }

    public boolean checkRow(int value, int row, int[][] grid) {
        boolean valid = false;
        int occurences = 0;
        for (int i = 0; i < 9; i++) {
            if (value == grid[row][i]) {
                occurences++;
            }
        }
        if (occurences == 0) {
            valid = true;
        }
        return valid;
    }

    public boolean checkCol(int value, int col, int[][] grid) {
        boolean valid = false;
        int occurences = 0;
        for (int i = 0; i < 9; i++) {
            if (value == grid[i][col]) {
                occurences++;
            }
        }
        if (occurences == 0) {
            valid = true;
        }
        return valid;
    }

    public boolean checkUnit(int value, int row, int col, int[][] grid) {
        boolean valid = false;
        int occurences = 0;
        for (int i = (row/3)*3; i < ((row/3)*3)+3;i++) {
            for (int j = (col/3)*3; j < ((col/3)*3)+3;j++) {
                if (value == grid[i][j]) {
                    occurences++;
                }
            }
        }
        if (occurences == 0) {
            valid = true;
        }
        return valid;
    }

    public boolean solve(int[][] grid) {
        int row = -1;
        int col = -1;

        if (isSolved(grid)) {
            return true;
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    row = i;
                    col = j;
                    break;
                }
            }
            if (row != -1) {
                break;
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (checkValidity(i, row, col, grid)) {
                grid[row][col] = i;
                if (solve(grid)) {
                    return true;
                }
                else {
                    grid[row][col] = 0;
                }
            }
        }
        return false;
    }

    public boolean canSolve(int row, int col, int exclude, int[][] grid) {
        for (int i = 1; i <= 9; i++) {
            if (i == exclude) {
                i++;
            }
            if (checkValidity(i, row, col, grid)) {
                int[][] copy = new int[9][9];
                copyGrid(grid,copy);
                copy[row][col] = i;
                return solve(copy);
            }
        }
        return false;
    }

    public boolean isSolved(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void removeVal(int[][] grid) {
        int row = (int)(Math.random()*9);
        int col = (int)(Math.random()*9);
        while (grid[row][col] == 0) {
            row = (int)(Math.random()*8);
            col = (int)(Math.random()*8);
        }
        int value = grid[row][col];

        if (!canSolve(row,col,value,grid))  {
            grid[row][col] = 0;
        }
        else {
            removeVal(grid);
        }
    }

    public void removeValues(int count, int[][] grid) {
        for (int i = count; i > 0; i--) {
            removeVal(grid);
        }
    }

    public void copyGrid(int[][] copyFrom,int[][] copyTo) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copyTo[i][j] = copyFrom[i][j];
            }
        }
    }

    public void printPuzzle(int[][] grid) {
        System.out.println("\n-------------------------");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j%3 == 0) {
                    System.out.print("| ");
                }
                if (grid[i][j] != 0) {
                    System.out.print(grid[i][j] + " ");
                }
                else {
                    System.out.print("  ");
                }
                if (j == 8) {
                    System.out.print("|");
                }
            }
            if (i == 2 || i == 5) {
                System.out.println("\n|-----------------------|");
            }
            else{
                System.out.println("");
            }
        }
        System.out.println("-------------------------");
    }
}
