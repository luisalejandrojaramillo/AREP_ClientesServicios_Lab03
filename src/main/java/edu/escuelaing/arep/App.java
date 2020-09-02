package edu.escuelaing.arep;

import edu.escuelaing.arep.httpserver.HttpServer;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        HttpServer server=new HttpServer();
        server.start();
    }
}
