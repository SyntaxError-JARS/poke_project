package com.revature.pokedex.services;

import com.revature.pokedex.daos.ElementTypeDao;
import com.revature.pokedex.models.ElementType;
import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.util.collections.List;
import com.revature.pokedex.util.logging.Logger;

public class ElementTypeServices implements Serviceable<ElementType> {
    private final ElementTypeDao elementTypeDao;
    private Logger logger = Logger.getLogger();


    public ElementTypeServices(ElementTypeDao elementTypeDao) {
        this.elementTypeDao = elementTypeDao;
    }

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
