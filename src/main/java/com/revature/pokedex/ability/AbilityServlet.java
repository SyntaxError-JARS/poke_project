package com.revature.pokedex.ability;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AbilityServlet extends HttpServlet {

    private final AbilityServices abilityServices;
    private final ObjectMapper mapper;

    public AbilityServlet(AbilityServices abilityServices, ObjectMapper mapper) {
        this.abilityServices = abilityServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("abilityName") != null){
            Ability ability = abilityServices.readById(req.getParameter("abilityName"));
            String payload = mapper.writeValueAsString(ability);
            resp.getWriter().write(payload);
            return;
        }

        List<Ability> abilities = abilityServices.readAll();
        String payload = mapper.writeValueAsString(abilities);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Ability abilities = mapper.readValue(req.getInputStream(), Ability.class);
        Ability ability = abilityServices.create(abilities);

        String payload = mapper.writeValueAsString(ability);

        resp.getWriter().write(payload);
        resp.setStatus(201);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        if(httpSession.getAttribute("authTrainer") == null){
            resp.getWriter().write("Unauthorized request - not log in as registered user");
            resp.setStatus(401); // Unauthorized
            return false;
        }
        return true;
    }
}
