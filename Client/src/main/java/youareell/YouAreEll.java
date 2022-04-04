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

    public static void main(String[] args) throws IOException, InterruptedException { // TODO - ?remove throws exception?
        IdController idController = IdController.shared();
        MessageController messageController = MessageController.shared();
        Message message = new Message("note to self", "Meredith-Brown");
        messageController.postMessage(idController.allIds.get("a8cf70a2ef5c4e46b3c467884056b3008a044190"),
                idController.allIds.get("a8cf70a2ef5c4e46b3c467884056b3008a044190"), message);
        System.out.println(idController.allIds.get("a8cf70a2ef5c4e46b3c467884056b3008a044190").getGithub());






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
