package controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MessageController {

    private HashSet<Message> messagesSeen = new HashSet<>();
    private final ServerController serverController = ServerController.shared();
    // why a HashSet??

    public MessageController() throws JsonProcessingException {
        JSONArray messagesJSON = serverController.messageGet();
        String messageString = messagesJSON.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        messagesSeen = objectMapper.readValue(messageString, new TypeReference<>() {
        });
//        ServerController serverController = ServerController.shared();
//        JSONArray messagesJSON = serverController.messageGet();
//        for (int i = 0; i < messagesJSON.size(); i++) {
//            Object object = messagesJSON.get(i);
//            String string = object.toString();
//            String[] messageList = string.split(",");
//            String toID = messageList[0].substring(8);
//            String sequence = messageList[1].substring(11);
//            String message = messageList[2].substring(10);
//            String fromID = messageList[3].substring(9);
//            String timestamp = messageList[4].substring(12);
//            Message messageObject = new Message(message, fromID, toID, timestamp, sequence);
//            messagesSeen.add(messageObject);
//        }
    }

    public ArrayList<Message> getMessages() {
    // messages should return the last 20 messages, nicely formatted
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
    // messages your_github_id should return the last 20 messages sent to you
        Message[] arrayZero = messagesSeen.toArray(new Message[messagesSeen.size()]); // remove nulls
        ArrayList<Message> array = new ArrayList<>();
        int count = 0;
        for (Message m : arrayZero) {
            if (m.getToId().equals(null)) { // TODO - do I still need this?

            } else {
                array.add(m);
                count++;
            }
        }
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            String stringId = Id.getGithub();
            if (array.get(i).getToId().equals(stringId)) {
                messages.add(array.get(i));
            }
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

    public Message getMessageForSequence(String seq) {
    // GET : Get msg with a sequence - returns a JSON message object for a sequence number
        for (Message m : messagesSeen) {
            if (m.getSeqId().equals(seq)) {
                return m;
            }
        }
        return null;
    }

    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) { // TODO - does this need to be most recent 20?
        ArrayList<Message> messagesFromFriend = new ArrayList<>();
        for (Message m : messagesSeen) {
            if (m.getFromId().equals(friendId.getGithub()) && m.getToId().equals(myId.getGithub())) {
                messagesFromFriend.add(m);
            }
        }
        return messagesFromFriend;
    }

    public Message postMessage(Id myId, Id toId, Message msg) throws IOException, InterruptedException { // TODO - post vs pull
    // send your_github_id 'Hello World' should post a new message in the timeline
    // send your_github_id 'my string message' to some_friend_githubid should post a message to your
    // friend from you on the timeline.
        // https://zetcode.com/java/getpostrequest/
        var messageAsMap = new HashMap<String, String>();
        messageAsMap.put("message", msg.getMessage());
        messageAsMap.put("toId", toId.getGithub());
        messageAsMap.put("fromId", myId.getGithub());
        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(messageAsMap); // request body is populated
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create
                ("http://zipcode.rocks:8085/messages")).header // TODO - confirm URL, this one def connects
                ("Message", "application/json; utf-8").POST
                (HttpRequest.BodyPublishers.ofString(requestBody)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body()); // TODO - delete?
        return msg; // TODO - is this the correct return?
    }
}