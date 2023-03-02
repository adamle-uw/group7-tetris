package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Main extends JPanel {
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int TWO = 2;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FOUR = 4;
    public static void main(final String[] theArgs) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        final int userWidth =  (int) size.getWidth();
        final int userHeight = (int) size.getHeight();
        frame.setSize(userWidth, userHeight);
        frame.setJMenuBar(MenuBar.createFileMenu(frame));

        //panels
        final JPanel tetrisBoard = new JPanel();
        final JPanel nextPiece = new JPanel();
        final JPanel userInfo = new JPanel();
        final JPanel rightRegion = new JPanel();
        tetrisBoard.setBackground(Color.red);
        nextPiece.setBackground(Color.blue);
        userInfo.setBackground(Color.green);
        rightRegion.setLayout(new BorderLayout());

        //make the regions
        frame.add(tetrisBoard, BorderLayout.CENTER);
        frame.add(rightRegion, BorderLayout.EAST);
        frame.add(userInfo, BorderLayout.WEST);
        rightRegion.add(nextPiece, BorderLayout.NORTH);
        tetrisBoard.setPreferredSize(new Dimension(userWidth / TWO, userHeight));
        rightRegion.setPreferredSize(new Dimension(userWidth / FOUR, userHeight));
        nextPiece.setPreferredSize(new Dimension(userWidth / FOUR, userWidth / FOUR));
        userInfo.setPreferredSize(new Dimension(userWidth / FOUR, userHeight));

        frame.setVisible(true);

    }

}
