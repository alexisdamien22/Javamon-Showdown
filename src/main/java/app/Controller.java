package app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import app.managers.PokemonManager;
import app.models.Pokemon;
    
public class Controller { 
    @FXML 
    private void initialize() {
        try {
            PokemonManager pokemonManager = new PokemonManager();
            Pokemon[] pokemons = pokemonManager.findAll();
            // Now you can use the pokemons array, e.g., display in UI
            for (Pokemon p : pokemons) {
                System.out.println("Loaded: " + p.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void applyTypeStyle(Button btn, String type) {
        btn.getStyleClass().removeIf(c -> c.startsWith("type-"));

        btn.getStyleClass().add("type-" + type.toLowerCase());
    }

} 
