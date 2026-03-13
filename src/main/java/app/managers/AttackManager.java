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

            Attack attack = new Attack(
                rs.getInt("Id"),
                rs.getString("Name"),
                rs.getString("Description"),
                rs.getInt("TypeId"),
                rs.getInt("PP"),
                rs.getString("Class"),
                rs.getInt("Power"),
                rs.getInt("Precision")
            );

            attackList.add(attack);
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

            attack = new Attack(
                rs.getInt("Id"),
                rs.getString("Name"),
                rs.getString("Description"),
                rs.getInt("TypeId"),
                rs.getInt("PP"),
                rs.getString("Class"),
                rs.getInt("Power"),
                rs.getInt("Precision")
            );
        }

        rs.close();
        stmt.close();

        return attack;
    }
}