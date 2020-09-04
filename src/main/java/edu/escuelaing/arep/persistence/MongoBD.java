package edu.escuelaing.arep.persistence;

import com.mongodb.*;

import java.util.ArrayList;

public class MongoBD {
    private DBCollection collection;
    private DB db;

    public MongoBD(){
        System.out.println("im in");
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://luisalejandroj:Alejo123@cluster0.fgamm.mongodb.net/AREPLab?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);
        //MongoDatabase database = mongoClient.getDatabase("test");
        db = mongoClient.getDB("AREPLab");
        collection = db.getCollection("AREPLab");
    }

    public ArrayList<BasicDBObject> consult() {
        ArrayList<BasicDBObject> registros = new ArrayList<BasicDBObject>();
        DBCursor mensajes = collection.find();
        while (mensajes.hasNext()){
            registros.add((BasicDBObject) mensajes.next());
        }
        return registros;
    }

}
