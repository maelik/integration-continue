package fr.epsi.utils;

import fr.epsi.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {

    public static void write(JSONArray users) throws IOException {
        FileWriter file = new FileWriter("users.json");

        file.write(users.toJSONString());
        file.flush();
    }

    public static void writeUsers(List<User> users) throws IOException {
        FileWriter file = new FileWriter("users.json");

        JSONArray jsonArray = new JSONArray();

        users.forEach(user -> {
            JSONObject userJson = new JSONObject();

            userJson.put("id", user.getId());
            userJson.put("firstname", user.getFirstname());
            userJson.put("lastname", user.getLastname());
            userJson.put("email", user.getEmail());

            jsonArray.add(userJson);
        });

        file.write(jsonArray.toJSONString());
        file.flush();
    }

}
