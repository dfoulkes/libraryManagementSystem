package objects;

import java.util.ArrayList;

/**
 * Created by mr-mou on 03/07/17.
 */
public class User {
    private String mUsername;
    private String mPassword;
    private String mRole;
    private ArrayList<Book> mBackpack;

    public User(String username,String password, String role, ArrayList<Book> backpack ){

        mUsername=username;
        mPassword=password;
        mRole =role;
        mBackpack=backpack;
    }


    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getRole() {
        return mRole;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public void setRole(String userType) {
        this.mRole = userType;
    }


    public ArrayList<Book> getBackpack() { //this array list like the user backpack
        return mBackpack;
    }

    public void setBackpack(ArrayList<Book> backpack) {
        this.mBackpack = backpack;
    }


}
