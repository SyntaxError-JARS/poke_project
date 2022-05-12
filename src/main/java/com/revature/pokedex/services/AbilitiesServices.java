package com.revature.pokedex.services;

import com.revature.pokedex.daos.AbilitiesDao;
import com.revature.pokedex.daos.ElementTypeDao;
import com.revature.pokedex.models.Abilities;
import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.util.collections.List;
import com.revature.pokedex.util.logging.Logger;

public class AbilitiesServices implements Serviceable<Abilities> {

    private final ElementTypeDao elementTypeDao;
    private final AbilitiesDao abilitiesDao;
    private Logger logger = Logger.getLogger();

    // DI - Dependency Injection
    public AbilitiesServices(ElementTypeDao elementTypeDao, AbilitiesDao abilitiesDao) {
        this.elementTypeDao = elementTypeDao;
        this.abilitiesDao = abilitiesDao;
    }

    @Override
    public Abilities create(Abilities newObject) {
        return null;
    }

    @Override
    public Abilities[] readAll() {
        return new Abilities[0];
    }

    @Override
    public Abilities readById(String id) {
        return null;
    }

    @Override
    public Abilities update(Abilities updatedObject) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validateInput(Abilities object) {
        return false;
    }
}
