package src;

/**
 *
 * @author USER
 */
public class Book {
    
    private String title;
    private String author;
    private String isbn;
    
    public Book(){
        this.isbn = isbn;
    }
    public Book(String isbn){
        
    }
    public Book(String title,String author){
        this.title = title;
        this.author = author;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    
    
}
