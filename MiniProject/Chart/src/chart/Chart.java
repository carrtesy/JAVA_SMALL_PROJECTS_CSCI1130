package chart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JFrame;

/**
 * Chart - Java mini-project, focusing on sub-classing.
 * To simplify the task, screen coordinates system transformation is provided.
 * That is,
 * <UL>
 * <LI> window frame dimensions adjustment and resize operations are handled.
 * <LI> origin (0, 0) is always at bottom left corner of the chart area.
 * <LI> irrespective of window size, chart x-y coordinates are in [0.0 - 1.0].
 * </UL>
 * @author pffung
 */
public class Chart extends JFrame {
    private static int chartCount = 0;
    protected int chartWidth;
    protected int chartHeight;
    protected int windowFrameXoffset;
    protected int windowFrameYoffset;
    public static final int DOT_SIZE = 6;
    
    // GUI and main() operations are executing in different threads, kind of in parallel.
    // We need concurrent data structures to provide thread-safe operations.
    //
    // For example: paint() method is doing its job of drawing dots
    //              while our main() method is adding a dot to the dots Set;
    //              this will cause a read-write conflict!
    // Multi-threaded programming is common in multi-core and even single-core systems.
    //
    // Here, we cannot use HashSet because it is thread-unsafe;
    // yet there is NO ConcurrentHashSet provided.
    // Thus, we use ConcurrentHashMap and employ its keySet,
    // as a workaround in Java collections framework,
    // Boolean values TRUE are dummy place-holders.
    private ConcurrentHashMap<Point2D, Color> dots;
    private ConcurrentHashMap<Line2D, Color> lines;

    // Default constructor
    public Chart()
    {
        initialize(getClass().getSimpleName(), 300, 200);
    }

    /**
     * Constructor with Chart title
     * @param title will be shown on window title bar
     */
    public Chart(String title)
    {
        initialize(title, 300, 200);
    }
    
    /**
     * Constructor with Chart title and Chart dimensions
     * @param title will be shown on window title bar
     * @param width is Chart width, NOT window width
     * @param height is Chart height, NOT window height

     */
    public Chart(String title, int width, int height)
    {
        initialize(title, width, height);
    }
    
    protected final void initialize(String title, int width, int height)
    {
        chartWidth = width;
        chartHeight = height;

        setTitle(title);

        setVisible(true);
        // after setVisible, get MinimumSize to determine window frame dimensions
        windowFrameXoffset = getMinimumSize().width / 2;
        windowFrameYoffset = getMinimumSize().height - getMinimumSize().width / 2;
        setSize(chartWidth + getMinimumSize().width, chartHeight + getMinimumSize().height);  
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocation(chartCount * 150, chartCount * 50);
        chartCount++;
        
        clearShapes();
    }

    protected final void clearShapes()
    {
        // create data structures for storing shape information to be drawn
        dots  = new ConcurrentHashMap<>();
        lines = new ConcurrentHashMap<>();
    }
    
    /**
     * Get Chart width in pixels, disregard of window frame thickness.
     * @return Chart width
     */
    public int getChartWidth()
    {
        return chartWidth;
    }
    
    /**
     * Get Chart height in pixels, disregard of window frame thickness.
     * @return Chart height
     */
    public int getChartHeight()
    {
        return chartHeight;
    }

    /**
     * Transform Chart x-coordinate from [0.0 - 1.0] to screen x-coordinate.
     */
    public int transformX(double x)
    {
        return (int)(windowFrameXoffset + x * getChartWidth());
    }
    
    /**
     * Transform Chart y-coordinate from [0.0 - 1.0] to screen y-coordinate.
     */
    public int transformY(double y)
    {
        return (int)(windowFrameYoffset + getChartHeight() - y * getChartHeight());
    }
    
    /**
     * Standard GUI method for drawing shapes on screen.
     * We can override (re-define) this method.
     * In this class, we make it draw all the stored lines and dots.
     * Programmers shall NOT invoke this method directly!
     * The method repaint() will schedule a call to this method.
     * @param pen is a system provided Graphics object
     */
    @Override
    public void paint(Graphics pen)
    {
        // invoke super class (JFrame) paint() method
        super.paint(pen);
        
        // draw lines first
        if (lines != null)
        {
            Set<Line2D> copyOfElementsForThreadSafeOperation = lines.keySet();
            for (var aLine : copyOfElementsForThreadSafeOperation)
            {
                pen.setColor(lines.get(aLine));
                pen.drawLine(transformX(aLine.getX1()), transformY(aLine.getY1()),
                             transformX(aLine.getX2()), transformY(aLine.getY2()));
            }
        }
        // dots will lay on top of the lines
        if (dots != null)
        {
            Set<Point2D> copyOfElementsForThreadSafeOperation = dots.keySet();
            for (var aDot : copyOfElementsForThreadSafeOperation)
            {
                pen.setColor(dots.get(aDot));
                pen.fillOval(transformX(aDot.getX()) - DOT_SIZE/2, transformY(aDot.getY()) - DOT_SIZE/2, DOT_SIZE, DOT_SIZE);
            }
        }
    }
    
    /**
     * Draw a dot centered at (x, y) of diameter DOT_SIZE.
     * The shape information is stored first.  It will be displayed and redrawn later.
     * Other classes and subclasses may invoke this method.
     * @param x
     * @param y 
     */
    public void drawDot(double x, double y, Color c)
    {
        dots.put(new Point2D.Double(x, y), c);
        repaint();
    }
    
    /**
     * Draw a line segment (x1, y1) - (x2, y2).
     * The shape information is stored first.  It will be displayed and redrawn later.
     * Other classes and subclasses may invoke this method.
     * @param x1
     * @param y1
     * @param x2
     * @param y2 
     */
    public void drawLine(double x1, double y1, double x2, double y2, Color c)
    {
        lines.put(new Line2D.Double(x1, y1, x2, y2), c);
        repaint();
    }

    /* Chart main() method for illustration purpose */
    public static void main(String[] args) {

        Chart chart1 = new Chart();
        chart1.drawDot(0.0, 0.0, Color.WHITE);
        chart1.drawDot(0.5, 0.8, Color.ORANGE);
        chart1.drawLine(0, 0, 1, 1, Color.GREEN);
        chart1.setLocation(0, 300);

        
        
        Chart chart2 = new Chart("Mine Field");
        chart2.setAlwaysOnTop(true);
        for (int x = 0; x <= chart2.getChartWidth(); x += 50)
            for (int y = 0; y <= chart2.getChartHeight(); y += 35)
                chart2.drawDot((double)x / chart2.getChartWidth(), (double) y / chart2.getChartHeight(),
                               new Color((float)x / chart2.getChartWidth(), (float) y / chart2.getChartHeight(), (float) y / chart2.getChartHeight()));


        double minAngle = -360;
        double maxAngle = 540;
        double rangeAngle = maxAngle - minAngle;
        double angle = minAngle;
        // sin(angle) result in [-1.0 , +1.0]; so we need to shift up and scale down the y range
        double x = (angle - minAngle) / rangeAngle, y = (Math.sin(Math.toRadians(angle)) + 1.0) / 2;
        while (angle <= maxAngle)
        {
            angle += 5;
            chart2.drawLine(x, y, x = (angle - minAngle) / rangeAngle, y = (Math.sin(Math.toRadians(angle)) + 1.0) / 2, Color.BLACK);
        }
        
        
        
        Chart chart3 = new Chart("HSI", 500, 350);
        chart3.drawLine(0, 0, .30, .40, Color.RED);
        chart3.drawLine(      .30, .40, .60, .20, Color.ORANGE);
        chart3.drawLine(                .60, .20, .90, 0.78, Color.MAGENTA);
        chart3.drawLine(                          .90, 0.78, 0.99, .60, Color.GREEN);
        chart3.drawLine(1, 0, 0, 1, Color.BLUE);
    }
    
}
