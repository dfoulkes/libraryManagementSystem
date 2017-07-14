package libraryassign;

import java.util.ArrayList;

/**
 *
 * @author Shaksham Kapoor
 */
public class User 
{
    String name;
    int user_id;
    
    public User(String name, int id)
    {
        this.name = name;
        this.user_id = id;
    }
    
    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getId() 
    {
        return user_id;
    }

    public void setId(int id) 
    {
        this.user_id = id;
    }

    @Override
    public String toString() 
    {
        String user_details = "-----------Details of the user-----------------\n";
        user_details += "Name: " + this.name;
        user_details += "\nUser_Id: " + this.user_id;
        return user_details;
    }
}
