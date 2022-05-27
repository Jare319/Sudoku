import javax.swing.*;
import java.awt.*;

public class SudokuGUI {
    
    SudokuGUI() {
        JFrame f = new JFrame("Sudoku");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(new Dimension(600,800));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
        f.setJMenuBar(menuBar);

        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));

        JPanel topMainPanel = new JPanel();
        topMainPanel.setPreferredSize(new Dimension(600,600));
        topMainPanel.setBackground(Color.BLACK);
        f.add(topMainPanel);

        JPanel botMainPanel = new JPanel();
        botMainPanel.setPreferredSize(new Dimension(600,200));
        f.add(botMainPanel);

        JButton[][] buttonArr = new JButton[9][9];
        topMainPanel.setLayout(new GridLayout(9,9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Sudoku.displayPuzzle[i][j] == 0) {
                    buttonArr[i][j] = new JButton();
                }
                else {
                    buttonArr[i][j] = new JButton(Integer.toString(Sudoku.displayPuzzle[i][j]));
                }
                buttonArr[i][j].setBackground(Color.white);
                buttonArr[i][j].setFont(new Font("Monospaced",Font.BOLD,30));
                topMainPanel.add(buttonArr[i][j]);
            }
        }

        f.pack();
        f.setVisible(true);
    }
    
    
    

}
