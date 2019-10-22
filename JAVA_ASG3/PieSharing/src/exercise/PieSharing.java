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

/**
 * PieSharing
 * Introduction to Computing: Java Assignment
 * @author Dongmin Kim
 * @since  15 October 2019
 */
public class PieSharing {

    /**
     * main method
     */
    public static void main(String[] args) {
        
        String title;
        String ans = null;
        PanelDisplay pd = new PanelDisplay();
        
        // 1. Three pre-defined PieQuestions
        PieQuestion t1 = new PieQuestion("Trial 1", 1, 4, '+', 1, 2);
        PieQuestion t2 = new PieQuestion("Trial 2", 2, 5, '-', 1, 6);
        PieQuestion t3 = new PieQuestion("Trial 3", 1, 7, '-', 1, 8);
        
        
        PieQuestion [] t = {t1, t2, t3};
        for(PieQuestion pq : t)
        {
            while(true)
            {
                do{
                    ans = pq.getUserInputAnswer();
                } while(pq.checkInput(ans) == false);
                        
                if(pq.checkAnswer(ans)){
                    break;
                } else {
                    pd.updateHints(pq.a, pq.b, pq.op, pq.c, pq.d);
                }
            }
        }
        // 2. Ten random PieQuestions
        PieQuestion rp;
        
        for(int i = 1; i<=10; i++){
            title = "Q."+i;
            rp = new PieQuestion(title);
            
            while(true)
            {
                do{
                    ans = rp.getUserInputAnswer();
                } while(rp.checkInput(ans) == false);
                
                if(rp.checkAnswer(ans)){
                    pd.setScore(pd.getScore()+1);
                    break;
                } else {
                    pd.updateHints(rp.a, rp.b, rp.op, rp.c, rp.d);
                }
            }
            
       
        }
        
    }
    
}
