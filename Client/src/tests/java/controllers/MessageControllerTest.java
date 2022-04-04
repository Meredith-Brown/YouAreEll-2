package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import junit.framework.TestCase;
import models.Id;
import models.Message;
import org.junit.Assert;

import java.util.ArrayList;

public class MessageControllerTest extends TestCase { // TODO - redo when you van post
    ServerController serverController = ServerController.shared();

    public void testGetMessages() { // TODO - see how everyone else tested this
    // messages should return the last 20 messages, nicely formatted
        // TODO - test once we can post messages - post message, then confirm message exists in 20 most recent
        // given
        // when
        // ArrayList<Message> messageArrayList = messageController.getMessages();
        // then
        // Assert.assertTrue(messageArrayList.size() <= 20);
        // assert in chronological order???
    }

    public void testGetMessagesForId() throws JsonProcessingException {
        // given
        MessageController messageController = new MessageController();
        IdController idController = new IdController();
        int expectedCount = 4;
        ArrayList<Id> listOfIds = idController.getIds();
        Id toId1 = null;
        for (int i = 0; i < listOfIds.size(); i++) {
            if (listOfIds.get(i).getGithub().equals("keerthana-java")) {
                toId1 = listOfIds.get(i);
            }
        }
        // when
        int actual = messageController.getMessagesForId(toId1).size();
        // then
        Assert.assertEquals(expectedCount, actual);
    }

    public void testGetMessageForSequence() throws JsonProcessingException {
        // given
        MessageController messageController = new MessageController();
        String expectedMessage = "'iwannasleep'";
        String expectedFromId = "AmandaJ-Huang";
        String expectedToId = "AmandaJ-Huang";
        // when
        Message message = messageController.getMessageForSequence("2609eb7971584d550b688d87868f13ba91ad3628");
        // then
        String actualMessage = message.getMessage();
        String actualFromId = message.getFromId();
        String actualToId = message.getToId();
        Assert.assertEquals(expectedMessage, actualMessage);
        Assert.assertEquals(expectedFromId, actualFromId);
        Assert.assertEquals(expectedToId, actualToId);
    }

    public void testGetMessagesFromFriend() throws JsonProcessingException {
        // given
        MessageController messageController = new MessageController();
        IdController idController = new IdController();
        int expectedCount = 5; // Amanda H -> Amanda H
        ArrayList<Id> listOfIds = idController.getIds();
        Id myId1 = null;
        Id toId1 = null;
        for (int i = 0; i < listOfIds.size(); i++) {
            if (listOfIds.get(i).getGithub().equals("AmandaJ-Huang")) {
                myId1 = listOfIds.get(i);
                toId1 = listOfIds.get(i);
            }
        }
        // when
        int actual = messageController.getMessagesFromFriend(myId1, toId1).size();
        // then
        Assert.assertEquals(expectedCount, actual);
    }

    public void testPostMessage() {
    }
}