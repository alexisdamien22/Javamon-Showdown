package app.managers;

import app.models.Type;
import java.sql.*;
import java.util.HashMap;

public class TypeManager extends AbstractManager {

    private final HashMap<Integer, Type> cache = new HashMap<>();

    public TypeManager() throws Exception {
        super();
        loadAll();
    }

    private void loadAll() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT Id, Name FROM types");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("Id");
            String name = rs.getString("Name");
            cache.put(id, new Type(id, name));
        }

        rs.close();
        stmt.close();
    }

    public Type get(int id) {
        return cache.get(id);
    }
}
