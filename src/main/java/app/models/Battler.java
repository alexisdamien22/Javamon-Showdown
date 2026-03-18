package app.models;

public class Battler {

    private int[] currentHp;    // PV actuels
    private int level = 50;   // Niveau par défaut (modifiable)

    private Pokemon[] team;
    private int activeIndex = 0;

    public Battler(Pokemon[] team) {
        this.team = team;
        this.currentHp = new int[team.length];

        for (int i = 0; i < team.length; i++) {
            currentHp[i] = team[i].getHp();
        }
    }

    public String getName() { return team[activeIndex].getName(); }
    public Type[] getTypes() { return team[activeIndex].getTypes(); }
    public Pokemon getBase() { return team[activeIndex]; }
    public Pokemon[] getTeam() { return team; }
    public int getActiveIndex() { return activeIndex; }


    public boolean hasType(int typeId) {
        for (Type t : team[activeIndex].getTypes()) {
            if (t.getId() == typeId) return true;
        }
        return false;
    }


    public int getAtk() { return team[activeIndex].getAttack(); }
    public int getDef() { return team[activeIndex].getDefense(); }
    public int getSpAtk() { return team[activeIndex].getAtkSp(); }
    public int getSpDef() { return team[activeIndex].getDefSp(); }
    public int getSpeed() { return team[activeIndex].getSpeed(); }
    public int getMaxHp() { return team[activeIndex].getHp(); }

    public int getCurrentHp() { return currentHp[activeIndex]; }

    public int getTeamSize() { return team.length; }


    public void takeDamage(int dmg) {
        currentHp[activeIndex] = Math.max(0, currentHp[activeIndex] - dmg);
    }

    public boolean isKO(int index) {
        return currentHp[index] <= 0;
    }

    public boolean isKO() {
        return currentHp[activeIndex] <= 0;
    }

    public int getLevel() { return level; }

    public void switchTo(int index) {
        if (index >= 0 && index < team.length) {
            activeIndex = index;
        }
    }

    public boolean hasRemainingPokemon() {
        for (int hp : currentHp) {
            if (hp > 0) return true;
        }
        return false;
    }
}