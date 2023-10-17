package com.springrestmsr.projectspring.servidor;

import com.springrestmsr.projectspring.cliente.DistributeTasks;

import java.net.ServerSocket;
import java.net.Socket;

public class TaskServer {

    public static void main(String[] args) throws Exception {

        System.out.println("--- Iniciando Servidor ---");
        ServerSocket server = new ServerSocket(12345);

        while (true) {
            Socket socket = server.accept();
            System.out.println("-- Aceitando novo cliente na porta: " + socket.getPort());

            DistributeTasks distributeTasks = new DistributeTasks(socket);
            Thread threadClient = new Thread(distributeTasks);
            threadClient.start();
        }
    }
}
