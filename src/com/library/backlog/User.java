package com.library.backlog;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
	private String username;
	private String password;
	static ArrayList<User> userList;
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	//adding user
	public void addNewUser(String username, String password){
		User newUser = new User(username, password);
		userList = new ArrayList<User>();
		if(userList.contains(username)){
			System.out.println("The username already exist, please enter another user name");
		}
		else{
			userList.add(newUser);
		}
	}
	
	public void login(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your user name: ");
		String inputUsername = sc.next();
		System.out.println("Enter your password");
		String inputPassword = sc.next();
		
		for (int i = 0; i <= userList.size(); i++){
			if(inputUsername.equals(username) && inputPassword.equals(password)){
				System.out.println("You have successfully logged in, welcome to our library");
				System.out.println("For searching a book please enter: SEARCH");
				System.out.println("if the book is  ");
				

			
			}
			/**TODO
			 * 	connect the other methods using cases here
			 */
			
		}
		
	}

}
