package com.revature.pokedex.services;

import com.revature.pokedex.models.Abilities;

public class AbilitiesServices implements Serviceable<Abilities> {
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
