import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * This will host as the runtime for the new app.
 *
 * @author mr-mou
 *
 *
 * password&username must be changed to ID.
 *
 * the object can be serialized or something to save the data.
 *
 * user & admin can list all books in the library  using method listBooks()
 * user can check out books using method chekoutBook()
 * user can return a book using method returnBook()
 * admin can register new user using method register()
 * admin can add new user books method addNewBook()
 * admin can get user info with method printUserInfo()
 * admin can print a report of checked out books by using method printReport()
 *
 *
 */
public class LibraryApp {
	private static final int CHECKING = 10;
	private static final int REGISTER = 0;
	public static ArrayList<String> bookNames;
	public static String command;
	private static Scanner sc;
	private static String uUserName;
	private static String uPassword;
	public static String uTypeOfUse;

	private static int objectIndex;


	private static ArrayList<UserData> objectArrayList;
	private static ArrayList<CheckedOutBooks> checkedOutBooks;
	private static boolean usernameIsAvailable=true;


	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		System.out.println("LibraryApp started...\n type 'help' to show list of commands");


		bookNames = new ArrayList<String>(0);
		checkedOutBooks = new ArrayList<CheckedOutBooks>(0);
		//adding some elements to bookNames ArrayList
		int c = 0;
		while (c < 10) {

			bookNames.add("Book number:" + c);
			c++;
		}

		objectArrayList = new ArrayList<UserData>();

		register("user1" ,"userpass1", "user", REGISTER);
		register("user2" ,"userpass2", "user", REGISTER);
		register("admin1" ,"adminpass1", "admin", REGISTER);


       /*int counter = 0;
        while (counter != objectArrayList.size()) {
            prntln((counter + 1) + ") " + objectArrayList.get(counter).getLoginUsernameData());
            counter++;
        }
*/



		login();





	}


	private static void adminStartScanning() {

		if (uTypeOfUse.equals("admin")) {
			command = sc.nextLine();
			if (command.equals("listbooks")) {

				listBooks();
				adminStartScanning();

			} else if (command.equals("addnewbook")) {

				String name;
				System.out.println("What is the book name?");
				name = sc.nextLine();
				addNewBook(name);
				adminStartScanning();

			} else if (command.equals("help")) {
				prntln(
						"listbooks     to list all books which remaining in the library "
								+ "\n" + "addnewbook    to add new book to the library"
								+ "\n" + "printreport   to print report of checked out books"
								+ "\n" + "register      to register new user"
								+ "\n" + "getuserinfo   to get info of user by username"
								+ "\n" + "logout        -"


				);
				adminStartScanning();

			}else if (command.equals("printreport")) {

				printReport();
				adminStartScanning();

			}else if (command.equals("register")) {

				String username; String password=""; String typeofuse="";
				prntln("register: enter new username");
				username=sc.nextLine();
				register(username, password, typeofuse, CHECKING);
				if (usernameIsAvailable) {
					prntln("Register: enter new password");
					password=sc.nextLine();

					prntln("Register: enter type of  user");
					typeofuse = sc.nextLine();
					register(username, password, typeofuse, REGISTER);
				}


				adminStartScanning();

			} else if (command.equals("getuserinfo")) {

				String username=sc.nextLine();
				printUserInfo(username);
				adminStartScanning();


			} else if (command.equals("logout")) {

				prntln("logout succeed! ");
				prntln("\n");
				login();
			} else {
				//if unknown command
				System.out.println(command + " is unknown command!");
				adminStartScanning();
			}
		}

	}

	private static void printUserInfo(String username) {
		int c=0;
		int index = 0;
		boolean bol=false;


		while (c < objectArrayList.size()) {

			if (objectArrayList.get(c).getLoginUsernameData().equals(username)) {
				prntln("found username at index " + c);
				index=c;
				bol=true;


			}
			c++;

		}

		if(bol && objectArrayList.get(index).getLoginUsernameData().equals(username)){
			ArrayList<String> oCBooksList =objectArrayList.get(index).getBooksArrayList();

			if (oCBooksList != null) {

				prntln(username+" checked out these books=");

				int counter=0;
				while (counter != oCBooksList.size()) {
					prntln((counter + 1) + ") " + oCBooksList.get(counter));
					counter++;
				}
			} else {
				prntln(username+"  haven't checked out any books!");

			}
			prntln("Password: "+objectArrayList.get(index).getLoginPasswordData());

			prntln("Type of user: "+objectArrayList.get(index).getLoginTypeData());
		}

	}

	public static void userStartScanning() {

		command = sc.nextLine();
		if (command.equals("listbooks")) {

			listBooks();
			userStartScanning();


		} else if (command.equals("checkoutbook")) {

			prntln("please enter the book name");
			String theBook = sc.nextLine();
			checkOutBook(theBook);
			userStartScanning();

		} else if (command.equals("returnbook")) {

			prntln("please enter the book name");
			String theBook = sc.nextLine();
			returnBook(theBook);
			userStartScanning();

		} else if (command.equals("myinfo")) {
			ArrayList<String> oCBooksList =objectArrayList.get(objectIndex).getBooksArrayList();


			if (oCBooksList!=null && !oCBooksList.get(0).isEmpty()) {

				prntln("You checked out those books=");

				int counter=0;
				while (counter != oCBooksList.size()) {
					prntln((counter + 1) + ") " + oCBooksList.get(counter));
					counter++;
				}
			}else prntln("You haven't checked out any books");
			userStartScanning();


		} else if (command.equals("logout")) {

			prntln("logout succeed! ");
			prntln("\n");
			login();
		} else if (command.equals("help")) {
			prntln(
					"listbooks     to list all books in the library "
							+ "\n" + "checkoutbook  to checkout a book from library"
							+ "\n" + "returnbook  to return checked out book to library"
							+ "\n" + "myinfo  to get info about checked out books"




			);
			userStartScanning();

		} else {
			//if unknown command
			System.out.println(command + " is unknown command!");
			userStartScanning();
		}

	}


	public static void listBooks() {

		int counter = 0;

		while (counter != bookNames.size()) {
			prntln((counter + 1) + ") " + bookNames.get(counter));
			counter++;
		}

	}


	private static void checkOutBook(String theBook) {

		if (bookNames.contains(theBook)) {
			prntln("found the Book!");

			bookNames.remove(theBook);

			UserData o =new UserData();
			o.setLoginUsernameData(uUserName);
			o.setLoginPasswordData(uPassword);
			o.setLoginTypeData("user");

			ArrayList<String> oCBooksList =objectArrayList.get(objectIndex).getBooksArrayList();
			if(oCBooksList!=null)
			{
				oCBooksList.add(theBook);
				o.setBooksArrayList(oCBooksList);
			}else{
				oCBooksList=new ArrayList<String>(0);
				oCBooksList.add(theBook);
				o.setBooksArrayList(oCBooksList);

			}
			objectArrayList.set(objectIndex, o );

			CheckedOutBooks cob =new CheckedOutBooks();
			cob.setBook(theBook);
			cob.setTime(currentTime());
			cob.setUsername(uUserName);
			checkedOutBooks.add(cob);


			prntln("process of check out completed!");

			prntln("You checked out those books=");

			int counter=0;
			while (counter != oCBooksList.size()) {
				prntln((counter + 1) + ") " + oCBooksList.get(counter));
				counter++;
			}

			prntln("library remaining books=");
			counter = 0;
			while (counter != bookNames.size()) {
				prntln((counter + 1) + ") " + bookNames.get(counter));
				counter++;
			}

		} else prntln("book not found!");



	}

	private static void addNewBook(String name) {


		boolean bookIsAlreadyExists;

		if (bookNames.contains(name)){

		}

		bookNames.add(name);
		System.out.println(name + " Successfully added to the library!");

	}

	private static void prntln(String text) {
		System.out.println(text);

	}

	private static void returnBook(String book){
		ArrayList<String> oCBooksList =objectArrayList.get(objectIndex).getBooksArrayList();

		if(oCBooksList!=null && oCBooksList.contains(book)){

			oCBooksList.remove(book);
			objectArrayList.get(objectIndex).setBooksArrayList(oCBooksList);
			bookNames.remove(book);

			//get the object by index then remove

			int c=0;
			while(c< checkedOutBooks.size()){
				if (checkedOutBooks.get(c).getBook().equals(book)){
					checkedOutBooks.remove(c);

				}

				c++;
			}



		}else prntln("the book isn't exist!");


	}

	private static void login() {

		prntln("Please enter your Username..");
		uUserName = sc.nextLine();
		prntln("Please enter your Password..");
		uPassword = sc.nextLine();

		int c = 0;

		while (c < objectArrayList.size()) {

			if (objectArrayList.get(c).getLoginUsernameData().equals(uUserName)) {
				prntln("found uUserName at index " + c);

				objectIndex = c;

			}
			c++;

		}
		if (uUserName.equals(objectArrayList.get(objectIndex).getLoginUsernameData()) && uPassword.equals(objectArrayList.get(objectIndex).getLoginPasswordData())) {

			uTypeOfUse = objectArrayList.get(objectIndex).getLoginTypeData();


			prntln("login success! welcome " + uTypeOfUse + " " + uUserName);

			if (uTypeOfUse.equals("user")) {

				userStartScanning();
			} else adminStartScanning();


		} else {
			prntln("Username or password is incorrect! please try again");
			login();
		}



	}
	private static void printReport(){

		long timeStamp =System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeStamp);






		int c=0;
		while(c<checkedOutBooks.size()){
			Map<TimeUnit, Long> diff =computeDiff(calendar.getTime(),checkedOutBooks.get(c).getTime());
			//problem here,, cannot compare diff with int

			prntln("Book name:( " + checkedOutBooks.get(c).getBook() + " ) user:( " + checkedOutBooks.get(c).getUsername() + " ) on:( " + checkedOutBooks.get(c).getTime() + " )");


			c++;
		}


	}

	private static void register(String username, String password, String typeofuse, int mode){

		if (mode == CHECKING || mode == REGISTER ) {
			int c=0;

			while(( c < objectArrayList.size()&& usernameIsAvailable)){

				if(objectArrayList.get(c).getLoginUsernameData().equals(username)){


					usernameIsAvailable=false;
					prntln("username is already exist!");



				}
				c++;

			}
		}

		if (mode == REGISTER ) {
			if (usernameIsAvailable) {
				UserData o =new UserData();
				o.setLoginUsernameData(username);
				o.setLoginPasswordData(password);
				o.setLoginTypeData(typeofuse);
				objectArrayList.add(o);

				prntln(username+" Registered successfully!");
			}
		}


	}


	private static Date currentTime(){

		long timeStamp =System.currentTimeMillis();


		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeStamp);
		return calendar.getTime();

	}

	public static Map<TimeUnit,Long> computeDiff(Date date1, Date date2) {
		long diffInMillies = date2.getTime() - date1.getTime();
		List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
		Collections.reverse(units);
		Map<TimeUnit,Long> result = new LinkedHashMap<TimeUnit,Long>();
		long milliesRest = diffInMillies;
		for ( TimeUnit unit : units ) {
			long diff = unit.convert(milliesRest,TimeUnit.MILLISECONDS);
			long diffInMilliesForUnit = unit.toMillis(diff);
			milliesRest = milliesRest - diffInMilliesForUnit;
			result.put(unit,diff);
		}
		return result;
	}

}



class UserData {

	private String loginUsernameData;
	private String loginPasswordData;
	private String loginTypeData;
	private ArrayList<String> booksArrayList=null;


	public String getLoginUsernameData() {
		return loginUsernameData;
	}

	public String getLoginPasswordData() {
		return loginPasswordData;
	}

	public String getLoginTypeData() {
		return loginTypeData;
	}



	public void setLoginUsernameData(String loginUsernameData) {
		this.loginUsernameData = loginUsernameData;
	}

	public void setLoginPasswordData(String loginPasswordData) {
		this.loginPasswordData = loginPasswordData;
	}

	public void setLoginTypeData(String loginTypeData) {
		this.loginTypeData = loginTypeData;
	}


	public ArrayList<String> getBooksArrayList() {
		return booksArrayList;
	}

	public void setBooksArrayList(ArrayList<String> booksArrayList) {
		this.booksArrayList = booksArrayList;
	}
}
class CheckedOutBooks{
	private String username;
	private String book;
	private Date Time;


	public String getUsername() {
		return username;
	}

	public String getBook() {
		return book;
	}

	public Date getTime() {
		return Time;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public void setTime(Date time) {
		Time = time;
	}
}