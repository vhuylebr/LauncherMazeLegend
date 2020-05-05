package sample;

import org.json.JSONException;
import org.json.JSONObject;

public class Friend {
    private String username;

    public Friend(JSONObject friend) throws JSONException {
        this.username = friend.getString("username");
    }

    public String getUsername() {
        return username;
    }
}
