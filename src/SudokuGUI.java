import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public class SudokuGUI {

    Cell[][] cellArr = new Cell[9][9];
    
    SudokuGUI() {
        Sudoku s = new Sudoku();
        JFrame f = new JFrame("Sudoku");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(new Dimension(600,800));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem newPuzzle = new JMenuItem(new AbstractAction("New Puzzle") {

            @Override
            public void actionPerformed(ActionEvent e) {
                s.generatePuzzle();
                updateCells();
            }
            
        });
        menu.add(newPuzzle);
        menuBar.add(menu);
        f.setJMenuBar(menuBar);

        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));

        JPanel topMainPanel = new JPanel();
        topMainPanel.setPreferredSize(new Dimension(600,600));
        f.add(topMainPanel);

        JPanel botMainPanel = new JPanel();
        botMainPanel.setPreferredSize(new Dimension(600,200));
        f.add(botMainPanel);

        ActionListener cellSelect = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetSelected();
                Cell selectedCell = (Cell)e.getSource();
                selectedCell.setBackground(new Color(178, 205, 219));
                selectedCell.selected = true;
            }
            
        };

        KeyListener cellFill = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        };

        topMainPanel.setLayout(new GridLayout(3,3));
        for (int i = 0; i < 9; i++) {
            JPanel p = new JPanel();
            p.setBackground(Color.BLACK);
            p.setLayout(new GridLayout(3,3));
            for (int j = 0; j < 9; j++) {
                cellArr[i][j] = new Cell(i,j);
                cellArr[i][j].addActionListener(cellSelect);
                cellArr[i][j].setBackground(Color.white);
                cellArr[i][j].setFocusPainted(false);
                cellArr[i][j].setFont(new Font("Monospaced",Font.BOLD,30));
                if (Sudoku.displayPuzzle[i][j] == 0) {
                    cellArr[i][j].setText("");
                }
                else {
                    cellArr[i][j].setText(Integer.toString(Sudoku.displayPuzzle[i][j]));
                }
                p.add(cellArr[i][j]);
            }
            topMainPanel.add(p);
            
        }

        f.addKeyListener(cellFill);
        f.pack();
        f.setVisible(true);
    }

    public void updateCells() {
        resetSelected();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Sudoku.displayPuzzle[i][j] == 0) {
                    cellArr[i][j].setText("");
                }
                else {
                    cellArr[i][j].setText(Integer.toString(Sudoku.displayPuzzle[i][j]));
                }
            }
        }
    }

    public void resetSelected() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cellArr[i][j].setBackground(Color.white);
                cellArr[i][j].selected = false;
            }
        } 
    }
    
    
    

}
