package app.models;

import java.util.ArrayList;

public class Battler {

    private int[] currentHp;
    private int level = 50;

    private Pokemon[] team;
    private int activeIndex = 0;

    private ArrayList<Attack>[] attacks;

    @SuppressWarnings("unchecked")
    public Battler(Pokemon[] team) {
        this.team = team;
        this.currentHp = new int[team.length];
        this.attacks = new ArrayList[team.length];

        for (int i = 0; i < team.length; i++) {
            currentHp[i] = team[i].getHp();
            attacks[i] = new ArrayList<>();
        }
    }

    public String getName() { return team[activeIndex].getName(); }
    public Type[] getTypes() { return team[activeIndex].getTypes(); }
    public Pokemon getBase() { return team[activeIndex]; }
    public Pokemon[] getTeam() { return team; }
    public int getActiveIndex() { return activeIndex; }
    public int getTeamSize() { return team.length; }

    public int getAtk() { return team[activeIndex].getAttack(); }
    public int getDef() { return team[activeIndex].getDefense(); }
    public int getSpAtk() { return team[activeIndex].getAtkSp(); }
    public int getSpDef() { return team[activeIndex].getDefSp(); }
    public int getSpeed() { return team[activeIndex].getSpeed(); }
    public int getMaxHp() { return team[activeIndex].getHp(); }

    public int getCurrentHp() { return currentHp[activeIndex]; }

    public boolean hasType(int typeId) {
        for (Type t : team[activeIndex].getTypes()) {
            if (t.getId() == typeId) return true;
        }
        return false;
    }

    public void setAttacksForActive(ArrayList<Attack> list) {
        attacks[activeIndex] = list;
    }

    public ArrayList<Attack> getAttacksForActive() {
        return attacks[activeIndex];
    }

    public void takeDamage(int dmg) {
        currentHp[activeIndex] = Math.max(0, currentHp[activeIndex] - dmg);
    }

    public boolean isKO(int index) {
        return currentHp[index] <= 0;
    }

    public boolean isKO() {
        return currentHp[activeIndex] <= 0;
    }

    public boolean hasRemainingPokemon() {
        for (int hp : currentHp) {
            if (hp > 0) return true;
        }
        return false;
    }

    public void switchTo(int index) {
        if (index >= 0 && index < team.length) {
            activeIndex = index;
        }
    }

    public int getLevel() { return level; }
}
