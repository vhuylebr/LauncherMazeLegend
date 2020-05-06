package sample.Services;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

public class Api extends OkHttpClient {
    static private Api instance = new Api();
    private String URL = "http://mazelegends-api.herokuapp.com";

    Api() { super(); }

    public static Api getInstance() {
        return instance;
    }

    public void login(String username, String password, Callback callback) throws JSONException {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("email", username);
        jsonBody.put("password", password);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),  jsonBody.toString());
        final Request request = new Request.Builder()
                .url(URL + "/users/login")
                .post(body)
                .build();

        newCall(request).enqueue(callback);
    }

    public void signup(JSONObject json, Callback callback) throws JSONException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json.toString());
        final Request request = new Request.Builder()
                .url(URL + "/users")
                .post(body)
                .build();
        newCall(request).enqueue(callback);
    }
}
