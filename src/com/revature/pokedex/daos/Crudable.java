package com.revature.pokedex.daos;

import java.io.FileNotFoundException;
import java.io.IOException;

// This is another form of abstraction
public interface Crudable<T> {

    // public final int age = 16; we call a constant variable because by default it's final and cannot changed

    // Create
    T create(T newObject);

    // Read
    T[] findAll() throws IOException;
    T findById(String id);

    // Update
    public boolean update(T updatedObj);

    //Delete
    boolean delete(String id);

}
