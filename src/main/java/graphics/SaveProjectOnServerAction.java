package graphics;

import BusinessLogic.DataBaseConnector;
import BusinessLogic.UserRepository;
import BusinessLogic.UserService;
import DataModel.User;
import com.google.gson.Gson;
import document.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveProjectOnServerAction extends AbstractAction implements  Action {
    private MainFrame mainFrame;
    private User user;

    public SaveProjectOnServerAction(MainFrame mainFrame, User user) {
        super("Сохранить проект на сервер");
        this.mainFrame = mainFrame;
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Document document = mainFrame.getDocument();
        DataBaseConnector connector = new DataBaseConnector();
        UserRepository userRepository = new UserRepository(connector);
        UserService userService = new UserService(userRepository);

        Gson serializer = new Gson();
        User currentUser = userService.getById(user.getName(), user.getPassword());
        User newUser = new User(currentUser.getName(), currentUser.getId(), currentUser.getPassword(), serializer.toJson(document), false);
        userService.update(newUser);
    }
}

