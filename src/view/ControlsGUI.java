/*
 * TCSS 305 - Winter 2023
 * Final Group Project - Tetris
 */

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

/**
 * This program defines the behavior and methods contained in objects of the ControlsGUI class.
 *
 * @author Evan Abrahamson
 * @author Aryan Damle
 * @author Martha Emerson
 * @author Keegan Sanders
 * @version Winter 2023
 */
public class ControlsGUI {
    /**
     * Constant for adding a third button.
     */
    private static final int THREE = 3;
    /**
     * The String representation of pressing the A key.
     */
    private static final String KEY_PRESS_A = "A";
    /**
     * The String representation of pressing the D key.
     */
    private static final String KEY_PRESS_D = "D";
    /**
     * The String representation of pressing the S key.
     */
    private static final String KEY_PRESS_S = "S";
    /**
     * The String representation of pressing the W key.
     */
    private static final String KEY_PRESS_W = "W";
    /**
     * The Menu label for Controls.
     */
    private static final String CONTROLS = "Controls";
    /**
     * The Menu label for the Controls Menu.
     */
    private static final String CONTROLS_MENU = "Controls Menu";
    /**
     * The String representation of pressing the down arrow key.
     */
    private static final String DOWN = "Down";
    /**
     * The String representation of pressing the left arrow key.
     */
    private static final String LEFT = "Left";
    /**
     * The String representation of pressing the right arrow key.
     */
    private static final String RIGHT = "Right";
    /**
     * The String representation of pressing the up arrow key.
     */
    private static final String ROTATE = "Rotate";
    /**
     * The ArrayList of Buttons.
     */
    private final ArrayList<Button> myButts = new ArrayList<>();
    /**
     * The dimensions (width and height) of the Window.
     */
    private final Dimension mySize = new Dimension(
            (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4,
            (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4);
    /**
     * The font size for the text in the Controls menu.
     */
    private final int myFontSize = (int) (mySize.getWidth() / 10);

    /**
     * A constructor for class ControlsGUI.
     */
    public ControlsGUI() {
        loadControlsGui();
    }

    /**
     * Loads the GUI Controls.
     */
    void loadControlsGui() {
        SwingUtilities.invokeLater(() -> {
            final Font newButtonFont = new Font(CONTROLS, Font.PLAIN, myFontSize);
            final ControlsKeyListener kList = new ControlsKeyListener();
            final JFrame controlsFrame = new JFrame(CONTROLS_MENU);
            final JPanel controlsPanel = new JPanel(new GridLayout(2, 3));

            controlsFrame.setMinimumSize(mySize);

            myButts.add(new Button(KEY_PRESS_W));
            myButts.add(new Button(KEY_PRESS_A));
            myButts.add(new Button(KEY_PRESS_S));
            myButts.add(new Button(KEY_PRESS_D));

            myButts.forEach(butt -> butt.addKeyListener(kList));
            myButts.forEach(butt -> butt.setFont(newButtonFont));

            controlsPanel.add(new Box(1));
            controlsPanel.add(myButts.get(0));
            controlsPanel.add(new Box(1));
            controlsPanel.add(myButts.get(1));
            controlsPanel.add(myButts.get(2));
            controlsPanel.add(myButts.get(THREE));

            controlsFrame.add(controlsPanel);
            controlsFrame.setVisible(true);
        });
    }

    /**
     * ...
     *
     * @param theButtKey        ...
     * @param theButtControl    ...
     * @param theCount          ...
     */
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

    /**
     * ...
     */
    private final class ControlsKeyListener extends KeyAdapter {
        /**
         * A Map of Keys.
         */
        private final Map<Integer, Runnable> myKeyMap;
        /**
         * Counter for the number of KeyEvents.
         */
        private int myCount;

        /**
         * Creates a HashMap of KeyListener controls.
         */
        private ControlsKeyListener() {
            myKeyMap = new HashMap<>();
            mapKeys();
        }

        /**
         * Inserts KeyEvents into myKeyMap.
         */
        private void mapKeys() {
            myKeyMap.put(KeyEvent.VK_W, () -> this.keyPress(KEY_PRESS_W, ROTATE));
            myKeyMap.put(KeyEvent.VK_A, () -> this.keyPress(KEY_PRESS_A, LEFT));
            myKeyMap.put(KeyEvent.VK_S, () -> this.keyPress(KEY_PRESS_S, DOWN));
            myKeyMap.put(KeyEvent.VK_D, () -> this.keyPress(KEY_PRESS_D, RIGHT));
        }

        /**
         * ...
         *
         * @param theEvent the event to be processed.
         */
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (myKeyMap.containsKey(theEvent.getKeyCode())) {
                myCount = 0;
                myKeyMap.get(theEvent.getKeyCode()).run();
            }
        }

        /**
         * Changes the state when the key is released.
         *
         * @param theEvent the event to be processed
         */
        @Override
        public void keyReleased(final KeyEvent theEvent) {
            if (myKeyMap.containsKey(theEvent.getKeyCode())) {
                myCount = 1;
                myKeyMap.get(theEvent.getKeyCode()).run();
            }
        }

        /**
         * Passes information from the inner class to the outer class.
         *
         * @param theKey        the key that is being typed.
         * @param theControl    the second value for what each key is.
         */
        private void keyPress(final String theKey, final String theControl) {
            pressButt(theKey, theControl, myCount);
        }
    }
}
