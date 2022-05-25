package com.revature.pokedex.pokemon;

import com.revature.pokedex.ability.Ability;
import com.revature.pokedex.element_type.ElementType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @Column(name = "pokemon_name")
    private String pokemonName;
    private int hp;
    private int atk;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "element_type", referencedColumnName = "id")
    private ElementType elementType;
    @ManyToOne(optional = false)
    @JoinColumn(name="ability1", referencedColumnName = "ability_name", updatable = false)
    private Ability ability1;
    @ManyToOne(optional = false)
    @JoinColumn(name="ability2", referencedColumnName = "ability_name", updatable = false)
    private Ability ability2;

    public Pokemon() {
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonName='" + pokemonName + '\'' +
                ", hp=" + hp +
                ", atk=" + atk +
                ", elementType=" + elementType +
                ", ability1=" + ability1 +
                ", ability2=" + ability2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pokemon)) return false;
        Pokemon pokemon = (Pokemon) o;
        return getHp() == pokemon.getHp() && getAtk() == pokemon.getAtk() && Objects.equals(getPokemonName(), pokemon.getPokemonName()) && Objects.equals(getElementType(), pokemon.getElementType()) && Objects.equals(getAbility1(), pokemon.getAbility1()) && Objects.equals(getAbility2(), pokemon.getAbility2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPokemonName(), getHp(), getAtk(), getElementType(), getAbility1(), getAbility2());
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    public Ability getAbility1() {
        return ability1;
    }

    public void setAbility1(Ability ability1) {
        this.ability1 = ability1;
    }

    public Ability getAbility2() {
        return ability2;
    }

    public void setAbility2(Ability ability2) {
        this.ability2 = ability2;
    }
}
