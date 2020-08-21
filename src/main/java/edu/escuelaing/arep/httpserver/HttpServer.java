package edu.escuelaing.arep.httpserver;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpServer {
    private int port = 36000;
    private boolean running = false;

    public HttpServer(){
    }
    public HttpServer(int port){
        this.port = port;
    }

    public void start(){

    }

    private void processRequest(Socket clientSocket) throws IOException {

        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        Map<String, List<String>> request = new HashMap<>();
        while ((inputLine=in.readLine())!=null){
            System.out.println("");

        }

    }


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(36000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Recibí: " + inputLine);
            if (!in.ready()) {break; }
        }
        outputLine =
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "<meta charset=\"UTF-8\">" +
                        "<title>Title of the document</title>\n" +
                        "</head>" +
                        "<body>" +
                        "<h1>Mi propio mensaje</h1>" +
                        "</body>" +
                        "</html>" + inputLine;
        out.println(outputLine);
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
