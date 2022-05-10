package com.revature.pokedex.services;

import com.revature.pokedex.daos.AbilitiesDao;
import com.revature.pokedex.daos.ElementTypeDao;
import com.revature.pokedex.daos.PokemonDao;
import com.revature.pokedex.models.Pokemon;
import com.revature.pokedex.util.logging.Logger;

public class PokemonServices implements Serviceable<Pokemon> {
    private final PokemonDao pokemonDao;
    private final ElementTypeDao elementTypeDao;
    private final AbilitiesDao abilitiesDao;
    private Logger logger = Logger.getLogger();

    public PokemonServices(PokemonDao pokemonDao, ElementTypeDao elementTypeDao, AbilitiesDao abilitiesDao){
        this.pokemonDao = pokemonDao;
        this.elementTypeDao = elementTypeDao;
        this.abilitiesDao = abilitiesDao;

    }

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
