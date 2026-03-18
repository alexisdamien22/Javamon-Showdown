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

    public BattleResult executeTurn(Battler player, Battler enemy,
                                    Attack playerAttack, Attack enemyAttack) {

        BattleResult result = new BattleResult();

        boolean playerFirst = determineOrder(player, enemy, playerAttack, enemyAttack);

        if (playerFirst) {

            performAttack(player, enemy, playerAttack, result);

            if (enemy.isKO()) {
                handleEnemyKO(enemy, result);

                if (!enemy.hasRemainingPokemon()) {
                    result.setWinner("player");
                }
                return result;
            }

            performAttack(enemy, player, enemyAttack, result);

            if (player.isKO()) {
                result.add(BattleLogEntry.Type.KO, player.getName() + " est K.O. !");

                if (player.hasRemainingPokemon()) {
                    result.add(BattleLogEntry.Type.STATUS, "Choisissez un Pokémon à envoyer !");
                    result.setForceSwitch(true);
                    return result;
                }

                result.setWinner("enemy");
                return result;
            }

        } else {

            performAttack(enemy, player, enemyAttack, result);

            if (player.isKO()) {
                result.add(BattleLogEntry.Type.KO, player.getName() + " est K.O. !");

                if (player.hasRemainingPokemon()) {
                    result.add(BattleLogEntry.Type.STATUS, "Choisissez un Pokémon à envoyer !");
                    result.setForceSwitch(true);
                    return result;
                }

                result.setWinner("enemy");
                return result;
            }

            performAttack(player, enemy, playerAttack, result);


            if (enemy.isKO()) {
                handleEnemyKO(enemy, result);

                if (!enemy.hasRemainingPokemon()) {
                    result.setWinner("player");
                }
                return result;
            }
        }

        return result;
    }

    private boolean determineOrder(Battler p1, Battler p2, Attack a1, Attack a2) {

        int prio1 = (a1 == null) ? 6 : a1.getPriority();
        int prio2 = (a2 == null) ? 6 : a2.getPriority();

        if (prio1 != prio2)
            return prio1 > prio2;

        if (p1.getSpeed() == p2.getSpeed()) {
            return Math.random() < 0.5;
        }
        return p1.getSpeed() >= p2.getSpeed();
    }

    private void performAttack(Battler attacker, Battler defender,
                                Attack attack, BattleResult result) {

            if (attack == null) return;

            if (attack.getPp() <= 0) {
                result.add(BattleLogEntry.Type.STATUS,
                        attack.getName() + " n’a plus de PP !");
                return;
            }

            attack.setPp(attack.getPp() - 1);

            result.add(BattleLogEntry.Type.ATTACK,
                    attacker.getName() + " utilise " + attack.getName() + " !");

            int damage = calculator.compute(attacker, defender, attack);

            result.add(BattleLogEntry.Type.DAMAGE,
                    defender.getName() + " perd " + damage + " PV !");

            defender.takeDamage(damage);

            if (defender.isKO()) {
                result.add(BattleLogEntry.Type.KO,
                        defender.getName() + " est K.O. !");
            }
        }


    private void handleEnemyKO(Battler enemy, BattleResult result) {

        result.add(BattleLogEntry.Type.KO,
                enemy.getName() + " est K.O. !");

        if (!enemy.hasRemainingPokemon()) return;

        int next = findNextAlive(enemy);
        enemy.switchTo(next);

        result.add(BattleLogEntry.Type.STATUS,
                "L'ennemi envoie " + enemy.getName() + " !");
    }

    private int findNextAlive(Battler b) {
        for (int i = 0; i < b.getTeamSize(); i++) {
            if (!b.isKO(i)) return i;
        }
        return 0;
    }
}