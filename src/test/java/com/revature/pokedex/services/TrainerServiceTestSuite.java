package com.revature.pokedex.services;

import com.revature.pokedex.daos.TrainerDao;
import com.revature.pokedex.models.Trainer;
import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TrainerServiceTestSuite {

    TrainerServices sut;

    @BeforeEach
    public void testPrep(){
        sut = new TrainerServices(new TrainerDao());
    }

    @Test
    public void test_validateInput_givenValidTrainer_returnTrue(){

        // Arrange
        Trainer trainer = new Trainer("valid", "valid", "valid","valid","valid");

        // Act
        boolean actualResult = sut.validateInput(trainer);

        // Assert
        Assertions.assertTrue(actualResult);

    }

    @Test
    public void test_create_givenValidUser_returnsTrainer(){
        // Arrange
        Trainer trainer = new Trainer("valid", "valid", "valid","valid","valid");

        // Act
        Trainer actualTrainer = sut.create(trainer);

        // Assert
        Assertions.assertEquals("valid", actualTrainer.getFname());
        Assertions.assertEquals("valid", actualTrainer.getLname());
        Assertions.assertEquals("valid", actualTrainer.getPassword());
        Assertions.assertEquals("valid", actualTrainer.getEmail());
        Assertions.assertEquals("valid", actualTrainer.getDob());
    }

    @Test
    @Disabled
    public void test3(){

    }

}
