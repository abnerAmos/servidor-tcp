package com.springrestmsr.projectspring.cliente;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TaskClient2 {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 12345);
        System.out.println("--- Conexão Estabelecida ---");

        PrintStream exit = new PrintStream(socket.getOutputStream());
        exit.println("c2");

        Scanner keyboard = new Scanner(System.in);
        keyboard.nextLine();

        exit.close();
        keyboard.close();
        socket.close();
    }
}
