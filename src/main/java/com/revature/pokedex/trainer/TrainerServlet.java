package com.revature.pokedex.trainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.util.exceptions.ResourcePersistanceException;
import com.revature.pokedex.util.interfaces.Authable;
import com.revature.pokedex.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.revature.pokedex.util.interfaces.Authable.checkAuth;

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
        // The below code allows to split information from the endpoint after the /trainers/. Reminder the first element is empty because it takes the value from before the first /
//        String pathInfo = req.getPathInfo();
//        String[] pathParts = pathInfo.split("/");
//        System.out.println(pathParts[0] + pathParts[1] + pathParts[2]);


        // Handling the query params in the /trainers?id=x&email=y
        if(req.getParameter("id") != null && req.getParameter("email") != null){
            resp.getWriter().write("Hey you have the follow id and email " + req.getParameter("id") + " " + req.getParameter("email") );
            return;
        }

        // Handling the query params in the endpoint /trainers?id=x
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

        if(!checkAuth(req, resp)) return;
        Trainer authTrainer = (Trainer) req.getSession().getAttribute("authTrainer");

        Trainer reqTrainer = mapper.readValue(req.getInputStream(), Trainer.class);

        if(authTrainer.getEmail().equals(reqTrainer.getEmail())) {

            Trainer updatedTrainer = trainerServices.update(reqTrainer);

            String payload = mapper.writeValueAsString(updatedTrainer);
            resp.getWriter().write(payload);
        } else {
            resp.getWriter().write("Email provided does not match the user currently logged in");
            resp.setStatus(403);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Trainer trainer = mapper.readValue(req.getInputStream(), Trainer.class); // from JSON to Java Object (Pokemon)
        Trainer persistedTrainer = trainerServices.create(trainer);

        String payload = mapper.writeValueAsString(persistedTrainer); // Mapping from Java Object (Pokemon) to JSON

        resp.getWriter().write("Persisted the provided trainer as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req,resp)) return;
        if(req.getParameter("email") == null){
            resp.getWriter().write("In order to delete, please provide your user email as a verification into the url with ?email=your@email.here");
            resp.setStatus(401);
            return;
        }

        String email = req.getParameter("email");
        Trainer authTrainer = (Trainer) req.getSession().getAttribute("authTrainer");

        if(!authTrainer.getEmail().equals(email)){
            resp.getWriter().write("Email provided does not match the user logged in, double check for confirmation of deletion");
            return;
        }

        try {
            trainerServices.delete(email);
            resp.getWriter().write("Delete user from the database");
            req.getSession().invalidate();
        } catch (ResourcePersistanceException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(500);
        }
    }
}
