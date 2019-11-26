/*
 *  CSCI1130 Assignment #5 #Image Toonifier
 * 
 *  I declare that the assignment here submitted is original
 *  except for source material explicitly acknowledged,
 *  and that the same or closely related material has not been
 *  previously submitted for another course.
 *  I also acknowledge that I am aware of University policy and
 *  regulations on honesty in academic work, and of the disciplinary
 *  guidelines and procedures applicable to breaches of such
 *  policy and regulations, as contained in the website.
 *  
 *  University Guideline on Academic Honesty:
 *    http://www.cuhk.edu.hk/policy/academichonesty
 *  Faculty of Engineering Guidelines to Academic Honesty:
 *    http://www.erg.cuhk.edu.hk/erg/AcademicHonesty
 *  
 *  Student Name: Dongmin Kim
 *  Student ID  : 1155139586
 *  Date        : #2019-11-22
 */
package imagetoonifier;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author dongmin kim
 */
public class PGM {
    // instance fields
    private String imageName;
    private int width, height;
    private int maxValue; 
    private int[][] image; // Default constructor for creating a simple checker PGM image of 2 x 3
    
    public PGM() {
        imageName = "Simple";
        width = 2;
        height = 3;
        maxValue = 255; // value for white in default/given file
        image = new int[height][width]; // note: height as row, width as column
        image[0][0] = image[1][1] = 255; // white dots
        image[0][1] = image[2][0] = 127; // gray dots
        image[1][0] = image[2][1] = 0; // white dots
    }
    
    public PGM(String name, int [][] imageArr, int w, int h)
    {
        imageName = name;
        image = imageArr;
        width = w;
        height = h;
        maxValue = 255;
    }
 
    // Constructor for creating a "mid-gray" PGM image of w x h
    // All pixels shall carry value of 127
    public PGM(String name, int w, int h) {
    /*** student's work here to construct a gray box ***/
        imageName = name;
        width = w;
        height = h;
        maxValue = 255;
        image = new int[height][width];
        
        for (int i = 0; i < height; i++)
        {
            for(int j = 0; j< width; j++)
            {
                image[i][j] = 127;
            }
        }
        
    }
    
    // Constructor for reading a PGM image file
    public PGM(String filename) {
        this.imageName = filename;
        read(filename);
    }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getMaxValue() { return maxValue; }
    public String getImageName() { return imageName; }
    public int[][] getImage() { return image; }
    
    public void read(String filename) {

        try {
            File f = new File(filename);
            Scanner reader = new Scanner(f);
            String header = reader.nextLine();

            if (header == null || header.length() < 2 || 
                    header.charAt(0) != 'P' || header.charAt(1) != '2') {
                throw new Exception("Wrong PGM header!");
            }

            do { // skip lines start with '#' (if any)
                header = reader.nextLine();
            } while (header.charAt(0) == '#');
            
            // get from last line instead of file
            Scanner readStr = new Scanner(header);
            width = readStr.nextInt();
            height = readStr.nextInt();
            
            // get the rest from file
            maxValue = reader.nextInt();
            
            /*** student's work here to read the image ***/
            image = new int[width][height];
            
            for(int i = 0; i < width; i++)
            {
                for(int j = 0; j < height; j++)
                {
                    image[i][j] = reader.nextInt();
                }
            }
         } catch (Exception e){
            /*** student's work here to handle exception(s) ***/
           e.printStackTrace();
         }
    }
        
    public void write(String filename, PGM resultPGM) {
    /*** student's work here to write the PGM image ***/
        File f = new File(filename);
        int w = this.getWidth();
        int h = this.getHeight();
        int[][] imageArr;
        
        imageArr = resultPGM.getImage();
        
        try{
            
            // 1. Set up a header
            FileWriter fw = new FileWriter(f);
            fw.write("P2\n");
            fw.write(w + " " + h+"\n");
            fw.write(this.getMaxValue() +"\n");
            
            // 2. Write Data
            for (int x = 0 ; x< w; x++)
            {
                for(int y = 0; y < h; y++)
                {
                    fw.write(imageArr[x][y] + " ");
                }
                fw.write("\n");
            }
            
            fw.close();
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
