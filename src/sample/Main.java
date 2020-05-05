package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene signin = new Scene(FXMLLoader.load(getClass().getResource("loginStage.fxml")), 900, 500);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(signin);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
