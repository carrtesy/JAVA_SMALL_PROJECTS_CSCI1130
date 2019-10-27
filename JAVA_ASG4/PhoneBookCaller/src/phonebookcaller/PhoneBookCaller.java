/*
 *  CSCI1130 Assignment #NUMBER #PROJECTNAME
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
 *  Date        : #DATE
 */
package phonebookcaller;
import java.util.HashMap; // for phone book data management
import javax.swing.*; // for GUI dialogues and image handling
import java.io.*; // for file opening and stream handling
import javazoom.jl.player.advanced.AdvancedPlayer; // for MP3 stream playing 

/**
 *
 * @author ehdal
 */
public class PhoneBookCaller {

    private static String dialogQuestionMarkIconImageFilename = "question-mark-1451232961b8W_from_publicdomainpictures.net_en_images=142577.gif";
    private HashMap<String, String> phoneBook; // a String-String mapping type
    
    private void clearAndSetupDefaultPhoneBook()
    {
        // drops, if any, existing phone book and creating a new one
        phoneBook = new HashMap<>();
        addContact("Rocky", "39437000");
        addContact("Kitty", "852-87654321");
        addContact("Betty", "(853) 91239123");
        addContact("Donald", "(86)-90018900");
        addContact("Roy", "+886 51903481");
        addContact("Shelly", "85271237890");
    }
    /**
    * Show all contacts in the phone book on System.out in some order AND
    * @return a String representation of the phone book in a HTML table
    */
    public String showAllContacts()
    {
        String table = "<table>";
        table += "<tr>";
        table += "<td>Name<td>Phone";
        table += "</tr>";
        // for-each: iterating all records in the phoneBook which is a HashMap
        for (String name : phoneBook.keySet()) {
            // String name will loop through all names (keys)
            String phone = phoneBook.get(name);
            table += "<tr>";
            table += "<td>" + name + "<td>" + phone;
            table += "</tr>";
         }
        table += "</table><hr>";

        System.out.printf("%-20s %s\n", "Name", "Phone");
        System.out.printf("-------------------- --------------\n");
        /*** student's work here to print the phone book on System.out ***/

        return table; // a String representation of the phone book in HTML
    }
    /**
    * Show a menu of choices and get user's input
    * @return an integer value: 0 means Quit, and options 1, 2, ...
    */
    public int showMenu()
    {
        String menuHTML = "<html>";
        menuHTML += showAllContacts();
        menuHTML += "Please pick an action:<hr>";
        menuHTML += "0. Quit<br>";
        menuHTML += "1. Add contact<br>";
        menuHTML += "2. Make a call<br>";
        menuHTML += "3. Clear ALL contact<br>";
        menuHTML += "4. Save ALL contact<br>";
        menuHTML += "</html>";
        String[] options = {"Quit", "Add", "Call", "Clear", "Save"};

        ImageIcon icon = new ImageIcon(dialogQuestionMarkIconImageFilename);
        int choice = JOptionPane.showOptionDialog(null, menuHTML,
        this.getClass().getSimpleName(), 0, 0, icon, options, null);
        if (choice == JOptionPane.CLOSED_OPTION) choice = 0; // CLOSED_OPTION = -1
        System.out.println("Choice: " + choice);
        
        return choice;
     }
    public void addContact(String name, String phone) {
        phoneBook.put(name, phone);
    }
 
    public void call(String name) {
    // When calling a number, "dial" each digit by playing the corresponding given MP3 recording file:
    //
    //"DTMF_DialTone_MP3/DTMF-n.mp3" where n is a digit in 0 – 9.
    //
    }
 
    public void savePhoneBook(String filename) {
        for (String i : phoneBook.keySet()){
            System.out.println(i);
        }
    }
 
    public static void playMP3File(String filename) {
 
        try{
            FileInputStream mp3Stream = new FileInputStream(filename);
            AdvancedPlayer mp3Player = new AdvancedPlayer(mp3Stream);
            mp3Player.play();
        } catch(Exception e) {
            e.printStackTrace();
        } 
        
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PhoneBookCaller pbc = new PhoneBookCaller(); 
        int option;
        
        pbc.clearAndSetupDefaultPhoneBook();
        
        while(true){
            option = pbc.showMenu();
            if(option == 0 ) break;
            
            switch(option){
                case 1:
                    System.out.println("add contract!");
                    break;
                case 2:
                    System.out.println("make a call!");
                    break;
                case 3:
                    System.out.println("clear all contract!");
                    break;
                case 4:
                    System.out.println("save all contract!");
                    break;
            }
        }
        
        pbc.playMP3File("DTMF_DialTone_MP3/DialTone.mp3");
        
    }
    
}
