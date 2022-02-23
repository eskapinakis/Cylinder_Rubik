package board;

import java.awt.Color;

public class Square {

    boolean isSelected;
    Color background;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(final boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(final Color background) {
        this.background = background;
    }

}

