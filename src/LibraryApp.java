package library;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

//Please use JDK 8 
public class LibraryApp {

    private static final Library LIB = new Library();

    public static void main(String[] args) {
        LIB.addNewUser(new User(1, "prabath", "1234", "Prabath Madushan", UserType.ADMIN));
        Scanner scn = new Scanner(System.in);
        run(scn);
    }
    
    

    private static void run(Scanner scn) {
        login(scn);
    }
    static int loginCount = 0;

    private static void login(Scanner scn) {
        System.out.println("---------Please Login------------");
        String username = getTextInput("Please enter username:", scn);
        String password = getTextInput("Please enter password", scn);
        loginCount++;
        if (loginCount < 3) {
            if (LIB.login(username, password)) {
                System.out.println("---------Welcmoe to the Library---------");
                oparateLibrary(scn);
            } else {
                login(scn);
            }
        } else {
            System.out.println("Too many request for login! System sutdown automaticaly..!");
        }
    }

    private static void oparateLibrary(Scanner scn) {
        String command = scn.next();
        if (command.equalsIgnoreCase("Exit")) {
            System.out.println("Good bay");
        } else if (command.equalsIgnoreCase("newBook")) {
            addNewBook(scn);
        } else if (command.equalsIgnoreCase("viewBooks")) {
            viewAllBooks(scn);
        } else if (command.equalsIgnoreCase("searchBook")) {
            searchBook(scn);
        } else if (command.equalsIgnoreCase("removeBook")) {
            removeBook(scn);
        } else if (command.equalsIgnoreCase("newUser")) {

        } else if (command.equalsIgnoreCase("viewUsers")) {

        } else if (command.equalsIgnoreCase("newUser")) {

        } else if (command.equalsIgnoreCase("removeUser")) {

        } else if (command.equalsIgnoreCase("viewAllUsers")) {

        } else if (command.equalsIgnoreCase("removeUser")) {

        } else if (command.equalsIgnoreCase("checkout")) {

        } else if (command.equalsIgnoreCase("return")) {

        } 
        else if (command.equalsIgnoreCase("help")) {
            help(scn);
        } else {
            System.out.println(command + " is not recognized please type \"help\" for see avibale commands");
            oparateLibrary(scn);
        }
    }

    private static void help(Scanner scn) {
        String help = "Add New Book:newbook\n"
                + "View All book details:viewbooks\n"
                + "Serach for a book:searchbook\n"
                + "Remove a book:removebook\n"
                + "Add new user:newuser\n";
        System.out.println(help);
        oparateLibrary(scn);
    }

    private static void removeBook(Scanner scn) {

        oparateLibrary(scn);
    }

    private static void searchBook(Scanner scn) {
        System.out.println("Please enter 1 to Serach by id, enter 2 to seach by name");
        String subCommand = scn.next();
        if (subCommand.endsWith("1")) {

        } else if (subCommand.endsWith("2")) {

        }
        oparateLibrary(scn);
    }

    private static void viewAllBooks(Scanner scn) {
        if (LIB.getBooks().isEmpty()) {
            System.out.println("Library is empty");
        } else {
            System.out.println(Book.TABLE_HEADER);
            LIB.getBooks().forEach(System.out::println);
        }
        oparateLibrary(scn);
    }

    //IO
    private static void addNewBook(Scanner scn) {
        String name = getTextInput("Please enter name of the book", scn);
        String isbn = getTextInput("Please enter ISBN of the book", scn);
        int pageCount = getNumberInput("Please enter page count of the book", scn);
        String author = getTextInput("Please enter Author of the book", scn);
        Date date = new Date();
        System.out.println("Added date set as " + new SimpleDateFormat("yyyy/MMM/dd").format(date));
        Book book = new Book(name, isbn, pageCount, author, date);
        LIB.addNewBook(book);
        oparateLibrary(scn);
    }

    private static String getTextInput(String what, Scanner scn) {
        //Enter validate String rules
        System.out.println(what);
        return scn.next();
    }

    //validate Input Number
    private static int getNumberInput(String what, Scanner scn) {
        System.out.println(what);
        String numberAsString = scn.next();
        try {
            int number = Integer.parseInt(numberAsString);
            return number;
        } catch (NumberFormatException e) {
            System.err.println("Please enter a number try again..!");
            return getNumberInput(what, scn);
        }
    }
}
