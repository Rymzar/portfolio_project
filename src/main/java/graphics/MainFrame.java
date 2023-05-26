package graphics;

import BusinessLogic.DataBaseConnector;
import BusinessLogic.UserRepository;
import BusinessLogic.UserService;
import DataModel.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import document.Document;
import graphics.components.AboutAction;
import graphics.models.ContactDetailsModel;
import graphics.models.DocumentModel;
import graphics.models.SubsectionListModel;
import graphics.views.ContactDetailsView;
import graphics.views.DocumentView;
import graphics.views.ModifySubsectionListView;
import graphics.views.SubsectionListView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private Document document;
    private AboutAction aboutAction = new AboutAction();
    private JSplitPane pane;
    private User user;

    public MainFrame(Document document, User user) {
        this.user = user;
        this.document = document;
        setupFrame();
        setupComponents();
        setupMenu();
    }

    private void setupFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 720);
    }


    private void setupLayout() {
    }

    public void reset(Document document) {
        this.document = document;
        remove(pane);
        setupComponents();
        SwingUtilities.updateComponentTreeUI(this);
    }

    public Document getDocument(){
        return this.document;
    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem newProject = new JMenuItem(new NewProjectAction(this));
        JMenuItem loadProject = new JMenuItem(new LoadProjectAction(this));
        JMenuItem saveProject = new JMenuItem(new SaveProjectAction(this));
        JMenuItem exportProject = new JMenuItem(new ExportProjectAction(this));
        JMenuItem saveProjectOnServer = new JMenuItem(new SaveProjectOnServerAction(this, user));
        JMenuItem saveProjectInPdfAction = new JMenuItem(new SaveProjectInPdfAction(this, user));

        fileMenu.add(newProject);
        fileMenu.add(saveProject);
        fileMenu.add(loadProject);
        fileMenu.add(exportProject);
        fileMenu.add(saveProjectOnServer);
        fileMenu.add(saveProjectInPdfAction);

        menuBar.add(fileMenu);

        JMenu aboutMenu = new JMenu("Информация");
        JMenuItem about = new JMenuItem(aboutAction);

        aboutMenu.add(about);
        menuBar.add(aboutMenu);


        setJMenuBar(menuBar);
    }

    private void setupComponents() {
        pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new DocumentView(new DocumentModel(document)));
        panel.add(new ContactDetailsView(new ContactDetailsModel(document.getDetails())));

        pane.add(panel);

        DataBaseConnector connector = new DataBaseConnector();
        UserRepository userRepository = new UserRepository(connector);
        UserService userService = new UserService(userRepository);
        ArrayList<User> users = userService.getAll();


        DefaultListModel<User> userListModel = new DefaultListModel<>();
        JList<User> userList = new JList<>(userListModel);

        for (User user : users) {
            userListModel.addElement(user);
        }

        JPanel usersPan = new JPanel();

        JLabel nameLabel = new JLabel("Имя");
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JTextField name = new JTextField(10);

        JScrollPane scrollPane = new JScrollPane(userList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(300, scrollPane.getPreferredSize().height));

        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    User user = userList.getSelectedValue();
                    User currentUser = userService.getById(user.getName(), user.getPassword());
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        Document document = mapper.readValue(currentUser.getDocument(), Document.class);
                        reset(document);
                        setTitle("Электронное портфолио - " + document.getTitle());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };

        userList.addMouseListener(mouseListener);

        name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                userListModel.clear();
                for (User user : users) {
                    if (user.getName().toLowerCase().contains(name.getText())) {
                        userListModel.addElement(user);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                userListModel.clear();
                for (User user : users) {
                    if (user.getName().toLowerCase().contains(name.getText())) {
                        userListModel.addElement(user);
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                userListModel.clear();
                for (User user : users) {
                    if (user.getName().toLowerCase().contains(name.getText())) {
                        userListModel.addElement(user);
                    }
                }
            }
        });

        usersPan.add(name);
        usersPan.add(scrollPane);


        JTabbedPane tabbedPane = new JTabbedPane();
        SubsectionListModel model = new SubsectionListModel(document.getContent());
        tabbedPane.add("Все достижения", pane.add(new SubsectionListView(model)));
        tabbedPane.add("Редактировать достижение",new ModifySubsectionListView(model));
        if(user.isAdmin()){
            tabbedPane.add("Пользователи", usersPan);
        }
        pane.add(tabbedPane);

        add(pane, BorderLayout.CENTER);
    }


}

