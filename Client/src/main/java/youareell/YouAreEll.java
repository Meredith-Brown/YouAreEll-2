package youareell;

import controllers.*;
import models.Id;
import models.Message;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class YouAreEll { // TODO - all tests

    TransactionController tt;

    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public static void main(String[] args) throws IOException { // TODO - ?remove throws exception?
        ServerController serverController = ServerController.shared(); // TODO - remove these 2 lines (this and below)
        JSONArray ids = serverController.idGet();
        MessageController messageController = MessageController.shared(); // TODO - delete
//        System.out.println(messageController.messagesSeen); // TODO - delete
//        Iterator<Message> i = messageController.messagesSeen.iterator(); // TODO - delete
//        while (i.hasNext()) { // TODO - delete
//            System.out.println(i.next().getTimestamp()); // TODO - delete
//        }
//        System.out.println("------------------");

//                System.out.println(messageController.messagesSeen); // TODO - delete
//        Iterator<Message> i = messageController.messagesSeen.iterator(); // TODO - delete
//        while (i.hasNext()) { // TODO - delete
//            System.out.println(i.next().getToId()); // TODO - delete
//        }
//                System.out.println("------------------");

        //TODO - test me
        IdController idController = IdController.shared();
        HashMap<String, Id> allIds = idController.getter();
        ArrayList<Message> messArray = messageController.getMessagesForId(allIds.get
                ("c18ef75a51089718f660a1b5f7ea1860fdd00e7d"));
        for (int x = 0; x < messArray.size(); x++) {
            System.out.println(messArray.get(x));
        }
//        for (int j = 0; j < messageController.getMessagesForId(allIds.get
//                ("\"Bet\"")).size(); j++) { // TODO - figure out how to make IDs so we can test
//            System.out.println(messageController.getMessagesForId(allIds.get
//                ("\"Bet\"")).get(j).getTimestamp());
       // } // issue seems to be that ids ids have "" and message ids dont'

//        IdController idController2 = IdController.shared();
//        HashMap<String, Id> allIds2 = idController2.getter();
//        System.out.println(allIds2 + "\n");
//        System.out.println(allIds2.get("\"temp-java\""));
//        System.out.println(idController.listOfUsers);
//        ArrayList<String> newArrayList = new ArrayList<>();
//        newArrayList.add("hi");
//        newArrayList.add("there");
//        newArrayList.add("meredith");
//        for (int g = 0; g < newArrayList.size(); g++) {
//            System.out.println(newArrayList.get(g) + "\n");
//        }


//        System.out.println("-------------");
//        for (int j = 0; j < messageController.getMessages().size(); j++) {
//            System.out.println(messageController.getMessages().get(j));
//        }
//         messageController.getMessages(); // TODO - delete















//        // hmm: is this Dependency Injection? // TODO - fix
//        YouAreEll urlhandler = new YouAreEll(
//            new TransactionController(
//                new MessageController(), new IdController()
//        ));
//        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
//        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
//    }
//
//    public String get_ids() {
//        return tt.makecall("/ids", "GET", "");
//    }
//
//    public String get_messages() {
//        return MakeURLCall("/messages", "GET", "");
    }


}
