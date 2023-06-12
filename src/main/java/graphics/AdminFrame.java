package graphics;

import BusinessLogic.UserService;
import DataModel.User;
import document.Document;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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

    }

    public void creatUser() {
        //вернее меню
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("HELP");
        menuBar.add(menu);

        JMenuItem item = new JMenuItem("Руководство админа");
        menu.add(item);
        //регистрация пользователя
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Регистрация нового пользователя");

        JTextField nameField = new JTextField(10);
        JTextField passwordField = new JTextField(10);
        JButton addButton = new JButton("Зарегистрировать");

        panel.add(label);
        panel.add(nameField);
        panel.add(passwordField);
        panel.add(addButton);

        //вывод списка пользователей
        UserService userService = new UserService();
        List<User> users = userService.findAllUsers();

        JComponent one = new JLabel("Пользователи");
        one.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        JComponent two = new JLabel("Портфолио пользователей");
        two.setBorder(BorderFactory.createLineBorder(Color.ORANGE));


        //размещение элементов
        getContentPane().add(BorderLayout.SOUTH, panel);
        getContentPane().add(BorderLayout.NORTH, menuBar);
        JSplitPane demo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, one, two);
        getContentPane().add(BorderLayout.CENTER, demo);
        setVisible(true);

        addButton.addActionListener((actionEvent) -> {
            String userName = nameField.getText();
            String userPassword = passwordField.getText();
            User event = new User(1L, userName,  userPassword,  false);

            User user = userService.getByName(userName);
            if(user == null) {
                userService.saveUser(event);
                JFrame f1 = (JFrame) SwingUtilities.windowForComponent(this);
                f1.dispose();
            } else {
                JFrame jFrame = new JFrame();
                JOptionPane.showConfirmDialog(jFrame, "Пользователь с таким логином или паролем существует");
            }
        });
    }


}
