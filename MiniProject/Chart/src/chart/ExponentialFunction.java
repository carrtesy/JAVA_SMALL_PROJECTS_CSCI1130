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

/**
 *
 * @author Dongmin Kim
 */
public class ExponentialFunction implements Function {
    
    public ExponentialFunction()
    {
        System.out.println("Constructing a new " + getClass().getSimpleName() + " object " + this);
        System.out.println("This is an instance of Function: " + (this instanceof Function));
    }
    
    @Override
    public String toString()
    {
        return String.format("e^x");
    }
    
    @Override
    public double valueAt(double x)
    {
        return Math.exp(x);
    }
}