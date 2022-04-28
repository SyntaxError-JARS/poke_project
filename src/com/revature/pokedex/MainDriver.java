package com.revature.pokedex;

public class MainDriver {

    public static void main(String[] args){

        // System is a class
        // out is a attribute of the class
        // println is the method
        System.out.println("This is the beginnings of our Pokedex application");

        // making our welcome message for users
        String welcome = "Welcome to the Pokedex!";
        String option1 = "1) Login";
        String option2 = "2) Register";
        // String() calls the constructor for the String Class
        // new keyword instantiates it
        String option3 = new String(); // This is the same as ""

        // This is declaration
        String goodbye;
        // Reassignment
        goodbye = "Have a wonderful day";

        // Reassignment
        option3 = "3) Exit";

        // String concatenation
        // System.out.println(welcome + option1 + option2 + option3);
//        System.out.println(welcome + "\n" + option1 + "\n" + option2 + "\n" + option3);
//        System.out.println(goodbye);

        // String conctenation is memory intensive
        // print formater
        System.out.printf("%s \n %s \n %s \n %s", welcome, option1, option2, option3).println();

        // Strings
        // Strings are immutable meaning they cannot be changed once create, only reassigned
        // option3 = option3 + " the pokedex"; // hot key to duplicate a lin is ctrl + d
        option3 += " the pokedex";
        System.out.println(option3); // After reassingment you can call the new variable with the changes
    }

}
