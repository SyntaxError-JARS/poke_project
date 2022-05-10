package com.revature.pokedex.services;

import com.revature.pokedex.models.ElementType;

public class ElementTypeServices implements Serviceable<ElementType> {
    @Override
    public ElementType create(ElementType newObject) {
        return null;
    }

    @Override
    public ElementType[] readAll() {
        return new ElementType[0];
    }

    @Override
    public ElementType readById(String id) {
        return null;
    }

    @Override
    public ElementType update(ElementType updatedObject) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validateInput(ElementType object) {
        return false;
    }
}
