package libraryassign;

import java.util.ArrayList;

/**
 *
 * @author Shaksham Kapoor
 */
public class LibraryFunc 
{
    private ArrayList<Books> book;
    
    public LibraryFunc()
    {
        book = new ArrayList<>();
    }
    
    public void addBook(Books b)
    {
        boolean available = false;
        for (Books bk : book)
        {
            if(bk.equals(b))
            {
                available = true;
                break;
            }
        }
        
        if(available == true)
        {
            System.out.println("The following book : " + b.getName() + " is already present in the libary.");
        }
        else
        {
            book.add(b);
            System.out.println("The following book : " + b.getName() + " has been added to the libary.");
        }
    }
    
    public Books searchBook(int isbn)
    {
        Books bk = null;
        for (Books b : book)
        {
            if(b.getIsbn() == isbn)
            {
                bk = b;
                break;
            }
        }
        if(bk == null)
            System.out.println("The book is not present in the libary !!!");
        
        return bk;
    }
    
    public void remove(Books b)
    {
        boolean remove = false;
        for(Books bk : book)
        {
            if(bk.equals(b))
            {
                book.remove(b);
                remove = true;
            }
        }
        
        if(remove == true)
            System.out.println("The book is no longer available in the library!!!");
        else
            System.out.println("The following book : " + b.getName() + " is not present in the libary.");
    }
    
    public ArrayList<Books> getBooks() 
    {
        return book;
    }
}
