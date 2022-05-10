package com.revature.pokedex.models;

import java.util.Objects;

public class ElementType {
    public String type;

    public ElementType(){

    }

    public ElementType(String type) {
        this.type = type;
    }

    // Getters & Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElementType)) return false;
        ElementType that = (ElementType) o;
        return Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType());
    }

    @Override
    public String toString() {
        return "ElementType{" +
                "type='" + type + '\'' +
                '}';
    }
}
