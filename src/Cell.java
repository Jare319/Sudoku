import javax.swing.JButton;

public class Cell extends JButton {
    
    public int row;
    public int col;
    public boolean selected = false;
    public boolean editable = true;

    Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

}
