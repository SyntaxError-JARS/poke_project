package com.revature.pokedex.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.daos.PokemonDao;
import com.revature.pokedex.exceptions.AuthenticationException;
import com.revature.pokedex.exceptions.InvalidRequestException;
import com.revature.pokedex.models.Abilities;
import com.revature.pokedex.models.Pokemon;
import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.services.PokemonServices;
import com.revature.pokedex.web.dto.LoginCreds;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PokemonServlet extends HttpServlet {

    private final PokemonServices pokemonServices;
    private final ObjectMapper mapper;

    public PokemonServlet(PokemonServices pokemonServices, ObjectMapper mapper) {
        this.pokemonServices = pokemonServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") != null){
            Pokemon pokemon = pokemonServices.readById(req.getParameter("id"));
            String payload = mapper.writeValueAsString(pokemon);
            resp.getWriter().write(payload);
            return;
        }

        Pokemon[] pokemons = pokemonServices.readAll();
        String payload = mapper.writeValueAsString(pokemons);

        resp.getWriter().write(payload);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            Pokemon pokemon = mapper.readValue(req.getInputStream(), Pokemon.class);
            Pokemon newPokemon = pokemonServices.create(pokemon);

            String payload = mapper.writeValueAsString(newPokemon);
            resp.getWriter().write("Here is what you enter into our database");
            resp.getWriter().write(payload);
            resp.setStatus(201);

        } catch (Exception e){
            resp.setStatus(500);
            resp.getWriter().write(e.getMessage());
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

}
