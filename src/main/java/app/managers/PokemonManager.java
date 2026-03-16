package app.managers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import app.models.Pokemon;
import java.util.ArrayList;
import java.util.List;

public class PokemonManager extends AbstractManager {
    public PokemonManager() throws Exception {
        super();
    }

    public Pokemon[] findAll() throws SQLException {
        List<Pokemon> pokemonList = new ArrayList<>();
        PreparedStatement stmtpokemons = connection.prepareStatement("SELECT * FROM pokemons");
        ResultSet rsPokemons = stmtpokemons.executeQuery();
        while (rsPokemons.next()) {
            System.out.println("Pokemon Name: " + rsPokemons.getString("Name"));
            
            List<Integer> typeList = new ArrayList<>();
            PreparedStatement stmttypes = connection.prepareStatement("SELECT TypeId FROM pokemontypes WHERE PokemonId = ?");
            stmttypes.setInt(1, rsPokemons.getInt("Id"));
            ResultSet rsTypes = stmttypes.executeQuery();
            while (rsTypes.next()) {
                typeList.add(rsTypes.getInt("TypeId"));
            }
            rsTypes.close();
            stmttypes.close();
            
            int[] types = typeList.stream().mapToInt(i -> i).toArray();
            
            Pokemon pokemon = new Pokemon(
                rsPokemons.getInt("Id"),
                rsPokemons.getString("Name"),
                types,
                rsPokemons.getInt("Hp"),
                rsPokemons.getInt("Atk"),
                rsPokemons.getInt("Def"),
                rsPokemons.getInt("AtkSpe"),
                rsPokemons.getInt("DefSpe"),
                rsPokemons.getInt("Speed")
            );
            pokemonList.add(pokemon);
        }
        rsPokemons.close();
        stmtpokemons.close();
        
        return pokemonList.toArray(new Pokemon[0]);
    }

    public Pokemon findOneByName(String name) throws SQLException {
        Pokemon pokemon = null;
        PreparedStatement stmtpokemons = connection.prepareStatement("SELECT * FROM pokemons WHERE Name = ?");
        stmtpokemons.setString(1, name);    
        ResultSet rsPokemons = stmtpokemons.executeQuery();
        if (rsPokemons.next()) {
            System.out.println("Pokemon Name: " + rsPokemons.getString("Name"));
            
            List<Integer> typeList = new ArrayList<>();
            PreparedStatement stmttypes = connection.prepareStatement("SELECT TypeId FROM pokemontypes WHERE PokemonId = ?");
            stmttypes.setInt(1, rsPokemons.getInt("Id"));
            ResultSet rsTypes = stmttypes.executeQuery();
            while (rsTypes.next()) {
                typeList.add(rsTypes.getInt("TypeId"));
            }
            rsTypes.close();
            stmttypes.close();
            
            int[] types = typeList.stream().mapToInt(i -> i).toArray();
            
            pokemon = new Pokemon(
                rsPokemons.getInt("Id"),
                rsPokemons.getString("Name"),
                types,
                rsPokemons.getInt("Hp"),
                rsPokemons.getInt("Atk"),
                rsPokemons.getInt("Def"),
                rsPokemons.getInt("AtkSpe"),
                rsPokemons.getInt("DefSpe"),
                rsPokemons.getInt("Speed")
            );
        }
        rsPokemons.close();
        stmtpokemons.close();
        
        return pokemon;
    }

    public Pokemon findOneById(int id) throws SQLException {
        Pokemon pokemon = null;
        PreparedStatement stmtpokemons = connection.prepareStatement("SELECT * FROM pokemons WHERE Id = ?");
        stmtpokemons.setInt(1, id);    
        ResultSet rsPokemons = stmtpokemons.executeQuery();
        if (rsPokemons.next()) {
            System.out.println("Pokemon Name: " + rsPokemons.getString("Name"));
            
            List<Integer> typeList = new ArrayList<>();
            PreparedStatement stmttypes = connection.prepareStatement("SELECT TypeId FROM pokemontypes WHERE PokemonId = ?");
            stmttypes.setInt(1, rsPokemons.getInt("Id"));
            ResultSet rsTypes = stmttypes.executeQuery();
            while (rsTypes.next()) {
                typeList.add(rsTypes.getInt("TypeId"));
            }
            rsTypes.close();
            stmttypes.close();
            
            int[] types = typeList.stream().mapToInt(i -> i).toArray();
            
            pokemon = new Pokemon(
                rsPokemons.getInt("Id"),
                rsPokemons.getString("Name"),
                types,
                rsPokemons.getInt("Hp"),
                rsPokemons.getInt("Atk"),
                rsPokemons.getInt("Def"),
                rsPokemons.getInt("AtkSpe"),
                rsPokemons.getInt("DefSpe"),
                rsPokemons.getInt("Speed")
            );
        }
        rsPokemons.close();
        stmtpokemons.close();
        
        return pokemon;
    }
}
