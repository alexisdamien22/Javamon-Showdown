package app.models;

public class Battler {

    private Pokemon base;     // Le Pokémon venant du PokemonManager
    private int currentHP;    // PV actuels
    private int level = 50;   // Niveau par défaut (modifiable)

    public Battler(Pokemon base) {
        this.base = base;
        this.currentHP = base.getHp();
    }

    public String getName() {
        return base.getName();
    }

    public Type[] getTypes() {
        return base.getTypes();
    }

    public boolean hasType(int typeId) {
        for (int t : base.getTypes()) {
            if (t == typeId) return true;
        }
        return false;
    }


    public int getAtk() { return base.getAttack(); }
    public int getDef() { return base.getDefense(); }
    public int getSpAtk() { return base.getAtkSp(); }
    public int getSpDef() { return base.getDefSp(); }
    public int getSpeed() { return base.getSpeed(); }

    public int getCurrentHP() { return currentHP; }

    public void takeDamage(int dmg) {
        currentHP = Math.max(0, currentHP - dmg);
    }

    public boolean isKO() {
        return currentHP <= 0;
    }

    public int getLevel() {
        return level;
    }

    public Pokemon getBase() {
        return base;
    }
}