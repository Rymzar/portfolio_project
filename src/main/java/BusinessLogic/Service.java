package BusinessLogic;

import java.util.ArrayList;

public interface Service<T> {

    ArrayList<T> getAll();

    void update(T target);

    void add(T target);
}
