package com.revature.pokedex.daos;

import com.revature.pokedex.exceptions.ResourcePersistanceException;
import com.revature.pokedex.models.Pokemon;
import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.util.ConnectionFactory;
import com.revature.pokedex.util.logging.Logger;

import java.io.IOException;
import java.sql.*;

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
    public Pokemon[] findAll() throws IOException {
        Pokemon[] pokemons = new Pokemon[10];
        int index = 0;

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

                pokemons[index] = pokemon;
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return pokemons;
    }

    @Override
    public Pokemon findById(String id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resoruces, because Connection extends the interface Auto-Closeable

            String sql = "select * from pokemon where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id));

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
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
