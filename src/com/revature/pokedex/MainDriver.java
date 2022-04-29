package com.revature.pokedex; // determining the package structure for the output

// importing the class from other packages to be leveraged
import com.revature.pokedex.models.Pokemon;
import com.revature.pokedex.models.Trainer;

// importing everything from java.io.
// this is a library that's provided by....JRE
import java.io.*;

// What's this?
// Creating a class called MainDriver
public class MainDriver {



    // What's this? Why here?
    // Instance of buffered reaader, get input from users
    // So you don't have to define for each method, no need to continually build on the dependencies for a bufffered aka taking up memoery
    // What's static? Keyword to make method used by that class on "class loading"
    // look into static imports
    static BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));

    // What's this? Why  we have it?
    // main method signature, does this change? No*
    // * public static void (String... args) // Var Args
    public static void main(String[] args){
        boolean isRunning = true;

        // What's this block? All these options?
        // All variables for our welcome message, declaring String variables and to be called later
        // Strings aren't mutable, Strings are a Class
        // Each of these map to a variable
        String welcome = "Welcome to the Pokedex!";
        String option1 = "1) Login";
        String option2 = "2) Register";
        String option3 = "3) View/Create pokemon";
        String option4 = "4) View all trainers";
        String option5 = new String("5) Exit the pokedex"); // This is the same as ""

        while(isRunning) {
        // This is where we take the above variables and print them using a formatter
        // %s for STrings or %d for digits (numbers) in the string provided initially, then you provide the respective variables
        // println() is just invoking another method from out to add a new line after
        System.out.printf("%s \n %s \n %s \n %s \n %s \n %s", welcome, option1, option2, option3, option4, option5).println();

        // try-block specifically does what....
        // if any execption it was throw to the catch-block. It's considered "risky", a potential for the statements within to throw an excpetion.

            try {
                System.out.print("\n Select number from above\n >"); // just print, prints out to console and that's it, no appended new line
                // What's happening here?
                // Taking input from the readline in terminalReader as a String!!! Always a STRING From BufferedReader
                String userSelection = terminalReader.readLine();

                // What's a switch statement?
                // If we hve many cases or conditions and well continue to evaluated until coditions is met based
                // on the user selection
                switch (userSelection) {
                    case "1":
                        System.out.println("User has selected login...");
                        break;
                    case "2":
                        System.out.println("User has selected register...");
                        register(); // ctrl + left-click
                        break;
                    case "3":
                        System.out.println("User has selected view/create pokemon...");
                        pokemonInput(); // ctrl + left-click
                        break;
                    case "4":
                        System.out.println("User has selected view trainers...");
                        Trainer[] trainers = readTrainer(); // ignore for now
                        // What's happening in the for loop?
                        // very common, intialization; conditional;increment (happens AFTER each iteration)
                        // 1) intialization occurs ONCE at the beginning
                        // 2) conditional is checked afterwards;
                        // 3) the statements with in the for loop are run ONE TIME
                        // 4) then the increment occurs
                        // 5) condidtion is revalated, if true go through the statements again using the reassing i variable
                        for (int i = 0; i < trainers.length; i++) {
                            Trainer trainer = trainers[i];
                            System.out.println(trainer.toString());
                        }


                        break;
                    case "5":
                        System.out.println("User has selected exit...");
                        isRunning = false;
                        break;
                    default: // why have a default? catch all if input doesn't match any case above.
                        System.out.println("No valid user input provide");
                        break;
                }

            } catch (IOException | RuntimeException e) { // This will catch any of the specified exceptions.
                e.printStackTrace(); // prining  out the exception that is throw
            }
        }
        // Recursively calling itself
        // I want this application to continue running until the user selects exit'
        // This is bad because the recursion can continually run and end up with stack overlow (too much memory used)
        // main(args);

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
                            Integer.parseInt(hp), // What is Integer? it's a class
                            Integer.parseInt(atk), // .parseInt is taking the String and converting to the primitive int
                            elementType,
                            ability1,
                            ability2
        );

        // Every primitive has an associated Class with it's full word used. i.e. int Integer, char Character, boolean Boolean etc...
        // These are wrapper classes, we can convert from one class into the primitive of another.
        // They also allow us to call various methods that can be useful, on top of that they give us a means to easily convert into primitives
        // by leveraging "auto-boxing" (you all to figure out)

        System.out.println(pokemon1);
    }

    // What is this and why?
    // prompt new Trainer to register to program
    // static method that returns nothing
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

        // What's happening here???
        // Breaking or splitting the String fullName into an String array by " " spaces
        String[] nameArray = fullName.split(" ");
        String fname = nameArray[0];
        String lname = nameArray[nameArray.length - 1];

        // What's happening here??
        //
        if (!password.equals(passwordCheck)) { // password != passwordCheck
            System.out.println("Passwords don't match");
            return; // why return??? Control Flow, this breaks this method and returns us to main
        }


        // Trainer trainer = new Trainer(); // why is this red?? there isn't a No-Arg constructor
        // What's happening here? Intialization a new Trainer object in memory
        Trainer newTrainer = new Trainer(fname, lname, email, password, dob);
        System.out.println(newTrainer); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        // What's this??? Obtaining the file from the relative path
        File trainerPersist = new File("resources/data.txt"); // Note check out maxwells stuff.

        // What's happening here???
        // try-with-resoruces - it works with auto-closable classes.
        try(FileWriter fileWriter = new FileWriter(trainerPersist, true)) {
            fileWriter.write(newTrainer.toFileString() + "\n"); // write is method to write into the specified fill
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    static Trainer[] readTrainer() throws IOException {

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

}


