package com.revature.pokedex.models;

public class Trainer {

    private String fname;
    private String lname;
    private String email;
    private String password;
    private String dob;

    public Trainer(String fname, String lname, String email, String password, String dob) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

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
        StringBuilder mutableString = new StringBuilder();
        mutableString
                .append(fname)
                .append(",")
                .append(lname)
                .append(",")
                .append(email)
                .append(",")
                .append(password)
                .append(",")
                .append(dob);

        return mutableString.toString();
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }


}
