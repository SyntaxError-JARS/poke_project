package com.revature.pokedex.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.daos.ElementTypeDao;
import com.revature.pokedex.models.Abilities;
import com.revature.pokedex.models.ElementType;
import com.revature.pokedex.services.ElementTypeServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ElementTypeServlet extends HttpServlet {
    private final ElementTypeServices elementTypeServices;
    private final ObjectMapper mapper;

    public ElementTypeServlet(ElementTypeServices elementTypeServices, ObjectMapper mapper) {
        this.elementTypeServices = elementTypeServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") != null){
            ElementType elementType = elementTypeServices.readById(req.getParameter("id"));
            String payload = mapper.writeValueAsString(elementType);
            resp.getWriter().write(payload);
            return;
        }

        ElementType[] elementTypes = elementTypeServices.readAll();
        String payload = mapper.writeValueAsString(elementTypes);

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
