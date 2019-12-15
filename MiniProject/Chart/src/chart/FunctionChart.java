/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chart;

import java.awt.Color;

/**
 * FunctionChart outlines a Function for x in [+/- extremeX] with interval size of stepX
 * @author pffung
 */
public class FunctionChart extends Chart {
    protected Function f;
    protected double minX, maxX, stepX, minY, maxY;
    
    public FunctionChart(Function f_x, double extremeX, double extremeY, double stepX)
    {
        f = f_x;
        this.maxX = Math.abs(extremeX);
        this.minX = -maxX;
        this.stepX = Math.abs(stepX);
        this.maxY = Math.abs(extremeY);
        this.minY = -maxY;
        this.setTitle(getClass().getSimpleName() + ": " + f);
        
        drawAxes();
        plot();
    }
    
    public void drawAxes()
    {
        this.drawLine(0, 0.5, 1, 0.5, Color.BLUE);
        this.drawLine(0.5, 0, 0.5, 1, Color.BLUE);
        validate();
    }
            
    public void plot()
    {
        double rangeX = maxX - minX;
        double rangeY = maxY - minY;
        double x1 = minX;
        double y1 = f.valueAt(x1);
        drawDot( (x1 - minX) / rangeX, (y1 - minY) / rangeY, Color.ORANGE);
        double x2 = x1 + stepX, y2;
        while (x2 <= maxX)
        {
            x2 = x1 + stepX;
            y2 = f.valueAt(x2);
            
            drawDot( (x2 - minX) / rangeX, (y2 - minY) / rangeY, Color.ORANGE);

            drawLine((x1 - minX) / rangeX, (y1 - minY) / rangeY,
                     (x2 - minX) / rangeX, (y2 - minY) / rangeY, Color.GREEN);

            x1 = x2;
            y1 = y2;
        }
        
        validate();
    }
}
