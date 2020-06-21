/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.GridBagLayout;
import java.util.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author yhoo2160
 */
public class parameterClass {
    public static int time = 180;
    public static JFrame topWin = new JFrame();
    public static JPanel panel = new JPanel((new GridBagLayout()));
    public static JPanel panel_west = new JPanel((new GridBagLayout()));
    public static JScrollPane scrollPane;
    public static Timer timer;
    public static int[] num1 = null;
    public static int[] num2 = null;
    public static int[] ans = null;
    public static int[] ans_flg = null;
    public static JTextField ansarea[] = null;
    public static JTextField name = null;
    public static JLabel score = new JLabel();
    public static JLabel timeLable = new JLabel();
    public static JButton endButton = new JButton("End");
    public static JButton startButton = new JButton("Start");
    public static JButton backButton = new JButton("Back");
    
}
