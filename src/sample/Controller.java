package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import sample.Services.Api;

import java.io.IOException;
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
                    Scene dashboardScene = new Scene(FXMLLoader.load(getClass().getResource("dashboardScene.fxml")), 900, 500);
                    Platform.runLater(() -> {
                        currentStage.setScene(dashboardScene);
                    });
                } else {
                    System.out.println(response);
                    errorLabel.setVisible(true);
                }
            }
        });
    }
}
