package graphics;

import com.fasterxml.jackson.databind.ObjectMapper;
import document.Document;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;

public class LoadProjectAction extends AbstractAction implements Action {
    private MainFrame mainFrame;

    public LoadProjectAction(MainFrame mainFrame) {
        super("Загрузить проект");
        this.mainFrame = mainFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Открыть проект из файла");
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON (.json)", "json"));
        int result = fileChooser.showOpenDialog(this.mainFrame);
        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (FileReader reader = new FileReader(selectedFile)){
//                Gson serializer = new Gson();
                ObjectMapper mapper = new ObjectMapper();
                Document document = mapper.readValue(reader, Document.class);
                mainFrame.reset(document);
                mainFrame.setTitle("Электронное портфолио - " + document.getTitle());
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(mainFrame, "При загрузке документа произошла следующая ошибка: " + e1, "Ошибка при загрузке", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}