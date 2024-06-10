package com.messagingapp;
import java.io.Serializable;


public class Message implements Serializable {
	
	//initialize messaging attributes
	private String sender;
	private String receiver;
	private String type; //message (text,photo,vid)
	private String content; //text/file path
	
	
	//create message
	public Message(String sender, String receiver, String type, String content) {
		this.sender = sender;
		this.receiver = receiver;
		this.type = type;
		this.content = content;
		
	}
	
	public String getSender() {
		return sender;
	}
	
	public String getReceiver() {
		return receiver;
	}
	
	public String getType() {
		return type;
	}
	
	public String getContent() {
		return content;
	}

	

}
