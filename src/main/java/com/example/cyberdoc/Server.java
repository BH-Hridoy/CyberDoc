package com.example.cyberdoc;


import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Server(ServerSocket serverSocket) {

        try{
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (Exception e){
            System.out.println("Error creating server.");
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }



    public void sendMessageToClient(String messageToClient){
        try {
            bufferedWriter.write(messageToClient);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error sending the message to client.");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }


    public void receiveMessageFromClient(VBox vBox){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    try {
                        String messageFromClient = bufferedReader.readLine();
                        ServerController.addLabel(messageFromClient, vBox);
                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println("Error receiving message from the client");
                        closeEverything(socket, bufferedReader,bufferedWriter);
                        break;
                    }
                }
            }
        }).start();
    }


    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try{
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();

            }
            if(socket != null){
                socket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
