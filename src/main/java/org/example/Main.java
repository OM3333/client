package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            ConnectionThread connection = new ConnectionThread("localhost",5000);
            connection.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Please login");
            String login = reader.readLine();
            Message loginMessage = new Message("login",login);
            connection.sendMessage(loginMessage);

            while(true){
                String message = reader.readLine();
                connection.sendMessage(new Message("message",message));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}