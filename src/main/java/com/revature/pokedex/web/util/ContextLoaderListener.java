package com.revature.pokedex.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.daos.AbilitiesDao;
import com.revature.pokedex.daos.ElementTypeDao;
import com.revature.pokedex.daos.PokemonDao;
import com.revature.pokedex.daos.TrainerDao;
import com.revature.pokedex.models.Abilities;
import com.revature.pokedex.models.ElementType;
import com.revature.pokedex.models.Pokemon;
import com.revature.pokedex.services.AbilitiesServices;
import com.revature.pokedex.services.ElementTypeServices;
import com.revature.pokedex.services.PokemonServices;
import com.revature.pokedex.services.TrainerServices;
import com.revature.pokedex.web.servlets.*;

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
        AbilitiesDao abilitiesDao = new AbilitiesDao();
        PokemonDao pokemonDao = new PokemonDao();

        // Instantiate and intialize the services with their dao dependency
        TrainerServices trainerServices = new TrainerServices(trainerDao);
        ElementTypeServices elementTypeServices = new ElementTypeServices(elementTypeDao);
        AbilitiesServices abilitiesServices = new AbilitiesServices(elementTypeDao, abilitiesDao);
        PokemonServices pokemonServices = new PokemonServices(pokemonDao,elementTypeDao,abilitiesDao);


        AuthServlet authServlet = new AuthServlet(trainerServices, mapper);
        TrainerServlet trainerServlet = new TrainerServlet(trainerServices, mapper);
        ElementTypeServlet elementTypeServlet = new ElementTypeServlet(elementTypeServices, mapper);
        AbilitiesServlet abilitiesServlet = new AbilitiesServlet(abilitiesServices, mapper);
        PokemonServlet pokemonServlet = new PokemonServlet(pokemonServices, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("TrainerServlet", trainerServlet).addMapping("/trainers/*");
        context.addServlet("ElementTypeServlet", elementTypeServlet).addMapping("/elementTypes/*");
        context.addServlet("AbilitiesServlet", abilitiesServlet).addMapping("/abilities/*");
        context.addServlet("PokemonServlet", pokemonServlet).addMapping("/pokemon/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
