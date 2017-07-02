package com.library.domain;

/**
 * Created by danfoulkes on 01/07/2017.
 * Project: libraryManagementSystem
 * Package: com.library.core
 */
public class Book {

    private final String isdn;
    private final String title;
    private final Integer libraryReferenceNumber;
    private CheckoutTicket checkedOut;


    public Book(String isdn, String title, Integer libraryReferenceNumber) {
        this.isdn = isdn;
        this.title = title;
        this.libraryReferenceNumber = libraryReferenceNumber;
    }


    public String getIsdn() {
        return isdn;
    }

    public String getTitle() {
        return title;
    }

    public Integer getLibraryReferenceNumber() {
        return libraryReferenceNumber;
    }

    @Override
    public int hashCode(){
        return libraryReferenceNumber;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Book){
            return libraryReferenceNumber.equals(((Book) o).getLibraryReferenceNumber());
        }
    return false;
    }

    public CheckoutTicket checkout(CheckoutTicket checkedOut){
        this.checkedOut = checkedOut;
        return checkedOut;
    }

    public CheckoutTicket getTicket() {
        return checkedOut;
    }
}
