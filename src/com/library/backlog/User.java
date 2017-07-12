package com.library.backlog;

import java.util.ArrayList;
import java.util.Scanner;

import com.library.userroles.UserRole;

public class User {
	private String username;
	private String password;
	private static UserRole role;
	//here the program forced me to use static WHY?   
	
	static ArrayList<User> userList;
	
	public User(UserRole role, String username, String password){
		this.role = role;
		this.username = username;
		this.password = password;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public static UserRole getRole(){
		return role;
	}

	}//end of class USer
