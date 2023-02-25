package view;
import java.awt.*;
import javax.swing.*;


public class Main extends JPanel {
    public static void main(final String[] theArgs) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        final int userWidth =  (int) size.getWidth();
        final int userHeight = (int) size.getHeight();
        frame.setSize(userWidth, userHeight);
        frame.setVisible(true);

        //panels
        final JPanel tetrisBoard = new JPanel();
        final JPanel nextPiece = new JPanel();
        final JPanel userInfo = new JPanel();
        tetrisBoard.setBackground(Color.red);
        nextPiece.setBackground(Color.blue);
        userInfo.setBackground(Color.green);
    }
}
