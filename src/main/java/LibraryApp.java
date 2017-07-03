import com.library.builders.BookBuilder;
import com.library.builders.UserBuilder;
import com.library.domain.Book;
import com.library.domain.CheckoutTicket;
import com.library.domain.Library;
import com.library.domain.User;
import com.library.enums.Role;
import com.library.exceptions.UserNotFound;
import com.library.exceptions.noBookFoundException;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * This will host as the runtime for the new app.
 * @author danfoulkes
 *
 */
public class LibraryApp {

	Integer bookId = 0;
	Library library = new Library();
	Scanner in = new Scanner(System.in);
	User currentUser;

	public LibraryApp(){
		library.addUser(UserBuilder.getBuilder().withId(1l).withUsername("dan").withPassword("password").withRole(Role.ADMIN).build());
		library.addUser(UserBuilder.getBuilder().withId(1l).withUsername("user").withPassword("password").withRole(Role.USER).build());

	}

	public void login(){
		while(true) {
			System.out.println("Enter username");
			String username = in.nextLine();
			System.out.println("Enter password");
			String password = in.nextLine();
			try {
				currentUser = library.login(username, password);
				handleUser(currentUser);
			} catch (UserNotFound userNotFound) {
				System.out.println("invalid user please try again.");
			}
		}
	}

	private void handleUser(User user){
		Boolean exit = false;
		while(!exit) {
			if (Role.ADMIN.equals(user.getRole())) {
				exit = handleAdminUser(exit);
			}
			else if(Role.USER.equals(user.getRole())) {
				exit = handleBasicUser(exit);
			}
		}
	}

	private Boolean handleBasicUser(Boolean exit) {
		String action = getAction(userMenu());
		//show books
		if (action.equals("1")) {
			listBooksAvailable();
		}
		//check in book
		else if (action.equals("2")) {
			checkIn();
		}
		//check in book
		else if(action.equals("3")){
			checkedOut();
		}
		//checkout
		else if(action.equals("4")){
			exit =true;
		}
		return exit;
	}

	private void checkIn() {
		String action = getAction("Enter the id of your book");
		Book book = null;
		try {
			book = library.getBook(Integer.parseInt(action)).orElseThrow(noBookFoundException::new);
			library.checkIn(currentUser, book);
		} catch (noBookFoundException e) {
			System.out.println("Sorry.. could not find a book by this name");
		}
	}

	private void checkedOut() {
		String action = getAction("Enter the id of your book");
		Book book = null;
		try {
			book = library.getBook(Integer.parseInt(action)).orElseThrow(noBookFoundException::new);
			library.checkout(currentUser, book);
			System.out.println("Book is checked out");
		} catch (noBookFoundException e) {
			System.out.println("Sorry.. could not find a book by this name");
		}
	}

	private String userMenu() {
		StringBuilder builder = new StringBuilder();
		return builder.append("Menu \n\n")
				.append("1. List all books \n")
				.append("2. check-in book \n")
				.append("3. check-out book \n")
				.append("4. Logout \n")
				.toString();
	}

	private Boolean handleAdminUser(Boolean exit) {
		String action = getAction(adminMenu());
		if (action.equals("1")) {
			addBook();
        } else if (action.equals("2")) {
            showOverdueBooks();
        }
        else if(action.equals("3")){
			listBooks();
        }
		else if(action.equals("4")){
			exit =true;
		}
		return exit;
	}

	private void addBook(){
		String isdn  = getAction("isdn number");
		String title = getAction("title");
		bookId++;
		Book newBook = BookBuilder.getBuilder()
						.withTitle(title)
						.withIsdn(isdn)
						.withLibraryReferenceNumber(bookId)
						.build();
		library.addBook(newBook);
		System.out.println("Title "+title+" added to the collection.");
	}

	private String getAction(String message){
		System.out.println(message);
		return in.nextLine();
	}

	public String adminMenu(){
		StringBuilder builder = new StringBuilder();
		return builder.append("Menu \n\n")
				.append("1. Add Book \n")
				.append("2. show overdue \n")
				.append("3. List all books \n")
				.append("4. Logout \n")
				.toString();
	}

	private void listBooks(){
		Set<Book> booksList = library.getBooks();
		System.out.println("----- Books in the Library ------");
		booksList.stream()
				.forEach(x -> displayBook(x));
	}

	private void listBooksAvailable(){
		Set<Book> booksList = library.getBooks();
		System.out.println("----- Books in the Library ------");
		booksList.stream()
				.filter(x -> x.getTicket() == null || Boolean.FALSE.equals(x.getTicket().getCheckout()))
				.forEach(x -> displayBook(x));
	}

	private void displayBook(Book x) {
		System.out.println(bookDetails(x));
	}

	private String bookDetails(Book book){
		StringBuilder builder = new StringBuilder();
		return builder.append(book.getLibraryReferenceNumber()).append(", ")
				.append(book.getTitle()).append(", ")
				.append((book.getTicket() != null ? book.getTicket().getCheckout() : "NEW")).append(", ")
				.append(book.getTicket() != null ? book.getTicket().getUser().getUsername(): "No User has this book checked out")
				.toString();
	}


	private void showOverdueBooks() {
		try {
			List<Book> booksList = library.overDueBooks(currentUser);

			System.out.println("--------- Overdue Books ---------");
			System.out.println("ID, Title, Checked out On, Username");
			for(Book i : booksList){
				System.out.println(overdueBook(i));
			}
		} catch (com.library.exceptions.invalidPermissions invalidPermissions) {
			System.out.println("you do not have permissions for this operation.");
		}
	}

	private String overdueBook(Book book){
		StringBuilder builder = new StringBuilder();
		CheckoutTicket ticket = book.getTicket();
		return builder.append(book.getLibraryReferenceNumber()).append(",")
				.append(book.getTitle()).append(",")
				.append(ticket.getCheckedOutOn()).append(",")
				.append(ticket.getUser().getUsername()).toString();
	}


	public static void main(String[] args) {
		LibraryApp app = new LibraryApp();
		app.login();
	}

}
