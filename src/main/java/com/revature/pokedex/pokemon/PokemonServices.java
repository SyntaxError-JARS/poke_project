package com.revature.pokedex.pokemon;

import com.revature.pokedex.ability.AbilityDao;
import com.revature.pokedex.element_type.ElementTypeDao;
import com.revature.pokedex.util.interfaces.Serviceable;

import java.util.List;

public class PokemonServices implements Serviceable<Pokemon> {
    private final PokemonDao pokemonDao;
    private final ElementTypeDao elementTypeDao;
    private final AbilityDao abilityDao;

    public PokemonServices(PokemonDao pokemonDao, ElementTypeDao elementTypeDao, AbilityDao abilityDao){
        this.pokemonDao = pokemonDao;
        this.elementTypeDao = elementTypeDao;
        this.abilityDao = abilityDao;

    }

    @Override
    public Pokemon create(Pokemon newPokemon) {
        return pokemonDao.create(newPokemon);
    }

    @Override
    public List<Pokemon> readAll() {
            return pokemonDao.findAll();
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
