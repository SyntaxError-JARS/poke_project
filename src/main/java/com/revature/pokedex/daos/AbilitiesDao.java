package com.revature.pokedex.daos;

import com.revature.pokedex.exceptions.ResourcePersistanceException;
import com.revature.pokedex.models.Abilities;
import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.util.ConnectionFactory;
import com.revature.pokedex.util.logging.Logger;

import java.io.IOException;
import java.sql.*;

public class AbilitiesDao implements Crudable<Abilities> {

    private Logger logger = Logger.getLogger();

    @Override
    public Abilities create(Abilities newAbilities) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into abilities values (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-indexed, so first ? starts are 1
            ps.setString(1, newAbilities.getAbilityName());
            ps.setInt(2, newAbilities.getAtkMultiplier());
            ps.setInt(3, newAbilities.getDmgType());

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new ResourcePersistanceException("Ability was not entered into database due to some issue.");
            }

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return newAbilities;
    }

    @Override
    public Abilities[] findAll() throws IOException {
        Abilities[] abilities = new Abilities[10];
        int index = 0;

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resoruces, because Connection extends the interface Auto-Closeable

            String sql = "select * from abilities";
            Statement s = conn.createStatement();

            ResultSet rs =s.executeQuery(sql);

            while (rs.next()) {
                Abilities ability = new Abilities();

                ability.setAbilityName(rs.getString("ability_name"));
                ability.setAtkMultiplier(rs.getInt("atk_multiplier"));
                ability.setDmgType(rs.getInt("dmg_type"));

                abilities[index] = ability;
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return abilities;
    }

    @Override
    public Abilities findById(String abilityName) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "select * from abilities where ability_name = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, abilityName); // Wrapper class example

            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords

            if(!rs.next()){
                throw new ResourcePersistanceException("Ability was not found in the database, please check ID entered was correct.");
            }

            Abilities ability = new Abilities();

            ability.setAbilityName(rs.getString("ability_name")); // this column lable MUST MATCH THE DB
            ability.setAtkMultiplier(rs.getInt("atk_multiplier"));
            ability.setDmgType(rs.getInt("dmg_type"));
            return ability;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Abilities updatedAbilities) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
