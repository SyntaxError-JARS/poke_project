package com.revature.pokedex.pokemon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.util.exceptions.ResourcePersistanceException;
import com.revature.pokedex.util.interfaces.Authable;

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

    public PokemonServlet(PokemonServices pokemonServices, ObjectMapper mapper) {
        this.pokemonServices = pokemonServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        if(!checkAuth(req, resp)) return;
        // TODO: Let's create a pokemon
        Pokemon newPokemon = mapper.readValue(req.getInputStream(), Pokemon.class); // from JSON to Java Object (Pokemon)
        Pokemon persistedPokemon = pokemonServices.create(newPokemon);

        String payload = mapper.writeValueAsString(persistedPokemon); // Mapping from Java Object (Pokemon) to JSON

        resp.getWriter().write("Persisted the provided pokemon as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAuth(req, resp)) return;

        Pokemon pokemonUpdate = mapper.readValue(req.getInputStream(), Pokemon.class);
        Pokemon updatedPokemon = pokemonServices.update(pokemonUpdate);

        String payload = mapper.writeValueAsString(updatedPokemon);
        resp.getWriter().write(payload);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
