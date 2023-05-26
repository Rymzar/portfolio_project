package graphics.components;

import document.Entry;

import javax.swing.*;
import java.awt.*;

public class EntryListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        String title = "[Добавить период]";
        if (value != null) {
            Entry item = (Entry) value;
            title = item.getTitle();
            if(title.isEmpty()) {
                title = "[Новое достижение]";
            }
        }

        listCellRendererComponent.setText(title);
        return listCellRendererComponent;

    }
}
