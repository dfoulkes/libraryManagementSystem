package libraryassign;

import java.util.ArrayList;

/**
 *
 * @author Shaksham Kapoor
 */
public class LibraryFunc 
{
    private ArrayList<Book> book;
    
    public LibraryFunc()
    {
        book = new ArrayList<>();
    }
    
    public void addBook(Book b)
    {
        boolean available = false;
        for (Book bk : book)
        {
            if(bk.equals(b))
            {
                available = true;
                break;
            }
        }
        
        if(available == true)
        {
            System.out.println("The following book : " + b.getName() + " : is already present in the libary.");
        }
        else
        {
            book.add(b);
            System.out.println("The following book : " + b.getName() + " : has been added to the libary.");
        }
    }
    
    public void searchBook(int isbn)
    {
        Book bk = null;
        for (Book b : book)
        {
            if(b.getIsbn() == isbn)
            {
                bk = b;
                break;
            }
        }
        if(bk == null)
            System.out.println("The book is not present in the libary !!!");
        else
        {
            //if the searched book is found, display the result
            System.out.println("Book name : " + bk.getName());
            System.out.println("Author Name: " + bk.getAuthor());
        }
    }
    
    public boolean remove(String b)
    {
        boolean remove = false;
        for(Book bk : book)
        {
            if(bk.getName().equals(b))
            {
                book.remove(b);
                remove = true;
            }
        }
        return remove;
    }
    
    public ArrayList<Book> getBooks() 
    {
        return book;
    }
}
