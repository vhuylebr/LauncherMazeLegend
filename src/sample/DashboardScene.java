package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.json.JSONException;
import org.json.JSONObject;

public class DashboardScene {
    @FXML
    private ListView friendListView;
    @FXML
    private ListView chatListView;

    ObservableList friendObservableList = FXCollections.observableArrayList();
    ObservableList chatObservableList = FXCollections.observableArrayList();

    @FXML public void initialize() {
        friendListView.setCellFactory((Callback<ListView, ListCell<Friend>>) param -> new FriendListViewItem());
        friendListView.setItems(friendObservableList);

        chatListView.setCellFactory((Callback<ListView, ListCell<String>>) param -> new ChatListViewItem());
        chatListView.setItems(chatObservableList);
    }

    @FXML void addItem() throws JSONException {
        friendObservableList.add(new Friend(new JSONObject("{\"username\":\"toto\"}")));
        chatObservableList.add("toto");
    }
}
