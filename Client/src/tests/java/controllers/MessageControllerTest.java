package controllers;

import junit.framework.TestCase;
import models.Message;
import org.junit.Assert;

import java.util.ArrayList;

public class MessageControllerTest extends TestCase {
    ServerController serverController = ServerController.shared();
    MessageController messageController = MessageController.shared();

    public void testGetMessages() { // TODO - see how everyone else tested this
    // messages should return the last 20 messages, nicely formatted
        // TODO - test once we can post messages - post message, then confirm message exists in 20 most recent
        // given
        // when
        ArrayList<Message> messageArrayList = messageController.getMessages();
        // then
        Assert.assertTrue(messageArrayList.size() <= 20);
        // assert in chronological order???
    }

    public void testGetMessagesForId() {
    }

    public void testGetMessageForSequence() {
    }

    public void testGetMessagesFromFriend() {
    }

    public void testPostMessage() {
    }
}