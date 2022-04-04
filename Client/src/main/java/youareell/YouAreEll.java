package youareell;

import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.*;
import models.Id;
import models.Message;
import views.IdTextView;
import views.MessageTextView;

import java.io.IOException;
import java.util.List;

public class YouAreEll { // TODO - all tests

    TransactionController tt;

    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public YouAreEll(MessageController messageController, IdController idController) {
        this.tt = new TransactionController(messageController, idController);
    }

    public static void main(String[] args) throws IOException, InterruptedException { // TODO - ?remove throws exception?
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(
                new MessageController(), new IdController()
        ));
        System.out.println(urlhandler.MakeURLCall("/ids")); //, "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/messages")); //, "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/post"));
    }

    private String MakeURLCall(String s) {
        if (s.equals("/ids")) {
            return get_ids();
        } else if (s.equals("/messages")) {
            return get_messages();
        } else if (s.equals("/post")) {
            return post_Ids;
        }
    }

    public String get_ids() throws JsonProcessingException {
        List<Id> id = tt.getIds();
        String show = "";
        for (Id i : id) {
            IdTextView view = new IdTextView(i);
            show += view + "\n";
        }
        return show;
    }

    public String get_messages() {
        List<Message> message = tt.getMessages();
        String show = "";
        for (Message m : message) {
            MessageTextView view = new MessageTextView();
            show += view.toString(m) + "\n";
        }
        return show;
    }

    public String post_Ids() {
        IdController idController = new IdController();
        idController.postId(new Id());
        return "New Id Created";
    }


}
