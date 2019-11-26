/**
 * CSCI1130 Introduction to Computing Using Java
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
 *   http://www.cuhk.edu.hk/policy/academichonesty/
 *
 * 
 *  Student Name  : Dongmin Kim
 *  Student ID    : 1155139586
 *  Class/Section : CSCI3230B
 *  Date          : 2019-11-22
 */
package imagetoonifier;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Arrays;
import java.lang.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ypchui
 * @author Dongmin Kim
 */
public class ImageToonifier {

    //private static String dialogIconImageFilename = "toonShaded.gif";
    private static boolean isPaletteReady = false, isEdgeReady = false;
    
    public boolean getStatus(){ return isPaletteReady && isEdgeReady;}
    
    public void setPaletteTrue(){
        isPaletteReady = true;
    }
    
    public void setEdgeTrue(){
        isEdgeReady = true;
    }
    
    public void setPaletteFalse(){
        isPaletteReady = false;
    }
    
    public void setEdgeFalse(){
        isEdgeReady = false;
    }
    
    /**
    * Show a menu of choices and get user's input
    *
    * @return an integer value: -1 means Quit, and options 0, 1, 2, 3
    */
    public int showImageWithMenu(PGM imgPGM) {

        String menuHTML = "<html>";
        menuHTML += "Please pick an action:<hr>";
        menuHTML += "0. Load a New Image<br>";
        menuHTML += "1. Generate Palette & Save<br>";
        menuHTML += "2. Generate Edge & Save<br>";
        menuHTML += "3. Blend \"Palette + Edge\", Save & Quit<br>";
        menuHTML += "<br>";
        menuHTML += "Palette ready? " + (isPaletteReady ? "Yes" : "No") + "<br>";
        menuHTML += "Edge ready?    " + (isEdgeReady ? "Yes" : "No") + "<br>";
        menuHTML += "</html>";
        String[] options = {"Load New", "Gen Palette & Save", "Gen Edge & Save", "Blend, Save & Quit"};

        int w = imgPGM.getWidth();
        int h = imgPGM.getHeight();
        int image[][] = {{0}};
        BufferedImage img;

        if (h <= 0 || w <= 0 || imgPGM.getImage() == null) {
            JOptionPane.showConfirmDialog(null, "Width x Height = " + imgPGM.getWidth() + "x" + imgPGM.getHeight(), " corrupted!", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, null);
            w = h = 1;
        } else {
            image = imgPGM.getImage();
        }
    
        img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                img.setRGB(col, row, new Color(image[row][col], image[row][col], image[row][col]).getRGB());
            }
        }

        ImageIcon icon = new ImageIcon(img);
        int choice = JOptionPane.showOptionDialog(null, menuHTML, this.getClass().getSimpleName(), 0, 0, icon, options, null);

        System.out.println("Choice: " + choice);
        return choice;
    }

    // Show image on screen
    public void showImageOnly(String title, PGM imgPGM) {
        if (imgPGM.getHeight() <= 0 || imgPGM.getWidth() <= 0 || imgPGM.getImage() == null) {
            JOptionPane.showConfirmDialog(null, "Width x Height = " + imgPGM.getWidth() + "x" + imgPGM.getHeight(), " corrupted!", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, null);
            return;
        }

        BufferedImage img = new BufferedImage(imgPGM.getWidth(), imgPGM.getHeight(), BufferedImage.TYPE_INT_RGB);

        int image[][] = imgPGM.getImage();

        for (int row = 0; row < imgPGM.getHeight(); row++) {
            for (int col = 0; col < imgPGM.getWidth(); col++) {
                img.setRGB(col, row, new Color(image[row][col], image[row][col], image[row][col]).getRGB());
            }
         }
        JOptionPane.showConfirmDialog(null, "", title, JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(img));
    }

    // Show a file dialog and get user's input of filename
    public static String getNameFromFileDialog() {
        JFrame frame = new JFrame(); // frame for file dialog

        FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
        fd.setDirectory(".");
        fd.setFile("*.pgm");  // for Windows
        fd.setFilenameFilter(new FilenameFilter() { // for MacOS
             @Override
          public boolean accept(File dir, String name) {
              return name.endsWith(".pgm");
          }
        });
        fd.setVisible(true);
        String filename = fd.getFile();
        fd.dispose();
        frame.dispose();
        return filename;
    }
    
     public PGM palette(PGM imgPGM) {
    /*** student's work here to generate palette by median filter ***/

        PGM resultPGM;

        int w, h;
        int [][] resultArray;
        int filterValue;
        
        w = imgPGM.getWidth();
        h = imgPGM.getHeight();
        resultArray = new int[w][h];
                
        for (int x = 0; x < w; x++)
        {
             for (int y = 0; y < h; y++)
             {
                 filterValue = this.median(imgPGM, x, y);
                 resultArray[x][y] = filterValue;
             }
         }
        
        resultPGM = new PGM("palette.pgm", resultArray, w, h);
        
        return resultPGM;
    }

    public int median(PGM imgPGM, int x, int y){
        
        int[] sortArray;
        boolean outWidthBound, outHeightBound;
        int idx;
        int w, h;
        
        sortArray = new int[9];
        int[][] imgCur = imgPGM.getImage();
        w = imgPGM.getWidth();
        h = imgPGM.getHeight();
        
        // 1. put all values into sortArray
        for (int i = -1; i <= 1; i++)
        {
            // check if row index is out of bound: below 0 or above width
            outWidthBound = x+i < 0 || x+i >= w;
            
            for (int j = -1; j <= 1; j++)
            {
                // check if row index is out of bound: below 0 or above width
                outHeightBound = y+j < 0 || y+j >= h;
                
                // Assign values
                // [[0 1 2],
                //  [3 4 5],
                //  [6 7 8]]
                idx = (i+1) + 3 * (j+1);
                
                
                if(outWidthBound || outHeightBound) // if out of bound: assign 0
                {
                    sortArray[idx] = 0;
                }
                else                                // else assign original value 
                {
                    sortArray[idx] = imgCur[x+i][y+j];
                }
                
            }
        }
        
        // 2. sort
        Arrays.sort(sortArray);
        
        // 3. return median
        return sortArray[4];
    }
     
    // define some help methods e.g. PGM median() { //... }
    
    public PGM edge(PGM imgPGM) {
    /*** student's work here to generate edge by convolution-based filters ***/
    PGM result;
    
    int[][] resultArr;
    int w, h;
    
    w = imgPGM.getWidth();
    h = imgPGM.getHeight();

    resultArr = this.convolute(imgPGM);
    // invert the intensity value i.e new_value = 255 – old_value;
    for(int x = 0; x < w; x++)
    {
        for(int y = 0; y < h; y++)
        {
            resultArr[x][y] = 255 - resultArr[x][y];
        }
    }
    
    result = new PGM("edge.pgm", resultArr, w, h);
    return result;
    }
    
    
    public int[][] convolute(PGM imgPGM){
        
        int w, h;
        double[][] imgCurDouble, boxBlurImg, laplaceImg;
        int resultValue;
        int[][] resultImg;
        
        // 0. prepare data
        int[][] imgCur = imgPGM.getImage();
        w = imgPGM.getWidth();
        h = imgPGM.getHeight();
        
        imgCurDouble = new double[w][h];
        for (int i = 0; i < w; i++)
        {
            for (int j = 0; j < h; j++)
            {
                imgCurDouble[i][j] = (double)imgCur[i][j];
            }
        }
        
        // 1. convolutional layer 1 : Box Blur
        boxBlurImg = new double[w][h];
        for (int x = 0; x < w; x++)
        {
            for (int y = 0; y < h; y++)
            {
                boxBlurImg[x][y] = boxBlurFilter(imgCurDouble, x, y, w, h);
            }
        }
        
        // 2. convolutional layer 2 : Laplace
        laplaceImg = new double[w][h];
        for (int x = 0; x < w; x++)
        {
            for (int y = 0; y < h; y++)
            {
                laplaceImg[x][y] = laplaceFilter(boxBlurImg, x, y, w, h);
            }
        }
        
        // 3. after 2 conv. result int[][]
        resultImg = new int[w][h];
        for (int x = 0; x < w; x++)
        {
            for (int y = 0; y < w; y++)
            {
                resultValue = (int)Math.round(laplaceImg[x][y]);
                if(resultValue > 255) resultValue = 255;
                if(resultValue < 0) resultValue =0;
                resultImg[x][y] = resultValue;
            }
        }
        return resultImg;
    }

    public double boxBlurFilter(double[][] image, int x, int y, int w, int h){
        // Box Blur Filter
        // for 3 x 3, 
        // blend 
        // 1/9 1/9 1/9
        // 1/9 1/9 1/9
        // 1/9 1/9 1/9
                
        boolean outWidthBound, outHeightBound;
        double sum = 0; // store sum of sub-part 
        double result; // result : sum/9
        
        
        // 1. put all values into sortArray
        for (int i = -1; i <= 1; i++)
        {
            // check if row index is out of bound: below 0 or above width
            outWidthBound = x+i < 0 || x+i >= w;
            
            for (int j = -1; j <= 1; j++)
            {
                // check if row index is out of bound: below 0 or above width
                outHeightBound = y+j < 0 || y+j >= h;
                
                if(outWidthBound || outHeightBound) // if out of bound: assign 0
                {
                    sum += 0;
                }
                else                                // else assign original value 
                {
                    sum += image[x+i][y+j];
                }
            }
        }
        
        result = sum / 9;
        return result;
    }
    
    public double laplaceFilter(double [][] image, int x, int y, int w, int h){
        // Laplace Filter
        // for 3 x 3
        // blend 
        // -1 -1 -1
        // -1  8 -1
        // -1 -1 -1
        boolean outWidthBound, outHeightBound;
        double result = 0;
        int idx;
        double[][] laplace = {{-1, -1, -1},{-1, 8, -1},{-1, -1, -1}};
        
        // 1. put all values into sortArray
        for (int i = -1; i <= 1; i++)
        {
            // check if row index is out of bound: below 0 or above width
            outWidthBound = x+i < 0 || x+i >= w;
            
            for (int j = -1; j <= 1; j++)
            {
                // check if row index is out of bound: below 0 or above width
                outHeightBound = y+j < 0 || y+j >= h;
                
                if(outWidthBound || outHeightBound) // if out of bound: assign 0
                {
                    result += 0;
                }
                else                                // else multiply by filter 
                {
                    result += image[x+i][y+j] * laplace[i+1][j+1];
                }
                
            }
        }
        
        return result;
        
    }
    
    public PGM averageBlend(PGM a, PGM b)
    {
        // width, height should be same
        PGM result;
        int[][] blend, aImage, bImage;
        int w,h;
        
        w = a.getWidth();
        h = a.getHeight();
        
        blend = new int[w][h];
        aImage = a.getImage();
        bImage = b.getImage();

        for (int x = 0; x < w; x++)
        {
            for (int y = 0; y < h; y++)
            {
                blend[x][y] = (aImage[x][y] + bImage[x][y])/2;
            }
        }
        
        result = new PGM("toonified.pgm", blend, w, h);
        
        return result;       
    }
    
    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        // Main Methods
        
        // a) Create a new ImageToonifier object.
        ImageToonifier toonifier = new ImageToonifier();
        
        // b) Display two initial PGM files using a method showImageOnly() 
        // and the given image file“boat_316x316.pgm” using another method showImageWithMenu().
        
        // b-1
        PGM imgDefault;
        imgDefault = new PGM();
        toonifier.showImageOnly("Simple PGM", imgDefault);
        
        PGM imgBlank;
        imgBlank = new PGM("Mid Gray", 40, 30);
        toonifier.showImageOnly("Blank PGM", imgBlank);
        
        // b-2
        String filename = "boat_316x316.pgm"; // default file at start
        PGM imgFileCurrent, curPalette, curEdge;
        imgFileCurrent = new PGM(filename);
        
        int status;
        boolean exit = false;
        status = toonifier.showImageWithMenu(imgFileCurrent);
        
        // c) Request filename from file dialog calling method getNameFromFileDialog() 
        // as needed and reset flags.

        // d) Process images and repeatedly call showImageWithMenu() and showImageOnly(). 
        
        
        /*** student's work here to repeatedly obtain and process user’s input ***/
        
        while(status != -1)
        {
            switch(status)
            {
                case 0:
                    filename = getNameFromFileDialog();
                    toonifier.setPaletteFalse();
                    toonifier.setEdgeFalse();
                    imgFileCurrent = new PGM(filename);
                    break;
                case 1:
                    curPalette = toonifier.palette(imgFileCurrent);
                    toonifier.showImageOnly("Palette generated, click OK to save", curPalette);
                    curPalette.write("palette.pgm", curPalette);
                    toonifier.setPaletteTrue();
                    break;
                case 2:
                    curEdge = toonifier.edge(imgFileCurrent);
                    toonifier.showImageOnly("Edge generated, click OK to save", curEdge);
                    curEdge.write("edge.pgm", curEdge);
                    toonifier.setEdgeTrue();
                    break;
                case 3:
                    if(toonifier.getStatus() == false)
                    {
                        // warning sign
                        JOptionPane.showMessageDialog(null, "Filtered images not all ready, generate them first", "Warning", JOptionPane.WARNING_MESSAGE);
                    }    
                    else
                    {
                        PGM palette = new PGM("palette.pgm");
                        PGM edge = new PGM("edge.pgm");
                        PGM toonified = toonifier.averageBlend(palette, edge);
                        toonifier.showImageOnly("Toonified Image generated, click OK to save", toonified);
                        toonified.write("toonifed.pgm", toonified);
                        
                        JOptionPane.showMessageDialog(null, "Toonifying Done, press \"OK\" to quit.", "Warning", JOptionPane.WARNING_MESSAGE);

                        exit = true;
                    }
                    break;
            }
            if(exit == true) break;
            status = toonifier.showImageWithMenu(imgFileCurrent);

        }
        // End of your code
    }
}
