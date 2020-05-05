package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import sample.Services.Api;

import java.io.IOException;

public class SignupController {
    @FXML
    GridPane grid;
    @FXML private TextField usernameTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML void exit() {
        System.exit(0);
    }

    @FXML void back() throws IOException {
        Stage currentStage = (Stage) grid.getScene().getWindow();
        currentStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("loginStage.fxml")), 900, 500));
    }
    @FXML void createAccount() throws JSONException {
        JSONObject body = new JSONObject();
        body.put("username", usernameTextField.getText());
        body.put("email", emailTextField.getText());
        body.put("password", passwordField.getText());
        Api.getInstance().signup(body, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                errorLabel.setVisible(true);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                back();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    errorLabel.setVisible(true);
                }
            }
        });
    }
}
