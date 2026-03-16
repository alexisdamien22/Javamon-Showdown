package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import app.models.Attack;
import app.models.Battler;
import app.managers.AttackManager;
import app.managers.TypeEffectivenessManager;
import app.BattleEngine;
import app.BattleResult;

import java.util.ArrayList;

public class FightController {

    @FXML private Button attackBtn1;
    @FXML private Button attackBtn2;
    @FXML private Button attackBtn3;
    @FXML private Button attackBtn4;

    @FXML private TextArea logArea;

    private Battler player;
    private Battler enemy;

    private ArrayList<Attack> playerAttacks;

    private BattleEngine engine;

    public void initialize() {
        try {
            TypeEffectivenessManager typeManager = new TypeEffectivenessManager();
            engine = new BattleEngine(typeManager);

            AttackManager attackManager = new AttackManager();

            // Charger les attaques du joueur
            playerAttacks = attackManager.findByPokemonId(player.getBase().getId());

            // Afficher les noms dans les boutons
            setAttackButtons();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAttackButtons() {
        attackBtn1.setText(playerAttacks.get(0).getName());
        attackBtn2.setText(playerAttacks.get(1).getName());
        attackBtn3.setText(playerAttacks.get(2).getName());
        attackBtn4.setText(playerAttacks.get(3).getName());
    }

    // ============================
    // ACTIONS DES BOUTONS
    // ============================

    @FXML
    private void onAttack1Clicked() { executeAttack(playerAttacks.get(0)); }

    @FXML
    private void onAttack2Clicked() { executeAttack(playerAttacks.get(1)); }

    @FXML
    private void onAttack3Clicked() { executeAttack(playerAttacks.get(2)); }

    @FXML
    private void onAttack4Clicked() { executeAttack(playerAttacks.get(3)); }

    // ============================
    // EXECUTION DU TOUR
    // ============================

    private void executeAttack(Attack chosenAttack) {

        // IA ennemie simple : attaque 1
        Attack enemyAttack = enemy.getBase().getAttacks().get(0);

        BattleResult result = engine.executeTurn(player, enemy, chosenAttack, enemyAttack);

        updateLog(result);

        if (result.hasWinner()) {
            logArea.appendText("\n" + result.getWinner() + " a gagné !");
            disableButtons();
        }
    }

    private void updateLog(BattleResult result) {
        for (String line : result.getLogs()) {
            logArea.appendText(line + "\n");
        }
    }

    private void disableButtons() {
        attackBtn1.setDisable(true);
        attackBtn2.setDisable(true);
        attackBtn3.setDisable(true);
        attackBtn4.setDisable(true);
    }

    // ============================
    // SETTERS POUR INJECTER LES POKÉMONS
    // ============================

    public void setPlayer(Battler player) {
        this.player = player;
    }

    public void setEnemy(Battler enemy) {
        this.enemy = enemy;
    }
}
