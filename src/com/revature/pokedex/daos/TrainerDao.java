package com.revature.pokedex.daos;

import com.revature.pokedex.models.Trainer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TrainerDao implements Crudable<Trainer>{

    @Override
    public Trainer create(Trainer newObject) {
        return null;
    }

    @Override
    public Trainer[] findAll() throws IOException {
        // FileWriter's evil counterpart, to read files
        FileReader fileReader = new FileReader("resources/data.txt");
        BufferedReader reader = new BufferedReader(fileReader); // BufferedReader is now reading the file, line-by-line

        // Initializing an array of 10 Trainers
        Trainer[] trainers = new Trainer[10]; // when we specify a size, the array cannot grow. It's max limit is 10 because we gave 10
        // Trainer[] trainers = new Trainer[100]; you have to specify a size in the []
        // Reading the input, being the file, one line a time
        String line = reader.readLine();
        int index = 0; // we want to keep track of where we are placing each trainer from the file into the the array

        while (line != null){ // the last line of the file is null
            String[] trainerInfo = line.split(","); // What's happening? If comma, split into individual string in array

            String fname = trainerInfo[0];
            String lname = trainerInfo[1];
            String email = trainerInfo[2];
            String password = trainerInfo[3];
            String dob = trainerInfo[4];

            Trainer trainer = new Trainer(fname, lname, email, password, dob);
            trainers[index] = trainer;

            index++; // increment the index by 1, must occur after the trainer[index] re-assignment
            line = reader.readLine(); // re-assigns the line variable to the next line in the file, not-impacted by any means by index
        }
        reader.close(); // we need to close our reader

        return trainers;
    }

    @Override
    public Trainer findById(String id) {
        return null;
    }

    @Override
    public boolean update(Trainer updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
