/*
 * TCSS 305 - Winter 2023
 * Final Group Project - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This program defines the behavior and methods contained in objects of the UserInfo class.
 *
 * @author Evan Abrahamson
 * @author Aryan Damle
 * @author Martha Emerson
 * @author Keegan Sanders
 * @version Winter 2023
 */
public class UserInfo implements PropertyChangeListener {
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FOUR = 4;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FIVE = 5;
    /**
     * A constant multiplier to calculate points when 1 line is cleared.
     */
    private static final int FORTY = 40;
    /**
     * A constant multiplier to calculate points when 2 lines are cleared.
     */
    private static final int ONE_HUNDRED = 100;
    /**
     * A constant multiplier to calculate points when 3 lines are cleared.
     */
    private static final int THREE_HUNDRED = 300;
    /**
     * A constant multiplier to calculate points when 4 lines are cleared.
     */
    private static final int TWELVE_HUNDRED = 1200;
    /**
     * A constant multiplier to calculate points when 4 lines are cleared.
     */
    private static final int EIGHTY = 80;
    /**
     * A constant multiplier to calculate points when 4 lines are cleared.
     */
    private static final int ONE_HUNDRED_TWENTY = 120;
    /**
     * A constant multiplier to calculate points when 4 lines are cleared.
     */
    private static final int ONE_HUNDRED_SIXTY = 160;
    /**
     * The font size.
     */
    private static final int FONT_SIZE = 40;
    /**
     * Starting level for the game.
     */
    private static final int BASE_LEVEL = 1;
    /**
     * Label for a New Piece being created in property listeners.
     */
    private static final String NEW_PIECE_PROPERTY = "NewPieceCreate";
    /**
     * The number of cleared lines needed to reach the next level.
     */
    private static final int LINES_TO_LEVEL_UP = 5;
    /**
     * The multiplier of how much each level speeds up the game.
     */
    private static final double LEVEL_SPEEDUP_RATE = 1.2;
    /**
     * The property value for Row Clear Change.
     */
    private static final String ROW_CLEAR = "Row Location";
    /**
     * The String constant for Points.
     */
    private static final String POINTS = "<html>Points: ";
    /**
     * The String constant for Level.
     */
    private static final String LEVEL = "<br/><br/>Level: ";
    /**
     * The String constant for Lines Cleared.
     */
    private static final String LINES_CLEARED = "<br/><br/>Lines Cleared: ";
    /**
     * The String constant for Lines until Next Level.
     */
    private static final String LINES_UNTIL_NEXT_LEVEL = "<br/><br/>Lines until Next Level: ";
    /**
     * The panel that displays user info.
     */
    private final JPanel myUserInfo;
    /**
     * The total points earned by the user.
     */
    private int myUserPoints;
    /**
     * The number of lines cleared by the user.
     */
    private int myLinesCleared;
    /**
     * The timer delay.
     */
    private int myTimerTick;
    /**
     * The difficulty level.
     */
    private int myLevel;
    /**
     * Label for the User Info text.
     */
    private final JLabel myLabel;
    /**
     * The temporarily held lines cleared in order to count total lines.
     */
    private int myTempLinesCleared = myLinesCleared;
    /**
     * The points from the previous tick.
     */
    private int myLastPoints;



    /**
     * A constructor for the class UserInfo.
     *
     * @param theUserWidth  the width of the user's computer monitor.
     * @param theUserHeight the height of the user's computer monitor.
     * @param theTimerTick  the current Timer tick rate.
     */
    public UserInfo(final int theUserWidth, final int theUserHeight, final int theTimerTick) {
        myUserInfo = new JPanel();
        this.myUserInfo.setBackground(Color.green);
        this.myUserInfo.addPropertyChangeListener(this);
        myUserInfo.setPreferredSize(new Dimension(theUserWidth / FOUR, theUserHeight));
        myUserPoints = 0;
        setTimerTick(theTimerTick);
        myUserInfo.setLayout(new BoxLayout(myUserInfo, BoxLayout.X_AXIS));
        myLabel = new JLabel(POINTS + myUserPoints
                + LEVEL + myLevel + LINES_CLEARED
                + myLinesCleared + LINES_UNTIL_NEXT_LEVEL
                + (FIVE - (myLinesCleared % FIVE)), JLabel.CENTER);
        myLabel.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
        myUserInfo.add(myLabel);
    }

    private void setTimerTick(final int theTimerTick) {
        myTimerTick = theTimerTick;
        myLevel = BASE_LEVEL;
        for (int i = 1; i < myLevel; i++) {
            myTimerTick = (int) ((double) myTimerTick / LEVEL_SPEEDUP_RATE);
            System.out.println(myTimerTick);
        }
    }

    /**
     * Returns the user info.
     *
     * @return the user info.
     */
    public JPanel getUserInfo() {
        return myUserInfo;
    }

    /**
     * Returns the current Timer tick rate.
     *
     * @return the current Timer tick rate.
     */
    public int getTimerTick() {
        return myTimerTick;
    }

    /**
     * ...
     *
     * @param theEvent A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (ROW_CLEAR.equals(theEvent.getPropertyName())) {
            myLinesCleared += 1;
            this.myLabel.setText(POINTS + myUserPoints
                    + LEVEL + myLevel + LINES_CLEARED
                    + myLinesCleared  + LINES_UNTIL_NEXT_LEVEL
                    + (LINES_TO_LEVEL_UP - (myLinesCleared % LINES_TO_LEVEL_UP)));
            if ((myLinesCleared % LINES_TO_LEVEL_UP) == 0 && myLinesCleared != 0) {
                myLevel++;
                myTimerTick = (int) ((double) myTimerTick / LEVEL_SPEEDUP_RATE);
            }
        }
        if (NEW_PIECE_PROPERTY.equals(theEvent.getPropertyName())) {
            myUserPoints += FOUR;
            myLastPoints = myUserPoints;
            this.myLabel.setText(POINTS + myUserPoints
                    + LEVEL + myLevel + LINES_CLEARED
                    + myLinesCleared + LINES_UNTIL_NEXT_LEVEL
                    + (LINES_TO_LEVEL_UP - (myLinesCleared % LINES_TO_LEVEL_UP)));
        }
        if (myLinesCleared == myTempLinesCleared + 1) {
            myUserPoints += FORTY * myLevel;
            myTempLinesCleared = myLinesCleared;
        }
        if (myLastPoints == myUserPoints - (ONE_HUNDRED_SIXTY * myLevel)) {
            myUserPoints -= ONE_HUNDRED_SIXTY * myLevel;
            myUserPoints += TWELVE_HUNDRED * myLevel;
            myLastPoints = myUserPoints;
        } else if (myLastPoints == myUserPoints - (ONE_HUNDRED_TWENTY * myLevel)) {
            myUserPoints -= ONE_HUNDRED_TWENTY * myLevel;
            myUserPoints += THREE_HUNDRED * myLevel;
            myLastPoints = myUserPoints;
        } else if (myLastPoints == myUserPoints - (EIGHTY * myLevel)) {
            myUserPoints -= EIGHTY * myLevel;
            myUserPoints += ONE_HUNDRED * myLevel;
            myLastPoints = myUserPoints;
        }

    }
}
