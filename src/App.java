public class App {
    public static void main(String[] args) throws Exception {
        Sudoku s = new Sudoku();
        s.printPuzzle(s.displayPuzzle);
        s.printPuzzle(s.completePuzzle);
    }
}
