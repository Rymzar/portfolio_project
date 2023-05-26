package graphics.components;

import java.awt.*;

public interface ComponentGenerator<T> {
    Component renderModel(T model);
}
