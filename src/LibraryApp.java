package src;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This will host as the runtime for the new app.
 * @author danfoulkes
 *
 */
public class LibraryApp {
	
	private static ArrayList<String> booksName;
        private Scanner sc = new Scanner(System.in); //test
	
	public static void main(String[] args) {
		
            booksName = new ArrayList<>();
            
	}
        
        private void addBooks(String name){
            booksName.add(name);
        }

}
