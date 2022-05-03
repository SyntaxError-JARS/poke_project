package com.revature.pokedex.util;

import com.revature.pokedex.menus.RegisterMenu;
import com.revature.pokedex.menus.WelcomeMenu;
import com.revature.pokedex.services.TrainerServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private static boolean isRunning;
    // once we add register we will need private final MenuRouter router;

    public AppState() {
        isRunning = true;
        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        TrainerServices trainerServices = new TrainerServices();

        WelcomeMenu welcomeMenu = new WelcomeMenu(terminalReader, trainerServices);
        RegisterMenu registerMenu = new RegisterMenu(terminalReader);
    }

}
