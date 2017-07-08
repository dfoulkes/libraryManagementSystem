
package library;

import java.util.Date;


public class Auth {
    private final User user;
    private final Date date;

    public  Auth(User user) {
        this.user = user;
        this.date = new Date();
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    
    
    

    
}
