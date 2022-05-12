package com.revature.pokedex.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.models.Abilities;
import com.revature.pokedex.services.AbilitiesServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AbilitiesServlet extends HttpServlet {

    private final AbilitiesServices abilitiesServices;
    private final ObjectMapper mapper;

    public AbilitiesServlet(AbilitiesServices abilitiesServices, ObjectMapper mapper) {
        this.abilitiesServices = abilitiesServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("abilityName") != null){
            Abilities ability = abilitiesServices.readById(req.getParameter("abilityName"));
            String payload = mapper.writeValueAsString(ability);
            resp.getWriter().write(payload);
            return;
        }

        Abilities[] abilities = abilitiesServices.readAll();
        String payload = mapper.writeValueAsString(abilities);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
