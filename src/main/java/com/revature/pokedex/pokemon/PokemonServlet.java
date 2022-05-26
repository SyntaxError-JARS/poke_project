package com.revature.pokedex.pokemon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.ability.Ability;
import com.revature.pokedex.ability.AbilityServices;
import com.revature.pokedex.element_type.ElementType;
import com.revature.pokedex.element_type.ElementTypeServices;
import com.revature.pokedex.util.exceptions.ResourcePersistanceException;
import com.revature.pokedex.util.interfaces.Authable;
import com.revature.pokedex.util.web.dto.PokemonInitializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.revature.pokedex.util.interfaces.Authable.checkAuth;

public class PokemonServlet extends HttpServlet implements Authable {

    private final PokemonServices pokemonServices;
    private final ObjectMapper mapper;
    private final ElementTypeServices elementTypeServices;
    private final AbilityServices abilityServices;

    public PokemonServlet(PokemonServices pokemonServices, ObjectMapper mapper, ElementTypeServices elementTypeServices, AbilityServices abilityServices) {
        this.pokemonServices = pokemonServices;
        this.mapper = mapper;
        this.elementTypeServices = elementTypeServices;
        this.abilityServices = abilityServices;
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        if(req.getParameter("pokemonName") != null){
            Pokemon pokemon = pokemonServices.readById(req.getParameter("pokemonName"));
            String payload = mapper.writeValueAsString(pokemon);
            resp.getWriter().write(payload);
            return;
        }

        List<Pokemon> pokemons = pokemonServices.readAll();
        String payload = mapper.writeValueAsString(pokemons);

        resp.getWriter().write(payload);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Pokemon newPokemon = new Pokemon();
        PokemonInitializer initPokemon = mapper.readValue(req.getInputStream(), PokemonInitializer.class); // from JSON to Java Object (Pokemon)
        try{
            ElementType elementType = elementTypeServices.readById(String.valueOf(initPokemon.getElementType()));
            Ability ability1 = abilityServices.readById(initPokemon.getAbility1());
            System.out.println(ability1);
            Ability ability2 = abilityServices.readById(initPokemon.getAbility2());

            newPokemon.setPokemonName(initPokemon.getPokemonName());
            newPokemon.setAtk(initPokemon.getAtk());
            newPokemon.setHp(initPokemon.getHp());
            newPokemon.setElementType(elementType);
            newPokemon.setAbility1(ability1);
            newPokemon.setAbility2(ability2);
        }catch (Exception e){
            resp.getWriter().write(e.getMessage());
        }

        System.out.println(newPokemon);
        Pokemon persistedPokemon = pokemonServices.create(newPokemon);

        String payload = mapper.writeValueAsString(persistedPokemon); // Mapping from Java Object (Pokemon) to JSON



        resp.getWriter().write("Persisted the provided pokemon as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        if(!checkAuth(req, resp)) return;

        Pokemon pokemonUpdate = mapper.readValue(req.getInputStream(), Pokemon.class);
        Pokemon updatedPokemon = pokemonServices.update(pokemonUpdate);


        String payload = mapper.writeValueAsString(updatedPokemon);
        resp.getWriter().write(payload);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if(!checkAuth(req, resp)) return;
        if(req.getParameter("pokemonName") == null){

            resp.getWriter().write("In order to delete, please provide your the pokemonName into the url with ?pokemonName=example-mander");
            resp.setStatus(401);
            return;
        }
        String pokemonName = req.getParameter("pokemonName");

        try {
            pokemonServices.delete(pokemonName);
            resp.getWriter().write("Delete pokemon from the database");
        } catch (ResourcePersistanceException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(500);
        }
    }

}
