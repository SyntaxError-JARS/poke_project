package com.revature.pokedex.daos;

import com.revature.pokedex.models.Trainer;

import java.io.*;

public class TrainerDao implements Crudable<Trainer>{

    @Override
    public Trainer create(Trainer newTrainer) {
        System.out.println("Here is the newTrainer as it enters our DAO layer: "+ newTrainer); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        // What's this??? Obtaining the file from the relative path
        File trainerPersist = new File("resources/data.txt"); // Note check out maxwells stuff.

        // What's happening here???
        // try-with-resoruces - it works with auto-closable classes.
        try(FileWriter fileWriter = new FileWriter(trainerPersist, true)) {
            fileWriter.write(newTrainer.toFileString() + "\n"); // write is method to write into the specified fill
        } catch (IOException e){
            System.out.println("Could not persist to file");
            e.printStackTrace();
            return null;
        }
        System.out.println("Returning the newTrainer that was inserted into our data.txt");
        return newTrainer;
    }

    @Override
    public Trainer[] findAll() throws IOException {
        // FileWriter's evil counterpart, to read files
        System.out.println("Locating file for our database.");
        FileReader fileReader = new FileReader("resources/data.txt");
        BufferedReader reader = new BufferedReader(fileReader); // BufferedReader is now reading the file, line-by-line

        // Initializing an array of 10 Trainers
        Trainer[] trainers = new Trainer[10]; // when we specify a size, the array cannot grow. It's max limit is 10 because we gave 10
        // Trainer[] trainers = new Trainer[100]; you have to specify a size in the []
        // Reading the input, being the file, one line a time
        System.out.println("Starting to read file, line by line");
        String line = reader.readLine();
        int index = 0; // we want to keep track of where we are placing each trainer from the file into the the array

        while (line != null){ // the last line of the file is null
            String[] trainerInfo = line.split(","); // What's happening? If comma, split into individual string in array

            String fname = trainerInfo[0];
            String lname = trainerInfo[1];
            String email = trainerInfo[2];
            String password = trainerInfo[3];
            String dob = trainerInfo[4];

            System.out.println("Inserted trainer into index" + index);
            Trainer trainer = new Trainer(fname, lname, email, password, dob);
            trainers[index] = trainer;

            index++; // increment the index by 1, must occur after the trainer[index] re-assignment
            System.out.println("Going to the next line for our following index.");
            line = reader.readLine(); // re-assigns the line variable to the next line in the file, not-impacted by any means by index
        }
        reader.close(); // we need to close our reader

        System.out.println("Returning trainers infomation to user.");
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

    public void checkEmail(String email) {
        String sql = "select email from trainer where email = " + email; // issues with SQL injection
    }
}
