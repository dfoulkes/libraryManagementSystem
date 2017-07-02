package com.library;

import com.library.builders.BookBuilder;
import com.library.builders.UserBuilder;
import com.library.domain.Book;
import com.library.domain.CheckoutTicket;
import com.library.domain.Library;
import com.library.domain.User;
import com.library.enums.Role;
import com.library.exceptions.invalidPermissions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by danfoulkes on 01/07/2017.
 * Project: libraryManagementSystem
 * Package: com.library.core
 */
public class LibraryTest {


    private Library library;

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
        library.addUser(aValidAdmin());
        assertThat(library.getUsers().size(), is(1));
    }

    @Test
    public void ShouldAllowUserToCheckoutBook(){
        library.addBook(aValidBook());
        CheckoutTicket ticket = library.checkout(aValidAdmin(),aValidBook());
        assertThat(library.getBook(1).orElse(null).getTicket().getCheckout(),is(true));
        assertThat(ticket.getCheckout(),is(true));
    }

    @Test
    public void shouldNotBeAbleToCheckoutSameBookTwice(){
        library.addBook(aValidBook());
        CheckoutTicket firstTicket = library.checkout(aValidAdmin(),aValidBook());
        assertThat(firstTicket.getCheckout(),is(true));
        CheckoutTicket secondTicket = library.checkout(aValidUser(),aValidBook());
        assertThat(secondTicket.getCheckout(),is(false));
        assertThat(library.getBook(aValidBook().getLibraryReferenceNumber()).orElse(null).getTicket().getUser().getUsername(), is(aValidAdmin().getUsername()));
    }

    @Test
    public void shouldAllowAUserToReturnABookTheBookShouldThenBecomeAvailable(){
        setupSmallLibrary();
        Book book = library.getBook(1).orElse(null);
        library.checkout(aValidUser(),book);
        assertThat(library.getBook(1).orElse(null).getTicket().getCheckout(),is(true));
        library.checkIn(aValidUser(), aValidBook());
        assertThat(library.getBook(1).orElse(null).getTicket().getCheckout(),is(false));
    }

    @Test
    public void shouldAllowAdminsToSeeBooksOverdue() throws invalidPermissions {
        setupSmallLibrary();
        checkoutAndMakeBookOverdue();
        List<Book> collection = library.overDueBooks(aValidAdmin());
        assertThat(collection.size(), is(1));
    }

    @Test(expected = invalidPermissions.class)
    public void shouldNotAllowUsersToViewOverDueBooks() throws invalidPermissions {
        setupSmallLibrary();
        checkoutAndMakeBookOverdue();
        List<Book> collection = library.overDueBooks(aValidUser());
        assertThat(collection.size(), is(1));
    }

    private void checkoutAndMakeBookOverdue() {
        library.checkout(aValidUser(),aValidBook());
        LocalDateTime overDueDate = LocalDateTime.now().minusDays(4);
        library.getBook(aValidBook().getLibraryReferenceNumber()).orElse(null).getTicket().setCheckedOutOn(overDueDate);
    }

    private void setupSmallLibrary() {
        library.addBook(BookBuilder.getBuilder()
                .withLibraryReferenceNumber(1)
                .withTitle("Java for Beginners")
                .withIsdn("isdn1")
                .build());

        library.addBook(BookBuilder.getBuilder()
                .withLibraryReferenceNumber(2)
                .withTitle("C++ for Beginners")
                .withIsdn("isdn2")
                .build());

        library.addBook(BookBuilder.getBuilder()
                .withLibraryReferenceNumber(3)
                .withTitle("Objective C for Beginners")
                .withIsdn("isdn3")
                .build());

        library.addUser(aValidUser());
        library.addUser(aValidAdmin());

    }

    private User aValidAdmin() {
        return UserBuilder.getBuilder()
                .withId(1L)
                .withUsername("dan")
                .withPassword("password")
                .withRole(Role.ADMIN)
                .build();
    }

    private User aValidUser() {
        return UserBuilder.getBuilder()
                .withId(2L)
                .withUsername("little")
                .withPassword("timmy")
                .withRole(Role.USER)
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