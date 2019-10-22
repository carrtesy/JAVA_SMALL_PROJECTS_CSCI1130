/*
 * CSCI1130 Assignment 2 Future Value
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
 * Date        : 2019-09-25
 */

package exercise;

import java.util.Scanner;

/**
 * Future Value
 * Introduction to Computing: Java Assignment
 * @author Dongmin Kim
 * @since  25 September 2019
 */

public class FutureValue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        Assigning Variables,
        With setting scanner
        */
        
        double p;                               // Principal 
        double r;                               // Annual Interest Rate
        int t;                                  // Timespan
        int comp_period;                        // Compounding Period
        
        Scanner scan = new Scanner(System.in);  // Scanner Setting for inputs
        
        /*
        First Phase: Get Input
            Principal [$10000.00 - $109700.00]
            Annual Interest Rate [1.0% - 10.0%]
            Timespan [2 - 10 years]
            Compounding Period [1, 6, or 12 months]
            if it is out of range, reject to get value
        */
        
        // 1. Principal
        System.out.print("Input Principal [$10000.00 - $109700.00]: ");
        p = scan.nextDouble(); 
        while (p < 10000 || p > 109700){
            System.out.println("Invalid Principal, please enter again.");
            System.out.println("Input Principal [$10000.00 - $109700.00]: ");
            p = scan.nextDouble();
        }
        
        // 2. Annual Interest Rate
        System.out.print("Input Annual Interest Rate [1.0% - 10.0%]: ");
        r = scan.nextDouble();
        while (r < 1 || r > 10){
            System.out.println("Invalid Annual Interest Rate, please enter again.");
            System.out.println("Input Annual Interest Rate [1.0% - 10.0%]: ");
            r = scan.nextDouble();
        }
        r = r/100; // to represent percentage value, divide by 100
        
        // 3. Timespan
        System.out.print("Input Timespan [2 - 10 years]: ");
        t = scan.nextInt();
        while (t < 2 || t > 10){
            System.out.println("Invalid Timespan, please enter again."); 
            System.out.println("Input Timespan [2 - 10 years]: ");
            t = scan.nextInt();
        }
        
        // 4. Compounding Period
        System.out.print("Input Compounding Period [1, 6, or 12 months]: ");
        comp_period = scan.nextInt();
        while (!(comp_period == 1 || comp_period == 6 || comp_period == 12)){
            System.out.println("Invalid Compounding Period, please enter again.");
            System.out.println("Input Compounding Period [1, 6, or 12 months]: ");
            comp_period = scan.nextInt();
        }
        
        /*
        Second Phase: Print various future values
        3 possible cases are:
            1) printing twice: print first period, and last period
            2) printing three times: print first & second period, and last period
            3) printing more than three times print first & second period, print '&', and last period
        Apply the formula FV = P * (1+r/m)^(t*m)
        */
        
        int m = 12 / comp_period; // compounding frequency per year
        int print_times = m*t;    // printing times for this
        
        switch(print_times){
            case 2:
                System.out.printf("Future Value after %d months: %.2f\n", comp_period * 1 ,p * Math.pow( 1 + r/m, 1));
                break;
            case 3:
                System.out.printf("Future Value after %d months: %.2f\n", comp_period * 1 ,p * Math.pow( 1 + r/m, 1));
                System.out.printf("Future Value after %d months: %.2f\n", comp_period * 2 ,p * Math.pow( 1 + r/m, 2));
                break;
            default:
                System.out.printf("Future Value after %d months: %.2f\n", comp_period * 1 ,p * Math.pow( 1 + r/m, 1));
                System.out.printf("Future Value after %d months: %.2f\n", comp_period * 2 ,p * Math.pow( 1 + r/m, 2));
                System.out.println("...");
        }
        System.out.printf("Future Value at the end: %.2f\n", p * Math.pow( 1 + (r/m), m*t));
        

        /*
        Phase 3: Print time to obtain x2 x3 x4
        Apply the formula that
        n * P = P * (1+r/m)^(t*m)
        <=> ln(n) = t * m * ln(1+r/m)
        <=> t = ln(n)/(m* ln(1+r/m))
        */
        
        for(int i = 2; i <= 4; i++)
        {
            System.out.printf("Time to obtain asset x%d: %.2f years\n", i, (Math.log(i)) / (Math.log(1+(r/m))*m));
        }
        
    }
    
}
