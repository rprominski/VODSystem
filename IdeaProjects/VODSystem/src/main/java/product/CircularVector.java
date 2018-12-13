package product;

import java.util.Vector;

public class CircularVector<T> extends Vector<T> {
    private int maxSize;

    public CircularVector(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public synchronized boolean add(T object) {
        if(size() == maxSize) {
            remove(get(0));
        }
        return super.add(object);
    }
}