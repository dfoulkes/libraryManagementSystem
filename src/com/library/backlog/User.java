package com.library.backlog;

import java.util.ArrayList;
import java.util.Scanner;

import com.library.userroles.UserRole;

public class User {
	private String username;
	private String password;
	private static UserRole role;

	static ArrayList<User> userList;
	
	public User(UserRole role, String username, String password){
		this.role = role;
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
	
	public static UserRole getRole() {

		return role;
	}
	
	//adding user
	public void addNewUser(String username, String password){
		User newUser = new User(UserRole.USER, username, password);
		userList = new ArrayList<User>();
		if(userList.contains(newUser)){
			System.out.println("The user already exist, please enter another");
		}
		else{
			userList.add(newUser);
		}
	}
	
	public void login(){
		
		//this should also be changed for admin and user according to role and everything
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your username: ");
		String inputUsername = sc.nextLine();
		System.out.println("Enter your password");
		String inputPassword = sc.nextLine();
		sc.close();
		User userLogin = new User(role, inputUsername, inputPassword);
		
		//also role doesn't work find out why and whether getRole() would do the job or not
		
		if(userList.contains(userLogin)){
			for (User users: userList){
				if(inputUsername.equals(username) && inputPassword.equals(password)){
					if(User.getRole().equals(UserRole.ADMIN)){
					
					
					//do something 
					// assigning operation
					
					
					System.out.println("You have successfully logged in as a admin and you can perform the forlowing tasks");
					System.out.println("For searching a book please enter: SEARCH");
					//read the input and assign the methods
					//also try to do the search separately for the admin
					//do the checkout if possible
					System.out.println("if the book is  ");
					
				}
				
				else
					if(User.getRole().equals(UserRole.USER)){
						
						//do something
						// assigning operation
						
						System.out.println("You have successfully logged in as a admin and you can perform the forlowing tasks");
						System.out.println("For searching a book please enter: SEARCH");

						
						}//end of inner if statement
					}//end of if statement after enhanced loop
				}//end of enhanced for loop
			}//end of out if 
		}//end of login method
	}//end of class USer
