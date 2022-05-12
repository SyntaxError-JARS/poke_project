package com.revature.pokedex.services;

import com.revature.pokedex.daos.ElementTypeDao;
import com.revature.pokedex.models.ElementType;
import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.util.collections.List;
import com.revature.pokedex.util.logging.Logger;

import java.io.IOException;

public class ElementTypeServices implements Serviceable<ElementType> {
    private final ElementTypeDao elementTypeDao;
    private Logger logger = Logger.getLogger();


    public ElementTypeServices(ElementTypeDao elementTypeDao) {
        this.elementTypeDao = elementTypeDao;
    }

    @Override
    public ElementType create(ElementType newElementType) {
        return elementTypeDao.create(newElementType);
    }

    @Override
    public ElementType[] readAll() {
        try {
            return elementTypeDao.findAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ElementType readById(String id) {
       return elementTypeDao.findById(id);
    }

    @Override
    public ElementType update(ElementType updatedElementType) {
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
