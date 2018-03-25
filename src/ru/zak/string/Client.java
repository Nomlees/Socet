package ru.zak.string;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try (Socket socket = new Socket("localhost", 8079);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {

            while (true){
                String string;
                System.out.println("Введите строку для отправки на сервер: ");
                string = scan.nextLine();
                writer.write(string);
                System.out.println("клиент отправил: " + string);
                System.out.println("прислал сервер: " + string);
                if (string.equals("end")){
                    break;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

