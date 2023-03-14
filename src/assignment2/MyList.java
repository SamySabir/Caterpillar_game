package assignment2;

import java.util.Iterator;

public interface MyList<E> extends Iterable<E>{

    int getSize();

    boolean isEmpty();

    boolean add(E e);

    void clear();

    E remove();



}
