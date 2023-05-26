package graphics;

import com.fasterxml.jackson.databind.ObjectMapper;
import document.Document;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveProjectAction extends AbstractAction implements  Action {
    private MainFrame mainFrame;

    public SaveProjectAction(MainFrame mainFrame) {
        super("Сохранить проект");
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Document document = mainFrame.getDocument();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Сохранить проект как");
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON (.json)", "json"));
        int result = fileChooser.showSaveDialog(this.mainFrame);
        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(selectedFile)){
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(writer, document);
//                writer.write(serializer.toJson(document));
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(mainFrame, "При сохранении произошла следующая ошибка: " + e1, "Ошибка при сохранении", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}

