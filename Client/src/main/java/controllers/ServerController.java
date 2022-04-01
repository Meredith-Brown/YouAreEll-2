package controllers;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
// import spiffyUrlManipulator; // TODO - import something here

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerController<JsonString> {
    private String rootURL = "http://zipcode.rocks:8085";
    HttpURLConnection connection;

    private static ServerController svr = new ServerController();

    private ServerController() {}

    public static ServerController shared() { // Singleton --- getInstance
        return svr;
    }

    public JsonString idGet() {
        BufferedReader reader;
        String line;
        StringBuffer response = new StringBuffer();
        JSONParser jsonParser = new JSONParser();
        try {
            URL url = new URL("http://zipcode.rocks:8085/ids");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            int status = connection.getResponseCode();
            System.out.println(status); // TODO - remove
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                Object obj = jsonParser.parse(reader);
                JSONArray ids = (JSONArray) obj;
                System.out.println(ids);
//                line = reader.readLine();
//                while(line != null) {
//                    response.append(line);
//                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                // loop // TODO ??? (and above)
                Object obj = jsonParser.parse(reader);
                JSONArray ids = (JSONArray) obj;
                System.out.println(ids);
                // line = reader.readLine();
//                while (line != null) {
//                    System.out.println(line); // TODO - remove
//                    System.out.println("---------linebreak--------"); // TODO - remove
//                    response.append(line);
//            }
            reader.close();
            }
            System.out.println(response.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return (JsonString) response;
        // url -> /ids/
        // send the server a get with url
        // return json from server
    }

//    public JsonString idPost(JsonTypeInfo.Id) { // TODO - these 2 methods
//        // url -> /ids/
//        // create json from Id
//        // request
//        // reply
//        // return json
//    }
//    public JsonString idPut(Id) {
//        // url -> /ids/
//    }


}

// ServerController.shared.doGet()