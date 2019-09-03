package fr.epsi;

import fr.epsi.utils.JsonReader;
import fr.epsi.utils.JsonWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, ParseException {
        User user = new User();
        user.setId(2);
        user.setFirstname("Ben");
        user.setLastname("Ben");
        user.setEmail("Ben");

        try {
            add(user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(select(2).getEmail());

        delete(2);
    }

    public static User add(User user) throws IOException, ParseException {
        JSONObject userJson = new JSONObject();

        userJson.put("id", user.getId());
        userJson.put("firstname", user.getFirstname());
        userJson.put("lastname", user.getLastname());
        userJson.put("email", user.getEmail());

        JSONArray users = JsonReader.read();
        users.add(userJson);

        JsonWriter.write(users);

        return user;
    }

    public static void delete(long userId) throws IOException, ParseException {
        List<User> users = JsonReader.readUsers();

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == userId) {
                iterator.remove();
            }
        }

        JsonWriter.writeUsers(users);
    }

    public static User select(long userId) throws IOException, ParseException {
        List<User> users = JsonReader.readUsers();

        User findUser = null;

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == userId) {
                findUser = user;
                break;
            }
        }

        return findUser;
    }
}
