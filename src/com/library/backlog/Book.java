package com.library.backlog;

import java.util.ArrayList;
import java.util.Scanner;

public class Book {
	/**
	 * this class specifies the actions that would be done on the books.
	 * For Example: Book can be added
	 * 				Book can be checkedout
	 * 				Book can be return in TIME
	 */
	
	private String isbn;
	private String title;
	private boolean checkedOut;
	static ArrayList<Book> bookList;
	
	
	// for now I not sure if should use this constructor
	public Book(String isbn, String title){
		this.isbn = isbn;
		this.title = title;
	}
	
	//setter isbn: its not need here because this already being done by the constructor
	public void setIsbn(String isbn){
		this.isbn = isbn;
	}
	
	//setter title: its not need here because this already being done by the constructor
	public void setTitle(String title){
		this.title = title;
	}
	
	//getter isbn
	public String getIsbn(String isbn){
		return isbn;
	}
	
	//getter title
	public String getTitle(){
		return title;
	}
	
	/**
	 * TODO
	 * @param isbn
	 * @return
*/
	public void checkOut(){
		checkedOut = true;
	}
	
	
	public void returned(){
		checkedOut = false;
	}
	
	public boolean ischeckedOut(){
		return checkedOut;
	}
	 
	//adding new book
	public static void addNewBook(String bookName, String isbn){
		
		Book newBook = new Book(bookName, isbn);
		
		//the arraylist to which books can be added
		bookList = new ArrayList<Book>(); 
		
		//book already exist or not
		if(bookList.contains(bookName) && bookList.contains(isbn)){
			System.out.println("The book already exists");
		}
		else{
			bookList.add(newBook);
		}
	}
		
		//searching for a book
		
	public void search(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Search for a book with title or isbn");
		String bookSearch = sc.next();
		
			for(int i=0; i<= bookList.size(); i++){
				if(bookSearch.equals(title) || bookSearch.equals(isbn)){
					if(bookList.get(i).checkedOut == false){
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
			
		
	}