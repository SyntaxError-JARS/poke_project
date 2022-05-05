package com.revature.pokedex.services;

import com.revature.pokedex.daos.TrainerDao;
import com.revature.pokedex.models.Trainer;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TrainerServices {

    private TrainerDao trainerDao = new TrainerDao();

    public void readTrainers(){
        System.out.println("Begin reading Trainers in our file database.");
        Trainer[] trainers;

        try {
            // TODO: What trainerDao intellisense telling me?
            trainers = trainerDao.findAll();
            System.out.println("All trainers have been found here are the results: \n");
//            for (int i = 0; i < trainers.length; i++) {
//                Trainer trainer = trainers[i];
//                if(trainer != null) {
//                    System.out.println(trainer);
//                }
//            }

            // first time declaring variable you must defined data type (primitive or non-primitive)
            // trainer is now declared as a reference variables for an instance of a Trainer class
            // new keyword allows for the construction (or more technically the instantiation of a Trainer class with a No-Arg Construtor)
            // new Trainer() is instantiating a new trainer object via the No-Args Constructor
            Trainer trainer = new Trainer();

            // TODO: Why is this declared as an Object and not a Trainer??
            Object trainer1 = new Trainer("Charles", "Jester", "cj@mail.com", "p", "1111");

            Trainer iCanNameThisWhatEverTheHeckoIWant = new Trainer();
            System.out.println(iCanNameThisWhatEverTheHeckoIWant.getLname());

            System.out.println(" ----------THIS THINGGGGGGGG--------------- ");
            System.out.println(trainer1.toString());
            System.out.println("-------------------------");
            // the (Trainer) is casting the Object trainer1 in java's Heap Memory to view as a Trainer object instead
            System.out.println(((Trainer) trainer1).getFname());

            // forEach
            for(Object t:trainers ){
                if(t != null) {
                    System.out.println((Trainer) t); // trainer indicates a single element in the trainers array
                }
            }

        } catch (IOException | NullPointerException e) {
             e.printStackTrace();
        }
    }



    // TODO: Implement me to check that the email is not already in our database.
    // public this allows the use of this method anywhere there is a TrainerServices object or within the class itself
    // boolean - it's a true or false value in java and it's specifying the return type
    // validateEmailNotUse() this is a method what we want to call to the DAO to check if the email is already in use
    // String email is the defiend parameters for arguments required when invoking this method
    public boolean validateEmailNotUsed(String email){
        trainerDao.checkEmail(email);
        return false;
    }
    
    public boolean registerTrainer(Trainer newTrainer){
        System.out.println("Trainer trying to be registered: " + newTrainer);
        if(!validateTrainerInput(newTrainer)){ // checking if false
            System.out.println("User was not validated");
            // TODO: throw - what's this keyword?
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateEmailNotUsed(newTrainer.getEmail());

        Trainer persistedTrainer = trainerDao.create(newTrainer);

        if(persistedTrainer == null){
            throw new RuntimeException();
        }
        System.out.println("Trainer has been persisted: " + newTrainer);
        return true;
    }

    private boolean validateTrainerInput(Trainer newTrainer) {
        System.out.println("Validating Trainer: " + newTrainer);
        if(newTrainer == null) return false;
        if(newTrainer.getFname() == null || newTrainer.getFname().trim().equals("")) return false;
        if(newTrainer.getLname() == null || newTrainer.getLname().trim().equals("")) return false;
        if(newTrainer.getEmail() == null || newTrainer.getEmail().trim().equals("")) return false;
        if(newTrainer.getPassword() == null || newTrainer.getPassword().trim().equals("")) return false;
        return newTrainer.getDob() != null || !newTrainer.getDob().trim().equals("");
    }
}
