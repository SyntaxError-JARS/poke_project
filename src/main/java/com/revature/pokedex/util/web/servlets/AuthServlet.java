package com.revature.pokedex.util.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.util.exceptions.AuthenticationException;
import com.revature.pokedex.util.exceptions.InvalidRequestException;
import com.revature.pokedex.trainer.Trainer;
import com.revature.pokedex.trainer.TrainerServices;
import com.revature.pokedex.util.web.dto.LoginCreds;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// @WebServlet("/auth") // this requires a default No-Args constructor, but we make our own constructor in line 24

public class AuthServlet extends HttpServlet {

    private final TrainerServices trainerServices;
    // ObjectMapper provided by jackson
    private final ObjectMapper mapper;

    public AuthServlet(TrainerServices trainerServices, ObjectMapper mapper){
        this.trainerServices = trainerServices;
        this.mapper = mapper;
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        try {
            // The jackson library has the ObjectMapper with methods to readValues from the HTTPRequest body as an input stream and assign it to the class
            // Trainer reqTrainer = mapper.readValue(req.getInputStream(), Trainer.class);
            LoginCreds loginCreds = mapper.readValue(req.getInputStream(), LoginCreds.class);

            Trainer authTrainer = trainerServices.authenticateTrainer(loginCreds.getEmail(), loginCreds.getPassword());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authTrainer", authTrainer);

            String payload = mapper.writeValueAsString(authTrainer);

            resp.getWriter().write(payload);
            resp.setStatus(200);
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
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        req.getSession().invalidate();
        resp.getWriter().write("User has logged out!");
    }
}