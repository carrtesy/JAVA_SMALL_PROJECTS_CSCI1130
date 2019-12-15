package chart;

/**
 * CosineFunction in radian is a type of Function.
 * A CosineFunction object is an instanceof Function
 * @author pffung
 */
public class CosineFunction implements Function {
    
    public CosineFunction()
    {
        System.out.println("Constructing a new " + getClass().getSimpleName() + " object " + this);
        System.out.println("This is an instance of Function: " + (this instanceof Function));
    }
    
    @Override
    public String toString()
    {
        return String.format("cos(x)");
    }
    
    @Override
    public double valueAt(double x)
    {
        return Math.cos(x);
    }
    
}
