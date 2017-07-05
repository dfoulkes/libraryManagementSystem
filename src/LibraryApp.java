import java.util.ArrayList;

/**
 * This will host as the runtime for the new app.
 * @author muhammadtayyabsaeed
 *
 */
public class LibraryApp {
	
	static ArrayList<String> book;
	
	
	public  void main(String[] args) {
		
		book = new ArrayList<String>();
		
	}
	
	//Adding new books to the ArrayList<String> book
	public static void addNewBook(String bookName){
		if(book.contains(bookName)){
			System.out.println("The book already exists");
		}
		else{
			book.add(bookName);
		}
	}
	
	//registering a new book
	
	public void 
	
}
