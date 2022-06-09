package com.revature.pokedex.trainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.util.exceptions.InvalidRequestException;
import com.revature.pokedex.util.exceptions.ResourcePersistanceException;
import com.revature.pokedex.util.interfaces.Authable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/trainer-findall")
    public @ResponseBody List<Trainer> getAllTrainers(){
        return trainerServices.readAll();
    }

    @GetMapping("/trainers")
    public @ResponseBody ResponseEntity<List> findAllTrainers(){
        // ResponseEntity takes an Object for the ResponseBody and an HTTP Status Code
        return new ResponseEntity<>(trainerServices.readAll(), HttpStatus.I_AM_A_TEAPOT);
    }
}
