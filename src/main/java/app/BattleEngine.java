package app;

import app.models.Battler;
import app.models.Attack;
import app.managers.TypeEffectivenessManager;

public class BattleEngine {

    private final DamageCalculator calculator;
    private final TypeEffectivenessManager typeManager;

    public BattleEngine(TypeEffectivenessManager typeManager) {
        this.typeManager = typeManager;
        this.calculator = new DamageCalculator(typeManager);
    }

    // ============================
    // EXECUTION D'UN TOUR
    // ============================
    public BattleResult executeTurn(Battler player, Battler enemy, Attack playerAttack, Attack enemyAttack) {

        BattleResult result = new BattleResult();

        // Déterminer l'ordre
        boolean playerFirst = determineOrder(player, enemy, playerAttack, enemyAttack);

        if (playerFirst) {
            performAttack(player, enemy, playerAttack, result);

            if (enemy.isKO()) {
                result.setWinner("player");
                return result;
            }

            performAttack(enemy, player, enemyAttack, result);

            if (player.isKO()) {
                result.setWinner("enemy");
                return result;
            }

        } else {
            performAttack(enemy, player, enemyAttack, result);

            if (player.isKO()) {
                result.setWinner("enemy");
                return result;
            }

            performAttack(player, enemy, playerAttack, result);

            if (enemy.isKO()) {
                result.setWinner("player");
                return result;
            }
        }

        return result;
    }

    // ============================
    // ORDRE DU TOUR
    // ============================
    private boolean determineOrder(Battler p1, Battler p2, Attack a1, Attack a2) {

        int prio1 = a1.getPriority();
        int prio2 = a2.getPriority();

        if (prio1 != prio2)
            return prio1 > prio2;

        if (p1.getSpeed() == p2.getSpeed()) {
            return Math.random() < 0.5;
        }
        return p1.getSpeed() >= p2.getSpeed();
    }

    // ============================
    // EXECUTION D'UNE ATTAQUE
    // ============================
    private void performAttack(Battler attacker, Battler defender, Attack attack, BattleResult result) {

        if (attack == null) return;

        // Log attaque
        result.add(BattleLogEntry.Type.ATTACK,
                attacker.getName() + " utilise " + attack.getName() + " !");

        // Calcul dégâts
        int damage = calculator.compute(attacker, defender, attack);

        // Log dégâts
        result.add(BattleLogEntry.Type.DAMAGE,
                defender.getName() + " perd " + damage + " PV !");

        // Appliquer dégâts
        defender.takeDamage(damage);

        // Log KO
        if (defender.isKO()) {
            result.add(BattleLogEntry.Type.KO,
                    defender.getName() + " est K.O. !");
        }
    }
}
