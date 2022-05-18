package com.revature.pokedex.pokemon;

import com.revature.pokedex.abilities.AbilitiesDao;
import com.revature.pokedex.element_type.ElementTypeDao;
import com.revature.pokedex.util.interfaces.Serviceable;
import com.revature.pokedex.util.logging.Logger;

import java.io.IOException;
import java.util.List;

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
    public Pokemon create(Pokemon newPokemon) {
        return pokemonDao.create(newPokemon);
    }

    @Override
    public List<Pokemon> readAll() {
        try {
            return pokemonDao.findAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pokemon readById(String pokemonName) {
        return pokemonDao.findById(pokemonName);
    }

    @Override
    public Pokemon update(Pokemon updatedPokemon) {
        if(!pokemonDao.update(updatedPokemon)){
            return null;
        }

        return updatedPokemon;
    }

    @Override
    public boolean delete(String name) {
        return pokemonDao.delete(name);
    }

    @Override
    public boolean validateInput(Pokemon object) {
        return false;
    }
}
