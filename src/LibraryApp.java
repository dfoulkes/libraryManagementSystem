package library;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LibraryApp {

    static Library lib = new Library();

    public static void main(String[] args) {
        lib.addNewUser(new User(1, "prabath", "1234", "Prabath Madushan", UserType.ADMIN));
        Scanner scn = new Scanner(System.in);
        run(scn);

    }

    public static void run(Scanner scn) {
        login(scn);
    }
    static int loginCount = 0;

    public static void login(Scanner scn) {
        System.out.println("---------Please Login------------");
        System.out.println("Please enter username");
        String username = scn.next();
        System.out.println("Please enter password");
        String password = scn.next();
         loginCount++;
        if (loginCount < 3) {
            if (lib.login(username, password)) {
                System.out.println("---------Welcmoe to the Library---------------");
                oparateLibrary(scn);
            } else {
                login(scn);
            }
           
        } else {
            System.out.println("Too many request for login! System sutdown automaticaly..!");
        }
    }

    public static void oparateLibrary(Scanner scn) {
        String command = scn.next();
        if (command.equalsIgnoreCase("Exit")) {
            System.out.println("Good bay");
        } else if (command.equalsIgnoreCase("newBook")) {
            System.out.println("Please enter name of the book");
            String name = scn.next();
            System.out.println("Please enter ISBN of the book");
            String isbn = scn.next();
            System.out.println("Please enter page count of the book");
            int pageCount = scn.nextInt();
            System.out.println("Please enter Author of the book");
            String author = scn.next();
            Date date = new Date();
            System.out.println("Added date set as " + new SimpleDateFormat("yyyy/MMM/dd").format(date));
            Book book = new Book(name, isbn, pageCount, author, date);
            lib.addNewBook(book);
            oparateLibrary(scn);
        } else if (command.equalsIgnoreCase("viewBooks")) {
            viewAllBooks();
            oparateLibrary(scn);
        } else if (command.equalsIgnoreCase("searchBook")) {

        } else if (command.equalsIgnoreCase("removeBook")) {

        } else if (command.equalsIgnoreCase("newUser")) {

        } else if (command.equalsIgnoreCase("viewUsers")) {

        } else if (command.equalsIgnoreCase("newUser")) {

        } else if (command.equalsIgnoreCase("removeUser")) {

        } else if (command.equalsIgnoreCase("viewAllUsers")) {

        } else if (command.equalsIgnoreCase("removeUser")) {

        } else if (command.equalsIgnoreCase("help")) {
            String help="Add New Book:newbook\n"
                    + "View All book details:viewbooks\n"
                    + "Serach for a book:searchbook\n"
                    + "Remove a book:removebook\n"
                    + "Add new user:newuser\n";
            System.out.println(help);
            oparateLibrary(scn);
            
        } else {
            System.out.println(command + " is not recognized please look the documentation");
            oparateLibrary(scn);
        }
    }

    public static void viewAllBooks() {
        if (lib.getBooks().isEmpty()) {
            System.out.println("Library is empty");
        } else {
            lib.getBooks().forEach(System.out::println);
        }
    }

    //IO
    public static void addNewBook() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter book name..");
        String bookname = scn.next();
        System.out.println("Enter ISBM..");
        String ISBN = scn.next();
        System.out.println("Enter Page counts");
        int pageCount = scn.nextInt();
        System.out.println("Enter Author");
        String author = scn.next();
        Book java = new Book(bookname, ISBN, pageCount, author, new Date());
        //lib.addNewBook(java);

    }
}
