package com.springrestmsr.projectspring.servidor;

import com.springrestmsr.projectspring.cliente.DistributeTasks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskServer {

    private ExecutorService threadPool;
    private ServerSocket server;
    private AtomicBoolean running;
    private BlockingQueue<String> queue;

    public TaskServer() throws IOException {
        System.out.println("--- Iniciando Servidor ---");
        this.server = new ServerSocket(12345);
        this.threadPool = Executors.newCachedThreadPool();  // Cresce e diminui dinâmicamente
        this.running = new AtomicBoolean(true);
        this.queue = new ArrayBlockingQueue<>(2);
        initConsumers();
    }

    private void initConsumers() {
        for (int i = 0; i < 2; i++) {
            TaskConsumer consumer = new TaskConsumer(queue);
            this.threadPool.execute(consumer);
        }
    }

    public void init() throws IOException {
        while (this.running.get()) {
            try {
                Socket socket = server.accept();
                System.out.println("-- Aceitando novo cliente na porta: " + socket);

                DistributeTasks distributeTasks = new DistributeTasks(socket, this);
                this.threadPool.execute(distributeTasks);
            } catch (SocketException e) {
                System.out.println("esta executando? " + this.running);
            }
        }
    }

    public void stop() throws IOException {
        this.running.set(false);
        this.server.close();
        this.threadPool.shutdown();
    }

    public static void main(String[] args) throws Exception {
        TaskServer serv = new TaskServer();
        serv.init();
    }

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
}
