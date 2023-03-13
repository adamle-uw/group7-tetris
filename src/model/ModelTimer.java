package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ModelTimer implements ActionListener {
    /**
     * Board instance.
     */
    private final Board myBoard;
    public ModelTimer(final Board theBoard) {
        super();
        myBoard = theBoard;
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myBoard.step();
        System.out.print(myBoard);
    }
}
