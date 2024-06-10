package com.messagingapp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MesaagingService {
	
	//dir for message to byte data
	private static final String MESSAGES_DIR = "data/message";
	
	//check if message dir exists
	public void MessagingService() {
		File dir = new File(MESSAGES_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	
	
	public void sendMessage(String sender, String receiver, String type, String content) {
		Message message = new Message(sender, receiver, type, content);
		saveMessage(receiver, message);
	}
	
	
	@SuppressWarnings("unchecked") //data being read is expected
	public List<Message> getMessages(String username) {
        List<Message> messages = new ArrayList<>();
        File userMessagesFile = new File(MESSAGES_DIR + username + ".ser");
        if (userMessagesFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userMessagesFile))) {
                messages = (List<Message>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return messages;
       
	}
	
	
	private void saveMessage(String receiver, Message message) {
		List<Message> messages = getMessages(receiver);
		messages.add(message);
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MESSAGES_DIR + receiver + ".ser"))) {
            oos.writeObject(messages);
	} catch (IOException e) {
		e.printStackTrace();
		}
	
	}
	
}
