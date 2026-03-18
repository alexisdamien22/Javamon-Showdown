package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import app.models.Attack;
import app.models.Battler;
import app.models.Pokemon;
import app.managers.AttackManager;
import app.managers.TypeEffectivenessManager;
import app.BattleEngine;
import app.BattleLogEntry;
import app.BattleRenderer;
import app.BattleResult;

import java.util.ArrayList;

public class FightController {

    private int turn = 1;

    private boolean playerForcedToSwitch = false;

    @FXML private Button attackBtn1;
    @FXML private Button attackBtn2;
    @FXML private Button attackBtn3;
    @FXML private Button attackBtn4;

    @FXML private VBox logContainer;

    @FXML private Canvas canvas;

    private BattleRenderer renderer;


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

            renderer = new BattleRenderer(canvas);
            renderer.render(player, enemy);

            AttackManager attackManager = new AttackManager();

            playerAttacks = attackManager.getRandomAttacksForPokemon(player.getBase().getId());
            player.setAttacksForActive(playerAttacks);

            enemyAttacks = attackManager.getRandomAttacksForPokemon(enemy.getBase().getId());
            enemy.setAttacksForActive(enemyAttacks);


            setAttackButtons();

            updateSwitchButtons();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAttackButtons() {

        Button[] buttons = { attackBtn1, attackBtn2, attackBtn3, attackBtn4 };

        for (int i = 0; i < 4; i++) {
            playerAttacks = player.getAttacksForActive();

            if (i < playerAttacks.size()) {
                fillAttackButton(buttons[i], playerAttacks.get(i));
                buttons[i].setDisable(false);
                buttons[i].setVisible(true);
            } else {
                buttons[i].setDisable(true);
                buttons[i].setVisible(false);
            }
        }
    }


    private void disableAttackButtons() {
        attackBtn1.setDisable(true);
        attackBtn2.setDisable(true);
        attackBtn3.setDisable(true);
        attackBtn4.setDisable(true);
    }

    private void enableAttackButtons() {
        attackBtn1.setDisable(false);
        attackBtn2.setDisable(false);
        attackBtn3.setDisable(false);
        attackBtn4.setDisable(false);
    }



    private void fillAttackButton(Button button, Attack attack) {

        VBox box = (VBox) button.getGraphic();

        Label nameLabel = (Label) box.getChildren().get(0);
        Label typeLabel = (Label) box.getChildren().get(1);
        Label ppLabel   = (Label) box.getChildren().get(2);

        nameLabel.setText(attack.getName());
        typeLabel.setText(getTypeName(attack.getType().getId()));

        ppLabel.setText(attack.getPp() + "/" + attack.getMaxPp());

        button.setDisable(attack.getPp() <= 0);

        button.getStyleClass().removeIf(c -> c.startsWith("type-"));

        String cssType = "type-" + attack.getType().getName().toLowerCase();
        cssType = cssType.replace("é", "e").replace("è", "e");
        button.getStyleClass().add(cssType);
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

        addLog(new BattleLogEntry(BattleLogEntry.Type.INFO, "=== Tour " + turn + " ==="));
        turn++;

        Attack enemyAttack = enemyAttacks.get((int)(Math.random() * enemyAttacks.size()));

        BattleResult result = engine.executeTurn(player, enemy, chosenAttack, enemyAttack);

        updateLog(result);

        renderer.render(player, enemy);

        setAttackButtons();

        if (result.hasWinner()) {
            addLog(new BattleLogEntry(BattleLogEntry.Type.INFO, result.getWinner() + " a gagné !"));
            disableAttackButtons();
            return;
        }

        if (result.mustForceSwitch()) {
            playerForcedToSwitch = true;
            disableAttackButtons();
            updateSwitchButtons();
            return;
        }
        
        updateSwitchButtons();
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

    public void setPlayer(Battler player) {
        this.player = player;
    }

    public void setEnemy(Battler enemy) {
        this.enemy = enemy;
    }

    @FXML private Button pokemon1;
    @FXML private Button pokemon2;
    @FXML private Button pokemon3;

    private void switchPokemon(int index) {

        if (player.isKO(index) || index == player.getActiveIndex()) return;

        player.switchTo(index);

        try {
            if (player.getAttacksForActive().isEmpty()) {
                AttackManager attackManager = new AttackManager();
                player.setAttacksForActive(
                    attackManager.getRandomAttacksForPokemon(player.getBase().getId())
                );
            }

            playerAttacks = player.getAttacksForActive();

            setAttackButtons();
        } catch (Exception e) {
            e.printStackTrace();
        }



        addLog(new BattleLogEntry(
            BattleLogEntry.Type.STATUS,
            "Vous envoyez " + player.getBase().getName() + " !"
        ));

        renderer.render(player, enemy);
        updateSwitchButtons();

        if (playerForcedToSwitch) {
            playerForcedToSwitch = false;
            enableAttackButtons();
            return;
        }

        disableAttackButtons();

        Attack enemyAttack = enemyAttacks.get((int)(Math.random() * enemyAttacks.size()));
        BattleResult result = engine.executeTurn(player, enemy, null, enemyAttack);

        updateLog(result);
        renderer.render(player, enemy);

        if (result.mustForceSwitch()) {
            playerForcedToSwitch = true;
            disableAttackButtons();
            updateSwitchButtons();
            return;
        }

        if (result.hasWinner()) {
            disableAttackButtons();
            return;
        }

        enableAttackButtons();
    }


    private void updateSwitchButtons() {

        Button[] buttons = { pokemon1, pokemon2, pokemon3 };

        for (int i = 0; i < player.getTeamSize(); i++) {

            Pokemon p = player.getTeam()[i];
            Button btn = buttons[i];

            // Nom du Pokémon
            btn.setText(p.getName());

            btn.getStyleClass().removeAll("switch-active", "switch-ko", "switch-available");

            if (player.isKO(i)) {
                btn.getStyleClass().add("switch-ko");
                btn.setDisable(true);
                continue;
            }

            if (i == player.getActiveIndex()) {
                btn.getStyleClass().add("switch-active");
                btn.setDisable(true);
                continue;
            }

            btn.getStyleClass().add("switch-available");
            btn.setDisable(false);
        }
    }


    @FXML private void onSwitch1() { switchPokemon(0); }
    @FXML private void onSwitch2() { switchPokemon(1); }
    @FXML private void onSwitch3() { switchPokemon(2); }
}