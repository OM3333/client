package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.*;
import java.net.Socket;

public class ConnectionThread extends Thread{
    private Socket socket;
    private PrintWriter writer;

    public ConnectionThread(String address, int port) throws IOException {
        socket = new Socket(address,port);
    }

    @Override
    public void run() {
        try {
            InputStream input  = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            this.writer = new PrintWriter(output, true);

            String message;
            while ((message = reader.readLine()) != null) {
                Message messageIn = Message.fromJson(message);
                System.out.println(messageIn.text);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message){
        Message messageOut = new Message("message",message);
        try {
            writer.println(messageOut.toJson());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
