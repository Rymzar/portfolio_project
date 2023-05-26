package graphics.views;

import document.Document;
import document.Subsection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class DocumentComponentView extends JPanel {
    private Document document;

    private JLabel titleLabel;
    private JLabel tasterLabel;
    private JTextArea detailsLabel;
    private JTextArea contentTextArea;

    public DocumentComponentView(Document document) {
        this.document = document;

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridx = 0;
        constraints.gridy = 0;

        // Заголовок (ФИО)
        titleLabel = new JLabel("Заголовок: " + document.getTitle());
        add(titleLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;

        // Текст о себе
        String tasterText = "О себе:\n" + document.getTaster();
        tasterLabel = new JLabel(tasterText);
        add(tasterLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;

        // Перечисление достижений
        StringBuilder contentText = new StringBuilder("Достижения:\n");
        ArrayList<Subsection> content = document.getContent();
        for (Subsection subsection : content) {
            contentText.append(subsection.getTitle()).append("\n");
        }

        constraints.gridx = 0;
        constraints.gridy = 3;

        // Контактные данные
        StringBuilder detailsText = new StringBuilder("Контакты:");
        for(Map.Entry<String, String> entry : document.getDetails().getContact_details()) {
            detailsText.append("\nТип связи: ").append(entry.getKey()).append(", ").append("Значение: ").append(entry.getValue());
        }
        detailsLabel = new JTextArea(detailsText.toString());
        detailsLabel.setEditable(false);
        add(detailsLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;


        contentTextArea = new JTextArea(contentText.toString());
        contentTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(contentTextArea);
        add(scrollPane, constraints);
    }
}
