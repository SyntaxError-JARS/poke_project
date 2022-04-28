package com.revature.pokedex;

import java.io.*;

public class MainDriver { // Pascal casing, whihc indicates class or interface

    // This creates the depedency for both of our methods
    static BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args){

        // making our welcome message for users
        String welcome = "Welcome to the Pokedex!";
        String option1 = "1) Login";
        String option2 = "2) Register";
        // String() calls the constructor for the String Class
        // new keyword instantiates it
        String option3 = "3) View/Create pokemon";
        String option4 = new String("4) Exit the pokedex"); // This is the same as ""

        // String conctenation is memory intensive
        // print formater
        System.out.printf("%s \n %s \n %s \n %s \n %s", welcome, option1, option2, option3, option4).println();

        // Strings
        // when evaluating if two strings match for VALUE you cannot use ==
//        String a = "Hello";
//        String b = new String("Hello");
//        System.out.println(a == b); // matches the memory location, not the value
//        System.out.println(a.equals(b));
//
//        String c = "Hello";
//        System.out.println(a == c); // matching to the memory location
        // Check out Scanner if you want
        // Buffered Readers read everything as strings
 //       BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));

        // Try-catch blocks
        // try - attempts the "risky" code
        // catch - handles any exceptions that are thrown
        // THESE ARE NOT ERRORS - YOU SHOULD NEVER CATCH AND ERROR
        try {
            System.out.println("Select number from above\n >");
            String userSelection = terminalReader.readLine(); // the () invokes the readline method!!!
            // This attempt to cast will not work, you cannot cast varying data types
            // can cast primitives to other primitives
            // long longVar = (long) intVar
            // Primitives:
                // char, int, long, byte, short, boolean, float, double, BigInt?
                // don't use double or floats for money unless you want to lose it (they aren't precise)
            // if( (int) userSelection == 1 ) {
//            if ( userSelection.equals("1")) {
//                System.out.println("User has selected login...");
//            } else if ( userSelection.equals("2")) {
//                System.out.println("User has selected register...");
//            } else if ( userSelection.equals("3")) {
//                System.out.println("User has selected view/create pokemon...");
//            } else if ( userSelection.equals("4")) {
//                System.out.println("User has selected exit...");
//            }

            // Instead of 1000 if statements we can use switch statement
            switch (userSelection){
                case "1":
                    System.out.println("User has selected login...");
                    break; // this breaks out of the switch statement, part of control flow
                case "2":
                    System.out.println("User has selected register...");
                    break;
                case "3":
                    System.out.println("User has selected view/create pokemon...");
                    pokemonInput();
                    break;
                case "4":
                    System.out.println("User has selected exit...");
                    break;
                default: // always need a default and commonly goes at the end
                    System.out.println("No valid user input provide");
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace(); // prints out the exception that is thrown
        }

    }

    static void pokemonInput() throws IOException { // you can throw exceptions instead of handling them, this will throw up "level', referred to as Ducking
        //BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in)); // This creates a brand new buffered reader and input stream reader in the heap
        System.out.println("What is your pokemon's name?");
        String pokemonName = terminalReader.readLine(); // when naming variables & methods, use camelCase

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

        System.out.printf("Pokemon Name: %s, HP: %s, ATK: %s, Element Type: %s, Ability 1: %s, Ability 2: %s",
                pokemonName, hp, atk, elementType, ability1, ability2).println();
    }

}
