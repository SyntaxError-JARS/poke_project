package com.revature.pokedex.pokemon;

import com.revature.pokedex.util.HibernateUtil;
import com.revature.pokedex.util.interfaces.Crudable;
import com.revature.pokedex.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class PokemonDao implements Crudable<Pokemon> {

    @Override
    public Pokemon create(Pokemon newPokemon) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newPokemon);
            transaction.commit();
            return newPokemon;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<Pokemon> findAll() {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            List<Pokemon> pokemons = session.createQuery("FROM Pokemon").list();
            transaction.commit();
            return pokemons;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public Pokemon findById(String pokemonName) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Pokemon pokemon = session.load(Pokemon.class, pokemonName);
            transaction.commit();
            return pokemon;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(Pokemon updatedPokemon) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedPokemon);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean delete(String name) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Pokemon pokemon = session.load(Pokemon.class, name);
            session.remove(pokemon);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
