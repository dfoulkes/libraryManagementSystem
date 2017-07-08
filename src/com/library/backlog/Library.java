package com.library.backlog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
	
	String isbn;
	String title;
	Book book;
		
	private List<Book> bookList;
	// actually we doing this here:
	// List<Book> bookList = new ArrayList<Book>(); 
	// List is an interface and ArrayList is the implementation of it, List gives more flexibility than an ArrayList and less bugs
	
	public Library(){
		
		//the arraylist to which books can be added
		bookList = new ArrayList<Book>();
		
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

		}//end of search
		
	}