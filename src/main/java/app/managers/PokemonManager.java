package app.managers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import app.models.Pokemon;
import app.models.Type;

import java.util.ArrayList;
import java.util.List;

public class PokemonManager extends AbstractManager {

    private final TypeManager typeManager;

    public PokemonManager() throws Exception {
        super();
        this.typeManager = new TypeManager();
    }

    // ============================
    // MAPPER : transforme un ResultSet en Pokémon
    // ============================
    private Pokemon mapPokemon(ResultSet rs) throws SQLException {

        int id = rs.getInt("Id");
        String name = rs.getString("Name");

        // Charger les types
        Type[] types = loadTypesForPokemon(id);

        return new Pokemon(
                id,
                name,
                types,
                rs.getInt("Hp"),
                rs.getInt("Atk"),
                rs.getInt("Def"),
                rs.getInt("AtkSpe"),
                rs.getInt("DefSpe"),
                rs.getInt("Speed"),
                rs.getString("Lore"),
                rs.getString("Description")
        );
    }

    // ============================
    // Charge les types d’un Pokémon
    // ============================
    private Type[] loadTypesForPokemon(int pokemonId) throws SQLException {

        List<Type> types = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement(
                "SELECT TypeId FROM pokemontypes WHERE PokemonId = ?"
        );
        stmt.setInt(1, pokemonId);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int typeId = rs.getInt("TypeId");
            types.add(typeManager.get(typeId));
        }

        rs.close();
        stmt.close();

        return types.toArray(new Type[0]);
    }

    // ============================
    // FIND ALL
    // ============================
    public Pokemon[] findAll() throws SQLException {

        List<Pokemon> list = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM pokemons");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            list.add(mapPokemon(rs));
        }

        rs.close();
        stmt.close();

        return list.toArray(new Pokemon[0]);
    }

    // ============================
    // FIND BY NAME
    // ============================
    public Pokemon findOneByName(String name) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM pokemons WHERE Name = ?"
        );
        stmt.setString(1, name);

        ResultSet rs = stmt.executeQuery();

        Pokemon pokemon = null;
        if (rs.next()) {
            pokemon = mapPokemon(rs);
        }

        rs.close();
        stmt.close();

        return pokemon;
    }

    // ============================
    // FIND BY ID
    // ============================
    public Pokemon findOneById(int id) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM pokemons WHERE Id = ?"
        );
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        Pokemon pokemon = null;
        if (rs.next()) {
            pokemon = mapPokemon(rs);
        }

        rs.close();
        stmt.close();

        return pokemon;
    }
}
