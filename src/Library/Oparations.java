package library;

import java.util.Date;
import java.util.Scanner;

public class Oparations {

    static Library lib = new Library();

    public static void main(String[] args) {
        //addNewBook();
        Book book = new Book("Java", "123ABC", 1200, "Oracle", new Date());
        lib.addNewBook(book);
        lib.addNewBook(book);
        System.out.println(lib.searchBook("123ABC"));
        viewAllBooks();
    }

    public static void viewAllBooks() {
        if (lib.getBooks().isEmpty()) {
            System.out.println("Library is empty");
        }else{
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
       lib.addNewBook(java);
        
    }
}
