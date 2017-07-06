
package library;

import java.util.ArrayList;


public class Library {

    private final ArrayList<Book> books;

    public Library() {
        books=new  ArrayList<>();
    }
    
    
    
    public void addNewBook(Book book){
        boolean isAvailabe=false;
        for (Book b : books) {
            if (b.equals(book)) {
                isAvailabe=true;
                break;
            }
        }
        if (!isAvailabe) {
            books.add(book);
            System.out.println("Book "+book.getName()+" Added to library");
        }else{
            System.out.println("Book "+book.getName()+" already in");
        }
    }
    
    public Book searchBook(String isbn){
        Book book=null;
        for (Book b : books) {
            if (b.getIsbn().equalsIgnoreCase(isbn)) {
                book=b;
                break;
            }
        }
        if (book==null) {
            System.out.println("Cant find this book ");
        }
        return book;
    }
    
    
    public void removeBook(Book book){
        boolean isRemoved=false;
        for (Book b : books) {
            if (book.equals(b)) {
                books.remove(book);
                isRemoved=true;
            }
        }
        if (isRemoved) {
            System.out.println("Book "+book.getName()+" was Removed from library");
        }else{
             System.out.println("Can not find book "+book.getName()+" to delete");
        }
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    
    
    
}
