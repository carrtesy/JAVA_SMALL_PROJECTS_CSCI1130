package chart;

/**
 * QuadraticFunction is a type of Function
 * A QuadraticFunction object is an instanceof Function
 * @author pffung
 */
public class QuadraticFunction implements Function {
    
    public double A, B, C;
    
    public QuadraticFunction(double A, double B, double C)
    {
        this.A = A;
        this.B = B;
        this.C = C;
        System.out.println("Constructing a new " + getClass().getSimpleName() + " object " + this);
        System.out.println("This is an instance of Function: " + (this instanceof Function));
    }
    
    @Override
    public String toString()
    {
        return String.format("%f^2x%+fx%+f", A, B, C);
    }
    
    @Override
    public double valueAt(double x)
    {
        return A*x*x + B*x + C;
    }
}
