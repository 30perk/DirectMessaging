package com.messagingapp;

import java.io.*;
import java.util.HashMap;

public class UserDatabase {
	
	//using hash map to store usernames
	private HashMap<String, User> users; 
	//storing serialized user data --- object to byte sequence
	private static final String DATA_FILE = "data/users.ser";
	
	
	public UserDatabase() {
		users = new HashMap<>();
		loadUsers();
	}
	
	
	public boolean registerUser(String username, String password) {
		if (users.containsKey(username)) {
			return false;
		}
		users.put(username, new User(username, password));
		saveUsers();
		return true;
	}
	
	
	public User loginUser(String username, String password) {
		User user = users.get(username);
		if (user != null && user.checkPassword(password)) {
			return user;
		}
		return null;
	}
	
	
	private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	public boolean addContact(String username, String contactUsername) {
	    User user = users.get(username);
	    if (user != null) {
	        user.addContact(contactUsername);
	        user.saveContacts(); // Save contacts after adding
	        return true;
	    }
	    return false;
	}
	
	
	@SuppressWarnings("unchecked") //data being read is expected 
	private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            users = (HashMap<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // No users saved yet
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	

}
