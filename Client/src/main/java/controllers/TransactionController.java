package controllers;

import models.Id;

import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;

    public TransactionController(MessageController m, IdController j) {}

    public List<Id> getIds() { // TODO - filter out IDs from IDget on ServerController and compile into list
        // will get all IDs as a unit - filter into lsit

        return null;
    }
    public String postId(String idtoRegister, String githubName) {
        Id tid = new Id(idtoRegister, githubName);
        tid = idCtrl.postId(tid);
        return ("Id registered.");
    }
}

// The Controller exists between the view and the model. It listens to events triggered by the view
// (or another external source) and executes the appropriate reaction to these events. In most cases,
// the reaction is to call a method on the model. Since the view and the model are connected through a
// notification mechanism, the result of this action is then automatically reflected in the view.
