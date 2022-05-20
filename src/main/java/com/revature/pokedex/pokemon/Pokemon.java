package com.revature.pokedex.pokemon;

import com.revature.pokedex.abilities.Abilities;
import com.revature.pokedex.element_type.ElementType;
import jakarta.persistence.*;

public class Pokemon {

    @Id
    private String pokemonName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "element_id", referencedColumnName = "id")
    private ElementType hp;
    private int atk;
    private int elementType;
    @ManyToOne
    @JoinColumn(name="ability_name")
    private Abilities ability1;
    @ManyToOne
    @JoinColumn(name="ability_name")
    private Abilities ability2;
}
