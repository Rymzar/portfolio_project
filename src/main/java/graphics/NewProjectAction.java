package graphics;

import document.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class NewProjectAction extends AbstractAction implements Action {
    private MainFrame mainFrame;

    public NewProjectAction(MainFrame mainFrame) {
        super("Новый проект");
        this.mainFrame = mainFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int i = JOptionPane.showConfirmDialog(mainFrame, "Вы уверены что хотите начать новый проект?", "Подтвердить", JOptionPane.YES_NO_OPTION);
        if(i == JOptionPane.YES_OPTION) {
            mainFrame.reset(new Document());
            mainFrame.setTitle("Электронное портфолио - Новое портфолио");
        }
    }
}
