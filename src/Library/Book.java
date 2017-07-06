package library;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Book {

    private String name;
    private String isbn;
    private int pageCount;
    private String author;
    private Date added;

    public Book(String name, String isbn, int pageCount, String author, Date added) {
        this.name = name;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.author = author;
        this.added = added;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); 
    }

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.isbn);
        return hash;
    }

    @Override
    public String toString() {
        String bookString="-----------"+this.name+"-----------------\n";
        bookString+="Name: "+this.name;
        bookString+="\nISBN: "+this.isbn;
        bookString+="\nAuthor: "+this.author;
        bookString+="\nAdded Data: "+new SimpleDateFormat("yyyy/MM/dd").format(this.added)+"\n";
        return bookString;
        
    }

    
    
}
