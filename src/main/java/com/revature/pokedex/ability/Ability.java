package com.revature.pokedex.ability;

import com.revature.pokedex.element_type.ElementType;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "ability")
public class Ability {
    @Id
    @Column(name = "ability_name")
    private String abilityName;
    @Column(name = "atk_multiplier")
    private int atkMultiplier;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dmg_type", referencedColumnName = "id")
    private ElementType elementType;

    public Ability(String abilityName, int atkMultiplier, ElementType elementType) {
        this.abilityName = abilityName;
        this.atkMultiplier = atkMultiplier;
        this.elementType = elementType;
    }

    public Ability() {
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public int getAtkMultiplier() {
        return atkMultiplier;
    }

    public void setAtkMultiplier(int atkMultiplier) {
        this.atkMultiplier = atkMultiplier;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ability)) return false;
        Ability ability = (Ability) o;
        return getAtkMultiplier() == ability.getAtkMultiplier() && Objects.equals(getAbilityName(), ability.getAbilityName()) && Objects.equals(getElementType(), ability.getElementType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAbilityName(), getAtkMultiplier(), getElementType());
    }

    @Override
    public String toString() {
        return "Ability{" +
                "abilityName='" + abilityName + '\'' +
                ", atkMultiplier=" + atkMultiplier +
                ", elementType=" + elementType +
                '}';
    }
}
