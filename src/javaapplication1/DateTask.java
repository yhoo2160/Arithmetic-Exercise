/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.GridBagConstraints;
import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author yhoo2160
 */
public class DateTask extends TimerTask {

    @Override
    public void run() {
        if (parameterClass.time <= 0) {
            try {
                ButtonDemo.countingScore();
            } catch (IOException ex) {
                Logger.getLogger(DateTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            parameterClass.time--;

            int tmp;
            int min;
            int sec;

            sec = parameterClass.time % 60;
            tmp = parameterClass.time - sec;
            min = tmp / 60;

            parameterClass.timeLable.setText(min + ":" + sec);
            
            //parameterClass.topWin.add(parameterClass.timeLable, c1);
            parameterClass.topWin.setVisible(true);
        }

    }
}
