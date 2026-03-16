package app.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeEffectivenessManager extends AbstractManager {

    public TypeEffectivenessManager() throws Exception {
        super();
    }

    public double getMultiplier(int attackTypeId, int[] defenderTypes) throws SQLException {

        double result = 1.0;

        for (int defenderTypeId : defenderTypes) {

            String sql = "SELECT multiplier FROM typeEffectiveness WHERE attackTypeId = ? AND defenderTypeId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, attackTypeId);
            stmt.setInt(2, defenderTypeId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                result *= rs.getDouble("multiplier");
            } else {
                result *= 1.0; // par défaut
            }

            rs.close();
            stmt.close();
        }

        return result;
    }
}