package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FriendListViewItemData {
    @FXML
    private VBox vBox;
    @FXML
    private Label label1;
    @FXML
    private Label label2;

    public FriendListViewItemData() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FriendListViewItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void setInfo(Friend friend)
    {
        label1.setText(friend.getUsername());
        label2.setText(friend.getUsername());
    }

    public VBox getBox()
    {
        return vBox;
    }
}
