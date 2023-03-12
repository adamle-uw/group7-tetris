package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ButtonsPanel extends JPanel implements ItemListener {
    static boolean myIsPaused;
    JPanel myButtonsPanel;
    private JButton myStart;
    private JButton myPause;
    private JButton myEnd;
    private int myUserWidth;
    public static boolean myGameStart;
    private static final int FOUR = 4;
    static JLabel myMusicLabel;
    static JComboBox myMusicCheckBox;
    private static String selectedMusic = "";

    public ButtonsPanel(final Timer theTimer) {
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        myUserWidth = (int) size.getWidth();
        myButtonsPanel = new JPanel();
        myButtonsPanel.setLayout(new FlowLayout());
        this.myButtonsPanel.setBackground(Color.WHITE);
        this.myButtonsPanel.setPreferredSize(new Dimension(myUserWidth / FOUR, myUserWidth / FOUR));
        myStart = new JButton("Start");
        myPause = new JButton("Pause");
        myEnd = new JButton("End");
        myIsPaused = false;
        myStart.addActionListener(e -> {
            myGameStart = true;
        });
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
       myEnd.addActionListener(e -> {
           theTimer.stop();

       });
       String musicChoices[] = {""};

       myMusicCheckBox = new JComboBox(musicChoices);
        ItemListener musicListener = null;
        myMusicCheckBox.addItemListener(null);
       myMusicLabel = new JLabel("Choose your music");


        myButtonsPanel.add(myStart);
        myButtonsPanel.add(myPause);
        myButtonsPanel.add(myEnd);
        myButtonsPanel.add(myMusicLabel);
        myButtonsPanel.add(myMusicCheckBox);

    }

    public ButtonsPanel(JPanel myButtonsPanel) {
        this.myButtonsPanel = myButtonsPanel;
    }

    public ButtonsPanel() {

    }


    public JPanel getMyButtonsPanel() {
        return myButtonsPanel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == myMusicCheckBox) {
            selectedMusic = myMusicCheckBox.getSelectedItem().toString();
        }
    }

    public static String getSelectedMusic() {
        return selectedMusic;
    }

}
