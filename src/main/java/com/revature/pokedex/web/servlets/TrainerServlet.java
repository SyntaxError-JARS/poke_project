package com.revature.pokedex.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.daos.TrainerDao;
import com.revature.pokedex.exceptions.ResourcePersistanceException;
import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.services.TrainerServices;
import com.revature.pokedex.util.collections.LinkedList;
import com.revature.pokedex.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.revature.pokedex.web.servlets.Authable.checkAuth;

// @WebServlet("/trainers")
public class TrainerServlet extends HttpServlet implements Authable {

    private final TrainerServices trainerServices;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger();

    public TrainerServlet(TrainerServices trainerServices, ObjectMapper mapper) {
        this.trainerServices = trainerServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp)) return;
//        String pathInfo = req.getPathInfo();
//        String[] pathParts = pathInfo.split("/");
//        System.out.println(pathParts[0] + pathParts[1] + pathParts[2]);


        if(req.getParameter("id") != null && req.getParameter("email") != null){
            resp.getWriter().write("Hey you have the follow id and email " + req.getParameter("id") + " " + req.getParameter("email") );
            return;
        }

        if(req.getParameter("id") != null){
            Trainer trainer;
            try {
                trainer = trainerServices.readById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (ResourcePersistanceException e){
                logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(trainer);
            resp.getWriter().write(payload);
            return;
        }

        List<Trainer> trainers = trainerServices.readAll();
        String payload = mapper.writeValueAsString(trainers);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
