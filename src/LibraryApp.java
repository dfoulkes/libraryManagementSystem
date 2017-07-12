import java.util.ArrayList;
import java.util.Scanner;
import com.library.userroles.UserRole;
import com.library.backlog.*;
/**
 * This will host as the runtime for the new app.
 * @author muhammadtayyabsaeed
 *
 */
public class LibraryApp {   
    
    
    public static Library library = new Library();
    
    
    
    
    public static  void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter your user name: ");
        String Username = sc.nextLine();
        System.out.println("Please enter your password: ");
        String Password = sc.nextLine(); 
        //sc.close(); don't close the scanner
        
        library.login(Username, Password);
        
        
        
         //////////////better would be if I use exception here book not found
        
        //adminstrativeUser.login();
        
        //I'll do alot changes here as soon as i get a fresh mind of monday may be 
        
        }
    }