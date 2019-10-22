/*
 * CSCI1130 Assignment 1 Shaky Display
 * Aim: Get acquainted with the JDK + NetBeans programming environment
 *      Learn the structure and format of a Java program by example
 * Remark: Type class names, variable names, method names, etc. AS IS
 *         You should type also ALL the comment lines (text in gray)
 *
 * I declare that the assignment here submitted is original
 * except for source material explicitly acknowledged,
 * and that the same or closely related material has not been
 * previously submitted for another course.
 * I also acknowledge that I am aware of University policy and
 * regulations on honesty in academic work, and of the disciplinary
 * guidelines and procedures applicable to breaches of such
 * policy and regulations, as contained in the website.
 *
 * University Guideline on Academic Honesty:
 *   http://www.cuhk.edu.hk/policy/academichonesty
 * Faculty of Engineering Guidelines to Academic Honesty:
 *   http://www.erg.cuhk.edu.hk/erg/AcademicHonesty
 *
 * Student Name: Dongmin Kim
 * Student ID  : 1155139586
 * Date        : 2019-09-14
 */
package exercise;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

/**
 * ShakyDisplay
 * Introduction to Computing: Java Assignment
 * @author Dongmin Kim
 * @since  14 September 2019
 */
public class ShakyDisplay extends JFrame implements ActionListener {

    // instance fields
    protected int width, height;
    protected JButton buttons[][];
    
    // default constructor
    public ShakyDisplay()
    {
        width = 20;
        height = 10;
        initDisplay();
    }
    
    // constructor with given width and height of the ShakyDisplay object
    public ShakyDisplay(int w, int h)
    {
        width = w;
        height = h;
        initDisplay();
    }
    
    // initialize the ShakyDisplay window
    public final void initDisplay()
    {
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException
               | IllegalAccessException
               | InstantiationException
               | UnsupportedLookAndFeelException exceptionObject){
        }
        
        setTitle("Java Shaky Display");
        setLayout(new GridLayout(height, width));
        buttons = new JButton[height][width];
        for(int row = 0; row < height; row++)
            for(int col = 0; col < width; col++)
            {
                buttons[row][col] = new JButton("(" + row + ", " + col + ")");
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
         setSize(width * 70, height * 70);
         setVisible(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // change button text color on user clicks
    @Override
    public void actionPerformed(ActionEvent eventObject)
    {
        JButton target = (JButton) (eventObject.getSource());
        if (target.getForeground() == Color.GREEN)
            target.setForeground(Color.BLUE);
        else if (target.getForeground() == Color.BLUE)
        {
            target.setForeground(null);
            shake();
        }
        else
            target.setForeground(Color.GREEN);
    }
    
    // slow down this process by sleeping this thread
    private void delay(long sleepInMS) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleepInMS);
        } catch (InterruptedException exceptionObject) {
            Thread.currentThread().interrupt();
        }
    }
    
    // shake the ShakyDisplay
    private void shake()
    {
        Point windowLocation = getLocation();
        
        double round = 5, max_radius = 10, step = 100;
        
        double limit = 2 * Math.PI * round;
        double angle_increment = limit / step;
        double radius_increment = max_radius / step;
        
        for (double angle = 0,  radius = 0;
             angle < limit;
             angle += angle_increment, radius += radius_increment)
        {
            setLocation((int) (Math.cos(angle) * radius) + windowLocation.x,
                        (int) (Math.sin(angle) * radius) + windowLocation.y);
            this.delay(6);
        }
        this.setLocation(windowLocation);
    }
    
    public void showMyInfo()
    {
        /*
        This part illustrates last 5 digits of student id
        Number to represent : 39586
        */
        
        // 3
        buttons[1][4].setBackground(Color.YELLOW);
        buttons[2][4].setBackground(Color.YELLOW);
        buttons[3][4].setBackground(Color.YELLOW);
        buttons[4][4].setBackground(Color.YELLOW);
        buttons[5][4].setBackground(Color.YELLOW);
        buttons[6][4].setBackground(Color.YELLOW);
        buttons[7][4].setBackground(Color.YELLOW);
        
        buttons[1][1].setBackground(Color.YELLOW);
        buttons[1][2].setBackground(Color.YELLOW);
        buttons[1][3].setBackground(Color.YELLOW);
        
        buttons[4][1].setBackground(Color.YELLOW);
        buttons[4][2].setBackground(Color.YELLOW);
        buttons[4][3].setBackground(Color.YELLOW);
        
        buttons[7][1].setBackground(Color.YELLOW);
        buttons[7][2].setBackground(Color.YELLOW);
        buttons[7][3].setBackground(Color.YELLOW);
        
        // 9 
        buttons[1][9].setBackground(Color.YELLOW);
        buttons[2][9].setBackground(Color.YELLOW);
        buttons[3][9].setBackground(Color.YELLOW);
        buttons[4][9].setBackground(Color.YELLOW);
        buttons[5][9].setBackground(Color.YELLOW);
        buttons[6][9].setBackground(Color.YELLOW);
        buttons[7][9].setBackground(Color.YELLOW);
        
        buttons[1][6].setBackground(Color.YELLOW);
        buttons[1][7].setBackground(Color.YELLOW);
        buttons[1][8].setBackground(Color.YELLOW);
        
        buttons[4][6].setBackground(Color.YELLOW);
        buttons[4][7].setBackground(Color.YELLOW);
        buttons[4][8].setBackground(Color.YELLOW);
        
        buttons[7][6].setBackground(Color.YELLOW);
        buttons[7][7].setBackground(Color.YELLOW);
        buttons[7][8].setBackground(Color.YELLOW);
        
        buttons[2][6].setBackground(Color.YELLOW);
        buttons[3][6].setBackground(Color.YELLOW);
        
        // 5
        buttons[1][14].setBackground(Color.YELLOW);
        buttons[4][14].setBackground(Color.YELLOW);
        buttons[5][14].setBackground(Color.YELLOW);
        buttons[6][14].setBackground(Color.YELLOW);
        buttons[7][14].setBackground(Color.YELLOW);
        
        buttons[1][11].setBackground(Color.YELLOW);
        buttons[1][12].setBackground(Color.YELLOW);
        buttons[1][13].setBackground(Color.YELLOW);
        
        buttons[4][11].setBackground(Color.YELLOW);
        buttons[4][12].setBackground(Color.YELLOW);
        buttons[4][13].setBackground(Color.YELLOW);
        
        buttons[7][11].setBackground(Color.YELLOW);
        buttons[7][12].setBackground(Color.YELLOW);
        buttons[7][13].setBackground(Color.YELLOW);
        
        buttons[2][11].setBackground(Color.YELLOW);
        buttons[3][11].setBackground(Color.YELLOW);
        
        // 8
        buttons[1][19].setBackground(Color.YELLOW);
        buttons[2][19].setBackground(Color.YELLOW);
        buttons[3][19].setBackground(Color.YELLOW);
        buttons[4][19].setBackground(Color.YELLOW);
        buttons[5][19].setBackground(Color.YELLOW);
        buttons[6][19].setBackground(Color.YELLOW);
        buttons[7][19].setBackground(Color.YELLOW);
        
        buttons[1][16].setBackground(Color.YELLOW);
        buttons[2][16].setBackground(Color.YELLOW);
        buttons[3][16].setBackground(Color.YELLOW);
        buttons[4][16].setBackground(Color.YELLOW);
        buttons[5][16].setBackground(Color.YELLOW);
        buttons[6][16].setBackground(Color.YELLOW);
        buttons[7][16].setBackground(Color.YELLOW);
        
        buttons[1][17].setBackground(Color.YELLOW);
        buttons[1][18].setBackground(Color.YELLOW);
        
        buttons[4][17].setBackground(Color.YELLOW);
        buttons[4][18].setBackground(Color.YELLOW);
        
        buttons[7][17].setBackground(Color.YELLOW);
        buttons[7][18].setBackground(Color.YELLOW);
        
        // 6
        buttons[1][24].setBackground(Color.YELLOW);
        buttons[4][24].setBackground(Color.YELLOW);
        buttons[5][24].setBackground(Color.YELLOW);
        buttons[6][24].setBackground(Color.YELLOW);
        buttons[7][24].setBackground(Color.YELLOW);
        
        buttons[1][21].setBackground(Color.YELLOW);
        buttons[2][21].setBackground(Color.YELLOW);
        buttons[3][21].setBackground(Color.YELLOW);
        buttons[4][21].setBackground(Color.YELLOW);
        buttons[5][21].setBackground(Color.YELLOW);
        buttons[6][21].setBackground(Color.YELLOW);
        buttons[7][21].setBackground(Color.YELLOW);
        
        buttons[1][22].setBackground(Color.YELLOW);
        buttons[1][23].setBackground(Color.YELLOW);
        
        buttons[4][22].setBackground(Color.YELLOW);
        buttons[4][23].setBackground(Color.YELLOW);
        
        buttons[7][22].setBackground(Color.YELLOW);
        buttons[7][23].setBackground(Color.YELLOW);
        
        /*
        This part illustrates surname of author
        Surname: KIM
        */
        int c = 0;
        buttons[height - 1][c++].setText("K");
        buttons[height - 1][c++].setText("I");
        buttons[height - 1][c++].setText("M");
    }
    /**
     * main method, starting point of java application
     * @param args are command line argument in a string array
     */
    public static void main(String[] args) {
        ShakyDisplay dpy;
        dpy = new ShakyDisplay(25, 10);
        dpy.showMyInfo();
    }
    
}
