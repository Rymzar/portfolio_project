package graphics;

import DataModel.User;
import document.Document;

import javax.swing.*;

public class AdminFrame extends JFrame {

    private Document document;
    private JSplitPane pane;
    private User user;

    public AdminFrame(Document document, User user) {
        this.document = document;
        this.user = user;
        setupFrame();
    }

    private void setupFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 720);
    }

    private void setupAdminPanel() {

    }


}
