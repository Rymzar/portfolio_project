package graphics.controllers;

import DataModel.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import java.io.IOException;


public class UserCellController extends ListCell<User> {

    @FXML
    private VBox gridPane;
    @FXML
    private Label name;

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);

        if (empty || user == null) {

            setText(null);
            setGraphic(null);

        } else {
            FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("event-cell.fxml"));
            mLLoader.setController(this);


            try {
                mLLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            name.setText("Название - " + user.getName());

            setGraphic(gridPane);
        }
    }
}

