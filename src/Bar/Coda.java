package Bar;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Coda<T> {
    private List<T> list;

    public Coda() {
        list = new ArrayList<>();
    }
    public int lenght() {
        return list.size();
    }
    public synchronized T pop() {
        if (list.isEmpty()) {
            return null;
        }
        return list.remove(0);
    }

    public synchronized void push(T element) {
        list.add(element);
    }
}
