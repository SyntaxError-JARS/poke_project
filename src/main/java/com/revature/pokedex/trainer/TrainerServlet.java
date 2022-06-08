package com.revature.pokedex.trainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.util.exceptions.InvalidRequestException;
import com.revature.pokedex.util.exceptions.ResourcePersistanceException;
import com.revature.pokedex.util.interfaces.Authable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.revature.pokedex.util.interfaces.Authable.checkAuth;

@Controller
@CrossOrigin // this handles CORS
public class TrainerServlet implements Authable {

    private final TrainerServices trainerServices;

    @Autowired
    public TrainerServlet(TrainerServices trainerServices) {
        this.trainerServices = trainerServices;
    }

    // TODO: Implement ME

}
