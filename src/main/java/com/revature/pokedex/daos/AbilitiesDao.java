package com.revature.pokedex.daos;

import com.revature.pokedex.models.Abilities;

import java.io.IOException;

public class AbilitiesDao implements Crudable<Abilities> {
    @Override
    public Abilities create(Abilities newObject) {
        return null;
    }

    @Override
    public Abilities[] findAll() throws IOException {
        return new Abilities[0];
    }

    @Override
    public Abilities findById(String id) {
        return null;
    }

    @Override
    public boolean update(Abilities updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
