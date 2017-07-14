package libraryassign;

import java.util.ArrayList;

/**
 *
 * @author Shaksham Kapoor
 */
public class UserDetails 
{
     private ArrayList<User> users;
    
     public UserDetails()
     {
        users = new ArrayList<>();
     }
    
    public void addUser(User u)
    {
        Boolean available = users.stream().filter(aUser -> aUser.equals(u)).findFirst().isPresent();

        if(available) {
            System.out.println("User is already present in the libary.");
        }
        else {
            users.add(u);
            System.out.println("User has been added to the libary.");
        }
    }
    
     public ArrayList<User> getUsers() 
    {
        return users;
    }
}
