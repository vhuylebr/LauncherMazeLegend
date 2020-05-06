package sample;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import sample.Services.Api;

import java.io.IOException;
import java.net.URISyntaxException;
;

public class Controller {

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML public void initialize() {

    }

    @FXML void changeStageToSignup() throws Exception {
        Stage currentStage = (Stage) usernameTextField.getScene().getWindow();
        currentStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("signupstage.fxml")), 900, 500));
    }

    @FXML void exit() {
        System.exit(0);
    }
    @FXML
    void onClickConnexion() throws JSONException {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        Api.getInstance().login(username, password, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                errorLabel.setText("Connexion error");
                errorLabel.setVisible(true);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Stage currentStage = (Stage) usernameTextField.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardScene.fxml"));
                    Parent root = loader.load();
                    DashboardScene controller = loader.getController();
                    Scene dashboardScene = new Scene(root, 900, 500);
                    try {
                        JSONObject bodyResponseJson = new JSONObject(response.body().string());
                        Socket socket = IO.socket("http://mazelegends-api.herokuapp.com");
                        socket.on(Socket.EVENT_CONNECT, args -> {
                            try {
                                JSONObject authenticated = new JSONObject();
                                authenticated.put("token", bodyResponseJson.getString("token"));
                                socket.emit("authenticate",  authenticated);
                                Platform.runLater(() -> {
                                    currentStage.setScene(dashboardScene);
                                    controller.setSocket(socket);
                                });
                                socket.disconnect();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }).on("unauthorized", args -> {System.out.println(args[0]);}).on(Socket.EVENT_DISCONNECT, args -> {System.out.println("disconnect");});
                        socket.connect();
                    } catch (URISyntaxException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    errorLabel.setVisible(true);
                }
            }
        });
    }
}
