package library;

import java.util.Date;

public class Checkout {

    private int id;
    private Book book;
    private Date date;
    private Member member;
    private User user;

    public Checkout() {
    }

    public Checkout(int id, Book book, Date date, Member member, User user) {
        this.id = id;
        this.book = book;
        this.date = date;
        this.member = member;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
