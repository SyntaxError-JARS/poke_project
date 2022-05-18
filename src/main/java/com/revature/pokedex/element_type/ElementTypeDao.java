package com.revature.pokedex.element_type;

import com.revature.pokedex.util.exceptions.ResourcePersistanceException;
import com.revature.pokedex.util.ConnectionFactory;
import com.revature.pokedex.util.interfaces.Crudable;
import com.revature.pokedex.util.logging.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ElementTypeDao implements Crudable<ElementType> {

    private Logger logger = Logger.getLogger();

    @Override
    public ElementType create(ElementType newElementType) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into elemental_type values (default, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-indexed, so first ? starts are 1
            ps.setString(1, newElementType.getType());
            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new ResourcePersistanceException("ElementType was not entered into database due to some issue.");
            }

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return newElementType;
    }

    @Override
    public List<ElementType> findAll() throws IOException {
        List<ElementType> elementTypes = new LinkedList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resoruces, because Connection extends the interface Auto-Closeable

            String sql = "select * from elemental_type";
            Statement s = conn.createStatement();

            ResultSet rs =s.executeQuery(sql);

            while (rs.next()) {
                ElementType elementType = new ElementType();

                elementType.setType(rs.getString("type"));

                elementTypes.add(elementType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return elementTypes;
    }

    @Override
    public ElementType findById(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "select * from elemental_type where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id)); // Wrapper class example

            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords

            if(!rs.next()){
                throw new ResourcePersistanceException("Element type was not found in the database, please check ID entered was correct.");
            }

            ElementType elementType = new ElementType();

            elementType.setType(rs.getString("type"));
            return elementType;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(ElementType updatedElementType) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
