package gui;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import product.Product;

public class ProductListCell extends ListCell<Product>{
    private HBox content;
    private Text name;

    public ProductListCell() {
        super();
        name = new Text();
        content = new HBox(new Label("[Tu bedzie grafika]"),name);
    }

    @Override
    protected void updateItem(Product product, boolean empty) {
        super.updateItem(product,empty);
        if(product != null && !empty) {
            name.setText(product.getName());
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}
