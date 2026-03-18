package app;

import app.models.Battler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BattleRenderer {

    private final Canvas canvas;
    private final GraphicsContext gc;

    private Image background;

    public BattleRenderer(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();

        background = new Image(getClass().getResourceAsStream("/imgs/BackgroundFightScene.png"));
    }

    public void render(Battler player, Battler enemy) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        drawBackground();
        drawEnemy(enemy);
        drawPlayer(player);

        drawPlayerHUD(player);
        drawEnemyHUD(enemy);
    }

    private void drawBackground() {
        gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawEnemy(Battler enemy) {
        Image sprite = new Image(getClass().getResourceAsStream(
                "/imgs/" + enemy.getBase().getName() + "_enemy.png"));

        gc.drawImage(sprite, 220, 65, 130, 130);
    }

    private void drawPlayer(Battler player) {
        Image sprite = new Image(getClass().getResourceAsStream(
                "/imgs/" + player.getBase().getName() + "_player.png"));

        gc.drawImage(sprite, 0, 120, 225, 225);
    }

    private void drawPlayerHUD(Battler player) {
        drawHud(
                player.getBase().getName(),
                player.getCurrentHp(),
                player.getMaxHp(),
                canvas.getWidth() - 200,
                canvas.getHeight() - 90
        );
    }

    private void drawEnemyHUD(Battler enemy) {
        drawHud(
                enemy.getBase().getName(),
                enemy.getCurrentHp(),
                enemy.getMaxHp(),
                20, 20
        );
    }

    private void drawHud(String name, int hp, int maxHp, double x, double y) {

        double width = 180;
        double height = 45;

        gc.setFill(Color.web("#e6e6e6"));
        gc.fillRoundRect(x, y, width, height, 8, 8);

        gc.setStroke(Color.web("#cccccc"));
        gc.setLineWidth(1.5);
        gc.strokeRoundRect(x, y, width, height, 8, 8);

        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        gc.fillText(name, x + 8, y + 18);

        gc.setFont(Font.font("Arial", 12));
        gc.fillText(hp + " / " + maxHp, x + 8, y + 34);

        double ratio = (double) hp / maxHp;
        double barWidth = 110 * ratio;

        Color barColor =
                ratio > 0.5 ? Color.web("#4CAF50") :
                ratio > 0.2 ? Color.web("#FFC107") :
                            Color.web("#F44336");

        gc.setFill(barColor);
        gc.fillRect(x + 65, y + 26, barWidth, 10);

        gc.setStroke(Color.BLACK);
        gc.strokeRect(x + 65, y + 26, 110, 10);
    }
}