package com.revature.pokedex.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.daos.TrainerDao;
import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.services.TrainerServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @WebServlet("/trainers")
public class TrainerServlet extends HttpServlet {

    private final TrainerServices trainerServices;
    private final ObjectMapper mapper;

    public TrainerServlet(TrainerServices trainerServices, ObjectMapper mapper) {
        this.trainerServices = trainerServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Trainer[] trainers = trainerServices.readAll();

        String payload = mapper.writeValueAsString(trainers);

        resp.getWriter().write(payload);
    }
}
