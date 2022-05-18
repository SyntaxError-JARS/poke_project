package com.revature.pokedex.pokemon;

import com.revature.pokedex.util.exceptions.ResourcePersistanceException;
import com.revature.pokedex.util.ConnectionFactory;
import com.revature.pokedex.util.interfaces.Crudable;
import com.revature.pokedex.util.logging.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PokemonDao implements Crudable<Pokemon> {

    private Logger logger = Logger.getLogger();

    @Override
    public Pokemon create(Pokemon newPokemon) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into pokemon values (default, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-indexed, so first ? starts are 1
            ps.setString(1, newPokemon.getPokemonName());
            ps.setInt(2, newPokemon.getHp());
            ps.setInt(3, newPokemon.getAtk());
            ps.setInt(4, newPokemon.getElementType());
            ps.setString(5, newPokemon.getAbility1());
            ps.setString(6, newPokemon.getAbility2());

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new ResourcePersistanceException("Pokemon was not entered into database due to some issue.");
            }

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return newPokemon;
    }

    @Override
    public List<Pokemon> findAll() throws IOException {
        List<Pokemon> pokemons = new LinkedList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from pokemon";
            Statement s = conn.createStatement();

            ResultSet rs =s.executeQuery(sql);

            while (rs.next()) {
                Pokemon pokemon = new Pokemon();

                pokemon.setPokemonName(rs.getString("pokemon_name"));
                pokemon.setHp(rs.getInt("hp"));
                pokemon.setAtk(rs.getInt("atk"));
                pokemon.setElementType(rs.getInt("element_type"));
                pokemon.setAbility1(rs.getString("ability1"));
                pokemon.setAbility2(rs.getString("ability2"));

                pokemons.add(pokemon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return pokemons;
    }

    @Override
    public Pokemon findById(String pokemonName) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resoruces, because Connection extends the interface Auto-Closeable

            String sql = "select * from pokemon where pokemon_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, pokemonName);

            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
                throw new ResourcePersistanceException("Pokemon was not found in the database, please check ID entered was correct.");
            }

            Pokemon pokemon = new Pokemon();
            pokemon.setPokemonName(rs.getString("pokemon_name"));
            pokemon.setHp(rs.getInt("hp"));
            pokemon.setAtk(rs.getInt("atk"));
            pokemon.setElementType(rs.getInt("element_type"));
            pokemon.setAbility1(rs.getString("ability1"));
            pokemon.setAbility2(rs.getString("ability2"));

            return pokemon;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Pokemon updatedPokemon) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "update pokemon set pokemon_name = ?, hp = ?, atk = ?, element_type = ?, ability1 = ?, ability2 = ? where pokemon_name = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, updatedPokemon.getPokemonName());
            ps.setInt(2, updatedPokemon.getHp());
            ps.setInt(3, updatedPokemon.getAtk());
            ps.setInt(4, updatedPokemon.getElementType());
            ps.setString(5, updatedPokemon.getAbility1());
            ps.setString(6, updatedPokemon.getAbility2());
            ps.setString(7, updatedPokemon.getPokemonName());


            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new ResourcePersistanceException("Pokemon was not entered into database due to some issue.");
            }

            return true;

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String name) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "delete from pokemon where pokemon_name = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new ResourcePersistanceException("Pokemon was not deleted from database due to some issue.");
            }

            return true;

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
