package product;

import java.util.Vector;

/**
 * Structure used to store some number of last added values.
 * @param <T>
 */
public class CircularVector<T> extends Vector<T> {
    private int maxSize;

    /**
     *
     * @param maxSize Maximal number of elements in structure.
     */
    public CircularVector(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Adds object. If maximum size is reached first the oldest element is remove.
     * @param object Object to add.
     * @return
     */
    @Override
    public synchronized boolean add(T object) {
        if(size() == maxSize) {
            remove(get(0));
        }
        return super.add(object);
    }
}