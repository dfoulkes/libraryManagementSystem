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
	private boolean checkedOut = false;
	
	
	public Book(String isbn, String title){
		this.isbn = isbn;
		this.title = title;
		}
	
	
	//getter isbn
	public String getIsbn(){
		return isbn;
		}
	
	
	//getter title
	public String getTitle(){
		return title;
		}
	
	
	public boolean checkOut(){
		checkedOut = true; //book borrowed no more in the stack
		return checkedOut;
		}
	
	
	public boolean returned(){
		checkedOut = false; //book back to stack and available for users
		return checkedOut;
		}
	
	
	public boolean getcheckedOut(){
		return checkedOut;
		}
		
	}//end of class Book


