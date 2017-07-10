import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Shaksham Kapoor
 */
public class Book 
{
    String name, author;
    Integer isbn, pgcount;
    Date date;
    
    public Book(String name, String auth, int isbn, int pg, Date d)
    {
        this.name = name;
        this.author = auth;
        this.isbn = isbn;
        this.pgcount = pg;
        this.date = d;
    }
    
    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public Integer getIsbn()
    {
        return isbn;
    }

    public void setIsbn(int isbn) 
    {
        this.isbn = isbn;
    }

    public int getPageCount() 
    {
        return pgcount;
    }

    public void setPageCount(int pageCount) 
    {
        this.pgcount = pageCount;
    }

    public String getAuthor() 
    {
        return author;
    }

    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public Date getAdded() 
    {
        return date;
    }

    public void setAdded(Date added) 
    {
        this.date = added;
    }

    @Override
    public String toString() 
    {
        String bookStr = "-----------Details of the book-----------------\n";
        bookStr += "Name: " + this.name;
        bookStr += "\nISBN: " + this.isbn;
        bookStr += "\nAuthor: " + this.author;
        bookStr += "\nDate of addition: "+ new SimpleDateFormat("yyyy/MM/dd").format(this.date)+"\n";
        return bookStr;
    }
}
