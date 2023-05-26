package graphics.components;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JTabbedPaneList<T> extends JTabbedPane {
    private ArrayList cache = new ArrayList();
    private ListModel<T> model;
    private ComponentGenerator<T> generator;
    private final PropertyChangeListenerGenerator<T> listenerGenerator;
    private final TabNameGenerator titleGenerator;

    public JTabbedPaneList(ListModel<T> model, ComponentGenerator<T> generator, PropertyChangeListenerGenerator<T> listenerGenerator, TabNameGenerator<T> titleGenerator) {
        this.model = model;
        this.generator = generator;
        this.listenerGenerator = listenerGenerator;
        this.titleGenerator = titleGenerator;

        TabbedPaneListDataListener<T> listener = new TabbedPaneListDataListener<>(generator);
        model.addListDataListener(listener);
        listener.initialSetup();

    }


    private class TabbedPaneListDataListener<T> implements ListDataListener {
        private ComponentGenerator<T> generator;
        private Map<T, Component> elements = new HashMap<>();

        public TabbedPaneListDataListener(ComponentGenerator<T> generator) {
            this.generator = generator;
        }

        private void initialSetup() {
            for (int i = 0; i < model.getSize(); i++){
                T item = (T) model.getElementAt(i);
                Component component = generator.renderModel(item);
                component.addPropertyChangeListener(listenerGenerator.generatePropertyChangeListener(JTabbedPaneList.this.model, i));
                cache.add(item);
                elements.put(item, component);
                String title = titleGenerator.generateName(item);
                if(title.isEmpty()){
                    title = "[Без названия]";
                }
                addTab(title, component);
            }

        }

        @Override
        public void intervalAdded(ListDataEvent e) {
            ListModel<T> list = (ListModel<T>) e.getSource();

            for (int i = e.getIndex1(); i >= e.getIndex0(); i--) {
                T item = list.getElementAt(i);
                Component component = generator.renderModel(item);
                component.addPropertyChangeListener(listenerGenerator.generatePropertyChangeListener(JTabbedPaneList.this.model, i));
                if(cache.size() > i)
                    cache.add(i, item);
                else
                    cache.add(item);
                elements.put(item, component);
                String title = titleGenerator.generateName(item);
                if(title.isEmpty()){
                    title = "[Без названия]";
                }
                addTab(title, component);
            }
        }

        @Override
        public void intervalRemoved(ListDataEvent e) {
            ListModel<T> list = (ListModel<T>) e.getSource();
            for (int i = e.getIndex1(); i >= e.getIndex0(); i--) {

                T elementAt = null;
                if (i < list.getSize()) {

                    elementAt = list.getElementAt(i);
                } else if (i < cache.size()) {
                    elementAt = (T) cache.get(i);
                    cache.remove(i);
                }
                if (elementAt != null) {
                    removeModelTab(elementAt);
                    elements.remove(elementAt);
                }
            }
        }

        private void removeModelTab(T elementAt) {
            Component c = elements.get(elementAt);
            for (int j = 0; j < getTabCount(); j++) {
                Component tabComponentAt = getComponentAt(j);
                if (tabComponentAt == c) {
                    removeTabAt(j);
                }
            }
        }

        @Override
        public void contentsChanged(ListDataEvent e) {
            ListModel<T> list = (ListModel<T>) e.getSource();
            for (int i = e.getIndex1(); i >= e.getIndex0(); i--) {
                // Remove existing tab

                T elementAt = null;
                if (i < list.getSize()) {
                    elementAt = list.getElementAt(i);
                } else if (i < cache.size()) {
                    elementAt = (T) cache.get(i);
                }
                if (elementAt != null) {
//                    removemodeltab(elementat);
//                    elements.remove(elementat);
//
//                    component container = generator.rendermodel(elementat);
//                    container.addpropertychangelistener(listenergenerator.generatepropertychangelistener(jtabbedpanelist.this.model, i));
//                    elements.put(elementat, container);
//                    addtab(titlegenerator.generatename(elementat), container);
                    setTitleAt(cache.indexOf(elementAt), titleGenerator.generateName(elementAt));
                }


            }
        }
    }
}
