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

    public void generateUnit(int unit) {
        int row = 0;
        int col = 0;
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
