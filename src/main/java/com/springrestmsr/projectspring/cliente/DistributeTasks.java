package com.springrestmsr.projectspring.cliente;

import com.springrestmsr.projectspring.servidor.TaskServer;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistributeTasks implements Runnable {

    private Socket socket;
    private TaskServer server;

    public DistributeTasks(Socket socket, TaskServer server) {
        this.socket = socket;
        this.server = server;
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

                if (command.trim().equals("fim")) {
                    out.println("-- Desligando servidor.");
                    this.server.stop();
                }

                System.out.println(command);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
