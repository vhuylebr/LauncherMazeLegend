package sample;

import javafx.scene.control.ListCell;

public class FriendListViewItem extends ListCell<Friend> {
    @Override
    protected void updateItem(Friend item, boolean empty) {
        super.updateItem(item, empty);
        if(item != null)
        {
            FriendListViewItemData data = new FriendListViewItemData();
            data.setInfo(item);
            setGraphic(data.getBox());
        }
    }

}
