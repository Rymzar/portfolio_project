package graphics;

import document.Document;
import latex.LatexSerializer;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ExportProjectAction extends AbstractAction implements Action {
    private MainFrame mainFrame;

    public ExportProjectAction(MainFrame mainFrame) {
        super("Экспортировать портфолио (LaTeX)");
        this.mainFrame = mainFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Document document = mainFrame.getDocument();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Экспортировать проект в формате Latex");

        fileChooser.setFileFilter(new FileNameExtensionFilter("Latex File (.tex)", "tex"));
        int result = fileChooser.showSaveDialog(this.mainFrame);
        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(selectedFile)){
                LatexSerializer serializer = new LatexSerializer(writer);
                serializer.serialize(document);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(mainFrame, "При сохранении документа произошла следующая ошибка: " + e1, "Ошибка экспорта", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
