package controllers;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
// import spiffyUrlManipulator; // TODO - import something here

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ServerController<JsonString> {
    private String rootURL = "http://zipcode.rocks:8085";
    HttpURLConnection connection;

    private static ServerController svr = new ServerController();

    private ServerController() {}

    public static ServerController shared() { // Singleton --- getInstance
        return svr;
    }

    public JSONArray idGet() {
        BufferedReader reader;
        JSONParser jsonParser = new JSONParser();
        JSONArray ids = null;
        try {
            URL url = new URL("http://zipcode.rocks:8085/ids");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                Object obj = jsonParser.parse(reader);
                ids = (JSONArray) obj;
                // System.out.println(ids); // TODO - uncomment out?
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                Object obj = jsonParser.parse(reader);
                ids = (JSONArray) obj;
                // System.out.println(ids); // TODO - uncomment out?
                reader.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return ids;
        // url -> /ids/
        // send the server a get with url
        // return json from server
    }

    public JSONArray messageGet() {
        BufferedReader reader;
        JSONParser jsonParser = new JSONParser();
        JSONArray messages = null;
        try {
            URL url = new URL("http://zipcode.rocks:8085/messages");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                Object obj = jsonParser.parse(reader);
                messages = (JSONArray) obj;
                // System.out.println(messages); // TODO - uncomment out?
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                Object obj = jsonParser.parse(reader);
                messages = (JSONArray) obj;
                // System.out.println(messages); // TODO - uncomment out?
                reader.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return messages;
        // url -> /messages/
        // send the server a get with url
        // return json from server
    }

    public JsonString idPost(Id id) {
        StringBuilder response = null;
        try {
            URL url = new URL("http://zipcode.rocks:8085/ids");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            connection.setRequestProperty("Id", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            ObjectMapper objectMapper = new ObjectMapper();
            String out = objectMapper.writeValueAsString(id);
            OutputStream os = connection.getOutputStream();
            byte[] input = out.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            int code = connection.getResponseCode();
            System.out.println(code);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.
                    getInputStream(), StandardCharsets.UTF_8))) {
                response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response);
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (JsonString) response;
    }

//        public JsonString idPut(Id) { // TODO
//        // url -> /ids/
//    }
}

// ServerController.shared.doGet()