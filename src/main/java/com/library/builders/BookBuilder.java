package com.library.builders;

import com.library.domain.Book;
import com.library.domain.CheckoutTicket;

/**
 * Created by danfoulkes on 01/07/2017.
 * Project: libraryManagementSystem
 * Package: com.library.core
 */
public class BookBuilder {

    private String isdn;
    private String title;
    private Integer libraryReferenceNumber;
    private CheckoutTicket checkedOut;



    public BookBuilder withIsdn(String isdn){
        this.isdn = isdn;
        return this;
    }

    public BookBuilder withTitle(String title){
        this.title = title;
        return this;
    }

    public BookBuilder withLibraryReferenceNumber(Integer referenceNumber){
        this.libraryReferenceNumber = referenceNumber;
        return this;
    }

    public BookBuilder withCheckoutTicket(CheckoutTicket checkedOut){
        this.checkedOut = checkedOut;
        return this;
    }

    public Book build(){
        Book book =  new Book(isdn,title,libraryReferenceNumber);
        book.checkout(checkedOut);
        return book;
    }

    public static BookBuilder getBuilder(){
        return new BookBuilder();
    }
}
