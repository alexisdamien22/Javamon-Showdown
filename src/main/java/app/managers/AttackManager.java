package app.managers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import app.models.Attack;

public class AttackManager extends AbstractManager {

    public AttackManager() throws Exception {
        super();
    }

    public ArrayList<Attack> findAll() throws SQLException {
        ArrayList<Attack> attackList = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM attacks");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            attackList.add(mapAttack(rs));
        }

        rs.close();
        stmt.close();

        return attackList;
    }

    public Attack findOneByName(String name) throws SQLException {
        Attack attack = null;

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM attacks WHERE Name = ?");
        stmt.setString(1, name);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            attack = mapAttack(rs);
        }

        rs.close();
        stmt.close();

        return attack;
    }

    public ArrayList<Attack> findByPokemonId(int pokemonId) throws SQLException {
        ArrayList<Attack> list = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement(
            "SELECT * FROM attacks WHERE PokemonId = ?"
        );
        stmt.setInt(1, pokemonId);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            list.add(mapAttack(rs));
        }

        rs.close();
        stmt.close();

        return list;
    }

    public ArrayList<Attack> findByTypeId(int typeId) throws SQLException {
        ArrayList<Attack> list = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement(
            "SELECT * FROM attacks WHERE TypeId = ?"
        );
        stmt.setInt(1, typeId);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            list.add(mapAttack(rs));
        }

        rs.close();
        stmt.close();

        return list;
    }

    private Attack mapAttack(ResultSet rs) throws SQLException {
        return new Attack(
            rs.getInt("Id"),
            rs.getString("NameFR"),
            rs.getString("Description"),
            rs.getInt("TypeId"),
            rs.getInt("PP"),
            rs.getString("Class"),
            rs.getObject("Power") != null ? rs.getInt("Power") : null,
            rs.getObject("Precision") != null ? rs.getInt("Precision") : null,
            rs.getInt("Priority")
        );
    }
}