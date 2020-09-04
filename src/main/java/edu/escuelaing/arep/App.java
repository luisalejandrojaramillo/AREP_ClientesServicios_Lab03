package edu.escuelaing.arep;

import com.mongodb.BasicDBObject;
import edu.escuelaing.arep.httpserver.HttpServer;
import edu.escuelaing.arep.httpserver.Request;

import edu.escuelaing.arep.persistence.MongoBD;

import java.io.*;
import java.util.ArrayList;

public class App {
    private static MongoBD mongo;

    /**
     * Las url a las que vamos a poder acceder
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        HttpServer server=new HttpServer();

        mongo=server.getMongoConnecion();

        server.get("/prueba", (req) -> prueba());

        server.get("/database", (req) -> data(req));

        server.start();
    }

    private static String prueba() {
        return  "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/"+"html"+"\r\n"
                + "\r\n"
                +"<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h1>Hello world, AREP Lab</h1>"
                + "</body>"
                + "</html>";
    }



    private static String data(Request req) {
        String res="";
        ArrayList<BasicDBObject> list= mongo.consult();
        for(BasicDBObject d:list) {

            res+="<tr><td>"+(d.get("nombre")).toString()+"</td><td>"+(d.get("mensaje")).toString()+"</td></tr>";
        }
        System.out.println(res);
        String header = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/"+"html"+"\r\n"
                + "\r\n";
        String view = "<!DOCTYPE html>"
                + "<html>"
                + "<style>"
                + "table, th, td {"
                + "border: 1px solid black;"
                + "border-collapse: collapse;"
                + "}"
                + "</style>"
                +"<center>"
                + "<Table>"
                + "<tr>"
                + "<th>Name</th>"
                + "<th>Description</th>"
                + "</tr>"
                + res
                + "</Table>"
                +"</center>"
                + "</body>"
                + "</html>";
        return header+view;
    }

}