package graphics;

import graphics.veiw.UserCreateFormView;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JSplitPane pane;

    public LoginFrame() {
        setupLogin();
        setupFrame();
    }

    private void setupFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 720);
    }

    private void setupLogin() {
        pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new UserCreateFormView());
        pane.add(panel);

        add(pane, BorderLayout.CENTER);
    }

}
