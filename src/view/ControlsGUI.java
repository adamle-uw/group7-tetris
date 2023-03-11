package view;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ControlsGUI {
    /**
     * ArrayList of Buttons (butts).
     */
    private final ArrayList<Button> myButts = new ArrayList<>();
    /**
     * Dimension variable for window size.
     */
    private final Dimension mySize = new Dimension(
            (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4,
            (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4);
    /**
     * int value for font size.
     */
    private final int myFontSize = (int) (mySize.getWidth() / 10);

    /**
     * Instance of KeyListener.
     */
    public ControlsGUI() {
        loadControlsGui();
    }

    void loadControlsGui() {
        SwingUtilities.invokeLater(() -> {
            final Font newButtonFont = new Font("Controls", Font.PLAIN, myFontSize);
            final ControlsKeyListener kList = new ControlsKeyListener();
            final JFrame controlsFrame = new JFrame("Controls Menu");
            final JPanel controlsPanel = new JPanel(new GridLayout(2, 3));
            controlsFrame.setMinimumSize(mySize);
            myButts.add(new Button("W"));
            myButts.add(new Button("A"));
            myButts.add(new Button("S"));
            myButts.add(new Button("D"));
            myButts.forEach(butt -> butt.addKeyListener(kList));
            myButts.forEach(butt -> butt.setFont(newButtonFont));
            controlsPanel.add(new Box(1));
            controlsPanel.add(myButts.get(0));
            controlsPanel.add(new Box(1));
            controlsPanel.add(myButts.get(1));
            controlsPanel.add(myButts.get(2));
            controlsPanel.add(myButts.get(3));

            controlsFrame.add(controlsPanel);
            controlsFrame.setVisible(true);
        });
    }

    private void pressButt(final String theButtKey, final String theButtControl,
                           final int theCount) {
        for (Button butt : myButts) {
            if (butt.getLabel().equals(theButtKey) && theCount == 0) {
                butt.setLabel(theButtControl);
            } else if (butt.getLabel().equals(theButtControl) && theCount == 1) {
                butt.setLabel(theButtKey);
            }
        }
    }
    private final class ControlsKeyListener extends KeyAdapter {
        /**
         * Map of Keys.
         */
        private final Map<Integer, Runnable> myKeyMap;
        /**
         * Int that increments per KeyEvent.
         */
        private int myCount;

        private ControlsKeyListener() {
            myKeyMap = new HashMap<>();
            mapKeys();
        }

        private void mapKeys() {
            myKeyMap.put(KeyEvent.VK_W, () -> this.keyPress("W", "Rotate"));
            myKeyMap.put(KeyEvent.VK_A, () -> this.keyPress("A", "Left"));
            myKeyMap.put(KeyEvent.VK_S, () -> this.keyPress("S", "Drop"));
            myKeyMap.put(KeyEvent.VK_D, () -> this.keyPress("D", "Right"));
        }
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (myKeyMap.containsKey(theEvent.getKeyCode())) {
                myCount = 0;
                myKeyMap.get(theEvent.getKeyCode()).run();
            }
        }

        @Override
        public void keyReleased(final KeyEvent theEvent) {
            if (myKeyMap.containsKey(theEvent.getKeyCode())) {
                myCount = 1;
                myKeyMap.get(theEvent.getKeyCode()).run();
            }
        }

        private void keyPress(final String theKey, final String theControl) {
            pressButt(theKey, theControl, myCount);
        }
    }
}
