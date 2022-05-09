package com.revature.pokedex.menus;
import com.revature.pokedex.services.TrainerServices;
import com.revature.pokedex.util.logging.Logger;

import java.io.BufferedReader;

import static com.revature.pokedex.util.AppState.shutdown;

public class WelcomeMenu extends Menu{

    private TrainerServices trainerServices;
    private final Logger logger;

    public WelcomeMenu(BufferedReader terminalReader, TrainerServices trainerServices, Logger logger) {
       super("Welcome", "/welcome", terminalReader);
       this.trainerServices = trainerServices;
       this.logger = logger;
    }

    @Override
    public void render() throws Exception {
        // String is the datatype we are declaring
        // welcome is the variable being declared as a STring
        // the value is being set to Welcome To the Pokedex! in the String pool
        String welcome = "Welcome to the Pokedex!";
        String option1 = "1) Login";
        String option2 = "2) Register";
        String option3 = "3) Find Trainer by ID";
        String option4 = "4) View all trainers";
        String option5 = new String("5) Exit the pokedex"); // This is the same as ""

        System.out.printf("%s \n %s \n %s \n %s \n %s \n %s", welcome, option1, option2, option3, option4, option5).println();

        System.out.print("\n Select number from above\n >");
        String userSelection = terminalReader.readLine();

        // TODO: What is a switch?
        switch (userSelection) {
            case "1":
                logger.info("User has selected login...");
                break;
            case "2":
                System.out.println("User has selected register...");
                RegisterMenu registerMenu = new RegisterMenu(terminalReader);
                registerMenu.render();
                // register(); // ctrl + left-click
                break;
            case "3":
                System.out.println("User has selected to find trainer by ID...");
                System.out.println("Please enter the ID for specific trainer: ");
                String id = terminalReader.readLine();

                System.out.println(trainerServices.findTrainerById(id));
                break;
            case "4":
                System.out.println("User has selected view trainers...");
                trainerServices.readTrainers();
                break;
            case "5":
                System.out.println("User has selected exit...");
                // shutdown application here
                shutdown();
                logger.info("Application shutting down");
                break;
            default: // why have a default? catch all if input doesn't match any case above.
                System.out.println("No valid user input provide");
                break;
        }
    }
}


