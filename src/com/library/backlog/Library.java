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

	private List<Book> bookList;
	// actually we doing this here:
	// List<Book> bookList = new ArrayList<Book>(); 
	// List is an interface and ArrayList is the implementation of it, List gives more flexibility than an ArrayList and less bugs
	
	
	public Library(){
		
		//the arraylist to which books can be added
		bookList = new ArrayList<Book>();
		userList = new ArrayList<User>();
	}
		
	
		//adding new book
		public void addNewBook(){

			@SuppressWarnings("resource")
			Scanner reader = new Scanner(System.in);  // Reading from System.in
			System.out.println("Enter book title:");
			title = reader.nextLine();
			
			System.out.println("Enter book isbn:");
			isbn = reader.nextLine();
			
			Book newBook= new Book(isbn, title);
			//book already exist or not
			if(bookList.contains(newBook)){
				System.out.println("The book already exists");
			}
			else{
				bookList.add(newBook);
			}
		}//end of addNewBook()
			
		//searching for a book
		public void search(){
			
			/**
			 * TODO
			 * prompt the user for a search when they want to search prompt them for title and isbn
			 */
			
			@SuppressWarnings("resource")
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
		
		
		
		public void login(String inputUsername, String inputPassword){
			
			User userLogin = new User(User.getRole(), inputUsername, inputPassword);
			
			User adminstrativeUser = new User(UserRole.ADMIN, "Tayyab", "adminpass");			
			
			if(userList.contains(userLogin) || userList.contains(adminstrativeUser)){
				for (User users: userList){
					if(inputUsername.equals(users.getUsername()) && inputPassword.equals(users.getPassword())){
						if(users.getRole().equals(UserRole.ADMIN)){
							
							System.out.println("You have successfully logged in as a admin and you can perform the forlowing tasks");
							String input;
						do{
							
	
							Scanner sc = new Scanner(System.in);
							System.out.println("To add new user enter: Add User");
							System.out.println("To add new book enter: add book");
							System.out.println("To quit your session enter: quit");
							input = sc.nextLine();
							sc.close();

							if(input.equalsIgnoreCase("Add User")){
								
								Scanner read = new Scanner(System.in);
								System.out.println("Please enter user name: ");
								String usernameEntered = read.nextLine();
								System.out.println("Please enter password: ");
								String passwordEntered = read.nextLine();
								read.close();
								
								User registerUser = new User(UserRole.USER, usernameEntered, passwordEntered);
								registerUser.addNewUser(userList);
							}
							else if(input.equalsIgnoreCase("add book")){

								
								Book registerBook = addNewBook();
								
								
							}
							
							
						}while(input == "quit");
						//do something 
						// assigning operation
						
						
						System.out.println("For searching a book please enter: SEARCH");
						//read the input and assign the methods
						//also try to do the search separately for the admin
						//do the checkout if possible
						System.out.println("if the book is  ");
						
					}
					
					else
						if(users.getRole().equals(UserRole.USER)){
							//do something
							// assigning operation
							
							System.out.println("You have successfully logged in as a admin and you can perform the forlowing tasks");
							System.out.println("For searching a book please enter: SEARCH");

							
							}//end of inner if statement
						}//end of if statement after enhanced loop
					}//end of enhanced for loop
				}//end of out if 
			}//end of login method///////////////better would be if I use exception here book not found


		public ArrayList<User> addAdmin(ArrayList<User> userList){
			User administrativeUser = new User(UserRole.ADMIN, "Tayyab", "adminpass");			
			userList.add(administrativeUser);
			return userList;
		}
		
	}