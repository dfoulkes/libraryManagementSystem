package src;

import java.util.ArrayList;

/**
 * This will host as the runtime for the new app.
 * @author danfoulkes
 *
 */
public class LibraryApp {
	
	private static ArrayList<String> booksName;
	
	public static void main(String[] args) {
		
            booksName = new ArrayList<>();
            
	}
        
        private void addBooks(String name){
            booksName.add(name);
        }

}
