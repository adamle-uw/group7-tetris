package view;

import model.Board;

import javax.swing.JPanel;
public class Main extends JPanel {
    private static final int FOUR = 4;
    public static void main(final String[] theArgs) {
        final GameGUI g = new GameGUI();
        g.start();
        final Board b = new Board();
        b.addPropertyChangeListener(g);
    }

}
