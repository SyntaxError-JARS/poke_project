package com.revature.pokedex.services;

import com.revature.pokedex.daos.TrainerDao;
import com.revature.pokedex.models.Trainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TrainerServices {

    private TrainerDao trainerDao = new TrainerDao();

    public void readTrainers(){
        Trainer[] trainers = new Trainer[0];
        try {
            trainers = trainerDao.findAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < trainers.length; i++) {
            Trainer trainer = trainers[i];
            System.out.println(trainer.toString());
        }


    }

}
