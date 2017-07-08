package libraryassign;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Shaksham Kapoor
 */
public class LibraryAssign 
{
    static LibraryFunc libf  = new LibraryFunc();
    
    public static void main(String[] args) 
    {
        Books book = new Books("Intro. to Statistical Learning", "Freedie and Hestie", 978146147, 440, new Date());
        Books book1 = new Books("Elements of Statistical Learning", "Freedie and Hestie", 978146148, 1020, new Date());
        libf.addBook(book);
        libf.addBook(book1);
        
        System.out.println("List of available books in the library : ");
        viewLibrary();
    }
    
    public static void viewLibrary()
    {
        if(libf.getBooks().isEmpty())
            System.out.println("Sorry! the library is currently empty.");
        else
            libf.getBooks().forEach(System.out::println);
    }
    
    public static void addNewBook() 
    {
        Scanner scn = new Scanner(System.in);
        System.out.println("Book name: ");
        String bkname = scn.next();
        System.out.println("ISBN: ");
        int ISBN = scn.nextInt();
        System.out.println("Page count: ");
        int pageCount = scn.nextInt();
        System.out.println("Author: ");
        String author = scn.next();
        Books newbk = new Books(bkname, author, ISBN, pageCount,new Date());
       libf.addBook(newbk);
        
    }
    
}
