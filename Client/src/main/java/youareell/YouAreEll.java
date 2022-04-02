package youareell;

import controllers.*;
import org.json.simple.JSONArray;

import java.io.IOException;

public class YouAreEll {

    TransactionController tt;

    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public static void main(String[] args) throws IOException { // TODO - remove throws exception
        // ServerController serverController = ServerController.shared(); // TODO - remove these 2 lines (this and below)
        // JSONArray ids = serverController.idGet();
        MessageController messageController = MessageController.shared(); // TODO - delete
        System.out.println(messageController.messagesSeen); // TODO - delete
        // System.out.println(messageController.intermittent); // TODO - delete

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
