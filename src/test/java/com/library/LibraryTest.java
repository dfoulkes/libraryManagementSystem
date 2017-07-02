package com.library;

import com.library.builders.BookBuilder;
import com.library.builders.UserBuilder;
import com.library.domain.Book;
import com.library.domain.CheckoutTicket;
import com.library.domain.Library;
import com.library.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by danfoulkes on 01/07/2017.
 * Project: libraryManagementSystem
 * Package: com.library.core
 */
public class LibraryTest {


    Library library;

    @Before
    public void setup(){
     library = new Library();
    }

    @Test
    public void shouldBeAbleToAddBook(){
        library.addBook(aValidBook());
        assertThat(library.getBooks().size(), is(1));
    }

    @Test
    public void shouldNotAllowDuplicateBookIds(){
        library.addBook(aValidBook());
        library.addBook(aValidBook());
        assertThat(library.getBooks().size(), is(1));
    }


    @Test
    public void shouldBeAbleToAddUser(){
        library.addUser(aValidUser());
        assertThat(library.getUsers().size(), is(1));
    }

    @Test
    public void ShouldAllowUserToCheckoutBook(){
        library.addBook(aValidBook());
        CheckoutTicket ticket = library.checkout(aValidUser(),aValidBook());
        assertThat(library.getBook(1).get().getTicket().getCheckout(),is(true));
        assertThat(ticket.getCheckout(),is(true));
    }

    @Test
    public void shouldNotBeAbleToCheckoutSameBookTwice(){
        library.addBook(aValidBook());
        CheckoutTicket firstTicket = library.checkout(aValidUser(),aValidBook());
        assertThat(firstTicket.getCheckout(),is(true));
        CheckoutTicket secondTicket = library.checkout(anotherValidUser(),aValidBook());
        assertThat(secondTicket.getCheckout(),is(false));
        assertThat(library.getBook(aValidBook().getLibraryReferenceNumber()).get().getTicket().getUser().getUsername(), is(aValidUser().getUsername()));
    }

    private User aValidUser() {
        return UserBuilder.getBuilder()
                .withId(1L)
                .withUsername("dan")
                .withPassword("password")
                .build();
    }

    private User anotherValidUser() {
        return UserBuilder.getBuilder()
                .withId(2L)
                .withUsername("little")
                .withPassword("timmy")
                .build();
    }

    private Book aValidBook() {
        return BookBuilder.getBuilder()
                .withLibraryReferenceNumber(1)
                .withTitle("Java for Beginners")
                .withIsdn("isdn12345")
                .build();
    }


}