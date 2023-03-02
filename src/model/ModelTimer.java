package model;

import java.awt.event.ActionEvent;
import javax.swing.Timer;


public class ModelTimer extends Board {

    /**Timer controls how often step() is called.*/
    private final Timer myTimer;

    public ModelTimer(Timer myTimer) {
        this.myTimer = new Timer(60, this::handleTimer);
    }


    private void handleTimer(final ActionEvent theEvent) {
        step();
    }



}
