package app;

import app.models.Battler;
import app.models.Attack;

import app.managers.TypeEffectivenessManager;

public class DamageCalculator {

    private final TypeEffectivenessManager typeEffectivenessManager;

    public DamageCalculator(TypeEffectivenessManager typeEffectivenessManager) {
        this.typeEffectivenessManager = typeEffectivenessManager;
    }

    public int compute(Battler attacker, Battler defender, Attack attack) {

        if (attack.getPower() == null) return 0;

        double atk = attack.isPhysical() ? attacker.getAtk() : attacker.getSpAtk();
        double def = attack.isPhysical() ? defender.getDef() : defender.getSpDef();

        double base = (((2 * attacker.getLevel() / 5.0) + 2) * attack.getPower() * (atk / def)) / 50 + 2;

        if (attacker.hasType(attack.getType().getId()))
            base *= 1.5;

        try {
            base *= typeEffectivenessManager.getMultiplier(attack.getType().getId(), defender.getTypes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        base *= (0.85 + Math.random() * 0.15);

        return (int) Math.max(1, base);
    }
}