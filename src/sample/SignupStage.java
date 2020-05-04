package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SignupStage extends Stage {
    private Stage prevStage;
    SignupStage(Stage prevStage) throws Exception {
        super();
        this.prevStage = prevStage;
        Parent root = FXMLLoader.load(getClass().getResource("signupstage.fxml"));
        this.initStyle(StageStyle.UNDECORATED);
        this.setScene(new Scene(root, 900, 500));
        this.show();
    }

    @Override
    public void close() {
        prevStage.show();
        super.close();
    }
}
