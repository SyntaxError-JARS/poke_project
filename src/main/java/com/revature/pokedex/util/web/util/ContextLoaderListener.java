package com.revature.pokedex.util.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.ability.AbilityDao;
import com.revature.pokedex.ability.AbilityServlet;
import com.revature.pokedex.element_type.ElementTypeDao;
import com.revature.pokedex.pokemon.PokemonDao;
import com.revature.pokedex.element_type.ElementTypeServlet;
import com.revature.pokedex.pokemon.PokemonServlet;
import com.revature.pokedex.trainer.TrainerDao;
import com.revature.pokedex.ability.AbilityServices;
import com.revature.pokedex.element_type.ElementTypeServices;
import com.revature.pokedex.pokemon.PokemonServices;
import com.revature.pokedex.trainer.TrainerServices;
import com.revature.pokedex.trainer.TrainerServlet;
import com.revature.pokedex.util.web.servlets.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Make our single ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Instantiate all Daos first
        TrainerDao trainerDao = new TrainerDao();
        ElementTypeDao elementTypeDao = new ElementTypeDao();
        AbilityDao abilityDao = new AbilityDao();
        PokemonDao pokemonDao = new PokemonDao();

        // Instantiate and intialize the services with their dao dependency
        TrainerServices trainerServices = new TrainerServices(trainerDao);
        ElementTypeServices elementTypeServices = new ElementTypeServices(elementTypeDao);
        AbilityServices abilityServices = new AbilityServices(elementTypeDao, abilityDao);
        PokemonServices pokemonServices = new PokemonServices(pokemonDao,elementTypeDao, abilityDao);


        AuthServlet authServlet = new AuthServlet(trainerServices, mapper);
        TrainerServlet trainerServlet = new TrainerServlet(trainerServices, mapper);
        ElementTypeServlet elementTypeServlet = new ElementTypeServlet(elementTypeServices, mapper);
        AbilityServlet abilityServlet = new AbilityServlet(abilityServices, mapper);
        PokemonServlet pokemonServlet = new PokemonServlet(pokemonServices, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("TrainerServlet", trainerServlet).addMapping("/trainers/*");
        context.addServlet("ElementTypeServlet", elementTypeServlet).addMapping("/elementTypes/*");
        context.addServlet("AbilitiesServlet", abilityServlet).addMapping("/abilities/*");
        context.addServlet("PokemonServlet", pokemonServlet).addMapping("/pokemon/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
