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
 
    public static void main(String[] args) { 
        launch(); 
    } 
} 
