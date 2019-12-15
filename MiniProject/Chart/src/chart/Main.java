/**
 * CSCI1130 Mini-project Chart
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
 *   https://www.erg.cuhk.edu.hk/erg/AcademicHonesty
 *
 * Student Name: Dongmin Kim
 * Student ID  : 1155139586
 * Date        : 2019-12-08
 */

package chart;

/**
 * Chart project Main class
 * @author pffung
 */
public class Main {
    public static void main(String[] args)
    {
//        Chart.main(null);
        
        // reference: https://www.wolframalpha.com/input/?i=y=x%5E2-5x%2B6
        Function quad_root_2_root_3_y_intercept_6 = new QuadraticFunction(1, -5, 6);
        
        FunctionChart fc1;
        fc1 = new FunctionChart(quad_root_2_root_3_y_intercept_6, 3.9, 1.7, 0.1);
        
        FunctionChart fc2;
        fc2 = new FunctionChart(quad_root_2_root_3_y_intercept_6, 3, 6.2, 0.2);

        Function cosine = new CosineFunction();
        FunctionChart fc3;
        fc3 = new FunctionChart(cosine, Math.PI * 2.5, 1.1, 0.3);

        Function ex = new ExponentialFunction();
        FunctionChart fc4;
        fc4 = new FunctionChart(ex, 10, 100, .5);  // plot e^x Function in macro scale

        FunctionChart fc5;
        fc5 = new FunctionChart(ex, 10, 0.1, .5);  // plot e^x Function in micro scale

        
        double[] X1 = {1,  5, 6, -9,  0, 8, 10, -15, 0};
        double[] Y1 = {5, -5, 0,  6, -7, 7, 10,   0, 8};
        ScatterChart sc1;
        sc1 = new ScatterChart(X1, Y1);

        double[] X2 = {-1000, -800, -10, -256, -512};
        double[] Y2 = {-256,  -300, -400, -10, -999};
        ScatterChart sc2;
        sc2 = new ScatterChart(X2, Y2);

    }
}

