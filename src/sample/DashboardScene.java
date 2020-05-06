package sample;

import io.socket.client.IO;
import io.socket.client.Socket;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class DashboardScene {
    @FXML
    private ListView friendListView;
    @FXML
    private ListView chatListView;
    private Socket socket;
    ObservableList friendObservableList = FXCollections.observableArrayList();
    ObservableList chatObservableList = FXCollections.observableArrayList();

    @FXML  public void start() {
        Platform.runLater(() -> {
            try {
                Process process = Runtime.getRuntime().exec(
                        "C:\\Users\\valer\\AppData\\Local\\Programs\\zaap\\Ankama Launcher.exe");
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML public void initialize() throws IOException, URISyntaxException {
        friendListView.setCellFactory((Callback<ListView, ListCell<Friend>>) param -> new FriendListViewItem());
        friendListView.setItems(friendObservableList);

        chatListView.setCellFactory((Callback<ListView, ListCell<String>>) param -> new ChatListViewItem());
        chatListView.setItems(chatObservableList);
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    @FXML void addItem() throws JSONException {
        //socket.on
    }
}
