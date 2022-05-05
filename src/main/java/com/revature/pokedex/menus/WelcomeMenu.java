package com.revature.pokedex.menus;
import com.revature.pokedex.services.TrainerServices;

import java.io.BufferedReader;

import static com.revature.pokedex.util.AppState.shutdown;

public class WelcomeMenu extends Menu{

    private TrainerServices trainerServices;

    public WelcomeMenu(BufferedReader terminalReader, TrainerServices trainerServices) {
       super("Welcome", "/welcome", terminalReader);
       this.trainerServices = trainerServices;
    }

    @Override
    public void render() throws Exception {
        // String is the datatype we are declaring
        // welcome is the variable being declared as a STring
        // the value is being set to Welcome To the Pokedex! in the String pool
        String welcome = "Welcome to the Pokedex!";
        String option1 = "1) Login";
        String option2 = "2) Register";
        String option3 = "3) View/Create pokemon";
        String option4 = "4) View all trainers";
        String option5 = new String("5) Exit the pokedex"); // This is the same as ""

        System.out.printf("%s \n %s \n %s \n %s \n %s \n %s", welcome, option1, option2, option3, option4, option5).println();

        System.out.print("\n Select number from above\n >");
        String userSelection = terminalReader.readLine();

        // TODO: What is a switch?
        switch (userSelection) {
            case "1":
                System.out.println("User has selected login...");
                break;
            case "2":
                System.out.println("User has selected register...");
                RegisterMenu registerMenu = new RegisterMenu(terminalReader);
                registerMenu.render();
                // register(); // ctrl + left-click
                break;
            case "3":
                System.out.println("User has selected view/create pokemon...");
                // pokemonInput(); // ctrl + left-click
                break;
            case "4":
                System.out.println("User has selected view trainers...");
                trainerServices.readTrainers();
                break;
            case "5":
                System.out.println("User has selected exit...");
                // shutdown application here
                shutdown();
                break;
            default: // why have a default? catch all if input doesn't match any case above.
                System.out.println("No valid user input provide");
                break;
        }
    }
}


