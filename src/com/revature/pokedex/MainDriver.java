package com.revature.pokedex;

import com.revature.pokedex.models.Pokemon;
import com.revature.pokedex.models.Trainer;

import java.io.*;

public class MainDriver {

    static BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args){

        String welcome = "Welcome to the Pokedex!";
        String option1 = "1) Login";
        String option2 = "2) Register";
        String option3 = "3) View/Create pokemon";
        String option4 = "4) View all trainers";
        String option5 = new String("5) Exit the pokedex"); // This is the same as ""


        System.out.printf("%s \n %s \n %s \n %s \n %s \n %s", welcome, option1, option2, option3, option4, option5).println();


        try {
            System.out.print("\n Select number from above\n >");
            String userSelection = terminalReader.readLine();

            switch (userSelection){
                case "1":
                    System.out.println("User has selected login...");
                    break;
                case "2":
                    System.out.println("User has selected register...");
                    register();
                    break;
                case "3":
                    System.out.println("User has selected view/create pokemon...");
                    pokemonInput();
                    break;
                case "4":
                    System.out.println("User has selected view trainers...");
                    Trainer[] trainers = readTrainer();
                    for (int i = 0; i < (trainers.length - 1); i++){
                        Trainer trainer = trainers[i];
                        System.out.println(trainer.toString());
                    }
                    break;
                case "5":
                    System.out.println("User has selected exit...");
                    break;
                default:
                    System.out.println("No valid user input provide");
                    break;
            }

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        main(args);

    }

    static void pokemonInput() throws IOException {
        System.out.println("What is your pokemon's name?");
        String pokemonName = terminalReader.readLine();

        System.out.println("What is their hp?");
        String hp = terminalReader.readLine();

        System.out.println("What is their attack value?");
        String atk = terminalReader.readLine();

        System.out.println("What is their element type?");
        String elementType = terminalReader.readLine();

        System.out.println("What is their ability 1?");
        String ability1 = terminalReader.readLine();

        System.out.println("What is their ability 2?");
        String ability2 = terminalReader.readLine();

        Pokemon pokemon1 = new Pokemon(
                            pokemonName,
                            Integer.parseInt(hp), //
                            Integer.parseInt(atk), //
                            elementType,
                            ability1,
                            ability2
        );

        System.out.println(pokemon1);
    }

    static void register() throws IOException {

        System.out.println("What is your full name?");
        String fullName = terminalReader.readLine();

        System.out.println("What is your email?");
        String email = terminalReader.readLine();

        System.out.println("What is your password?");
        String password = terminalReader.readLine();

        System.out.println("Re-enter password");
        String passwordCheck = terminalReader.readLine();

        System.out.println("DOB?");
        String dob = terminalReader.readLine();

        String[] nameArray = fullName.split(" ");
        String fname = nameArray[0];
        String lname = nameArray[0];

        if (!password.equals(passwordCheck)) {
            System.out.println("Passwords don't match");
            return;
        }

        Trainer newTrainer = new Trainer(fname, lname, email, password, dob);
        System.out.println(newTrainer);

        File trainerPersist = new File("resources/data.txt");

        try(FileWriter fileWriter = new FileWriter(trainerPersist, true)) {
            fileWriter.write(newTrainer.toFileString() + "\n");
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    static Trainer[] readTrainer() throws IOException {

        FileReader fileReader = new FileReader("resources/data.txt");
        BufferedReader reader = new BufferedReader(fileReader);

        Trainer[] trainers = new Trainer[10];

        String line = reader.readLine();
        int index = 0;

        while (line != null){
            String[] trainerInfo = line.split(",");

            String fname = trainerInfo[0];
            String lname = trainerInfo[1];
            String email = trainerInfo[2];
            String password = trainerInfo[3];
            String dob = trainerInfo[4];

            Trainer trainer = new Trainer(fname, lname, email, password, dob);
            trainers[index] = trainer;

            index++;
            line = reader.readLine();
        }
        reader.close();

        return trainers;
    }

}


