package com.messagingapp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	
	//initialize user attributes username, pw, and contacts
	private String username;
	private String password;
	private List<String> contacts;
	
	
	//new user
	public User (String username, String password) {
		this.username = username;
		this.password = password;
		this.contacts = new ArrayList<>();
	}
	
	
	public String getUsername() {
		return username;
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	//List of contacts
	public List<String> getContacts() {
		return contacts;
	}
	
	//add new contacts
	public void addContact1(String contactUsername) {
		
	}


	public void addContact(String contactUsername) {
		if (!contacts.contains(contactUsername)) { 
			contacts.add(contactUsername);
		}
	}
	
	



	public void saveContacts() {
	    // Save contacts to a file or database
	    // You can use serialization, JSON, XML, or any other suitable format
	    // For simplicity, let's assume saving to a text file here
	    try (PrintWriter writer = new PrintWriter(new FileWriter(username + "_contacts.txt"))) {
	        for (String contact : contacts) {
	            writer.println(contact);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}




	

}
