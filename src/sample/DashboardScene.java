package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

public class DashboardScene {
    @FXML
    private ListView friendListView;

    ObservableList observableList = FXCollections.observableArrayList();

    @FXML public void initialize() {
        friendListView.setCellFactory((Callback<ListView, ListCell<Friend>>) param -> new FriendListViewItem());
        friendListView.setItems(observableList);
    }

    @FXML void addItem() throws JSONException {
            observableList.add(new Friend(new JSONObject("{\"username\":\"toto\"}")));
    }
}
