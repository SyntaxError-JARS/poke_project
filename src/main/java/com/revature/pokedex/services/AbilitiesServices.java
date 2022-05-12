package com.revature.pokedex.services;

import com.revature.pokedex.daos.AbilitiesDao;
import com.revature.pokedex.daos.ElementTypeDao;
import com.revature.pokedex.models.Abilities;
import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.util.collections.List;
import com.revature.pokedex.util.logging.Logger;

import java.io.IOException;

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
    public Abilities create(Abilities newAbilities) {
        return abilitiesDao.create(newAbilities);
    }

    @Override
    public Abilities[] readAll() {
        try {
            return abilitiesDao.findAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Abilities readById(String abilityName) {
        return abilitiesDao.findById(abilityName);
    }

    @Override
    public Abilities update(Abilities updatedAbilities) {
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
