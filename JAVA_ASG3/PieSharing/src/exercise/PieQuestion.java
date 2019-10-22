/*
 *  CSCI1130 Assignment 3 PieSharing
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
 *  Date        : 2019-10-15
 */
package exercise;
import javax.swing.JOptionPane;
import java.util.Random;

/**
 * PieSharing
 * Introduction to Computing: Java Assignment
 * @author Dongmin Kim
 * @since  15 October 2019
 */
public class PieQuestion {
    /*
     Question has 6 variables, which are
        title, operation(+,-), and two fractions(a/b, c/d) 
    */
    public String title;
    public int a, b;
    public char op;
    public int c, d;
    
    /*
        Constructors for 6-parameters given cases
    */
    public PieQuestion(String title, int a, int b, char op, int c, int d){
        // get title, without checking values
        this.title = title;
        
        /*
            variables to check input validity
            flag: overall validity
            denomcheck: denominator value in 2-8
            numcheck: numerator value in 1-7
        */
        boolean flag = true;
        boolean denomCheck = b < 2 || 8 < b || d < 2 || 8 < d;
        boolean numCheck = a < 1 || 7 < a || c < 1 || 7 < c;
        float answer;
        
        // check if a,b,c,d is in proper range (denominator 2 - 8, numerator 1 - 7)
        if(denomCheck || numCheck){flag = false;}
        
        // check if op is in proper range (+, -)
        switch(op)
        {
            /*
                if operator is neither + nor -, the parameter is not valid.
            */
            case '+':
                answer = ((float) a / (float) b) + ((float) c / (float) d);
                break;
            case '-':
                answer = ((float) a / (float) b) - ((float) c / (float) d);
                break;
            default:
                flag = false;
                answer = -1;
        }
        
        // should be in 0< ans/den <1
        if (answer <= 0 || answer >= 1) {
                flag = false;
        }
        // if not satisfied, set the question to 1/4 + 1/2 by default
        // otherwise accept parameters
        if(flag == false){
            this.a = 1; this.b = 4;
            this.op = '+';
            this.c = 1; this.d = 2;
        } else {
            this.a = a; this.b = b;
            this.op = op;
            this.c = c; this.d = d;
        }
    }
    /*
        Constructor with only parameters title.
        others should be given in random manner.
    */
    public PieQuestion(String title){
        // random generation, need to be properly generated
        this.title = title;
        long seed; // seed to produce random integers
        Random rngObj;
        int a, b, c, d;
        float result;
        
        // set a,b,c,d,op is in proper range (denominator 2 - 8, numerator 1 - 7)
        while(true){
            /*
                Use system time to get random integer
                if it is out of valid range, continue and get other value.
                otherwise get break.
            */
            seed = System.currentTimeMillis(); 
            rngObj = new Random(seed);
            a = rngObj.nextInt(7)+2;
            c = rngObj.nextInt(7)+2;
            op = rngObj.nextInt(2) == 1? '+':'-';
            b = rngObj.nextInt(7)+1;
            d = rngObj.nextInt(7)+1;
            
            if(a>=b || b>=d) continue;
            result = (op == '+')? ((float)a/b) + ((float)c/d):((float)a/b) - ((float)c/d);
            
            if(result > 0 && result < 1) {
                break;
            } 
        }
        
        /*
            divide by gcd to get reduced form.
        */
        this.a = a / this.getGcd(a,b);
        this.b = b / this.getGcd(a,b);
        this.op = op;
        this.c = c / this.getGcd(c,d);
        this.d = d / this.getGcd(c,d);
    }
    
    /*
        Set the title of the question and get user input answer
    */
    public String getUserInputAnswer(){
        
        String dialog; 
        dialog = this.title+":"+this.a+"/"+this.b+" "+this.op+" "+this.c+"/"+this.d + " = ?/"+ this.getDenom();
        String inputAns = JOptionPane.showInputDialog(dialog , "<type answer here>");

        return inputAns;
    }
    
    /*
        return the denominator of the question using the data of question
    */
    public int getDenom(){
        int ans;
        int plus = this.a * this.d + this.b * this.c;
        int minus = this.a * this.d - this.b * this.c;
        int numer = this.op == '+'? plus:minus;
        int denom = this.b * this.d;
        
        ans = denom / this.getGcd(numer, denom);
        return ans;
    }
    
    /*
        return the answer of the question using the data of question
    */
    public int getAnswer(){
        int ans;
        int plus = this.a * this.d + this.b * this.c;
        int minus = this.a * this.d - this.b * this.c;
        int numer = this.op == '+'? plus:minus;
        int denom = this.b * this.d;
        
        ans = numer / this.getGcd(numer, denom);
        return ans;
    }
    
    /*
        Check the validity of the input
        Check if it is Null or contains characters other than digit
    */
    public boolean checkInput(String ans){
        if (ans == null) return false;
        
        for (int i = 0; i < ans.length(); i++) 
            if (Character.isDigit(ans.charAt(i)) == false) return false;  
        return true;
    }
    
    /*
        Check if answer is correct
    */
    public boolean checkAnswer(String ans){
        boolean correct = Integer.parseInt(ans) == this.getAnswer();
        return correct;
    }
    
    /*
        Get Great Common Divisor(GCD)
        Using Euclidean Algorithm
    */
    public int getGcd(int a, int b)
    {
        int _a = a > b? a: b;
        int _b = a > b? b: a;
        int r;
        
        while(_a != _b)
        {
            r = _a % _b;
            _a = _b;
            _b = r!=0? r: _b;
        }
        
        return _a;    
    }
}
