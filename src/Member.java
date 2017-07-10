package src;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author USER
 */
public class Member {

    //Scanner sc = new Scanner(System.in); 
    private String userName;
    private String password;
    private String role;
    static ArrayList<Member> userList = new ArrayList<Member>();

    public enum Role {
        ADMIN, USER
    }

    public Member() {
//        this.userName = "tab";
//        this.password = "123";
    }

    public Member(String userName, String password, Role role) {
        this.userName = userName;
        this.password = password;
        this.role = role.toString();
    }

    public Member(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void addSomeDefaultAdmin(String name, String pass) {
        Member members = new Member(name, pass, Role.ADMIN);
        userList.add(members);
    }

    public void addSomeDefaultMembers(String name, String pass) {
        Member members = new Member(name, pass, Role.USER);
        userList.add(members);
    }

    public void addNewUser(String newUser_Name, String newUser_Password) {
        Member members = new Member(newUser_Name, newUser_Password, Role.USER);

        //userList = new ArrayList<Member>();
        if (userList.contains(newUser_Name)) {
            System.out.println("User already Exists !! Please try another user name.");
        } else {
            userList.add(members);
            System.out.println("New Member ["+newUser_Name.toUpperCase()+"] has been added to the Library");
        }

    }

    public void addNewAdmin(String newAdmin_Name, String newAdmin_Password) {
        Member members = new Member(newAdmin_Name, newAdmin_Password, Role.ADMIN);

        //userList = new ArrayList<Member>();
        if (userList.contains(newAdmin_Name)) {
            System.out.println("User already Exists !! Please try another user name.");
        } else {
            userList.add(members);
            
        }
    }

    public void login(String logUserName, String logPassword) {
        for (int i = 0; i <= userList.size(); i++) {
            if (logUserName.equals(userList.get(i).getUserName()) && logPassword.equals(userList.get(i).getPassword())) {
                String logRole = userList.get(i).getRole();
                System.out.println(logRole);
                if (logRole.equals("ADMIN")) {
                    //todo some code
                    System.out.println("Logged in as ADMIN Successfully !!");
                    String inputFromAdmin;
                    do {

                        Scanner sc = new Scanner(System.in);
                        System.out.println("Select Number from bellow to perform Action:");
                        System.out.println("1. Add New Member");
                        System.out.println("2. Add New Book");
                        System.out.println("3. Search Book");
                        System.out.println("4. Logout");
                        inputFromAdmin = sc.nextLine();
                        
                        switch(inputFromAdmin){
                            case "1":
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("Enter New Member Name: ");
                                String newMemberName = scanner.nextLine();
                                System.out.println("Enter Password for new Member: ");
                                String newMemberPassword = scanner.nextLine();
                                
                                addNewUser(newMemberName, newMemberPassword);
                                
                                
                            case "2":
                                // add some new books
                            case "3":
                                // search functionalities
                            case "4":
                                LibraryApp.getUserInforLogin();
                        }
                        

                    } while (true);
                    //do something 
                    // assigning operation

                    

                } else if (logRole.equals("USER")) {
                    System.out.println("Logged in as ["+logUserName+"] Successfully !!");
                    String inputFromUser;
                    do {

                        Scanner sc = new Scanner(System.in);
                        System.out.println("Select Number from bellow to perform Action:");
                        System.out.println("1. Search Book");
                        System.out.println("2. some action");
                        System.out.println("3. some action");
                        System.out.println("4. Logout");
                        inputFromUser = sc.nextLine();
                        
                        switch(inputFromUser){
                            case "1":
                                Scanner scanner = new Scanner(System.in);
                                System.out.print("Enter Book Name:  ");
                                String newMemberName = scanner.nextLine();
                                
                                //some code to search book
                                
                            case "2":
                                // add some new books
                            case "3":
                                // search functionalities
                            case "4":
                                LibraryApp.getUserInforLogin();
                        }
                        

                    } while (true);
                    //do something 
                    // assigning operation
                }
                break;

            }
            /**
             * TODO connect the other methods using cases here
             */

        }

    }

}
