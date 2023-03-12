package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ButtonsPanel extends JPanel {
    static boolean myIsPaused;
    private final JPanel myButtonsPanel;
    private JButton myStart;
    private JButton myPause;
    private int myUserWidth;
    private static final int FOUR = 4;

    public ButtonsPanel(final Timer theTimer) {
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();


        myUserWidth = (int) size.getWidth();
        myButtonsPanel = new JPanel();
        this.myButtonsPanel.setBackground(Color.WHITE);
        this.myButtonsPanel.setPreferredSize(new Dimension(myUserWidth / FOUR, myUserWidth / FOUR));
        myStart = new JButton("Start");
        myPause = new JButton("Pause");
        myIsPaused = false;
        myStart.addActionListener(e -> new GameGUI().start());
       myPause.addActionListener(e -> {
            if (myIsPaused) {
                theTimer.start();
                myPause.setText("Pause");
                myIsPaused = false;
            } else {
                theTimer.stop();
                myPause.setText("Unpause");
                myIsPaused = true;
            }
        });

        myButtonsPanel.add(myStart);
        myButtonsPanel.add(myPause);
    }

    public JPanel getMyButtonsPanel() {
        return myButtonsPanel;
    }
}
