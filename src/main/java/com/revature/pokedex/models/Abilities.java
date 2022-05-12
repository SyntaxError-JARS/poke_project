package com.revature.pokedex.models;

import java.util.Objects;

public class Abilities {
    private String abilityName;
    private int atkMultiplier;
    private int dmgType;

    public Abilities(String abilityName, int atkMultiplier, int dmgType) {
        this.abilityName = abilityName;
        this.atkMultiplier = atkMultiplier;
        this.dmgType = dmgType;
    }

    public Abilities() {
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

    public int getDmgType() {
        return dmgType;
    }

    public void setDmgType(int dmgType) {
        this.dmgType = dmgType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Abilities)) return false;
        Abilities abilities = (Abilities) o;
        return Objects.equals(getAbilityName(), abilities.getAbilityName()) && Objects.equals(getAtkMultiplier(), abilities.getAtkMultiplier()) && Objects.equals(getDmgType(), abilities.getDmgType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAbilityName(), getAtkMultiplier(), getDmgType());
    }

    @Override
    public String toString() {
        return "Abilities{" +
                "abilityName='" + abilityName + '\'' +
                ", atkMultiplier='" + atkMultiplier + '\'' +
                ", dmgType='" + dmgType + '\'' +
                '}';
    }
}
