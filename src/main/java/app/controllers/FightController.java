package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import app.models.Attack;
import app.models.Battler;
import app.managers.AttackManager;
import app.managers.TypeEffectivenessManager;
import app.BattleEngine;
import app.BattleLogEntry;
import app.BattleResult;

import java.util.ArrayList;

public class FightController {

    @FXML private Button attackBtn1;
    @FXML private Button attackBtn2;
    @FXML private Button attackBtn3;
    @FXML private Button attackBtn4;

    @FXML private VBox logContainer;
    @FXML private TextArea logArea;

    private Battler player;
    private Battler enemy;

    private ArrayList<Attack> playerAttacks;
    private ArrayList<Attack> enemyAttacks;

    private BattleEngine engine;

    public void initialize() {

    }

    public void startBattle() {
        try {
            TypeEffectivenessManager typeManager = new TypeEffectivenessManager();
            engine = new BattleEngine(typeManager);

            AttackManager attackManager = new AttackManager();

            playerAttacks = attackManager.findByPokemonId(player.getBase().getId());
            enemyAttacks = attackManager.findByPokemonId(enemy.getBase().getId());

            setAttackButtons();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAttackButtons() {
        fillAttackButton(attackBtn1, playerAttacks.get(0));
        fillAttackButton(attackBtn2, playerAttacks.get(1));
        fillAttackButton(attackBtn3, playerAttacks.get(2));
        fillAttackButton(attackBtn4, playerAttacks.get(3));
    }

    private void fillAttackButton(Button button, Attack attack) {

        VBox box = (VBox) button.getGraphic();

        Label nameLabel = (Label) box.getChildren().get(0);
        Label typeLabel = (Label) box.getChildren().get(1);
        Label ppLabel   = (Label) box.getChildren().get(2);

        nameLabel.setText(attack.getName());
        typeLabel.setText(getTypeName(attack.getTypeId()));
        ppLabel.setText(attack.getPp() + "/" + attack.getPp());
    }

    private String getTypeName(int typeId) {
        return switch (typeId) {
            case 0 -> "Normal";
            case 1 -> "Feu";
            case 2 -> "Eau";
            case 3 -> "Plante";
            case 4 -> "Électrik";
            case 5 -> "Glace";
            case 6 -> "Combat";
            case 7 -> "Poison";
            case 8 -> "Sol";
            case 9 -> "Vol";
            case 10 -> "Psy";
            case 11 -> "Insecte";
            case 12 -> "Roche";
            case 13 -> "Spectre";
            case 14 -> "Dragon";
            case 15 -> "Ténèbres";
            case 16 -> "Acier";
            case 17 -> "Fée";
            default -> "???";
        };
    }

    @FXML
    private void onAttack1Clicked() { executeAttack(playerAttacks.get(0)); }

    @FXML
    private void onAttack2Clicked() { executeAttack(playerAttacks.get(1)); }

    @FXML
    private void onAttack3Clicked() { executeAttack(playerAttacks.get(2)); }

    @FXML
    private void onAttack4Clicked() { executeAttack(playerAttacks.get(3)); }

    private void executeAttack(Attack chosenAttack) {

        // IA ennemie simple : attaque aléatoire
        Attack enemyAttack = enemyAttacks.get((int)(Math.random() * enemyAttacks.size()));

        BattleResult result = engine.executeTurn(player, enemy, chosenAttack, enemyAttack);

        updateLog(result);

        if (result.hasWinner()) {
            logArea.appendText("\n" + result.getWinner() + " a gagné !");
            disableButtons();
        }
    }

    private void updateLog(BattleResult result) {
        for (BattleLogEntry entry : result.getLogs()) {
            addLog(entry);
        }
    }


    private void addLog(BattleLogEntry entry) {
        Label label = new Label(entry.getMessage());

        switch (entry.getType()) {
            case ATTACK -> label.getStyleClass().add("log-attack");
            case DAMAGE -> label.getStyleClass().add("log-damage");
            case EFFECTIVENESS -> label.getStyleClass().add("log-effectiveness");
            case STATUS -> label.getStyleClass().add("log-status");
            case KO -> label.getStyleClass().add("log-ko");
            default -> label.getStyleClass().add("log-info");
        }

        logContainer.getChildren().add(label);
    }

    private void disableButtons() {
        attackBtn1.setDisable(true);
        attackBtn2.setDisable(true);
        attackBtn3.setDisable(true);
        attackBtn4.setDisable(true);
    }

    public void setPlayer(Battler player) {
        this.player = player;
    }

    public void setEnemy(Battler enemy) {
        this.enemy = enemy;
    }
}