package controllers;

import java.util.ArrayList;
import java.util.HashSet;

import models.Id;
import models.Message;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MessageController {

    public HashSet<Message> messagesSeen = new HashSet<>(); // TODO - change back to private
    // why a HashSet??
    public HashSet<Object> messObject = new HashSet<>(); // TODO - delete
    public ArrayList<String> intermittent = new ArrayList<>(); // TODO - delete

    private MessageController() {
        ServerController serverController = ServerController.shared();
        JSONArray messagesJSON = serverController.messageGet();
//        Message[] messages = (Message[]) messagesJSON.toArray(new Message[0]);
//        for (int i = 0; i < messagesJSON.size(); i++) {
//            messagesSeen.add(messages[i]);
//        }
        for (int i = 0; i < messagesJSON.size(); i++) {
            Object object = messagesJSON.get(i);
            String string = object.toString();
            String[] messageList = string.split(",");
            String toID = messageList[0].substring(8);
            intermittent.add(toID);
            String sequence = messageList[1].substring(11);
            intermittent.add(sequence);
            String message = messageList[2].substring(9);
            intermittent.add(message);
            String fromID = messageList[3].substring(9);
            intermittent.add(fromID);
            String timestamp = messageList[4].substring(12);
            intermittent.add(timestamp);
            Message messageObject = new Message(message, fromID, toID, timestamp, sequence);
            messagesSeen.add(messageObject);
        }
        // convert object to message BEFORE adding to hashset
        // to do so... figure out how to break up the info for each message into each variable required
        // for a message
    }

    private static MessageController mesCon = new MessageController();

    public static MessageController shared() { // Singleton --- getInstance
        return mesCon;
    }

    public ArrayList<Message> getMessages() {
    // messages should return the last 20 messages, nicely formatted.
        // initalize instan of servercontroller
        // return arraylist
        return null;
    }
    public ArrayList<Message> getMessagesForId(Id Id) {
    // messages your_github_id should return the last 20 messages sent to you.
        return null;
    }
    public Message getMessageForSequence(String seq) {
    // GET : Get msg with a sequence - returns a JSON message object for a sequence number
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) { // TODO - post vs pull
    // send your_github_id 'Hello World' should post a new message in the timeline
    // send your_github_id 'my string message' to some_friend_githubid should post a message to your
        // friend from you on the timeline.
        return null;
    }
 
}