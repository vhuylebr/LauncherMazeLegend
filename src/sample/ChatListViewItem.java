package sample;

import javafx.scene.control.ListCell;

public class ChatListViewItem extends ListCell<String> {
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(item != null)
        {
            ChatListViewItemData data = new ChatListViewItemData();
            data.setInfo(item);
            setGraphic(data.getBox());
        }
    }
}
