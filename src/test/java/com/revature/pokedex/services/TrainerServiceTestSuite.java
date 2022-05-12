package com.revature.pokedex.services;

import com.revature.pokedex.daos.TrainerDao;
import com.revature.pokedex.models.Trainer;
import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TrainerServiceTestSuite {

    TrainerServices sut;
    TrainerDao mockTrainerDao;

    @BeforeEach
    public void testPrep(){
        // in order to specify and mock a dao
        mockTrainerDao = mock(TrainerDao.class);
        sut = new TrainerServices(mockTrainerDao);
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
        Trainer trainer = new Trainer("pie", "pie", "pie","pie","pie");
        // THe below code ensures that the services can continue execution and get expected results from the dao without any issues
        when(mockTrainerDao.create(trainer)).thenReturn(trainer);

        // Act
        Trainer actualTrainer = sut.create(trainer);

        // Assert
        Assertions.assertEquals("pie", actualTrainer.getFname());
        Assertions.assertEquals("pie", actualTrainer.getLname());
        Assertions.assertEquals("pie", actualTrainer.getPassword());
        Assertions.assertEquals("pie", actualTrainer.getEmail());
        Assertions.assertEquals("pie", actualTrainer.getDob());
        // Mockito is verifying that the creation method was execute only once!
        verify(mockTrainerDao, times(1)).create(trainer);
    }

    @Test
    @Disabled
    public void test3(){

    }

}
