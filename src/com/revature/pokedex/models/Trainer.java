package com.revature.pokedex.models;

public class Trainer {

    // Encapsulated these variables/attributes to the class or instance thereof
    // Another pillar of OOP Encapsulation
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String dob;


//    public Trainer(){}

    // public is allowing any instance of class to leverage this command
    // This is a constructor because it's using the class name
    // This requires all atttributes defined above to be passed
    // This assigns each argument to their respective parameter variable being fname, lname, email, etc
    // We assign this instance of the objects the passed argumented.
    // So now, "this" is refering to the instance and we are setting this.fname to equal the passed arugment that was assign fname

    // Overloading constructors another subset of polymorphism
    public Trainer(String fname, String lname, String email, String password, String dob) {
        super(); // just always there, by default of EVERY CLASS is Object
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public Trainer(String password){
        this.password = password;
    }

    // public is allowing any instance of class to leverage this command
    // This is a constructor because it's using the class name
    // this constructor requires no Arguments to be passed.
    public Trainer() {

    }

    // Getters & Setters
    public String getFname() {
        return fname;
    }

    // public is letting every instance of this class use the .setFname anywhere.
    // void - this means it will return nothing to me
    // setFname() - to reassign fname attribute
    // method takes in paramerters of String that will be assigned fname
    //this - is the keyword to indicate it's refering to the particular instance of that class
    // this.fname is refering to the attribute in that class
    // we are setting this objects fname attribute to equal the fname that has been provided as an argument
    public void setFname(String fname) {
        this.fname = fname;
    }

    // public is letting every instance of this class use the .getLName anywhere.
    // returning a String value when called
    // getLname() is a method to retrieve this instances lname attribute
    // no arguments required
    // return a string which happens to be our attribute lname
    public String getLname() {
        return lname;
    }

    // trainer.lname = "Jester" is bad, because you could reassign on accident if it were say and int and you did
    // trainer.age = 10;
    // This allows us to explicitly state we are setting the lname variablem, or reassigning it
    // Trainer trainer = new Trainer();
    // trainer.setLname("Jester")
    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String toFileString() {
        // StringBuilder, there is also a StringBuffer (it's thread-safe)
        // Is another class for Strings that allows them to be mutated
        StringBuilder mutableString = new StringBuilder();
        mutableString
                .append(fname).append(",")
                .append(lname).append(",")
                .append(email).append(",")
                .append(password).append(",")
                .append(dob);

        // Without changing the mutableString class from StringBuilder we wont' have an appropriate return type
        return mutableString.toString(); // We need the toString to return it to it's appropriate type
    }

    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return "Trainer{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }


}
