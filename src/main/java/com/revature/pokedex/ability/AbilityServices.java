package com.revature.pokedex.ability;

import com.revature.pokedex.element_type.ElementTypeDao;
import com.revature.pokedex.util.interfaces.Serviceable;

import java.util.List;

public class AbilityServices implements Serviceable<Ability> {

    private final ElementTypeDao elementTypeDao;
    private final AbilityDao abilityDao;

    // DI - Dependency Injection
    public AbilityServices(ElementTypeDao elementTypeDao, AbilityDao abilityDao) {
        this.elementTypeDao = elementTypeDao;
        this.abilityDao = abilityDao;
    }

    @Override
    public Ability create(Ability newAbility) {
        return abilityDao.create(newAbility);
    }

    @Override
    public List<Ability> readAll() {
            return abilityDao.findAll();

    }

    @Override
    public Ability readById(String abilityName) {
        return abilityDao.findById(abilityName);
    }

    @Override
    public Ability update(Ability updatedAbility) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validateInput(Ability object) {
        return false;
    }
}
