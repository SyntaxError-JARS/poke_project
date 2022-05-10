package com.revature.pokedex.daos;

import com.revature.pokedex.models.ElementType;

import java.io.IOException;

public class ElementTypeDao implements Crudable<ElementType> {
    @Override
    public ElementType create(ElementType newObject) {
        return null;
    }

    @Override
    public ElementType[] findAll() throws IOException {
        return new ElementType[0];
    }

    @Override
    public ElementType findById(String id) {
        return null;
    }

    @Override
    public boolean update(ElementType updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
