package com.revature.pokedex.element_type;

import com.revature.pokedex.util.interfaces.Serviceable;

import java.io.IOException;
import java.util.List;

public class ElementTypeServices implements Serviceable<ElementType> {
    private final ElementTypeDao elementTypeDao;


    public ElementTypeServices(ElementTypeDao elementTypeDao) {
        this.elementTypeDao = elementTypeDao;
    }

    @Override
    public ElementType create(ElementType newElementType) {
        return elementTypeDao.create(newElementType);
    }

    @Override
    public List<ElementType> readAll() {
            return elementTypeDao.findAll();
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
