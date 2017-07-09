package library;

import java.text.SimpleDateFormat;
import java.util.Arrays;
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
        String username = getTextInput("Please enter username", scn);
        String password = getTextInput("Please enter password", scn);
        loginCount++;
        if (loginCount < 3) {
            if (LIB.login(username, password)) {
                System.out.println("---------Welcmoe to the Library---------");
                System.out.println("please enter 'help' command to view all available cammands\nand do you need to exit please type 'exit'");
                oparateLibrary(scn);
            } else {
                login(scn);
            }
        } else {
            System.err.println("Too many request for login! System sutdown automaticaly..!");
        }
    }

    private static void oparateLibrary(Scanner scn) {
        String command = scn.next();
        if (command.equalsIgnoreCase("Exit")) {
            System.out.println("Thank you for using System..!");
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

        } else if (command.equalsIgnoreCase("help")) {
            help(scn);
        } else {
            System.out.println(command + " is not recognized please type \"help\" for see avibale commands");
            oparateLibrary(scn);
        }
    }

    private static void help(Scanner scn) {
        String data[][]={
            {"Description","Commands"},
            {"Add New Book","newbook"},
            {"View All Books","viewbooks"},
            {"Add New User","newUser"},
            {"View All User","viewusers"},
            {"Remove A User","removeUser"},
            {"Check Out a Book","checkout"},
            {"Return a book","returnbook"},
            {"View All Avilable Commands","help"},
            {"Exit The System","exit"},
            {"Logout","logout"}
        };
        System.out.println(formatAsATable(data));
        oparateLibrary(scn);
    }


    public static String formatAsATable(String[][] data) {
        String format = "";
        int cc = data[0].length;
        int maxl[] = new int[cc];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (i == 0) {
                    maxl[j] = data[i][j].length();
                } else if (maxl[j] < data[i][j].length()) {
                    maxl[j] = data[i][j].length();
                }
            }
        }
        int sSize = 0;
        int x = 0;
        for (int i = 1; i <= (cc * 2) + 1; i++) {
            if (i % 2 != 0) {
                format += "%-2s ";
                sSize += 3;
            } else {
                format += " %-" + (maxl[x] + 3) + "s ";
                sSize += maxl[x] + 3;
                x++;
            }
        }
        format += " %n";
        String sep = "";
        for (int i = 0; i < sSize + (2 * data[0].length) - 2; i++) {
            sep += "-";
        }
        sep += "\n";
        String newData[][] = new String[data.length][(cc * 2) + 1];
        for (int i = 0; i < newData.length; i++) {
            x = 0;
            for (int j = 0; j < newData[i].length; j++) {
                if ((j + 1) % 2 != 0) {
                    newData[i][j] = "|";
                } else {
                    newData[i][j] = data[i][x];
                    x++;
                }
            }
        }
        String table = sep;
        table += String.format(format, (Object[]) newData[0]);
        table += sep;
        for (int i = 1; i < newData.length; i++) {
            table += String.format(format, (Object[]) newData[i]);
        }
        table += sep;
        return table;
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
        System.out.println(formatAsATable(LIB.getAllBooksAsTableData()));
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
        what += ": ";
        System.out.print(what);
        return scn.next();
    }

    //validate Input Number
    private static int getNumberInput(String what, Scanner scn) {
        what += ": ";
        System.out.print(what);
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
