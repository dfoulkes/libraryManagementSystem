package libraryassign;

import java.util.ArrayList;

/**
 *
 * @author Shaksham Kapoor
 */
public class LibraryFunc 
{
    private ArrayList<Book> books;
    
    public LibraryFunc()
    {
        books = new ArrayList<>();
    }
    
    public void addBook(Book b)
    {
        Boolean available = books.stream().filter(aBook -> aBook.equals(b)).findFirst().isPresent();

        if(available) {
            System.out.println("The following books : " + b.getName() + " : is already present in the libary.");
        }
        else {
            books.add(b);
            System.out.println("The following books : " + b.getName() + " : has been added to the libary.");
        }
    }
    
    public void searchBook(Integer isbn)
    {
        Book bk = books.stream().filter(aBook -> isbn.equals(aBook.getIsbn())).findFirst().orElse(null);

        if(bk == null)
            System.out.println("The books is not present in the libary !!!");
        else
        {
            //if the searched books is found, display the result
            System.out.println("Book name : " + bk.getName());
            System.out.println("Author Name: " + bk.getAuthor());
        }
    }
    
    public boolean remove(String b)
    {
        boolean issue = books.stream().filter(aBook -> b.equals(aBook.getName())).findFirst().isPresent();

        if(issue)
        {
            books.remove(b);
        }
        return issue;
    }
    
    public ArrayList<Book> getBooks() 
    {
        return books;
    }
}