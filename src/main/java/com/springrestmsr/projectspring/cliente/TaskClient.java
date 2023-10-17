package com.springrestmsr.projectspring.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TaskClient {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 12345);
        System.out.println("--- Conexão Estabelecida ---");

        Thread threadOut = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PrintStream exit = new PrintStream(socket.getOutputStream());
                    Scanner keyboard = new Scanner(System.in);

                    while (keyboard.hasNextLine()) {
                        String line = keyboard.nextLine();

                        if (line.trim().equals(""))
                            break;

                        exit.println(line);
                    }
                    exit.close();
                    keyboard.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadIn = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("-- Recebendo dados do servidor");
                    Scanner responseServer = new Scanner(socket.getInputStream());
                    while (responseServer.hasNextLine()) {
                        String line = responseServer.nextLine();
                        System.out.println(line);
                    }
                    responseServer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadOut.start();
        threadIn.start();
        threadIn.join();

        System.out.println("Encerrando socket cliente");
        socket.close();
    }
}
