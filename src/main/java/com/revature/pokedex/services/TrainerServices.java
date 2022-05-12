package com.revature.pokedex.services;

import com.revature.pokedex.daos.TrainerDao;
import com.revature.pokedex.exceptions.AuthenticationException;
import com.revature.pokedex.exceptions.InvalidRequestException;
import com.revature.pokedex.exceptions.ResourcePersistanceException;
import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.util.collections.List;
import com.revature.pokedex.util.logging.Logger;

import java.io.IOException;

public class TrainerServices implements Serviceable<Trainer>{

    private TrainerDao trainerDao;
    private Logger logger = Logger.getLogger();

    public TrainerServices(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Override
    public Trainer[] readAll(){
        logger.info("Begin reading Trainers in our file database.");


        try {
            // TODO: What trainerDao intellisense telling me?
            Trainer[] trainers = trainerDao.findAll();
            logger.info("All trainers have been found here are the results: \n");
            return trainers;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Trainer readById(String id){
        try {
            Trainer trainer = trainerDao.findById(id);
            return trainer;
        } catch (ResourcePersistanceException e){
            logger.warn(e.getMessage());
            return null;
        }
    }

    @Override
    public Trainer update(Trainer updatedObject) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public boolean validateEmailNotUsed(String email){
        return trainerDao.checkEmail(email);
    }
    
    public Trainer create(Trainer newTrainer){
        logger.info("Trainer trying to be registered: " + newTrainer);
        if(!validateInput(newTrainer)){ // checking if false
            // TODO: throw - what's this keyword?
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }

        // TODO: Will implement with JDBC (connecting to our database)
        if(validateEmailNotUsed(newTrainer.getEmail())){
            throw new InvalidRequestException("User email is already in use. Please try again with another email or login into previous made account.");
        }

        Trainer persistedTrainer = trainerDao.create(newTrainer);

        if(persistedTrainer == null){
            throw new ResourcePersistanceException("Trainer was not persisted to the database upon registration");
        }
        logger.info("Trainer has been persisted: " + newTrainer);
        return persistedTrainer;
    }

    @Override
    public boolean validateInput(Trainer newTrainer) {
        logger.debug("Validating Trainer: " + newTrainer);
        if(newTrainer == null) return false;
        if(newTrainer.getFname() == null || newTrainer.getFname().trim().equals("")) return false;
        if(newTrainer.getLname() == null || newTrainer.getLname().trim().equals("")) return false;
        if(newTrainer.getEmail() == null || newTrainer.getEmail().trim().equals("")) return false;
        if(newTrainer.getPassword() == null || newTrainer.getPassword().trim().equals("")) return false;
        return newTrainer.getDob() != null || !newTrainer.getDob().trim().equals("");
    }

    public Trainer authenticateTrainer(String email, String password){

        if(password == null || password.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
        }

        Trainer authenticatedTrainer = trainerDao.authenticateTrainer(email, password);

        if (authenticatedTrainer == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedTrainer;

    }
}
