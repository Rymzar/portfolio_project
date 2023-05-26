package graphics.views;

import document.Entry;
import graphics.components.JTabbedPaneList;
import graphics.models.SubsectionModel;

import javax.swing.*;
import java.awt.*;

public class SubsectionView extends JPanel {

    private SubsectionModel model;

    public SubsectionView(SubsectionModel model) {
        this.model = model;
        JLabel titleLabel = new JLabel("Название:");
        JTextField titleField = new JTextField();
        titleField.setDocument(model.getTitleModel());
        titleLabel.setLabelFor(titleField);


        JTabbedPaneList<Entry> entryPane =
                new JTabbedPaneList<Entry>(model.getEntriesModel(), model.getEntryGenerator(), model.getEntryPropertyChangeListenerGenerator(),
                        Entry::getTitle);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0.2;
        constraints.weighty = 0.01;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(titleLabel, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 0.01;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.gridx = 2;
        constraints.gridy = 0;
        add(titleField, constraints);

        JPanel editPanel = new JPanel();
        editPanel.setBorder(BorderFactory.createTitledBorder("Записи"));
        editPanel.setLayout(new GridBagLayout());
        {
            GridBagConstraints bagConstraints = new GridBagConstraints();
            bagConstraints.fill = GridBagConstraints.BOTH;
            bagConstraints.weightx = 1.0;
            bagConstraints.weighty = 0.8;
            bagConstraints.gridwidth = 3;
            bagConstraints.gridheight = 5;
            bagConstraints.gridx = 0;
            bagConstraints.gridy = 0;

            JList<Entry> entryJList = new JList<>(model.getEntriesModel());
            entryJList.setCellRenderer(model.getListCellRenderer());
            editPanel.add(new JScrollPane(entryJList), bagConstraints);
            JButton addEntryButton = new JButton("Добавить запись");
            addEntryButton.addActionListener(model.getAddEntryActionListener(entryJList, entryPane));
            JPanel editButtons = new JPanel();
            editButtons.setLayout(new GridLayout(1, 2));
            editButtons.add(addEntryButton);
            JButton removeEntryButton = new JButton("Удалить запись");
            removeEntryButton.addActionListener(model.getRemoveEntryActionListener(entryJList, entryPane));
            editButtons.add(removeEntryButton);

            bagConstraints.fill = GridBagConstraints.BOTH;
            bagConstraints.weightx = 1.0;
            bagConstraints.weighty = 0.02;
            bagConstraints.gridwidth = 3;
            bagConstraints.gridheight = 1;
            bagConstraints.gridx = 0;
            bagConstraints.gridy = 6;


            editPanel.add(editButtons, bagConstraints);
        }

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.2;
        constraints.weighty = 0.5;
        constraints.gridwidth = 2;
        constraints.gridheight = 5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(editPanel, constraints);


        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 0.5;
        constraints.gridwidth = 3;
        constraints.gridheight = 5;
        constraints.gridx = 2;
        constraints.gridy = 1;
        add(entryPane, constraints);


    }


}

