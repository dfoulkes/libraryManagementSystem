package src;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This will host as the runtime for the new app.
 * @author danfoulkes
 *
 */
public class LibraryApp {
	
        private static Scanner sc = new Scanner(System.in); //test
     //   private static ArrayList<Book> booksName;
        private static Member mMembers;
	
	public static void main(String[] args) {
		
         //  booksName = new ArrayList<Book>();
           mMembers = new Member();
           
//           mMembers.addNewAdmin("tabrez", "123");
//           mMembers.addNewUser("razmi", "321");

            mMembers.addSomeDefaultAdmin("tab", "123");
            mMembers.addSomeDefaultMembers("raz", "123");
            mMembers.addSomeDefaultMembers("nah", "123");
            mMembers.addSomeDefaultMembers("bap", "123");
            mMembers.addSomeDefaultMembers("maa", "123");
           
            getUserInforLogin();
            
            
	}

    public static void getUserInforLogin() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            System.out.println("want to login ?");
            String loginTest = sc.next();
            if(loginTest.equals("yes")){
                
                System.out.println("Enter Name: ");
                String inputName = sc.next();
                System.out.println("Enter Password: ");
                String inputPassword = sc.next();
           
                mMembers.login(inputName, inputPassword);
            }
    }
  
}
