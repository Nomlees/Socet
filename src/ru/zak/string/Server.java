package ru.zak.string;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket( 8079);
        System.out.println("Сервер ждет клиента...");

        try(Socket clientSocket = serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))){

            System.out.println("Новое соединение: " + clientSocket.getInetAddress().toString());

            String string;
            while ((string = reader.readLine())!=null){
                writer.write(string);
                System.out.println("Клиент прислал на сервер:" + string);
                System.out.println("Сервер ответил: " + string);
                if(string.equals("end")){
                    break;
                }
                outputStream.flush();
            }
            System.out.println("Клиент отключился от сервера");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

