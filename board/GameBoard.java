package board;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import cylinder.*;

public class GameBoard {

    private JPanel board;
    private final Square[][] c1squares = new Square[6][3];

    public GameBoard() {
        initializeGui();
    }

    public final void initializeGui() {

        for (int i = 0; i < c1squares.length; i++) 
        for (int j = 0; j < c1squares[i].length; j++) {

            Square square = new Square();

            if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) 
                square.setBackground(Color.WHITE);
            else 
                square.setBackground(Color.BLACK);

            c1squares[i][j] = square;
        }

        board = new BoardPanel(c1squares);
        board.setBorder(new EmptyBorder(5, 5, 5, 5));

    }

    public final JComponent getGui() {
        return board;
    }

   
}
