package graphics.views;
import BusinessLogic.DataBaseConnector;
import BusinessLogic.UserRepository;
import BusinessLogic.UserService;
import DataModel.User;
import graphics.controllers.UserCellController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
public class ListUserView {

    @FXML
    private TextField name;

    @FXML
    private Button backButton;

    @FXML
    private ListView<User> userList;

    public void initialize() {

        DataBaseConnector connector = new DataBaseConnector();
        UserRepository userRepository = new UserRepository(connector);
        UserService userService = new UserService(userRepository);

        ArrayList<User> allEvents = userService.getAll();
        ObservableList<User> observableEvents = FXCollections.observableArrayList(allEvents);

        userList.setCellFactory(eventListView -> new UserCellController());
        userList.setItems(observableEvents);

    }

    @FXML
    protected void onBackClick() {
        Stage stage = null;
        Parent myNewScene = null;
        try {
            stage = (Stage) backButton.getScene().getWindow();
            myNewScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start-screen.fxml")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (myNewScene != null) {
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setTitle("My New Scene");
            stage.show();
        }


    }
}
