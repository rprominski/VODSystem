package gui;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import product.Product;

import java.awt.image.BufferedImage;

public class ProductListCell extends ListCell<Product>{
    private HBox content;
    private Text name;
    private ImageView image;
    public ProductListCell() {
        super();
        name = new Text();
        image = new ImageView();
        content = new HBox(image,name);
    }

    @Override
    protected void updateItem(Product product, boolean empty) {
        super.updateItem(product,empty);
        if(product != null && !empty) {
            name.setText(product.getName());
            Image photo = SwingFXUtils.toFXImage(product.getPhoto(), null);
            image.setImage(photo);
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}
