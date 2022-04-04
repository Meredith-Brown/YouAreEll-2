package controllers;

import java.util.ArrayList;
import java.util.HashMap;

import models.Id;
import models.Message;
import org.json.simple.JSONArray;

public class IdController {
    public HashMap<String, Id> allIds = new HashMap<>(); // TODO - revert to private

    private IdController() {
        String user = "";
        ServerController serverController = ServerController.shared();
        JSONArray idsJSON = serverController.idGet();
        for (int i = 0; i < idsJSON.size(); i++) {
            Object object = idsJSON.get(i);
            String string = object.toString();
            String[] idList = string.split(",");
            String gitHub = idList[0].substring(10);
            String name = idList[1].substring(7);
            if (idList[2].length() >=15) {
                user = idList[2].substring(10, idList[2].length() - 2);
            } else {
                user = idList[2].substring(10);
            }
            Id idObject = new Id(name, gitHub, user);
            allIds.put(user, idObject);
        }
    }

    private static IdController idCon = new IdController();

    public static IdController shared() { // Singleton --- getInstance
        return idCon;
    }

    // Id myId; // TODO - uncomment out?

    public HashMap<String, Id> getter() { // TODO - delete and complete method below
        return allIds;
    }

    public ArrayList<Id> getIds() {
        return null;
    }

    public Id postId(Id id) {
    // You register your name and github id by creating an ID JSON payload (see below) and POSTing it to
    // the server.
        // create json from id
        // call server, get json result Or error
        // result json to Id obj
        return null;
    }

    public Id putId(Id id) {
        return null;
    }
 
}