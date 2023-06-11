package graphics;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    // TODO отображение пользовательского интерфейса
    private JLabel firstName;
    private JLabel lastName;
    private JLabel surname;
    private JLabel phomeNumber;
    private JLabel faculty;
    private JLabel numberGroup;
    private JLabel trainingProfile;
    private JLabel direction;
    private JLabel aboutMe;

    public MainFrame() {

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridx = 0;
        constraints.gridy = 0;


    }

}
