package com.springrestmsr.projectspring.cliente;

import java.net.Socket;

public class DistributeTasks implements Runnable {

    private Socket socket;

    public DistributeTasks(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.println("Distribuindo tarefas para " + socket.getPort());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
