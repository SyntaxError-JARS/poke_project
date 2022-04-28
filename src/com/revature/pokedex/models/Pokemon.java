package com.revature.pokedex.models;

// Class are just "blueprints" for objectst that get instantiated in memory
public class Pokemon {

    // Attributes, variables, state
    private String pokemonName;
    private String hp;
    private String atk;
    private String elementType;
    private String ability1;
    private String ability2;

    // Constructors
    public Pokemon(){ // only need to specify a NoArgs constructor if you are making a custom one
        super(); // this is the parent class Object constructor being called
    }

    public Pokemon(String pokemonName, String hp, String atk, String elementType, String ability1, String ability2){
        super();
        this.pokemonName = pokemonName; // shadowing, with provided arguments
        this.hp = hp;
        this.atk = atk;
        this.elementType = elementType;
        this.ability1 = ability1;
        this.ability2 = ability2;
    }

    // Methods
    public String getPokemonName(){
        return pokemonName; // the second a return is called, nothing else executes after
        // return "4 + 7";
//        return 4 + 7;
        // System.out.println("hey name");
    }

    public String getHp(){
        return this.hp;
    }

    public String getAtk() {
        return atk;
    }

    public String getElementType() {
        return elementType;
    }

    public String getAbility1() {
        return ability1;
    }

    public String getAbility2() {
        return ability2;
    }

    @Override // Why did this pop up?
    public String toString() {
        return "Pokemon{" +
                "pokemonName='" + pokemonName + '\'' +
                ", hp='" + hp + '\'' +
                ", atk='" + atk + '\'' +
                ", elementType='" + elementType + '\'' +
                ", ability1='" + ability1 + '\'' +
                ", ability2='" + ability2 + '\'' +
                '}';
    }
}
