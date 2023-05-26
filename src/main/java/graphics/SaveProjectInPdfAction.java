package graphics;

import BusinessLogic.DataBaseConnector;
import BusinessLogic.UserRepository;
import BusinessLogic.UserService;
import DataModel.User;
import com.google.gson.Gson;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import document.Document;
import document.Entry;
import document.Subsection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;

public class SaveProjectInPdfAction extends AbstractAction implements  Action {
    private final MainFrame mainFrame;
    private final User user;

    public SaveProjectInPdfAction(MainFrame mainFrame, User user) {
        super("Сохранить проект в pdf");
        this.mainFrame = mainFrame;
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            document.Document userDocument = mainFrame.getDocument();
            DataBaseConnector connector = new DataBaseConnector();
            UserRepository userRepository = new UserRepository(connector);
            UserService userService = new UserService(userRepository);

            Gson serializer = new Gson();
            User currentUser = userService.getById(user.getName(), user.getPassword());
            User newUser = new User(currentUser.getName(), currentUser.getId(), currentUser.getPassword(), serializer.toJson(userDocument), false);
            userService.update(newUser);
            System.out.println(mainFrame.getDocument());
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Электронное портфолио " + mainFrame.getDocument().getTitle() + ".pdf"));
            document.open();

            document.Document doc = mainFrame.getDocument();
            // Заголовок (ФИО)
            String title = "Электронное портфолио " + doc.getTitle();

            // Текст о себе
            String tasterHeader = "О себе:\n";
            String tasterText = doc.getTaster();

            // Перечисление достижений
            String contentHeader = "Достижения:\n";
            StringBuilder contentText = new StringBuilder();
            ArrayList<Subsection> content = doc.getContent();
            int counter = 1;
            for (Subsection subsection : content) {
                contentText.append(counter).append(". ").append(subsection.getTitle()).append("\n").append("    Записи: \n");
                int innerCounter = 1;
                for (Entry contentEntry : subsection.getEntries()) {
                    contentText.append("          ").append(innerCounter).append(". ").append("Название: ").append(contentEntry.getTitle()).append("\n").append("              Дата: ").append(contentEntry.getDate()).append("\n").append("              Место: ").append(contentEntry.getLocation()).append("\n");
                    contentText.append("              Описание: \n");
                    for (String text : contentEntry.getDetails()) {
                        contentText.append("                    ").append(text).append("\n");
                    }
                    innerCounter++;
                }
                counter++;

            }

            // Контактные данные
            String detailsHeader = "Контакты: \n";
            StringBuilder detailsText = new StringBuilder();
            counter = 1;
            for(Map.Entry<String, String> entry : doc.getDetails().getContact_details()) {
                detailsText.append(counter).append(". ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                counter++;
            }

            String FONT = "./com/gopiandcode/graphics/ARIAL.TTF";
            BaseFont bf= BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            com.itextpdf.text.Font font=new   com.itextpdf.text.Font(bf,10,  com.itextpdf.text.Font.NORMAL);
            com.itextpdf.text.Font bigFont=new   com.itextpdf.text.Font(bf,16,  Font.BOLD);
            Paragraph titlePar = new Paragraph(title, bigFont);
            titlePar.setAlignment(Element.ALIGN_CENTER);
            document.add(titlePar);
            document.add(new Paragraph(tasterHeader, bigFont));
            document.add(new Paragraph(tasterText, font));
            document.add(new Paragraph(detailsHeader, bigFont));
            document.add(new Paragraph(detailsText.toString(), font));
            document.add(new Paragraph(contentHeader, bigFont));
            document.add(new Paragraph(contentText.toString(), font));
            document.close();
        }
        catch(Exception err) {
            err.printStackTrace();
        }

    }
}
