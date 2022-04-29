package com.revature.pokedex.models;

public class Pokemon {

    private String pokemonName;
    private int hp;
    private int atk;
    private String elementType;
    private String ability1;
    private String ability2;

    // This is a No-Args Constructor. IT's the default, IFFFl there is no other constructor added.
    // Otherwise, the custom constructor overwrites
    public Pokemon(){
        super();
    }

    public Pokemon(String pokemonName, int hp, int atk, String elementType, String ability1, String ability2){
        super();
        this.pokemonName = pokemonName; // shadowing, with provided arguments
        this.hp = hp;
        this.atk = atk;
        this.elementType = elementType;
        this.ability1 = ability1;
        this.ability2 = ability2;
    }

    public String getPokemonName(){
        return pokemonName;
    }

    public int getHp(){
        return this.hp;
    }

    public int getAtk() {
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

    @Override
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
