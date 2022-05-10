package com.revature.pokedex.daos;

import com.revature.pokedex.models.Pokemon;
import com.revature.pokedex.util.logging.Logger;

import java.io.IOException;

public class PokemonDao implements Crudable<Pokemon> {

    private Logger logger = Logger.getLogger();

    @Override
    public Pokemon create(Pokemon newObject) {
        return null;
    }

    @Override
    public Pokemon[] findAll() throws IOException {
        return new Pokemon[0];
    }

    @Override
    public Pokemon findById(String id) {
        return null;
    }

    @Override
    public boolean update(Pokemon updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
