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
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.isbn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        return true;
    }

    public String[] propertyList() {
        String[] pl = {
            this.name,
            this.isbn,
            this.pageCount + "",
            this.author + "",
            new SimpleDateFormat("yyyy/MMM/dd").format(this.added)
        };
        return pl;
    }

    public static String[] getTableHeader() {
        String[] tableHeader = {"NAME", "ISBN", "PAGE COUNT", "AUTHOR", "ADDED DATE"};
        return tableHeader;
    }
}
