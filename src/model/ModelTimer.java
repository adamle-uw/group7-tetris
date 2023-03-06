package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class ModelTimer implements ActionListener {

   private Board myBoard;
   public ModelTimer(final Board theBoard) {
       super();
       myBoard = theBoard;
   }

   @Override
    public void actionPerformed(final ActionEvent theEvent) {
       myBoard.step();
   }



}
