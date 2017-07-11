package com.library.backlog;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.library.userroles.UserRole;
public class Library {
    
    String isbn;
    String title;
    Book book;
    ArrayList<User> userList;
    private UserRole role;
    String usernameEntered;
    String passwordEntered;
    private List<Book> bookList;
    // actually we doing this here:
    Scanner scanner = new Scanner(System.in);
    // List<Book> bookList = new ArrayList<Book>(); 
    // List is an interface and ArrayList is the implementation of it, List gives more flexibility than an ArrayList and less bugs
    
    
    public Library(){
        //@Mou-Comment on calling the class just register the users
        bookList = new ArrayList<Book>();
        userList = new ArrayList<User>();
        addNewUser(UserRole.ADMIN, "Tayyab", "adminpass");
        
        //the arraylist to which books can be added
    }
        
    
        //adding new book
        public void addNewBook(String isbn, String title){
            //@Mou-Comment adding isb,title to the method as parameters
            Book newBook= new Book(isbn, title);
            if(this.bookList.contains(newBook)){
                System.out.println("The book already exists");
            }
            else{
                bookList.add(newBook);
                System.out.println("Book :"+title+"Successfully added to the library!");
            }
        }//end of addNewBook()
            
        //searching for a book
        public void search(){
            
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println("Enter book title:");
            String searchTitle = reader.nextLine();
            
            System.out.println("Enter book isbn:");
            String searchIsbn = reader.nextLine();
            
            Book searchBook = new Book(searchIsbn,searchTitle);
            
            if(bookList.contains(searchBook)){
            
            for(int i = 0; i <= bookList.size(); i++){
                
                if(searchIsbn.equalsIgnoreCase(book.getIsbn()) && searchTitle.equalsIgnoreCase(book.getTitle())){
                    
                    /**
                     * TODO compare the object bookSearch with objects contained in the ArrayList
                     * actually its not a TODO for I
                     * 
                     */
                    
                    if(!bookList.get(i).getcheckedOut()){
                        System.out.println("the book has been found and available");
                        }
                    
                    else{
                            System.out.println("The book has been assigned to someone else, please wait");
                            }
                    }
                
                else{
                    System.out.println("The book is not part of the library");
                    }
                }
            }
        }//end of search ///////////////better would be if I use exception here book not found
        
        
        
        @SuppressWarnings("unused")
        public void login(String inputUsername, String inputPassword){
            /*@Mou-Comment
            * first of all you shouldn't get  user date from User object like this -> User.getEtc()  because it always will return null
            * you have to get the data from a user which already exists*/
            
            User userLogin = null;
            //@Mou-Comment create variables for user data and set it null to catch null pointer
            String username=null;
            String password=null;
            UserRole role=null;
            
            //if(userLogin!= null && userLogin.getRole().equals(UserRole.ADMIN)){ //<- not like this@Mou-Comment
                for (User u: userList) {//@Mou-Comment start the login method directly with searching the userList for Username & Password
                    if (u.getUsername().equals(inputUsername) && u.getPassword().equals(inputPassword)) {
                        System.out.println("You have successfully logged in as a admin and you can perform the forlowing tasks");
                        /*
                        @Mou-Comment here where the program knows there is a user with this data so we bring all user data then break the loop
                        */
                        userLogin=u;
                        username=inputUsername;
                        password=inputPassword;
                        role=u.getRole(); //@Mou-Comment dont create it static
                        break;
                    }
                }
                    if(username!=null&& role.equals(UserRole.ADMIN)){
                        String input="";
                        while (!input.equals("Quit")) {
                            System.out.println("To add new user enter: addNewUser");
                            System.out.println("To add new book enter: addNewBook");
                            System.out.println("For searching a book please enter: searchBooks");
                            System.out.println("To quit your session enter: switchToOtherUser");
                            System.out.println("To quit your session enter: quit");
                            System.out.println("\n please enter your command:");
                            
                            input = scanner.nextLine();
                            switch (input){
                                case "addNewUser":
                                    String u, p, r;
                                    UserRole mRole = UserRole.USER;
                                    System.out.println("PaddNewUser: lease enter Username");
                                    u = scanner.nextLine();
                                    System.out.println("addNewUser: Please enter Password");
                                    p = scanner.nextLine();
                                    System.out.println("addNewUser: Please enter Role");
                                    r = scanner.nextLine();
                                    
                                    if(r.equals("Admin"))mRole = UserRole.ADMIN;
                                    else
                                    	if(r.equals("User"))mRole = UserRole.USER;
                                    addNewUser(mRole, u, p);
                                    System.out.println("\n please enter your command:");
                                    break;
                                    
                                case "addNewBook":
                                    String ispn, title;
                                    System.out.println("addNewUser: please enter isbn");
                                    ispn = scanner.nextLine();
                                    System.out.println("addNewUser: Please enter isbn");
                                    title = scanner.nextLine();
                                    addNewBook(ispn, title);
                                    System.out.println("\n please enter your command:");
                                    break;
                                    
                                case "searchBooks":
                                	search();
                                	break;
                                	
                                case "switchToOtherUser":
                                	login(username, password);
                                	break;
                                	
                                case "Quit":
                                	System.out.println("Program terminated");
                                default: System.out.println(" Wrong command!"); break;
                            }
                        }
                    }
                    
                    else if(userLogin!= null && userLogin.getRole().equals(UserRole.USER)){
                            //do something
                            // assigning operation
                            
                            System.out.println("You have successfully logged in as a admin and you can perform the forlowing tasks");
                            System.out.println("For searching a book please enter: SEARCH");
                            
                            }//end of inner if statement
                        }//end of if statement after enhanced loop
            //}//end of login method///////////////better would be if I use exception here book not found
        //@Mou-Comment no need for this, just use addNewUser()
        /*public void addAdmin(){
            User administrativeUser = new User(UserRole.ADMIN, "Tayyab", "adminpass");          
            userList.add(administrativeUser);
        }*/
        
        //adding user
        public void addNewUser(UserRole role, String username, String password){
            //@Mou-Comment add userDate as method parameters
            User newUser = new User(role, username, password);
            
            if(userList.contains(newUser)){
                System.out.println("The user already exist, please enter another");
            }
            else{
                userList.add(newUser);
                System.out.println(username+" Successfully registered ");
            }
            }
        
        
    }