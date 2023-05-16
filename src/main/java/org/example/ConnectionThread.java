package org.example;

import java.io.*;
import java.net.Socket;

public class ConnectionThread extends Thread{
    private Socket socket;
    private PrintWriter writer;

    public ConnectionThread(String address, int port) throws IOException {
        socket = new Socket(address,port);
        System.out.println("Plase login");

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
                System.out.println(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message){
        writer.println(message);
    }
}
