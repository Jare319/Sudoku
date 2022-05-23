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
    of eachother. Then, program will iterate through every index; for each index it will increment it, check to see if the value is valid, and repeat
    this until either a valid number is achieved, or all 9 possibilities are tried, in which case the program will jump to the previous index and repeat.
    */
    
    private int[][] displayPuzzle = new int[9][9]; //new array to contain the generated, incomplete puzzle.
    private int[][] completePuzzle = new int[9][9]; //new array to contain the completed puzzle which can be compared aginst when user is solving.

    Sudoku() {
        generatePuzzle();
    }

    public void generatePuzzle() {
        generateUnitRandom(0);
        generateUnitRandom(4);
        generateUnitRandom(8);

        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                while (!checkValidity(completePuzzle[i][j], i, j)) {
                    completePuzzle[i][j]++;
                    if (completePuzzle[i][j] > 9) {
                        completePuzzle[i][j] = 1;
                    }
                    count++;
                    if (count > 9) {
                        count = 0;
                        completePuzzle[i][j] = 0;
                        j--;
                        if (j < 0) {
                            j = 8;
                            i--;
                        }
                    }
                }
            }
        }

        printPuzzle();
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
        return checkRow(val,row,col) && checkCol(val,row,col) && checkUnit(val,row,col);
    }

    public boolean checkRow(int value, int row, int col) {
        boolean valid = false;
        return valid;
    }

    public boolean checkCol(int value, int row, int col) {
        boolean valid = false;
        return valid;
    }

    public boolean checkUnit(int value, int row, int col) {
        boolean valid = false;
        return valid;
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
