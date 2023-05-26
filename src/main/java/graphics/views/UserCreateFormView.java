package graphics.views;

import BusinessLogic.DataBaseConnector;
import BusinessLogic.UserRepository;
import BusinessLogic.UserService;
import DataModel.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import document.Document;
import graphics.MainFrame;

import javax.swing.*;
import java.awt.*;

public class UserCreateFormView extends JPanel {


    public UserCreateFormView() {
        setLayout(new GridBagLayout());

        JTextField nameField = new JTextField(12);
        JPasswordField passwordField = new JPasswordField(12);

        JLabel nameLabel = new JLabel("Имя:");
        JLabel passwordLabel = new JLabel("Пароль:");

        JButton addButton = new JButton("Зарегистрироваться");
        JButton enterButton = new JButton("Войти");

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

        constraints.gridy = 3;
        add(enterButton, constraints);

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
                MainFrame mainFrame = new MainFrame(new Document(), event);
                mainFrame.setVisible(true);

                JFrame f1 = (JFrame) SwingUtilities.windowForComponent(this);
                f1.dispose();
            }
        });

        enterButton.addActionListener((actionEvent) -> {
            String userName = nameField.getText();
            String userPassword = passwordField.getText();
            DataBaseConnector connector = new DataBaseConnector();
            UserRepository userRepository = new UserRepository(connector);
            UserService userService = new UserService(userRepository);
            User user = userService.getById(userName, userPassword);
            if(user != null) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    Document document;
                    if(user.getDocument() == null){
                        document = new Document();
                    } else {
                        document = mapper.readValue(user.getDocument(), Document.class);
                    }
                    MainFrame mainFrame = new MainFrame(document, user);
                    mainFrame.setVisible(true);
                    mainFrame.setTitle("Электронное портфолио - " + document.getTitle());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                JFrame f1 = (JFrame) SwingUtilities.windowForComponent(this);
                f1.dispose();
            }
        });





    }
}
