package com.springrestmsr.projectspring.cliente;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistributeTasks implements Runnable {

    private Socket socket;

    public DistributeTasks(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Distribuindo tarefas para " + socket.getPort());
            Scanner entry = new Scanner(socket.getInputStream());
            PrintStream out = new PrintStream(socket.getOutputStream());

            while (entry.hasNextLine()) {
                String command = entry.nextLine();
                out.println("Comando recebido: " + command);

                System.out.println(command);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
