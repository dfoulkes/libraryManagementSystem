package com.library.domain;

import java.time.LocalDateTime;

/**
 * Created by danfoulkes on 01/07/2017.
 * Project: libraryManagementSystem
 * Package: com.library.core
 */
public class CheckoutTicket {

    private User user;
    private LocalDateTime checkedOutOn;
    private Boolean checkout;

    public CheckoutTicket(User user, LocalDateTime checkedOutOn, Boolean checkout) {
        this.user = user;
        this.checkedOutOn = checkedOutOn;
        this.checkout = checkout;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCheckedOutOn() {
        return checkedOutOn;
    }

    public void setCheckedOutOn(LocalDateTime checkedOutOn) {
        this.checkedOutOn = checkedOutOn;
    }

    public Boolean getCheckout() {
        return checkout;
    }

    public void setCheckout(Boolean checkout) {
        this.checkout = checkout;
    }
}
