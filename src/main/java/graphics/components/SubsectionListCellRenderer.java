package graphics.components;

import document.Subsection;

import javax.swing.*;
import java.awt.*;

public class SubsectionListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        String title = "[Без названия]";
        if (value != null) {
            Subsection item = (Subsection) value;
            title = item.getTitle();
            if(title.isEmpty()) {
                title = "[Новое достижение]";
            }
        }

        listCellRendererComponent.setText(title);
        return listCellRendererComponent;

    }
}
