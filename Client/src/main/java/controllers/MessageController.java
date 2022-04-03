package controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

import models.Id;
import models.Message;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MessageController {

    public HashSet<Message> messagesSeen = new HashSet<>(); // TODO - change back to private
    // why a HashSet??

    private MessageController() {
        ServerController serverController = ServerController.shared();
        JSONArray messagesJSON = serverController.messageGet();
        for (int i = 0; i < messagesJSON.size(); i++) {
            Object object = messagesJSON.get(i);
            String string = object.toString();
            String[] messageList = string.split(",");
            String toID = messageList[0].substring(8);
            String sequence = messageList[1].substring(11);
            String message = messageList[2].substring(10);
            String fromID = messageList[3].substring(9);
            String timestamp = messageList[4].substring(12);
            Message messageObject = new Message(message, fromID, toID, timestamp, sequence);
            messagesSeen.add(messageObject);
        }
    }

    private static MessageController mesCon = new MessageController();

    public static MessageController shared() { // Singleton --- getInstance
        return mesCon;
    }

    public ArrayList<Message> getMessages() {
        // messages should return the last 20 messages, nicely formatted.
        Message[] array = messagesSeen.toArray(new Message[0]);
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            messages.add(array[i]);
        }
        ArrayList<Message> copy = (ArrayList<Message>) messages.stream().sorted(Comparator.
                comparing(Message::getTimestamp)).collect(Collectors.toList());
        ArrayList<Message> newestMessages = new ArrayList<>();
        if (copy.size() > 20) {
            for (int j = copy.size() - 1; j >= copy.size() - 21; j--) {
                newestMessages.add(copy.get(j));
            }
        } else {
            for (int j = copy.size() - 1; j == 0; j--) {
                newestMessages.add(copy.get(j));
            }
        }
        return newestMessages;
    }

    public ArrayList<Message> getMessagesForId(Id Id) {
    // messages your_github_id should return the last 20 messages sent to you.
//        Message[] array = messagesSeen.toArray(new Message[0]);
//        ArrayList<Message> messages = new ArrayList<>();
//        for (int i = 0; i < array.length; i++) {
//            String stringId = Id.getName();
//            if (array[i].getToId().equals(stringId)) {
//                messages.add(array[i]);
//            }
//        }
        Message[] arrayZero = messagesSeen.toArray(new Message[messagesSeen.size()]); // remove nulls
        ArrayList<Message> array = new ArrayList<>();
        int count = 0;
        for (Message m : arrayZero) {
            if (m.getToId().equals(null)) {

            } else {
                array.add(m);
                count++;
            }
        }
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            String stringId = Id.getGithub(); // TODO - seems to be using github ID - FIX?
            if (array.get(i).getToId().equals(stringId)) {
                messages.add(array.get(i));
            } else {
                System.out.println("Name: " + Id.getName()); // TODO - delete
                System.out.println("UID: " + Id.getUid()); // TODO - delete
                System.out.println("GitHub: " + Id.getGithub()); // TODO - delete
                // System.out.println(array.get(i).getToId()); // TODO - delete
            }
        }
//        ArrayList<Message> messages = new ArrayList<>();
//        for (int i = 0; i < array.length; i++) {
//            String stringId = Id.getName();
//            if (array[i].getToId().equals(stringId)) {
//                messages.add(array[i]);
//            }
//        }
        ArrayList<Message> copy = (ArrayList<Message>) messages.stream().sorted(Comparator.
                comparing(Message::getTimestamp)).collect(Collectors.toList());
        ArrayList<Message> newestMessages = new ArrayList<>();
        if (copy.size() > 20) {
            for (int j = copy.size() - 1; j >= copy.size() - 21; j--) {
                newestMessages.add(copy.get(j));
            }
        } else {
            for (int j = copy.size() - 1; j == 0; j--) {
                newestMessages.add(copy.get(j));
            }
        }
        return newestMessages;
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

    public int sort(String one, String two) {
        int count = 0;
        for (int j = 0; j < one.length(); j++) {
            char ch1 = one.charAt(j);
            int value1 = Character.getNumericValue(ch1);
            char ch2 = two.charAt(j);
            int value2 = Character.getNumericValue(ch2);
            if (value1 > value2) {
                return 1;
            } else if (value1 < value2) {
                return 2;
            }
        }
        return count;
    }
 
}