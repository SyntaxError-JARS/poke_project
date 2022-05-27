package com.revature.pokedex.trainer;

import com.revature.pokedex.util.exceptions.AuthenticationException;
import com.revature.pokedex.util.exceptions.InvalidRequestException;
import com.revature.pokedex.util.exceptions.ResourcePersistanceException;
import com.revature.pokedex.util.interfaces.Serviceable;
import java.io.IOException;
import java.util.List;

public class TrainerServices implements Serviceable<Trainer> {

    private TrainerDao trainerDao;

    public TrainerServices(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Override
    public List<Trainer> readAll(){
        // TODO: What trainerDao intellisense telling me?
        List<Trainer> trainers = trainerDao.findAll();
        return trainers;
    }

    @Override
    public Trainer readById(String id) throws ResourcePersistanceException{

        Trainer trainer = trainerDao.findById(id);
        return trainer;
    }

    @Override
    public Trainer update(Trainer updatedTrainer) {
        if (!trainerDao.update(updatedTrainer)){
            return null;
        }

        return updatedTrainer;
    }

    @Override
    public boolean delete(String email) {
        return trainerDao.delete(email);
    }

    public boolean validateEmailNotUsed(String email){
        return trainerDao.checkEmail(email);
    }
    
    public Trainer create(Trainer newTrainer){
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
        return persistedTrainer;
    }

    @Override
    public boolean validateInput(Trainer newTrainer) {
        if(newTrainer == null) return false;
        if(newTrainer.getFname() == null || newTrainer.getFname().trim().equals("")) return false;
        if(newTrainer.getLname() == null || newTrainer.getLname().trim().equals("")) return false;
        if(newTrainer.getEmail() == null || newTrainer.getEmail().trim().equals("")) return false;
        if(newTrainer.getPassword() == null || newTrainer.getPassword().trim().equals("")) return false;
        return newTrainer.getDob() != null || !newTrainer.getDob().trim().equals("");
    }

    public Trainer authenticateTrainer(String email, String password){

        if(password == null || password.trim().equals("") || email == null || email.trim().equals("")) {
            throw new InvalidRequestException("Either email or password is an invalid entry. Please try logging in again");
        }

        Trainer authenticatedTrainer = trainerDao.authenticateTrainer(email, password);

        if (authenticatedTrainer == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedTrainer;

    }
}
