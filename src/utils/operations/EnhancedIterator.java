package utils.operations;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Luis
 */
public class EnhancedIterator<E> implements Iterator {

    private List<E> list;
    private int indexSelected;

    public EnhancedIterator(List<E> list, int indexSelected) {
        this.list = list;
        this.indexSelected = indexSelected;
    }

    @Override
    public boolean hasNext() {
        return indexSelected < list.size() - 1;
    }

    @Override
    public E next() {
        indexSelected++;
        return current();
    }

    public boolean hasPrevious() {
        return indexSelected - 1 >= 0;
    }

    public E previous() {
        indexSelected--;
        return current();
    }

    public E current() {
        return list.get(indexSelected);
    }
}
