package com.revature.pokedex.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.exceptions.AuthenticationException;
import com.revature.pokedex.exceptions.InvalidRequestException;
import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.services.TrainerServices;
import com.revature.pokedex.web.dto.LoginCreds;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// @WebServlet("/auth") // this requires a default No-Args constructor
public class AuthServlet extends HttpServlet {

    private final TrainerServices trainerServices;
    // ObjectMapper provided by jackson
    private final ObjectMapper mapper;

    public AuthServlet(TrainerServices trainerServices, ObjectMapper mapper){
        this.trainerServices = trainerServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            // The jackson library has the ObjectMapper with methods to readValues from the HTTPRequest body as an input stream and assign it to the class
            // Trainer reqTrainer = mapper.readValue(req.getInputStream(), Trainer.class);
            LoginCreds loginCreds = mapper.readValue(req.getInputStream(), LoginCreds.class);

            Trainer authTrainer = trainerServices.authenticateTrainer(loginCreds.getEmail(), loginCreds.getPassword());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authTrainer", authTrainer);

            resp.getWriter().write("You have successfully logged in!");
        } catch (AuthenticationException | InvalidRequestException e){
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e){
            resp.setStatus(500);
            resp.getWriter().write(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.getWriter().write("User has logged out!");
    }
}
