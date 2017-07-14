package libraryassign;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Shaksham Kapoor
 */
public class LibraryAssign 
{
    private static LibraryFunc libf  = new LibraryFunc();
    private static UserDetails user  = new UserDetails();
    
    public static void main(String[] args) 
    {
        Scanner scan  = new Scanner(System.in);
        System.out.println("----------------Welcome to Library Management System-----------------------");
        
        //adding few books
        Book book = new Book("StatisticalLearning-1", "Freedie and Hestie", 978146147, 440, new Date());
        Book book1 = new Book("StatisticalLearning-2", "Freedie and Hestie", 978146148, 1020, new Date());
        libf.addBook(book);
        libf.addBook(book1);
        
        //adding a few users
        User use = new User("Shaksham Kapoor", 131369);
        User use1 = new User("Charchit Kapoor", 161207);
        user.addUser(use);
        user.addUser(use1);
        
        System.out.println("List of available books in the library : ");
        viewLibrary();
        System.out.println("Enrolled Users in the library : ");
        viewUsers();
        
        //Deciding whether the user is Admin or Any other User (who want a book or will return a book or will simply search for a book)
        System.out.println("Please select your role : ");
        System.out.println("1. Admin\n2. User");
        int role = scan.nextInt();
        
        switch(role)
        {
            case 1: System.out.println("Welcome Admin!! Enter your username and password : ");
                    System.out.print("Username : ");
                    String username = scan.next();
                    System.out.print("Password : ");
                    String password = scan.next();
                    
                    String user = "Deadshot";
                    String pass = "Deadshot@95";

                    //Checking whether the credentials entered are correct or not ?
                    if(user.equals(username) && pass.equals(password))
                    {
                        //ask whether the admin wants to add a new book to the libary
                        System.out.println("Do you want to add a new book or a new user ?(B (for book)/U (for User)) ");
                        String choice = scan.next();
                        do
                        {
                            if(choice.equals("n"))
                                break;
                            //calling the function to add new books to the library
                            if(choice.equals("B"))
                                choice = addNewBook("admin");
                            else if(choice.equals("U"))
                                choice = addNewUser();
                        }while(choice.equals("n") != true);   
                    }
                    else //wrong credentials means FRAUD !!!!!
                    {
                        System.out.println("The credentials you entered were wrong!!!");
                        System.exit(0);
                    }
                    viewLibrary();
                    viewUsers();
                    break;
            
            case 2: System.out.println("Welcome User !!!");
                    System.out.println("Please select your option : ");
                    System.out.println("1.Issue a Book \n2.Search for a book \n3.Return a book");
                    //take the choice of the user and work accordingly
                    int choice = scan.nextInt();
                    
                    if(choice == 1)
                    {
                        //Issue the book to the user
                        System.out.println("Enter the book name you want to issue");
                        String bookname = scan.next();
                        
                        boolean remove = libf.remove(bookname);
                        
                        //if the book was present in the library
                        if(remove == true)
                        {
                            System.out.println("One copy of the book has been issued!!!");
                            System.out.println("Return the book wihtin 3 days, otherwise I will beat the hell out of you and will fine you !!!!");
                            viewLibrary();// check whether the book has been removed from the libary or not
                        }
                        else
                            System.out.println("The queried book is not present in the libary.");
                    }
                    else if(choice == 2)
                    {
                        //Search for the queried ISBN Book Number
                        System.out.print("Enter the ISBN you want to search : ");
                        String str = scan.next();
                        libf.searchBook(Integer.parseInt(str));
                    }
                    else if(choice == 3)
                    {
                        //Returning the issued book
                        System.out.print("Enter the following credentials of the book you want to return: \n");
                        addNewBook("user");  
                        viewLibrary();//Check whether the book has been returned to the library
                        System.out.println("Thank You!!! The book has been successfully returned.");
                    }
                    else
                    {
                        System.out.println("Sorry! You entered a invalid choice. Come Again Later!!!!");
                        System.exit(0);
                    }
                    break;
        }
    }
    
    public static void viewLibrary()
    {
        if(libf.getBooks().isEmpty())
            System.out.println("Sorry! the library is currently empty.");
        else
            libf.getBooks().forEach(System.out::println);
    }
    
    public static String addNewBook(String role) 
    {
        Scanner scn = new Scanner(System.in);
        //Book name
        System.out.print("Book name: ");
        String bkname = scn.next();
        //Author Name
        System.out.print("Author: ");
        String author = scn.next();
        //ISBN Number
        System.out.print("ISBN: ");
        String num = scn.next();
        int ISBN = Integer.parseInt(num);
        //Number of Pages
        System.out.print("Page count: ");
        num = scn.next();
        int pageCount = Integer.parseInt(num);
        //Initializing the Book class Constructor
        Book newbk = new Book(bkname, author, ISBN, pageCount,new Date());
        //Adding the book to the library
        libf.addBook(newbk);
        
        if(role.equals("admin"))
        {
            //Ask whether the admin wants to add another book
            System.out.println("Do you want to add another book ?(y/n) ");
            String choice = scn.next();
            return choice;
        }
        else if(role.equals("user"))
            return null;
        
        return null;
    }

    private static String addNewUser() 
    {
        Scanner scn = new Scanner(System.in);
        //User name
        System.out.print("User name: ");
        String name = scn.next();
        //User Id
        System.out.print("ID: ");
        String num = scn.next();
        int Id = Integer.parseInt(num);
        
        User users = new User(name, Id);
        //Adding the book to the library
        user.addUser(users);
        
        System.out.println("Do you want to add another book or another user ?(B (for Book)/ U (for User) ");
        String choice = scn.next();
        return choice;
    }
    
    public static void viewUsers()
    {
        if(user.getUsers().isEmpty())
            System.out.println("Sorry! the library is currently empty.Nobody is enrolled");
        else
            user.getUsers().forEach(System.out::println);
    }
}
