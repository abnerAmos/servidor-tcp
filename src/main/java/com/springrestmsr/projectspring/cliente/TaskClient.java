package com.springrestmsr.projectspring.cliente;

import java.net.Socket;

public class TaskClient {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 12345);
        System.out.println("--- Conexão Estabelecida ---");
        socket.close();
    }
}
