package com.messagingapp;

import java.util.List;
import java.util.Scanner;


public class MessagingApp {
    // User database instance
    private static UserDatabase userDatabase = new UserDatabase();
    // Messaging service instance
    private static MesaagingService messagingService = new MesaagingService();

  
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Main loop for user interaction
        while (true) {
            // Display menu options
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Handle user's choice
            if (choice == 1) {
                // Registration process
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                // Register the user
                if (userDatabase.registerUser(username, password)) {
                    System.out.println("Registration successful!");
                } else {
                    System.out.println("Username already exists!");
                }
            } else if (choice == 2) {
                // Login process
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                // Login the user
                User user = userDatabase.loginUser(username, password);
                if (user != null) {
                    System.out.println("Login successful!");
                    // Handle user session
                    handleUserSession(user);
                } else {
                    System.out.println("Invalid username or password!");
                }
            }
        }
    }

    // Method to handle user session after login
    private static void handleUserSession(User user) {
        Scanner scanner = new Scanner(System.in);

        // Session loop for user interaction
        while (true) {
            System.out.println("1. Add Contact");
            System.out.println("2. Send Message");
            System.out.println("3. View Messages");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                // Add contact 
                System.out.print("Enter contact username: ");
                String contactUsername = scanner.nextLine();
                user.addContact(contactUsername);
                System.out.println("Contact added!");
            } else if (choice == 2) {
                // Send message 
                System.out.print("Enter receiver username: ");
                String receiver = scanner.nextLine();
                System.out.print("Enter message type (text/photo/video): ");
                String type = scanner.nextLine();
                System.out.print("Enter message content: ");
                String content = scanner.nextLine();
                // Send the message
                messagingService.sendMessage(user.getUsername(), receiver, type, content);
                System.out.println("Message sent!");
            } else if (choice == 3) {
                // View messages 
                List<Message> messages = messagingService.getMessages(user.getUsername());
                for (Message message : messages) {
                    System.out.println("From: " + message.getSender() + ", Type: " + message.getType() + ", Content: " + message.getContent());
                }
            } else if (choice == 4) {
                // Logout the user
                System.out.println("Logged out!");
                break; 
            }
        }
    }
}