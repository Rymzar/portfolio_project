package graphics.veiw;

import BusinessLogic.DataBaseConnector;
import BusinessLogic.UserRepository;
import BusinessLogic.UserService;
import DataModel.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import document.Document;
import graphics.AdminFrame;

import javax.swing.*;
import java.awt.*;

public class UserCreateFormView extends JPanel {
    public UserCreateFormView() {
        setLayout(new GridBagLayout());

        JTextField nameField = new JTextField(12);
        JPasswordField passwordField = new JPasswordField(12);

        JLabel nameLabel = new JLabel("Имя:");
        JLabel passwordLabel = new JLabel("Пароль:");
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

        constraints.gridy = 3;
        add(enterButton, constraints);

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
                    if (user.isAdmin() == true) {
                        document = new Document();
                        AdminFrame adminFrame = new AdminFrame(document, user);
                        adminFrame.setVisible(true);
                        adminFrame.setTitle("Панель администратора");
                    }
                    /*else if(user.getDocument() == null){
                        document = new Document();
                        MainFrame mainFrame = new MainFrame(document, user);
                        mainFrame.setVisible(true);
                        mainFrame.setTitle("Электронное портфолио - " + document.getTitle());
                    } else {
                        document = mapper.readValue(user.getDocument(), Document.class);
                        MainFrame mainFrame = new MainFrame(document, user);
                        mainFrame.setVisible(true);
                        mainFrame.setTitle("Электронное портфолио - " + document.getTitle());
                    }*/

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                JFrame f1 = (JFrame) SwingUtilities.windowForComponent(this);
                f1.dispose();
            } else {
                JFrame jFrame = new JFrame();
                JOptionPane.showConfirmDialog(jFrame, "Пользователя с таким логином не существует, обратитесь к администратору");
            }

        });
    }
}
