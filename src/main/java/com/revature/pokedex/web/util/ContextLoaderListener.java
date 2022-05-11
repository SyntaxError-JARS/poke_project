package com.revature.pokedex.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.daos.TrainerDao;
import com.revature.pokedex.services.TrainerServices;
import com.revature.pokedex.web.servlets.AuthServlet;
import com.revature.pokedex.web.servlets.TrainerServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ObjectMapper mapper = new ObjectMapper();
        TrainerDao trainerDao = new TrainerDao();
        TrainerServices trainerServices = new TrainerServices(trainerDao);

        AuthServlet authServlet = new AuthServlet(trainerServices, mapper);
        TrainerServlet trainerServlet = new TrainerServlet(trainerServices, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("TrainerServlet", trainerServlet).addMapping("/trainers/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
