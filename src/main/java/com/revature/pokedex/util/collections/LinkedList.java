package com.revature.pokedex.util.collections;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = LinkedListSerializer.class)
public class LinkedList <T> implements List<T>{



    @Override
    public boolean add(T element) {
        return false;
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean remove(T element) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T get(int index) {
        return null;
    }
}
