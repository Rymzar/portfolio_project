package graphics;

import BusinessLogic.DataBaseConnector;
import BusinessLogic.UserRepository;
import BusinessLogic.UserService;
import DataModel.User;
import document.Document;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminFrame extends JFrame {

    private Document document;
    private JSplitPane pane;
    private User user;

    public AdminFrame(Document document, User user) {
        this.document = document;
        this.user = user;
        setupFrame();
        creatUser();
        setupAdminPanel();
    }

    private void setupFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 720);
    }

    private void setupAdminPanel() {
        DataBaseConnector connector = new DataBaseConnector();
        UserRepository userRepository = new UserRepository(connector);
        UserService userService = new UserService(userRepository);
        ArrayList<User> users = userService.getAll();

    }

    public void creatUser() {
        setLayout(new GridBagLayout());

        JTextField nameField = new JTextField(12);
        JPasswordField passwordField = new JPasswordField(12);

        JLabel nameLabel = new JLabel("Имя:");
        JLabel passwordLabel = new JLabel("Пароль:");

        JButton addButton = new JButton("Зарегистрировать нового пользователя");

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(nameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(nameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(passwordField, constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(addButton, constraints);

        addButton.addActionListener((actionEvent) -> {
            String userName = nameField.getText();
            String userPassword = passwordField.getText();
            DataBaseConnector connector = new DataBaseConnector();
            UserRepository userRepository = new UserRepository(connector);
            UserService userService = new UserService(userRepository);

            User event = new User(userName, 1L, userPassword, "", false);

            User user = userService.getByName(userName);
            if(user == null) {
                userService.add(event);
                JFrame f1 = (JFrame) SwingUtilities.windowForComponent(this);
                f1.dispose();
            } else {
                JFrame jFrame = new JFrame();
                JOptionPane.showConfirmDialog(jFrame, "Пользователь с таким логином или паролем существует");
            }
        });
    }


}
