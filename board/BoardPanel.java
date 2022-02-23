package board;

import java.awt.Graphics;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	Square[][] squares;

    public BoardPanel(final Square[][] squares) {
        this.squares = squares;
    }

    
    public void paintComponent(final Graphics g) {

        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < squares.length; i++)
        for (int j = 0; j < squares[i].length; j++) {
	        Square currentSquare = squares[i][j];
	        g.setColor(currentSquare.getBackground());
	        g.fillRect(i * width / squares.length, j * height / squares.length, width / squares.length,
	                height / squares.length);
        }

    }

}