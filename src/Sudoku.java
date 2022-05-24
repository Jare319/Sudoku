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
    
    private int[][] displayPuzzle = new int[9][9]; //new array to contain the generated, incomplete puzzle.
    //private int[][] completePuzzle = new int[9][9]; //new array to contain the completed puzzle which can be compared aginst when user is solving.
    private int[][] completePuzzle = {{0,0,0,1,0,4,0,6,8},{6,0,0,3,0,0,2,1,9},{1,2,0,0,8,9,4,3,5},{4,0,0,2,0,3,0,8,1},{8,1,0,0,9,0,0,2,4},{0,0,2,0,1,8,0,7,0},{9,0,0,5,0,0,1,0,3},{2,4,0,0,3,0,0,5,0},{0,0,0,8,0,1,0,0,2}};
    public int count = 0;

    Sudoku() {
        generatePuzzle();
    }

    public void generatePuzzle() {
        //generateUnitRandom(0);
        //generateUnitRandom(4);
        //generateUnitRandom(8);

        solve();
        System.out.print(count);
        //printPuzzle();
    }

    public void generateUnitRandom(int unit) { //Generates a unit (without respect to other units) and adds it to the completed puzzle.
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
            completePuzzle[(i/3) + row][(i%3) + col] = values.get(i);
        }
    }

    public boolean checkValidity(int val, int row, int col) {
        //System.out.println(checkRow(val,row) && checkCol(val,col) && checkUnit(val,row,col));
        return checkRow(val,row) && checkCol(val,col) && checkUnit(val,row,col);
    }

    public boolean checkRow(int value, int row) {
        boolean valid = false;
        int occurences = 0;
        for (int i = 0; i < 9; i++) {
            if (value == completePuzzle[row][i]) {
                occurences++;
            }
        }
        if (occurences == 0) {
            valid = true;
        }
        return valid;
    }

    public boolean checkCol(int value, int col) {
        boolean valid = false;
        int occurences = 0;
        for (int i = 0; i < 9; i++) {
            if (value == completePuzzle[i][col]) {
                occurences++;
            }
        }
        if (occurences == 0) {
            valid = true;
        }
        return valid;
    }

    public boolean checkUnit(int value, int row, int col) {
        boolean valid = false;
        int occurences = 0;
        for (int i = (row/3)*3; i < ((row/3)*3)+3;i++) {
            for (int j = (col/3)*3; j < ((col/3)*3)+3;j++) {
                if (value == completePuzzle[i][j]) {
                    occurences++;
                }
            }
        }
        if (occurences == 0) {
            valid = true;
        }
        return valid;
    }

    public boolean solve() {
        int row = -1;
        int col = -1;

        if (isSolved()) {
            count++;
            return true;
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (completePuzzle[i][j] == 0) {
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
            if (checkValidity(i, row, col)) {
                completePuzzle[row][col] = i;
                if (solve()) {
                    return true;
                }
                else {
                    completePuzzle[row][col] = 0;
                }
            }
        }
        return false;
    }

    public boolean isSolved() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (completePuzzle[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printPuzzle() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(completePuzzle[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
