package gui;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import product.Product;
import user.User;

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
