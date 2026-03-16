package app.models;

public class Pokemon {
    private int id;
    private int combatId;
    private String name;
    private int[] types;
    private int hp;
    private int attack;
    private int defense;
    private int AtkSp;
    private int DefSp;
    private int speed;

    public Pokemon(int id, String name, int[] types, int hp, int attack, int defense, int AtkSp, int DefSp, int speed) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.AtkSp = AtkSp;
        this.DefSp = DefSp;
        this.speed = speed;
    }

    public void setCombatId(int combatId) { this.combatId = combatId; }
    public void setHp(int hp) { this.hp = hp; }

    public int getId() { return id; }
    public int getCombatId() { return combatId; }
    public String getName() { return name; }
    public int[] getTypes() { return types; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getAtkSp() { return AtkSp; }
    public int getDefSp() { return DefSp; }
    public int getSpeed() { return speed; }
}