package app; 
 
import app.controllers.FightController;
import app.managers.PokemonManager;
import app.models.Battler;
import app.models.Pokemon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene; 
import javafx.stage.Stage;
 
public class Main extends Application { 
    @Override 
    public void start(Stage stage) throws Exception { 
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/views/teamManagement.fxml")
        );
        Scene scene = new Scene(loader.load(), 1200, 800);
        stage.setTitle("Javamon Lab - Gestion d'équipe");
        stage.setScene(scene);
        stage.show();
    } 

    public void fightscene(Stage stage) throws Exception {
        // ============================
        // 1. CHARGEMENT DES POKÉMONS SQL
        // ============================

        PokemonManager pokemonManager = new PokemonManager();

        // Pokémon joueur (id 1 par exemple)
        Pokemon basePlayer = pokemonManager.findOneById(1);

        // Pokémon ennemi (id 7 par exemple)
        Pokemon baseEnemy = pokemonManager.findOneById(7);

        // Création des Battler (niveau 50)
        Battler player = new Battler(basePlayer);
        Battler enemy = new Battler(baseEnemy);

        // ============================
        // 2. CHARGEMENT DE LA SCÈNE
        // ============================

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/fightScene.fxml"));
        Parent root = loader.load();

        FightController controller = loader.getController();

        // Injecter les Pokémon
        controller.setPlayer(player);
        controller.setEnemy(enemy);

        // Lancer le combat
        controller.startBattle();

        stage.setScene(new Scene(root));
        stage.show();
    }
 
    public static void main(String[] args) { 
        launch(); 
    } 
} 
