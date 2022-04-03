package youareell;

import controllers.*;
import models.Message;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.Iterator;

public class YouAreEll {

    TransactionController tt;

    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public static void main(String[] args) throws IOException { // TODO - ?remove throws exception?
        // ServerController serverController = ServerController.shared(); // TODO - remove these 2 lines (this and below)
        // JSONArray ids = serverController.idGet();
        MessageController messageController = MessageController.shared(); // TODO - delete
        System.out.println(messageController.messagesSeen); // TODO - delete
        Iterator<Message> i = messageController.messagesSeen.iterator(); // TODO - delete
        while (i.hasNext()) { // TODO - delete
            System.out.println(i.next().getTimestamp()); // TODO - delete
        }
        System.out.println("------------------");
        for (int j = 0; j < messageController.getMessages().size(); j++) {
            System.out.println(messageController.getMessages().get(j).getTimestamp());
        }
        // messageController.getMessages(); // TODO - delete







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
