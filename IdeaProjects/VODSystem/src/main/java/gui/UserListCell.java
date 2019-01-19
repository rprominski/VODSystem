package gui;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import user.User;

/**
 * Describe how store user in cell of listview.
 */
public class UserListCell extends ListCell<User> {
    private HBox content;
    private Text name;
    public UserListCell() {
        super();
        name = new Text();
        content = new HBox(name);
    }

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user,empty);
        if(user != null && !empty) {
            name.setText(user.getName());
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}
