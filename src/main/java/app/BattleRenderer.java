package app;

import app.models.Battler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
    }

    private void drawBackground() {
        gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawEnemy(Battler enemy) {
        Image sprite = new Image(getClass().getResourceAsStream("/imgs/" + enemy.getBase().getName() + "_enemy.png"));

        gc.drawImage(sprite, 220, 65, 130, 130);

        drawHPBar(enemy, 220, 180);
        drawName(enemy, 220, 170);
    }

    private void drawPlayer(Battler player) {
        Image sprite = new Image(getClass().getResourceAsStream("/imgs/" + player.getBase().getName() + "_player.png"));

        gc.drawImage(sprite, 0, 120, 225, 225);

        drawHPBar(player, 40, 110);
        drawName(player, 40, 100);
    }

    private void drawName(Battler b, double x, double y) {
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", 14));
        gc.fillText(b.getBase().getName(), x, y);
    }

    private void drawHPBar(Battler b, double x, double y) {
        double ratio = (double) b.getCurrentHp() / b.getMaxHp();

        gc.setFill(Color.GRAY);
        gc.fillRect(x, y, 100, 8);

        Color hpColor = ratio > 0.5 ? Color.LIMEGREEN :
                        ratio > 0.2 ? Color.GOLD :
                                      Color.RED;

        gc.setFill(hpColor);
        gc.fillRect(x, y, 100 * ratio, 8);
    }
}
