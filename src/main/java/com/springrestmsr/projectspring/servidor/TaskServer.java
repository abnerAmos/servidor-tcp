package com.springrestmsr.projectspring.servidor;

import com.springrestmsr.projectspring.cliente.DistributeTasks;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskServer {

    public static void main(String[] args) throws Exception {

        System.out.println("--- Iniciando Servidor ---");
        ServerSocket server = new ServerSocket(12345);
//        ExecutorService threadPool = Executors.newFixedThreadPool(2); // Threads fixas
        ExecutorService threadPool = Executors.newCachedThreadPool(); // Cresce e diminui dinâmicamente

        // Verificando a quantidade de processadores e memória da maquina.
//        Runtime runtime = Runtime.getRuntime();
//        int qtdProcessadores = runtime.availableProcessors();
//        long totalMemory = runtime.totalMemory();
//        long freeMemory = runtime.freeMemory();
//        long maxMemory = runtime.maxMemory();
//        System.out.println("Qtd de processadores: " + qtdProcessadores);
//        System.out.println("Total memória: " + qtdProcessadores);
//        System.out.println("Total memória livre: " + freeMemory);
//        System.out.println("max memória: " + maxMemory);

        while (true) {
            Socket socket = server.accept();
            System.out.println("-- Aceitando novo cliente na porta: " + socket);

            DistributeTasks distributeTasks = new DistributeTasks(socket);
            threadPool.execute(distributeTasks);
        }
    }
}
