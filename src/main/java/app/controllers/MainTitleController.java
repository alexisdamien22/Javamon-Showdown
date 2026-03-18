package app.controllers;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainTitleController {
    @FXML private StackPane root;
    @FXML private Label pressEnterLabel;
    @FXML
    public void initialize() {
        startBlinkAnimation();
        setupKeyboardListener();
        setupClickListener();
    }
    private void startBlinkAnimation() {
        FadeTransition ft = new FadeTransition(
                Duration.millis(800),
                pressEnterLabel
        );
        ft.setFromValue(1.0);
        ft.setToValue(0.15);
        ft.setCycleCount(Animation.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
    }
    private void setupKeyboardListener() {
        root.sceneProperty().addListener(
                (obs, oldScene, newScene) -> {
                    if (newScene != null) {
                        newScene.setOnKeyPressed(e -> {
                            if (e.getCode() == KeyCode.ENTER) {
                                goToMainScreen();
                            }
                        });
                    }
                }
        );
    }
    private void setupClickListener() {
        pressEnterLabel.setOnMouseClicked(e -> goToMainScreen());
        pressEnterLabel.setStyle("-fx-cursor: hand;");
    }
    private void goToMainScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/views/teamManagement.fxml")
        );
        Scene scene = new Scene(loader.load(), 1200, 800);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Javamon Lab - Gestion d'équipe");
        stage.setScene(scene);
        stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}