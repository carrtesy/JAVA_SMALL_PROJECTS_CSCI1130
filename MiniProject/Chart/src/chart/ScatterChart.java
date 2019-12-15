/*
 *  CSCI1130 MINI PROJECT
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
 *  Date        : 2019-12-08
 */
package chart;
import java.awt.Color;
import java.util.Arrays;


/**
 *
 * @author Dongmin Kim
 */
public class ScatterChart extends Chart {
    protected double[] X, Y;
    protected double minX, maxX, minY, maxY;
    protected int datanum;
    
    public ScatterChart(double[] X, double[] Y)
    {
        this.X = X;
        this.Y = Y;
        this.minX = getMin(X);
        this.minY = getMin(Y);
        this.maxX = getMax(X);
        this.maxY = getMax(Y);
        this.datanum = X.length;
        this.setTitle(getClass().getSimpleName());
        
        drawAxes();
        plot();
    }
    
    public double getMin(double[] arr)
    {
        double min = arr[0];
        
        for(int i=0; i<arr.length; i++)
        {
            if (arr[i] < min) min = arr[i];
        }
        
        return min;
    }
    
    public double getMax(double[] arr)
    {
        double max = arr[0];
        
        for(int i=0; i<arr.length; i++)
        {
            if (arr[i] > max) max = arr[i];
        }
        
        return max;
    }
    
    public double getMedian(double[] arr)
    {
        double[] tmp = arr.clone();
        Arrays.sort(tmp);
        System.out.println("MEDIAN: "+ tmp[tmp.length/2]);
        return tmp[tmp.length/2];
    }
    
    /*
        drawAxes(), plot() : with the margin of 10px(10% margin each)
        --------------------
        |       10         |
        |   -----------    |
        |10 |         | 10 |
        |   |         |    | 
        |   -----------    |
        |       10         |      
        --------------------
    */
    public void drawAxes()
    {
        double rangeX = maxX - minX;
        double rangeY = maxY - minY;
        double X_0 = (((0 - minX) * 0.8) /rangeX) + 0.1;
        double Y_0 = (((0 - minY) * 0.8) /rangeY) + 0.1;
        
        this.drawLine(0, Y_0, 1, Y_0, Color.BLUE); // X axis
        this.drawLine(X_0, 0, X_0, 1, Color.BLUE); // Y axis
        validate();
    }
            
    public void plot()
    {
        double rangeX = maxX - minX;
        double rangeY = maxY - minY;
        double xcoord, ycoord;
        
        for(int i = 0; i < datanum; i++)
        {
            xcoord = (((X[i]-minX) * 0.8)/ rangeX) + 0.1;
            ycoord = (((Y[i]-minY) * 0.8)/ rangeY) + 0.1;
            drawDot( xcoord, ycoord, Color.ORANGE );
        }
        
        validate();
    }
    
}



    

