package ru.netology.javacore;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private final int port;
    private final Todos todos;


    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() {

        System.out.println("\nStarting server at " + port + "...\n");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (
                        Socket clientSocket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.println("New connection accepted. port: " + clientSocket.getPort());
                    final String jsonTask = in.readLine();
                    System.out.println("Client message: " + jsonTask);
                    final JsonObject jsonObject = JsonParser.parseString(jsonTask).getAsJsonObject();
                    String type = jsonObject.get("type").toString().replaceAll("\"", "");
                    String task = jsonObject.get("task").toString().replaceAll("\"", "");
                    switch (type) {
                        case "ADD":
                            System.out.println("Add task '" + task + "' to TODO list");
                            todos.addTask(task);
                            break;
                        case "REMOVE":
                            System.out.println("Remove task '" + task + "' from TODO list");
                            todos.removeTask(task);
                            break;
                    }
                    System.out.println("TODO list send to client... \n");
                    out.println("Tasks: " + todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("I can't start the server");
            e.printStackTrace();

        }
    }
}
