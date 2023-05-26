package graphics.components;

import javax.swing.*;
import javax.swing.text.TextAction;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AboutAction extends TextAction {
    /**
     * Creates a new JTextAction object.
     */
    public AboutAction() {
        super("О приложении");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
                "Приложение для формирования электронного портфолио",
                "Электронное портфолио",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
