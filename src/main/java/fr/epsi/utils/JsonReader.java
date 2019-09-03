package fr.epsi.utils;

import fr.epsi.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    public static JSONArray read() throws IOException, ParseException {
        File userFile = new File("users.json");

        if (userFile.exists()) {
            FileReader reader = new FileReader(userFile);
            JSONParser jsonParser = new JSONParser();
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            return (JSONArray) obj;
        }

        return new JSONArray();
    }

    public static List<User> readUsers() throws IOException, ParseException {
        JSONArray usersJson = JsonReader.read();

        final List<User> users = new ArrayList<>();

        usersJson.forEach(userJson -> {
            JSONObject json = (JSONObject) userJson;

            User user = new User();
            user.setId((long) json.get("id"));
            user.setFirstname((String) json.get("firstName"));
            user.setLastname((String) json.get("lastName"));
            user.setEmail((String) json.get("email"));

            users.add(user);
        });

        return users;
    }
}
