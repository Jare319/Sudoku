import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public class SudokuGUI {

    Cell[][] cellArr = new Cell[9][9];
    
    SudokuGUI() {
        Sudoku s = new Sudoku();
        s.printPuzzle(Sudoku.completePuzzle);
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
                setCells();
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

        topMainPanel.setLayout(new GridLayout(3,3));
        for (int i = 0; i < 9; i++) {
            JPanel p = new JPanel();
            p.setBackground(Color.BLACK);
            p.setLayout(new GridLayout(3,3));
            for (int j = 0; j < 9; j++) {
                cellArr[(i/3)*3][(j%3)*3] = new Cell((i/3)*3,(j%3)/3);
                System.out.println(i + " " + j);
                p.add(cellArr[(i/3)*3][(j%3)*3]);
            }
            topMainPanel.add(p);
            
        }
        setCells();
        f.pack();
        f.setVisible(true);
    }

    public void setCells() {
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
                Cell selectedCell = (Cell)KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
                if (selectedCell.editable) {
                    String value = Character.toString(e.getKeyChar());
                    selectedCell.setText(value);
                    if (Integer.parseInt(value) != Sudoku.completePuzzle[selectedCell.row][selectedCell.col]) {
                        selectedCell.setForeground(Color.RED);
                        selectedCell.setBackground(new Color(255, 166, 166));
                    }
                    else {
                        selectedCell.setForeground(Color.black);
                        selectedCell.setBackground(new Color(170, 255, 166));
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cellArr[i][j].setForeground(Color.black);
                cellArr[i][j].addActionListener(cellSelect);
                cellArr[i][j].addKeyListener(cellFill);
                cellArr[i][j].setBackground(Color.white);
                cellArr[i][j].setFocusPainted(false);
                if (Sudoku.displayPuzzle[i][j] == 0) {
                    cellArr[i][j].setText("");
                    cellArr[i][j].setFont(new Font("Monospaced",Font.PLAIN,40));
                }
                else {
                    cellArr[i][j].setText(Integer.toString(Sudoku.displayPuzzle[i][j]));
                    cellArr[i][j].setFont(new Font("Monospaced",Font.BOLD,40));
                    cellArr[i][j].editable = false;
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
