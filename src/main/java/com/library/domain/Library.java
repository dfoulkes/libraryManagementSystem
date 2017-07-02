package com.library.domain;



import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * Created by danfoulkes on 01/07/2017.
 * Project: libraryManagementSystem
 * Package: com.library.core
 */
public class Library {

    Set<Book> books = new HashSet<>();
    Set<User> users = new HashSet<>();
    ZoneId zoneId = ZoneId.systemDefault();
    private final Long MAXIMUM_CHECKOUT_TIME = 3L;

    public void addBook(Book book){
        books.add(book);
    }

    public Set<Book> getBooks(){
        return books;
    }

    public void addUser(User user){
        users.add(user);
    }

    public List<Book> overDueBooks(){
        return books.stream()
                    .filter(book -> isCheckedOut(book))
                    .filter(checkout -> getDueDate(checkout).toEpochSecond() < getCurrentEpochTime())
                    .collect(toList());
    }

    private ZonedDateTime getDueDate(Book checkout) {
        return checkout.getTicket().getCheckedOutOn().plusDays(MAXIMUM_CHECKOUT_TIME).atZone(zoneId);
    }

    private boolean isCheckedOut(Book book) {
        return book.getTicket().getCheckout().equals(Boolean.TRUE);
    }

    private long getCurrentEpochTime() {

        return LocalDateTime.now().atZone(zoneId).toEpochSecond();
    }


    /**
     * Java 8 has introduced a new class Optional in java.util package. It is used to represent a value is present or absent.
     * The main advantage of this new construct is that No more too many null checks and NullPointerException.
     * It avoids any runtime NullPointerExceptions and supports us in developing clean and neat Java APIs or Applications.
     * @param user
     * @param book
     */
    public CheckoutTicket checkout(User user, Book book){
        Optional<Book> checkingOutBook =  getBook(book.getLibraryReferenceNumber())
                        .filter(foundBook -> foundBook.getTicket() == null || Boolean.FALSE.equals(foundBook.getTicket().getCheckout()));
        if(checkingOutBook.isPresent()){
           return checkingOutBook.get().checkout(new CheckoutTicket(user, LocalDateTime.now(),Boolean.TRUE));
        }
        return new CheckoutTicket(user, LocalDateTime.now(),Boolean.FALSE);
    }


    public Optional<Book> getBook(int i) {

        /**
         *
         * This works the same as:
         *
         *  for(book x : books){
         *      if(i.equals(x){
         *          return x;
         *      }
         *  }
         *  return null;
         *
         *  Java 8 introduced Lambdas which is a functional programing
         */
        return books.stream()
                .filter(book -> book.getLibraryReferenceNumber().equals(i))
                .findFirst()
                .filter(book -> book.getLibraryReferenceNumber().equals(i));
    }

    public Set<User> getUsers() {
        return users;
    }
}
