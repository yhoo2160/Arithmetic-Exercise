/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.pow;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author yhoo2160
 */
public class ButtonDemo extends JFrame implements ActionListener {

    /**
     * @param args the command line arguments
     */
    JFrame startWin = new JFrame();
    JButton modeButton1 = new JButton("加法4級");
    JButton modeButton2 = new JButton("加法5級");
    JButton modeButton3 = new JButton("加法 - 進階題");
    JButton modeButton4 = new JButton("減法4級");
    JButton modeButton5 = new JButton("減法5級");
    JButton modeButton6 = new JButton("減法 - 進階題");
    JButton modeButton7 = new JButton("乘法3級");
    JButton modeButton8 = new JButton("乘法4級");
    JButton modeButton9 = new JButton("乘法5級");
    JButton modeButton10 = new JButton("乘法 - 進階題");
    JButton modeButton11 = new JButton("除法2級");
    JButton modeButton12 = new JButton("除法3級");
    JButton modeButton13 = new JButton("除法4級");
    JButton modeButton14 = new JButton("除法5級");
    static String symbol;
    static int numberofquestions = 200;

    //JFrame topWin = new JFrame();
    public static void main(String[] args) {
        // TODO code application logic here
        ButtonDemo buttonDemo = new ButtonDemo();

        //讓方向鍵-下 會切換FOCUS到下一個可以FOCUS的目標
        Set<AWTKeyStroke> set = new HashSet<>(KeyboardFocusManager.getCurrentKeyboardFocusManager().getDefaultFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        set.add(KeyStroke.getKeyStroke("DOWN"));
        KeyboardFocusManager.getCurrentKeyboardFocusManager().setDefaultFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, set);
        //讓方向鍵-下 會切換FOCUS到下一個可以FOCUS的目標
        Set<AWTKeyStroke> set2 = new HashSet<>(KeyboardFocusManager.getCurrentKeyboardFocusManager().getDefaultFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS));
        set2.add(KeyStroke.getKeyStroke("UP"));
        KeyboardFocusManager.getCurrentKeyboardFocusManager().setDefaultFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, set2);
    }

    public void start(int mode) {
        startWin.setVisible(false);

        parameterClass.topWin.setSize(720, 720);
        //parameterClass.topWin.setLayout(new GridBagLayout());
        parameterClass.topWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        parameterClass.scrollPane = new JScrollPane(parameterClass.panel);
        Container content = parameterClass.topWin.getContentPane();
        content.add(parameterClass.scrollPane, BorderLayout.CENTER);
        content.add(parameterClass.panel_west, BorderLayout.WEST);

        setUIFont(new FontUIResource("", Font.PLAIN, 25));

        //讓捲著會移動到看的到FOCUS的物件
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (!(evt.getNewValue() instanceof JComponent)) {
                    return;
                }
                JComponent focused = (JComponent) evt.getNewValue();
                if (content.isAncestorOf(focused)) {
                    System.out.println("Scrolling to " + focused.getName());
                    parameterClass.panel.scrollRectToVisible(focused.getBounds());
                }
            }
        });

        //parameterClass.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //parameterClass.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //addUnder10();
        //minusUnder10();
        questionMake(mode);

        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 0;
        //c4.gridy = 0;
        c4.gridy = 0;
        //c4.gridwidth = 16;
        c4.gridwidth = 1;
        c4.gridheight = 1;
        c4.weightx = 1;
        c4.weighty = 1;
        c4.fill = GridBagConstraints.BOTH;
        c4.anchor = GridBagConstraints.WEST;
        //parameterClass.panel.add(parameterClass.startButton, c4);
        parameterClass.panel_west.add(parameterClass.startButton, c4);
        parameterClass.startButton.addActionListener(this);

        GridBagConstraints c2 = new GridBagConstraints();
        //c2.gridx = 20;
        c2.gridx = 0;
        c2.gridy = 1;
        //c2.gridwidth = 16;
        c2.gridwidth = 1;
        c2.gridheight = 1;
        c2.weightx = 1;
        c2.weighty = 1;
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.WEST;
        //parameterClass.panel.add(parameterClass.endButton, c2);
        parameterClass.panel_west.add(parameterClass.endButton, c2);
        parameterClass.endButton.addActionListener(this);

        c2.gridy = 2;
        parameterClass.panel_west.add(parameterClass.backButton, c2);
        parameterClass.backButton.addActionListener(this);

        GridBagConstraints c1 = new GridBagConstraints();
        //c1.gridx = 64;
        //c1.gridy = 15;
        c1.gridx = 0;
        c1.gridy = 3;
        c1.gridwidth = 1;
        c1.gridheight = 1;
        c1.weightx = 1;
        c1.weighty = 1;
        c1.fill = GridBagConstraints.NONE;
        c1.anchor = GridBagConstraints.CENTER;
        parameterClass.panel_west.add(parameterClass.timeLable, c1);

        GridBagConstraints c5 = new GridBagConstraints();
        //c1.gridx = 64;
        //c1.gridy = 12;
        c5.gridx = 0;
        c5.gridy = 4;
        c5.gridwidth = 1;
        c5.gridheight = 1;
        c5.weightx = 1;
        c5.weighty = 1;
        c5.fill = GridBagConstraints.NONE;
        c5.anchor = GridBagConstraints.CENTER;
        parameterClass.panel_west.add(parameterClass.score, c5);

        parameterClass.topWin.setVisible(true);
    }

    public ButtonDemo() {

        startWin.setSize(720, 720);
        startWin.setLayout(new GridBagLayout());

        parameterClass.name = new JTextField();
        GridBagConstraints c0 = new GridBagConstraints();
        c0.gridx = 0;
        c0.gridy = 0;
        c0.gridwidth = 2;
        c0.gridheight = 1;
        c0.weightx = 1;
        c0.weighty = 1;
        c0.fill = GridBagConstraints.BOTH;
        c0.anchor = GridBagConstraints.CENTER;
        startWin.add(parameterClass.name, c0);

        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 1;
        c1.gridwidth = 1;
        c1.gridheight = 1;
        c1.weightx = 1;
        c1.weighty = 1;
        c1.fill = GridBagConstraints.BOTH;
        c1.anchor = GridBagConstraints.CENTER;
        startWin.add(modeButton1, c1);
        modeButton1.addActionListener(this);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 2;
        c2.gridwidth = 1;
        c2.gridheight = 1;
        c2.weightx = 1;
        c2.weighty = 1;
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.CENTER;
        startWin.add(modeButton2, c2);
        modeButton2.addActionListener(this);

        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 3;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        c3.weightx = 1;
        c3.weighty = 1;
        c3.fill = GridBagConstraints.BOTH;
        c3.anchor = GridBagConstraints.CENTER;
        startWin.add(modeButton3, c3);
        modeButton3.addActionListener(this);

        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 0;
        c4.gridy = 5;
        c4.gridwidth = 1;
        c4.gridheight = 1;
        c4.weightx = 1;
        c4.weighty = 1;
        c4.fill = GridBagConstraints.BOTH;
        c4.anchor = GridBagConstraints.CENTER;
        startWin.add(modeButton4, c4);
        modeButton4.addActionListener(this);

        GridBagConstraints c5 = new GridBagConstraints();
        c5.gridx = 0;
        c5.gridy = 6;
        c5.gridwidth = 1;
        c5.gridheight = 1;
        c5.weightx = 1;
        c5.weighty = 1;
        c5.fill = GridBagConstraints.BOTH;
        c5.anchor = GridBagConstraints.CENTER;
        startWin.add(modeButton5, c5);
        modeButton5.addActionListener(this);

        c5.gridy = 7;
        startWin.add(modeButton6, c5);
        modeButton6.addActionListener(this);

        c5.gridx = 1;
        c5.gridy = 1;
        startWin.add(modeButton7, c5);
        modeButton7.addActionListener(this);

        c5.gridy = 2;
        startWin.add(modeButton8, c5);
        modeButton8.addActionListener(this);

        c5.gridy = 3;
        startWin.add(modeButton9, c5);
        modeButton9.addActionListener(this);

        c5.gridy = 4;
        startWin.add(modeButton10, c5);
        modeButton10.addActionListener(this);

        c5.gridy = 5;
        startWin.add(modeButton11, c5);
        modeButton11.addActionListener(this);

        c5.gridy = 6;
        startWin.add(modeButton12, c5);
        modeButton12.addActionListener(this);

        c5.gridy = 7;
        startWin.add(modeButton13, c5);
        modeButton13.addActionListener(this);

        c5.gridy = 8;
        startWin.add(modeButton14, c5);
        modeButton14.addActionListener(this);

        startWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startWin.setVisible(true);
    }

    public final void questionMake(int mode) {
        int i;
        int j;
        int k;
        int rng;
        int rng2;
        int tmp;

        parameterClass.num1 = new int[numberofquestions];
        parameterClass.num2 = new int[numberofquestions];
        parameterClass.ans = new int[numberofquestions];
        parameterClass.ansarea = new JTextField[numberofquestions];

        switch (mode) {
            case 0:
            case 1:
            case 2:
                symbol = "+ ";
                break;
            case 3:
            case 4:
            case 5:
                symbol = "- ";
                break;
            case 6:
            case 7:
            case 8:
            case 9:
                symbol = "× ";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                symbol = "÷ ";
                break;
            default:
                symbol = " ";
        }

        NumberFormat nf = new DecimalFormat("##");

        for (i = 0; i < numberofquestions; i++) {

            switch (mode) {
                case 0:
                    //加法4級
                    //個位數+個位數 w/進位     30%
                    //十位數+個位數 w/不進位   40%
                    //十位數+個位數 w/進位     30%
                    if (i < (numberofquestions * 0.3)) {
                        /*
                        parameterClass.num1[i] = 0;
                        parameterClass.num2[i] = 0;
                        parameterClass.ans[i] = parameterClass.num1[i] + parameterClass.num2[i];
                        while( parameterClass.ans[i] < 10 )
                        {
                            rng = (int) (Math.random() * 8) + 1;
                            parameterClass.num1[i] = rng;
                            rng2 = (int) (Math.random() * 8) + 1;
                            parameterClass.num2[i] = rng2;
                            parameterClass.ans[i] = parameterClass.num1[i] + parameterClass.num2[i];
                        }
                         */
                        question_Plus(i, 0, 0, true);
                        break;
                    } else if (i < (numberofquestions * 0.7)) {
                        /*
                        rng = (int) (Math.random() * 88) + 11;
                        if( (rng-10)<8 )
                        {
                            rng2 = (int) (Math.random() * (rng-10)) + 1;
                        }
                        else
                        {
                            rng2 = (int) (Math.random() * 8) + 1;
                        }
                        parameterClass.num1[i] = rng - rng2;
                        parameterClass.num2[i] = rng2;
                        parameterClass.ans[i] = rng;
                         */
                        question_Plus(i, 1, 0, false);
                        break;
                    } else {
                        /*
                        parameterClass.num1[i] = 0;
                        parameterClass.num2[i] = 0;
                        parameterClass.ans[i] = parameterClass.num1[i] + parameterClass.num2[i];
                        while( parameterClass.ans[i] < 100 )
                        {
                            rng = (int) (Math.random() * 88) + 11;
                            parameterClass.num1[i] = rng;
                            rng2 = (int) (Math.random() * 8) + 1;
                            parameterClass.num2[i] = rng2;
                            parameterClass.ans[i] = rng + rng2;
                        }
                         */
                        question_Plus(i, 1, 0, true);
                        break;
                    }
                case 1:
                    //加法5級
                    //個位數+個位數 w/進位     10%
                    //十位數+個位數 w/不進位   20%
                    //十位數+個位數 w/進位     30%
                    //百位數+個位數 w/不進位   30%
                    //百位數+個位數 w/進位     10%
                    if (i < (numberofquestions * 0.1)) {
                        question_Plus(i, 0, 0, true);
                        break;
                    } else if (i < (numberofquestions * 0.3)) {
                        question_Plus(i, 1, 0, false);
                        break;
                    } else if (i < (numberofquestions * 0.6)) {
                        question_Plus(i, 1, 0, true);
                        break;
                    } else if (i < (numberofquestions * 0.9)) {
                        question_Plus(i, 2, 0, false);
                        break;
                    } else {
                        question_Plus(i, 2, 0, true);
                        break;
                    }
                case 2:
                    //加法 - 進階題(沒比例)
                    //個位數+十位數 w/進位     20%
                    //十位數+十位數 w/不進位   20%
                    //十位數+十位數 w/進位     20%
                    //百位數+十位數 w/不進位   20%
                    //百位數+十位數 w/進位     20%
                    if (i < (numberofquestions * 0.2)) {
                        question_Plus(i, 0, 1, true);
                        break;
                    } else if (i < (numberofquestions * 0.4)) {
                        question_Plus(i, 1, 1, false);
                        break;
                    } else if (i < (numberofquestions * 0.6)) {
                        question_Plus(i, 1, 1, true);
                        break;
                    } else if (i < (numberofquestions * 0.8)) {
                        question_Plus(i, 2, 1, false);
                        break;
                    } else {
                        question_Plus(i, 2, 1, true);
                        break;
                    }
                case 3:
                    //減法4級
                    //個位數-個位數            10%
                    //十位數-個位數 w/10       10%
                    //十位數-個位數 w/10的倍數  20%
                    //十位數-個位數 w/不退位    20%
                    //十位數-個位數 w/退位      40%
                    if (i < (numberofquestions * 0.1)) {
                        question_Minus(i, 0, 0, false);
                        break;
                    } else if (i < (numberofquestions * 0.2)) {
                        question_Minus_10(i, 1, 0, true);
                        break;
                    } else if (i < (numberofquestions * 0.4)) {
                        question_Minus_10(i, 1, 0, false);
                        break;
                    } else if (i < (numberofquestions * 0.6)) {
                        question_Minus(i, 1, 0, false);
                        break;
                    } else {
                        question_Minus(i, 1, 0, true);
                        break;
                    }
                case 4:
                    //減法5級
                    //十位數-個位數 w/10的倍數  10%
                    //十位數-個位數 w/不退位    20%
                    //十位數-個位數 w/退位      30%
                    //百位數-個位數 w/不退位    20%
                    //百位數-個位數 w/退位      20%
                    if (i < (numberofquestions * 0.1)) {
                        question_Minus_10(i, 1, 0, false);
                        break;
                    } else if (i < (numberofquestions * 0.3)) {
                        question_Minus(i, 1, 0, false);
                        break;
                    } else if (i < (numberofquestions * 0.6)) {
                        question_Minus(i, 1, 0, true);
                        break;
                    } else if (i < (numberofquestions * 0.8)) {
                        question_Minus(i, 2, 0, false);
                        break;
                    } else {
                        question_Minus(i, 2, 0, true);
                        break;
                    }
                case 5:
                    //減法 - 進階題
                    //十位數-十位數 w/不退位    25%
                    //十位數-十位數 w/退位      25%
                    //百位數-十位數 w/不退位    25%
                    //百位數-十位數 w/退位      25%
                    if (i < (numberofquestions * 0.25)) {
                        question_Minus(i, 1, 1, false);
                        break;
                    } else if (i < (numberofquestions * 0.5)) {
                        question_Minus(i, 1, 1, true);
                        break;
                    } else if (i < (numberofquestions * 0.75)) {
                        question_Minus(i, 2, 1, false);
                        break;
                    } else {
                        question_Minus(i, 2, 1, true);
                        break;
                    }
                case 6:
                    //乘法3級
                    //99乘法表                 70%
                    //十位數*個位數 w/不進位    10%
                    //十位數*個位數 w/進位       5%
                    //十位數*10或11             5%
                    //十位數平方數(<=15)         10%
                    if (i < (numberofquestions * 0.7)) {
                        question_Multi_99(i);
                        break;
                    } else if (i < (numberofquestions * 0.8)) {
                        question_Multi_under20(i, false);
                        break;
                    } else if (i < (numberofquestions * 0.85)) {
                        question_Multi_under20(i, true);
                        break;
                    } else if (i < (numberofquestions * 0.9)) {
                        question_Multi_exp(i, 1, 0);
                        break;
                    } else {
                        question_Multi_exp(i, 2, 15);
                        break;
                    }
                case 7:
                    //乘法4級
                    //99乘法表                 60%
                    //十位數*個位數 w/不進位    10%
                    //十位數*個位數 w/進位       5%
                    //十位數*10或11             5%
                    //十位數平方數(<=19)         20%
                    if (i < (numberofquestions * 0.6)) {
                        question_Multi_99(i);
                        break;
                    } else if (i < (numberofquestions * 0.7)) {
                        question_Multi_under20(i, false);
                        break;
                    } else if (i < (numberofquestions * 0.75)) {
                        question_Multi_under20(i, true);
                        break;
                    } else if (i < (numberofquestions * 0.8)) {
                        question_Multi_exp(i, 1, 0);
                        break;
                    } else {
                        question_Multi_exp(i, 2, 19);
                        break;
                    }
                case 8:
                    //乘法5級
                    //99乘法表                 50%
                    //十位數*個位數 w/不進位    10%
                    //十位數*個位數 w/進位      10%
                    //十位數*10或11             5%
                    //十位數平方數(<=25)        25%
                    if (i < (numberofquestions * 0.5)) {
                        question_Multi_99(i);
                        break;
                    } else if (i < (numberofquestions * 0.6)) {
                        question_Multi_under20(i, false);
                        break;
                    } else if (i < (numberofquestions * 0.7)) {
                        question_Multi_under20(i, true);
                        break;
                    } else if (i < (numberofquestions * 0.75)) {
                        question_Multi_exp(i, 1, 0);
                        break;
                    } else {
                        question_Multi_exp(i, 2, 25);
                        break;
                    }
                case 9:
                    //乘法5級
                    //十位數*個位數 w/20以上    33%
                    //百位數*個位數=百位數      33%
                    //十位數平方數以外的數      34%
                    if (i < (numberofquestions * 0.33)) {
                        question_Multi_exp(i, 4, 0);
                        break;
                    } else if (i < (numberofquestions * 0.66)) {
                        question_Multi_exp(i, 5, 0);
                        break;
                    } else {
                        question_Multi_exp(i, 3, 0);
                        break;
                    }
                case 10:
                    //除法2級
                    //個位數÷個位數                      20%
                    //十位數÷個位數(99乘法表內)           40%
                    //十位數÷個位數(各位數可直接相除)      40%
                    if (i < (numberofquestions * 0.2)) {
                        question_Division_99(i, false);
                        break;
                    } else if (i < (numberofquestions * 0.6)) {
                        question_Division_99(i, true);
                        break;
                    } else {
                        question_Division_2_1(i, true);
                        break;
                    }
                case 11:
                    //除法3級
                    //個位數÷個位數                      10%
                    //十位數÷個位數(99乘法表內)           20%
                    //十位數÷個位數(各位數可直接相除)      30%
                    //十位數÷個位數(各位數不可直接相除)    40%
                    if (i < (numberofquestions * 0.1)) {
                        question_Division_99(i, false);
                        break;
                    } else if (i < (numberofquestions * 0.3)) {
                        question_Division_99(i, true);
                        break;
                    } else if (i < (numberofquestions * 0.6)) {
                        question_Division_2_1(i, true);
                        break;
                    } else {
                        question_Division_2_1(i, false);
                        break;
                    }
                case 12:
                    //除法4級
                    //十位數÷個位數(99乘法表內)           10%
                    //十位數÷個位數(各位數可直接相除)      20%
                    //十位數÷個位數(各位數不可直接相除)    30%
                    //百位數÷個位數(各位數可直接相除)      40%
                    if (i < (numberofquestions * 0.1)) {
                        question_Division_99(i, true);
                        break;
                    } else if (i < (numberofquestions * 0.3)) {
                        question_Division_2_1(i, true);
                        break;
                    } else if (i < (numberofquestions * 0.6)) {
                        question_Division_2_1(i, false);
                        break;
                    } else {
                        question_Division_3_1(i, true);
                        break;
                    }
                case 13:
                    //除法5級
                    //十位數÷個位數(99乘法表內)           10%
                    //十位數÷個位數(各位數可直接相除)      10%
                    //十位數÷個位數(各位數不可直接相除)    20%
                    //百位數÷個位數(各位數可直接相除)      20%
                    //百位數÷個位數(各位數不可直接相除)    35%
                    //百位數÷十位數(平方數相除)            5%
                    if (i < (numberofquestions * 0.1)) {
                        question_Division_99(i, true);
                        break;
                    } else if (i < (numberofquestions * 0.2)) {
                        question_Division_2_1(i, true);
                        break;
                    } else if (i < (numberofquestions * 0.4)) {
                        question_Division_2_1(i, false);
                        break;
                    } else if (i < (numberofquestions * 0.6)) {
                        question_Division_3_1(i, true);
                        break;
                    } else if (i < (numberofquestions * 0.95)) {
                        question_Division_3_1(i, false);
                        break;
                    } else {
                        question_Division_3_2_square(i);
                        break;
                    }
                default:
            }

            /*
            if (i < 30) {
                j = 0;
                k = i;
            } else if (i < 60) {
                j = 20;
                k = i - 30;
            } else if (i < 90) {
                j = 38;
                k = i - 60;
            } else {
                j = 56;
                k = i - 90;
            }

            if (i > 30) {
                JLabel blank = new JLabel("      ");
                GridBagConstraints c4 = new GridBagConstraints();
                c4.gridx = j - 4;
                c4.gridy = k + 1;
                c4.gridwidth = 4;
                c4.gridheight = 1;
                c4.weightx = 0;
                c4.weighty = 0;
                c4.fill = GridBagConstraints.NONE;
                c4.anchor = GridBagConstraints.CENTER;
                parameterClass.panel.add(blank, c4);
            }
             */
            j = 0;
            k = i;

            /*
            JLabel label = new JLabel(String.valueOf(parameterClass.num1[i]));
            GridBagConstraints c1 = new GridBagConstraints();
            c1.gridx = j;
            c1.gridy = k + 1;
            c1.gridwidth = 2;
            c1.gridheight = 1;
            c1.weightx = 1;
            c1.weighty = 1;
            c1.fill = GridBagConstraints.NONE;
            c1.anchor = GridBagConstraints.CENTER;
            parameterClass.topWin.add(label, c1);
             */
            JLabel label2 = new JLabel(parameterClass.num1[i] + symbol + parameterClass.num2[i] + " =");
            GridBagConstraints c2 = new GridBagConstraints();
            c2.gridx = j + 10;
            c2.gridy = k + 1;
            c2.gridwidth = 1;
            c2.gridheight = 1;
            c2.weightx = 1;
            c2.weighty = 1;
            c2.fill = GridBagConstraints.NONE;
            c2.anchor = GridBagConstraints.EAST;
            parameterClass.panel.add(label2, c2);

            parameterClass.ansarea[i] = new JFormattedTextField(nf);
            //parameterClass.ansarea[i] = new JFormattedTextField();
            parameterClass.ansarea[i].setColumns(1);
            //double defaultHeight = parameterClass.ansarea[i].getSize().getHeight();
            //parameterClass.ansarea[i].setSize(new Dimension(0xc8, (int) defaultHeight));
            GridBagConstraints c0 = new GridBagConstraints();
            c0.gridx = j + 11;
            c0.gridy = k + 1;
            c0.gridwidth = 1;
            c0.gridheight = 1;
            c0.weightx = 1;
            c0.weighty = 1;
            c0.fill = GridBagConstraints.BOTH;
            c0.anchor = GridBagConstraints.CENTER;
            parameterClass.panel.add(parameterClass.ansarea[i], c0);
            parameterClass.ansarea[i].setEditable(false);
            parameterClass.ansarea[i].setName("field#" + i);

            JLabel blank = new JLabel("                         ");
            GridBagConstraints c4 = new GridBagConstraints();
            c4.gridx = j;
            c4.gridy = k + 1;
            c4.gridwidth = 10;
            c4.gridheight = 1;
            c4.weightx = 0;
            c4.weighty = 0;
            c4.fill = GridBagConstraints.NONE;
            c4.anchor = GridBagConstraints.CENTER;
            parameterClass.panel.add(blank, c4);

            //JLabel blank = new JLabel("      ");
            GridBagConstraints c5 = new GridBagConstraints();
            c5.gridx = j + 12;
            c5.gridy = k + 1;
            c5.gridwidth = 10;
            c5.gridheight = 1;
            c5.weightx = 0;
            c5.weighty = 0;
            c5.fill = GridBagConstraints.NONE;
            c5.anchor = GridBagConstraints.CENTER;
            parameterClass.panel.add(blank, c5);
        }
    }

    //i=序號 j=第一個數的位數-1 k=第二個數字的位數-1 carry_flag=是否進位
    public void question_Plus(int i, int j, int k, boolean carry_flag) {
        int rng, rng2, rng3;
        int digi1 = 0;
        int digi2 = 0;
        int a;

        if (j > 0) {
            for (a = 0; a < j; a++) {
                digi1 = (int) (((int) (Math.random() * 9) + 1) * (int) pow(10, j - a)) + digi1;
            }
        }
        if (k > 0) {
            for (a = 0; a < k; a++) {
                digi2 = (int) (((int) (Math.random() * 9) + 1) * (int) pow(10, k - a)) + digi2;
            }
        }

        rng = 0;
        rng2 = 0;

        if (carry_flag) {
            rng3 = 0;
            while (rng3 < 10) {
                rng = (int) (Math.random() * 9) + 1;
                rng2 = (int) (Math.random() * 9) + 1;
                rng3 = rng + rng2;
            }
        } else {
            rng3 = 20;
            while (rng3 > 9) {
                rng = (int) (Math.random() * 9) + 1;
                rng2 = (int) (Math.random() * 9) + 1;
                rng3 = rng + rng2;
            }
        }

        parameterClass.num1[i] = digi1 + rng;
        parameterClass.num2[i] = digi2 + rng2;
        parameterClass.ans[i] = parameterClass.num1[i] + parameterClass.num2[i];
    }

    //i=序號 j=第一個數的位數-1 k=第二個數字的位數-1 carry_flag=是否退位
    public void question_Minus(int i, int j, int k, boolean carry_flag) {
        int rng, rng2;
        int digi1 = 0;
        int digi2 = 0;
        int a;

        if (j > 0) {
            for (a = 0; a < j; a++) {
                digi1 = (int) (((int) (Math.random() * 9) + 1) * (int) pow(10, j - a)) + digi1;
            }
        }
        if (k > 0) {
            for (a = 0; a < k; a++) {
                digi2 = (int) (((int) (Math.random() * 9) + 1) * (int) pow(10, k - a)) + digi2;
            }
        }

        if (carry_flag) {
            //有退位
            rng = (int) (Math.random() * 9) + 1;
            rng2 = (int) (Math.random() * (9 - rng)) + rng + 1;
        } else {
            //沒退位
            rng = (int) (Math.random() * 9) + 1;
            rng2 = (int) (Math.random() * (rng - 2)) + 1;

        }

        parameterClass.num1[i] = digi1 + rng;
        parameterClass.num2[i] = digi2 + rng2;
        parameterClass.ans[i] = parameterClass.num1[i] - parameterClass.num2[i];
    }

    //i=序號 j=第一個數的位數-1 k=第二個數字的位數-1 fixed=第一個數字是否固定為1開頭
    public void question_Minus_10(int i, int j, int k, boolean fixed) {
        int rng = 0;
        int rng2 = 0;
        int sw, a, digi;

        if (fixed) {
            sw = 0;
        } else {
            sw = 9;
        }

        if (j > 0) {
            for (a = 0; a < j; a++) {
                digi = (int) pow(10, j - a);
                rng = ((int) ((Math.random() * sw) + 1) * digi) + rng;
            }
        }
        if (k > 0) {
            for (a = 0; a < k; a++) {
                rng2 = (int) (((Math.random() * 9) + 1) * pow(10, k - a)) + rng2;
            }
        } else {
            rng2 = (int) ((Math.random() * 9) + 1);
        }

        parameterClass.num1[i] = rng;
        parameterClass.num2[i] = rng2;
        parameterClass.ans[i] = parameterClass.num1[i] - parameterClass.num2[i];
    }

    //99乘法表 i=序號
    public void question_Multi_99(int i) {
        parameterClass.num1[i] = (int) (Math.random() * 9) + 1;
        parameterClass.num2[i] = (int) (Math.random() * 9) + 1;
        parameterClass.ans[i] = parameterClass.num1[i] * parameterClass.num2[i];
    }

    //i=序號 j=第一個數的位數-1 k=第二個數字的位數-1 carry_flag=是否進位
    public void question_Multi_under20(int i, boolean carry_flag) {
        int rng = 0, rng2 = 0, rng3;

        if (carry_flag) {
            rng3 = 0;
            while (rng3 < 10) {
                rng = (int) (Math.random() * 9) + 1;
                rng2 = (int) (Math.random() * 9) + 1;
                rng3 = rng * rng2;
            }
            parameterClass.num1[i] = rng + 10;
            parameterClass.num2[i] = rng2;
            parameterClass.ans[i] = parameterClass.num1[i] * parameterClass.num2[i];
        } else {
            rng = (int) (Math.random() * 19) + 1;
            if (rng == 10 || rng == 20) {
                rng2 = (int) (Math.random() * 9) + 1;

                parameterClass.num1[i] = rng;
                parameterClass.num2[i] = rng2;
                parameterClass.ans[i] = parameterClass.num1[i] * parameterClass.num2[i];
            } else {
                rng = (int) (Math.random() * 9) + 1;
                rng2 = (int) (Math.random() * 9) + 1;
                rng3 = rng * rng2;
                while (rng3 > 9) {
                    rng = (int) (Math.random() * 9) + 1;
                    rng2 = (int) (Math.random() * 9) + 1;
                    rng3 = rng * rng2;
                }
                parameterClass.num1[i] = rng + 10;
                parameterClass.num2[i] = rng2;
                parameterClass.ans[i] = parameterClass.num1[i] * parameterClass.num2[i];
            }
        }
    }

    //i=序號 mode:1=20以內*10或11 2=平方數 3=平方數以外的數 4=20以上的數*個位數 5=百位數*個位數=任一百位數 range=mode2用的範圍
    public void question_Multi_exp(int i, int mode, int range) {
        int rng = 0, rng2 = 0;
        int[] rand = {10, 11};
        int rng_tmp;

        switch (mode) {
            case 1:
                rng = (int) (Math.random() * 10) + 11;
                rng_tmp = (int) (Math.random() * 2);
                rng2 = rand[rng_tmp];
                break;
            case 2:
                rng = (int) (Math.random() * (range - 10)) + 11;
                rng2 = rng;
                break;
            case 3:
                rng = (int) (Math.random() * 89) + 11;
                rng2 = (int) (Math.random() * 89) + 11;
                while (rng2 == rng) {
                    rng2 = (int) (Math.random() * 89) + 11;
                }
                break;
            case 4:
                rng = (int) (Math.random() * 79) + 21;
                rng2 = (int) (Math.random() * 9) + 1;
                break;
            case 5:
                while (rng < 100) {
                    rng = (int) (Math.random() * 899) + 101;
                    rng2 = (int) (Math.random() * 9) + 1;
                    rng_tmp = rng % rng2;
                    rng = (rng - rng_tmp) / rng2;
                }
                break;
            default:
        }
        parameterClass.num1[i] = rng;
        parameterClass.num2[i] = rng2;
        parameterClass.ans[i] = parameterClass.num1[i] * parameterClass.num2[i];
    }

    //i=序號 carry_flag=是否進位
    public void question_Division_99(int i, boolean carry_flag) {
        /*
        九九乘法表內的數字除法
        因為2~5只要與另外一個數字和不超過6，乘起來就不會超過10
        所以可以把題目控制在個位數除個位數
         */
        int a, b, c;
        int tmp;

        if (carry_flag) {
            c = (int) (Math.random() * 8) + 2;
        } else {
            c = (int) (Math.random() * 9) + 1;
        }

        if (c == 1) {
            b = (int) (Math.random() * 8) + 2;
        } else if (c > 5) {
            if (carry_flag) {
                b = (int) (Math.random() * 8) + 2;
            } else {
                b = 1;
            }
        } else {
            if (carry_flag) {
                tmp = c + 3; //10-[(6-c)+1]
                b = (int) (Math.random() * tmp) + 7 - c;
            } else {
                tmp = 6 - c;
                b = (int) (Math.random() * tmp) + 1;
            }
        }

        parameterClass.num1[i] = b * c;
        parameterClass.num2[i] = c;
        parameterClass.ans[i] = b;

    }

    //i=序號 flag=是否能直接相除
    public void question_Division_2_1(int i, boolean flag) {
        /*
        十位數除個位數
         */

        if (flag) {
            int tmp_10, tmp_1, pow_10, pow_1;
            int[] random_org_num = {2, 2, 2, 3, 3, 3, 5, 7};
            int key = (int) (Math.random() * 8);
            int gen_val = random_org_num[key];

            switch (gen_val) {
                case 2:
                    pow_10 = (int) (Math.random() * 3) + 1;
                    pow_1 = (int) (Math.random() * 4);
                    break;
                case 3:
                    pow_10 = (int) (Math.random() * 2) + 1;
                    pow_1 = (int) (Math.random() * 3);
                    break;
                case 5:
                case 7:
                    pow_10 = 1;
                    pow_1 = (int) (Math.random() * 2);
                    break;
                default:
                    pow_10 = 1;
                    pow_1 = 1;
            }

            tmp_10 = gen_val * pow_10;
            tmp_1 = gen_val * pow_1;

            parameterClass.num1[i] = 10 * tmp_10 + tmp_1;
            parameterClass.num2[i] = gcd(tmp_10, tmp_1);
            parameterClass.ans[i] = parameterClass.num1[i] / parameterClass.num2[i];
        } else {
            int rng = 1, rng2 = 1, tmp;
            int tmp_10, tmp_1, tmp_gcd;
            int carry_test = 0;
            parameterClass.ans[i] = 0;

            while (carry_test == 0 | parameterClass.ans[i] < 10) {
                rng = (int) (Math.random() * 99) + 1;
                rng2 = (int) (Math.random() * 9) + 1;
                tmp = rng % rng2;

                rng = rng - tmp;
                tmp_1 = rng % 10;
                tmp_10 = (rng - tmp_1) / 10;
                tmp_gcd = gcd(tmp_10, tmp_1);
                carry_test = tmp_gcd % rng2;

                parameterClass.num1[i] = rng;
                parameterClass.num2[i] = rng2;
                parameterClass.ans[i] = rng / rng2;
            }
        }
    }

    //i=序號 flag=是否能直接相除
    public void question_Division_3_1(int i, boolean flag) {
        /*
        百位數除個位數
         */

        if (flag) {
            int tmp_100, tmp_10, tmp_1, pow_100, pow_10, pow_1;
            int[] random_org_num = {2, 2, 2, 3, 3, 3, 5, 7};
            int key = (int) (Math.random() * 8);
            int gen_val = random_org_num[key];

            switch (gen_val) {
                case 2:
                    pow_100 = (int) (Math.random() * 3) + 1;
                    pow_10 = (int) (Math.random() * 4);
                    pow_1 = (int) (Math.random() * 4);
                    break;
                case 3:
                    pow_100 = (int) (Math.random() * 2) + 1;
                    pow_10 = (int) (Math.random() * 3);
                    pow_1 = (int) (Math.random() * 3);
                    break;
                case 5:
                case 7:
                    pow_100 = 1;
                    pow_10 = (int) (Math.random() * 2);
                    pow_1 = (int) (Math.random() * 2);
                    break;
                default:
                    pow_100 = 1;
                    pow_10 = 1;
                    pow_1 = 1;
            }

            tmp_100 = gen_val * pow_100;
            tmp_10 = gen_val * pow_10;
            tmp_1 = gen_val * pow_1;

            parameterClass.num1[i] = 100 * tmp_100 + 10 * tmp_10 + tmp_1;
            parameterClass.num2[i] = gcd(tmp_100, gcd(tmp_10, tmp_1));
            parameterClass.ans[i] = parameterClass.num1[i] / parameterClass.num2[i];
        } else {
            int rng = 1, rng2 = 1, tmp;
            int tmp_10, tmp_1, tmp_gcd;
            int carry_test = 0;

            while (carry_test == 0) {
                rng = (int) (Math.random() * 99) + 1;
                rng2 = (int) (Math.random() * 9) + 1;
                tmp = rng % rng2;

                rng = rng - tmp;
                tmp_1 = rng % 10;
                tmp_10 = (rng - tmp_1) / 10;
                tmp_gcd = gcd(tmp_10, tmp_1);
                carry_test = tmp_gcd % rng2;
            }

            if ((int) (Math.random() * 2) == 0) {
                parameterClass.num1[i] = rng * 10 + rng2;
                parameterClass.num2[i] = rng2;
                parameterClass.ans[i] = rng / rng2;
            } else {
                parameterClass.num1[i] = rng + rng2 * 100;
                parameterClass.num2[i] = rng2;
                parameterClass.ans[i] = rng / rng2;
            }

        }
    }

    //i=序號
    public void question_Division_3_2_square(int i) {
        int rng = 0, tmp = 0;

        rng = (int) (Math.random() * 9) + 10;
        tmp = rng * rng;

        parameterClass.num1[i] = tmp;
        parameterClass.num2[i] = rng;
        parameterClass.ans[i] = rng;
    }

    public int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    public void startAnswering() {
        int i;
        for (i = 0; i < numberofquestions; i++) {
            parameterClass.ansarea[i].setEditable(true);
        }
        parameterClass.ansarea[0].grabFocus();
        parameterClass.timer = new java.util.Timer();
        parameterClass.timer.schedule(new DateTask(), 0, 1000);

    }

    public final void minusUnder10() {
        int i;
        int j;
        int k;
        int rng;

        parameterClass.num1 = new int[100];
        parameterClass.num2 = new int[100];
        parameterClass.ans = new int[100];
        parameterClass.ansarea = new JTextField[100];

        NumberFormat nf = new DecimalFormat("##");

        for (i = 0; i < 100; i++) {

            rng = (int) (Math.random() * 8) + 1;

            parameterClass.num1[i] = rng;
            parameterClass.num2[i] = (int) (Math.random() * rng);
            parameterClass.ans[i] = rng - parameterClass.num2[i];

            /*

            if (i < 30) {
                j = 0;
                k = i;
            } else if (i < 60) {
                j = 20;
                k = i - 30;
            } else if (i < 90) {
                j = 38;
                k = i - 60;
            } else {
                j = 56;
                k = i - 90;
            }

            if (i > 30) {
                JLabel blank = new JLabel("      ");
                GridBagConstraints c4 = new GridBagConstraints();
                c4.gridx = j - 4;
                c4.gridy = k + 1;
                c4.gridwidth = 4;
                c4.gridheight = 1;
                c4.weightx = 0;
                c4.weighty = 0;
                c4.fill = GridBagConstraints.NONE;
                c4.anchor = GridBagConstraints.CENTER;
                parameterClass.panel.add(blank, c4);
            }

             */
            j = 0;
            k = i;

            JLabel label = new JLabel(String.valueOf(parameterClass.num1[i]));
            GridBagConstraints c1 = new GridBagConstraints();
            c1.gridx = j;
            c1.gridy = k + 1;
            c1.gridwidth = 2;
            c1.gridheight = 1;
            c1.weightx = 0;
            c1.weighty = 0;
            c1.fill = GridBagConstraints.NONE;
            c1.anchor = GridBagConstraints.CENTER;
            parameterClass.panel.add(label, c1);

            JLabel label2 = new JLabel("- " + parameterClass.num2[i] + " =");
            GridBagConstraints c2 = new GridBagConstraints();
            c2.gridx = j + 2;
            c2.gridy = k + 1;
            c2.gridwidth = 6;
            c2.gridheight = 1;
            c2.weightx = 0;
            c2.weighty = 0;
            c2.fill = GridBagConstraints.NONE;
            c2.anchor = GridBagConstraints.CENTER;
            parameterClass.panel.add(label2, c2);

            parameterClass.ansarea[i] = new JFormattedTextField(nf);
            parameterClass.ansarea[i].setColumns(6);
            GridBagConstraints c0 = new GridBagConstraints();
            c0.gridx = j + 8;
            c0.gridy = k + 1;
            c0.gridwidth = 6;
            c0.gridheight = 1;
            c0.weightx = 0;
            c0.weighty = 0;
            c0.fill = GridBagConstraints.BOTH;
            c0.anchor = GridBagConstraints.CENTER;
            parameterClass.panel.add(parameterClass.ansarea[i], c0);
        }
    }

    public static void countingScore() throws IOException {
        int cnt = 0;
        int i;
        int tmp;

        parameterClass.ans_flg = new int[100];

        Calendar c = Calendar.getInstance();

        parameterClass.timer.cancel();
        File saveFile = new File(getYMDHMS(c) + "_" + parameterClass.name.getText() + "_output.txt");
        try (FileWriter fwriter = new FileWriter(saveFile)) {
            fwriter.write("Name=" + parameterClass.name.getText() + "\r\n");

            for (i = 0; i < 100; i++) {
                if (parameterClass.ansarea[i].getText().trim().equals("")) {
                    parameterClass.ans_flg[i] = 0;
                } else {
                    tmp = Integer.valueOf(parameterClass.ansarea[i].getText());
                    if (parameterClass.ans[i] == tmp) {
                        parameterClass.ans_flg[i] = 1;
                        cnt++;
                    } else {
                        parameterClass.ans_flg[i] = 2;
                        fwriter.write(parameterClass.num1[i] + symbol + parameterClass.num2[i] + "=" + tmp + "\r\n");
                    }
                }
                parameterClass.ansarea[i].setEditable(false);
            }

            fwriter.write("Score=" + String.valueOf(cnt * 2) + "\r\n");
            fwriter.close();
        }
        parameterClass.score.setText(String.valueOf(cnt * 2));

    }

    public static String getYMDHMS(Calendar c) { //輸出格式製作
        int[] a = {c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH),
            c.get(Calendar.HOUR_OF_DAY),
            c.get(Calendar.MINUTE),
            c.get(Calendar.SECOND)
        };
        StringBuilder sb = new StringBuilder();
        sb.append(a[0]);
        if (a[1] < 9) {
            sb.append("0").append(a[1] + 1);
        } //加 1 才會得到實際月份
        else {
            sb.append("").append(a[1]).append(1);
        }
        if (a[2] < 10) {
            sb.append("0").append(a[2]);
        } else {
            sb.append("").append(a[2]);
        }
        if (a[3] < 10) {
            sb.append("0").append(a[3]);
        } else {
            sb.append("").append(a[3]);
        }
        if (a[4] < 10) {
            sb.append("0").append(a[4]);
        } else {
            sb.append("").append(a[4]);
        }
        return sb.toString();
    }

    public void setUIFont(FontUIResource fui) {
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value != null && value instanceof FontUIResource) {
                UIManager.put(key, fui);
            }
        }
    }

    public void backTofirst() {
        parameterClass.time = 180;
        parameterClass.num1 = null;
        parameterClass.num2 = null;
        parameterClass.ans = null;
        parameterClass.ans_flg = null;
        parameterClass.ansarea = null;
        parameterClass.topWin.dispose();
        parameterClass.topWin = new JFrame();
        parameterClass.panel = new JPanel((new GridBagLayout()));
        startWin.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == parameterClass.endButton) {
            try {
                countingScore();
            } catch (IOException ex) {
                Logger.getLogger(ButtonDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (ae.getSource() == parameterClass.startButton) {
            startAnswering();
        } else if (ae.getSource() == parameterClass.backButton) {
            backTofirst();
        } else if (ae.getSource() == modeButton1) {
            start(0);
        } else if (ae.getSource() == modeButton2) {
            start(1);
        } else if (ae.getSource() == modeButton3) {
            start(2);
        } else if (ae.getSource() == modeButton4) {
            start(3);
        } else if (ae.getSource() == modeButton5) {
            start(4);
        } else if (ae.getSource() == modeButton6) {
            start(5);
        } else if (ae.getSource() == modeButton7) {
            start(6);
        } else if (ae.getSource() == modeButton8) {
            start(7);
        } else if (ae.getSource() == modeButton9) {
            start(8);
        } else if (ae.getSource() == modeButton10) {
            start(9);
        } else if (ae.getSource() == modeButton11) {
            start(10);
        } else if (ae.getSource() == modeButton12) {
            start(11);
        } else if (ae.getSource() == modeButton13) {
            start(12);
        } else if (ae.getSource() == modeButton14) {
            start(13);
        }
    }

}
