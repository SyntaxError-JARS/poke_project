package com.revature.pokedex.services;

import com.revature.pokedex.models.Pokemon;

public class PokemonServices implements Serviceable<Pokemon> {
    @Override
    public Pokemon create(Pokemon newObject) {
        return null;
    }

    @Override
    public Pokemon[] readAll() {
        return new Pokemon[0];
    }

    @Override
    public Pokemon readById(String id) {
        return null;
    }

    @Override
    public Pokemon update(Pokemon updatedObject) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validateInput(Pokemon object) {
        return false;
    }
}
