package controllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;
import org.json.simple.JSONArray;

public class IdController {
    private HashMap<String, Id> allIds = new HashMap<>();

    Id myId;

    public IdController() { // null constructor
//        String user = "";
//        ServerController serverController = ServerController.shared();
//        JSONArray idsJSON = serverController.idGet();
//        for (int i = 0; i < idsJSON.size(); i++) {
//            Object object = idsJSON.get(i);
//            String string = object.toString();
//            String[] idList = string.split(",");
//            String gitHub = idList[0].substring(10);
//            String name = idList[1].substring(7);
//            if (idList[2].length() >=15) {
//                user = idList[2].substring(10, idList[2].length() - 2);
//            } else {
//                user = idList[2].substring(10);
//            }
//            Id idObject = new Id(name, gitHub, user);
//            allIds.put(user, idObject);
//        }
    }

    public ArrayList<Id> getIds() throws JsonProcessingException {
        ServerController serverController = ServerController.shared();
        String getIds = String.valueOf(serverController.idGet());
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Id> ids = new ArrayList<>();
        ids = objectMapper.readValue(getIds, new TypeReference<>() {
        });
        for (Id i : ids) {
            allIds.put(i.getGithub(), i);
        }
        return ids;
    }

//    public Id postId(Id id) {
//        id = new Id("testMer", "testMer");
//        ServerController.shared().idPost(id);
//    // You register your name and github id by creating an ID JSON payload (see below) and POSTing it to
//    // the server.
//        // create json from id
//        // call server, get json result Or error
//        // result json to Id obj
//        return null;
//    }
//
//    public Id putId(Id id) { // TODO
//        return null;
//    }
}